package com.gimnasio.gympos.service;

import com.gimnasio.gympos.exception.StockInsuficienteException;
import com.gimnasio.gympos.model.DetalleVenta;
import com.gimnasio.gympos.model.Producto;
import com.gimnasio.gympos.model.Venta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InventarioService {

    private final String archivoProductos = "gimnasio_inventario.dat";
    private final String archivoVentas = "gimnasio_ventas.dat";
    
    private Map<String, Producto> inventario;
    private Map<String, Venta> historialVentas;
    private final ScheduledExecutorService hilosPersistencia;

    public InventarioService() {
        this.inventario = new ConcurrentHashMap<>();
        this.historialVentas = new ConcurrentHashMap<>();
        this.hilosPersistencia = Executors.newSingleThreadScheduledExecutor();

        cargarDatosPermanentes();

        this.hilosPersistencia.scheduleWithFixedDelay(this::guardarDatosEnDisco, 5, 10, TimeUnit.SECONDS);
    }

    private void cargarDatosPermanentes() {
        File fileProd = new File(archivoProductos);
        File fileVent = new File(archivoVentas);

        if (!fileProd.exists() || !fileVent.exists()) {
            cargarInventarioDemo();
            guardarDatosEnDisco();
            return;
        }

        try (ObjectInputStream oisProd = new ObjectInputStream(new FileInputStream(fileProd));
             ObjectInputStream oisVent = new ObjectInputStream(new FileInputStream(fileVent))) {
            
            this.inventario = (ConcurrentHashMap<String, Producto>) oisProd.readObject();
            this.historialVentas = (ConcurrentHashMap<String, Venta>) oisVent.readObject();
            
        } catch (Exception e) {
            cargarInventarioDemo();
        }
    }

    private synchronized void guardarDatosEnDisco() {
        try (ObjectOutputStream oosProd = new ObjectOutputStream(new FileOutputStream(archivoProductos));
             ObjectOutputStream oosVent = new ObjectOutputStream(new FileOutputStream(archivoVentas))) {
            
            oosProd.writeObject(inventario);
            oosVent.writeObject(historialVentas);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarInventarioDemo() {
        inventario.put("P001", new Producto("P001", "Agua purificada 600ml", 18.00, 30, 5));
        inventario.put("P002", new Producto("P002", "Bebida energética 500ml", 28.00, 20, 4));
        inventario.put("P003", new Producto("P003", "Proteína shake", 45.00, 15, 3));
        inventario.put("P004", new Producto("P004", "Barra energética de avena", 22.00, 25, 6));
    }

    public List<Producto> obtenerTodoElInventario() {
        return new ArrayList<>(inventario.values());
    }

    public List<Venta> obtenerHistorialVentas() {
        return new ArrayList<>(historialVentas.values());
    }

    public void agregarOAbastecerProducto(Producto producto) {
        if (producto == null || producto.getIdProducto() == null || producto.getIdProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El producto o su ID no pueden estar vacíos.");
        }
        if (producto.getPrecio() < 0 || producto.getStock() < 0 || producto.getStockMinimo() < 0) {
            throw new IllegalArgumentException("Los valores numéricos de precio o stock no pueden ser negativos.");
        }

        inventario.put(producto.getIdProducto(), producto);
        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }

    public void procesarTransaccionVenta(String idCliente, List<DetalleVenta> carrito) throws StockInsuficienteException {
        if (carrito == null || carrito.isEmpty()) {
            throw new IllegalArgumentException("No se puede procesar una venta con el carrito vacío.");
        }

        for (DetalleVenta renglon : carrito) {
            Producto prod = inventario.get(renglon.getIdProducto());
            if (prod == null) {
                throw new IllegalArgumentException("El producto con ID " + renglon.getIdProducto() + " no existe en el catálogo.");
            }
            if (prod.getStock() < renglon.getCantidad()) {
                throw new StockInsuficienteException("Venta rechazada: Stock insuficiente para '" + prod.getNombre() 
                    + "'. Disponibles: " + prod.getStock() + ", Solicitados: " + renglon.getCantidad());
            }
        }

        for (DetalleVenta renglon : carrito) {
            Producto prod = inventario.get(renglon.getIdProducto());
            synchronized (prod) {
                prod.setStock(prod.getStock() - renglon.getCantidad());
            }
        }

        String idVenta = "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Venta nuevaVenta = new Venta(idVenta, idCliente, carrito);
        historialVentas.put(idVenta, nuevaVenta);

        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }

    public void apagarServicio() {
        try {
            guardarDatosEnDisco();
            hilosPersistencia.shutdown();
            if (!hilosPersistencia.awaitTermination(3, TimeUnit.SECONDS)) {
                hilosPersistencia.shutdownNow();
            }
        } catch (InterruptedException e) {
            hilosPersistencia.shutdownNow();
        }
    }
}