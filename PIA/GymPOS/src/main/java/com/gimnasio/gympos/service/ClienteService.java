package com.gimnasio.gympos.service;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.exception.ClienteDuplicadoException;
import com.gimnasio.gympos.exception.ClienteNoEncontradoException;
import com.gimnasio.gympos.util.PersistenciaUtil;

import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteService {

    private final Map<String, Cliente> clientes = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final String rutaArchivo;

    public ClienteService(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.clientes.putAll(PersistenciaUtil.cargarClientes(rutaArchivo));
    }

    private void guardarAsincrono() {
        Map<String, Cliente> copiaSnapshot = new ConcurrentHashMap<>(this.clientes);
        executor.submit(() -> PersistenciaUtil.guardarClientes(copiaSnapshot, rutaArchivo));
    }

    public void registrarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getIdCliente())) {
            throw new ClienteDuplicadoException("El ID " + cliente.getIdCliente() + " ya está registrado en el sistema.");
        }
        clientes.put(cliente.getIdCliente(), cliente);
        guardarAsincrono();
    }

    public Cliente buscarCliente(String idCliente) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("No se encontró ningún cliente con el ID: " + idCliente);
        }
        return cliente;
    }

    public void actualizarCliente(Cliente cliente) {
        if (!clientes.containsKey(cliente.getIdCliente())) {
            throw new ClienteNoEncontradoException("No se puede actualizar. El cliente con ID " + cliente.getIdCliente() + " no existe.");
        }
        clientes.put(cliente.getIdCliente(), cliente);
        guardarAsincrono();
    }

    public void eliminarCliente(String idCliente) {
        if (!clientes.containsKey(idCliente)) {
            throw new ClienteNoEncontradoException("No se puede eliminar. El cliente con ID " + idCliente + " no existe.");
        }
        clientes.remove(idCliente);
        guardarAsincrono();
    }

    public Collection<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes.values());
    }

    public void apagarServicio() {
        executor.shutdown();
    }
}