package com.gimnasio.gympos.service;

import com.gimnasio.gympos.exception.AccesoDenegadoException;
import com.gimnasio.gympos.exception.ClaseLlenaException;
import com.gimnasio.gympos.exception.ClienteYaInscritoException;
import com.gimnasio.gympos.model.ClaseGrupal;
import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.Reserva;
import com.gimnasio.gympos.util.PersistenciaUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClaseService {

    private final String archivoDatos = "gimnasio_reservas.dat";
    private Map<String, ClaseGrupal> clases;
    private Map<String, Reserva> reservas;
    private final ScheduledExecutorService hilosPersistencia;

    public ClaseService() {
        this.clases = new ConcurrentHashMap<>();
        this.reservas = new ConcurrentHashMap<>();
        this.hilosPersistencia = Executors.newSingleThreadScheduledExecutor();
        
        cargarDatosPermanentes();
        
        this.hilosPersistencia.scheduleWithFixedDelay(this::guardarDatosEnDisco, 5, 10, TimeUnit.SECONDS);
    }

    private void cargarDatosPermanentes() {
        File file = new File(archivoDatos);
        if (!file.exists()) {
            cargarDatosInicialesDemo();
            guardarDatosEnDisco();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            this.clases = (ConcurrentHashMap<String, ClaseGrupal>) ois.readObject();
            this.reservas = (ConcurrentHashMap<String, Reserva>) ois.readObject();
        } catch (Exception e) {
            cargarDatosInicialesDemo();
        }
    }

    private synchronized void guardarDatosEnDisco() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDatos))) {
            oos.writeObject(clases);
            oos.writeObject(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosInicialesDemo() {
        clases.put("C01", new ClaseGrupal("C01", "Spinning", "Entrenador Alan", "08:00 AM", 2));
        clases.put("C02", new ClaseGrupal("C02", "Yoga", "Entrenadora Sofia", "10:00 AM", 15));
        clases.put("C03", new ClaseGrupal("C03", "Crossfit", "Entrenador Carlos", "07:00 PM", 1));
    }

    public List<ClaseGrupal> obtenerTodasLasClases() {
        return new ArrayList<>(clases.values());
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return new ArrayList<>(reservas.values());
    }

    public void agendarReserva(Cliente cliente, String idClase) throws ClaseLlenaException, AccesoDenegadoException, ClienteYaInscritoException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (!cliente.getMembresia().tieneAccesoAClasesGrupales()) {
            throw new AccesoDenegadoException("El cliente " + cliente.getNombre() + " no tiene acceso a clases grupales con su membresía actual.");
        }

        ClaseGrupal clase = clases.get(idClase);
        if (clase == null) {
            throw new IllegalArgumentException("La clase grupal seleccionada no existe.");
        }

        if (clase.getIdsClientesInscritos().contains(cliente.getIdCliente())) {
            throw new ClienteYaInscritoException("Operación inválida: El socio " + cliente.getNombre() + " ya se encuentra inscrito en esta clase.");
        }

        synchronized (clase) {
            if (clase.estaLlena()) {
                throw new ClaseLlenaException("No se pudo agendar: La clase '" + clase.getNombre() + "' ha alcanzado su cupo máximo.");
            }

            clase.inscribirCliente(cliente.getIdCliente());

            String idReserva = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Reserva nuevaReserva = new Reserva(idReserva, cliente.getIdCliente(), idClase);
            reservas.put(idReserva, nuevaReserva);
            
            hilosPersistencia.execute(this::guardarDatosEnDisco);
        }
    }

    public void cancelarReserva(String idReserva) {
        Reserva reserva = reservas.remove(idReserva);
        if (reserva != null) {
            ClaseGrupal clase = clases.get(reserva.getIdClase());
            if (clase != null) {
                synchronized (clase) {
                    clase.deDarBajaCliente(reserva.getIdCliente());
                    hilosPersistencia.execute(this::guardarDatosEnDisco);
                }
            }
        }
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
    
    public void crearClaseGrupal(ClaseGrupal nuevaClase) {
        if (nuevaClase == null || nuevaClase.getIdClase() == null || nuevaClase.getIdClase().trim().isEmpty()) {
            throw new IllegalArgumentException("La clase o su ID no pueden estar vacíos.");
        }

        if (nuevaClase.getCupoMaximo() <= 0) {
            throw new IllegalArgumentException("Operación inválida: El cupo máximo debe ser mayor a cero.");
        }

        clases.put(nuevaClase.getIdClase(), nuevaClase);
        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }

    public void actualizarClaseGrupal(ClaseGrupal claseEditada) {
        if (claseEditada == null || !clases.containsKey(claseEditada.getIdClase())) {
            throw new IllegalArgumentException("La clase especificada no existe.");
        }

        if (claseEditada.getCupoMaximo() <= 0) {
            throw new IllegalArgumentException("Operación inválida: El cupo máximo debe ser mayor a cero.");
        }

        ClaseGrupal claseOriginal = clases.get(claseEditada.getIdClase());

        if (claseEditada.getCupoMaximo() < claseOriginal.getIdsClientesInscritos().size()) {
            throw new IllegalArgumentException("No se puede reducir el cupo a " + claseEditada.getCupoMaximo() 
                + " porque ya hay " + claseOriginal.getIdsClientesInscritos().size() + " alumnos inscritos.");
        }

        clases.put(claseEditada.getIdClase(), claseEditada);
        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }

    public void eliminarClaseGrupal(String idClase) {
        clases.remove(idClase);
        reservas.values().removeIf(reserva -> reserva.getIdClase().equals(idClase));
        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }
    
    public void limpiarInscripcionesPorClienteEliminado(String idCliente) {
        if (idCliente == null || idCliente.trim().isEmpty()) return;

        for (ClaseGrupal clase : clases.values()) {
            synchronized (clase) {
                if (clase.getIdsClientesInscritos().contains(idCliente)) {
                    clase.deDarBajaCliente(idCliente);
                }
            }
        }

        reservas.values().removeIf(reserva -> reserva.getIdCliente().equals(idCliente));
        hilosPersistencia.execute(this::guardarDatosEnDisco);
    }
}