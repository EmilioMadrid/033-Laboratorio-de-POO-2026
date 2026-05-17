package com.gimnasio.gympos.service;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.exception.ClienteDuplicadoException;
import com.gimnasio.gympos.exception.ClienteNoEncontradoException;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

public class ClienteService {

    private final Map<String, Cliente> clientes = new HashMap<>();

    public void registrarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getIdCliente())) {
            throw new ClienteDuplicadoException("El ID " + cliente.getIdCliente() + " ya está registrado en el sistema.");
        }
        clientes.put(cliente.getIdCliente(), cliente);
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
    }

    public void eliminarCliente(String idCliente) {
        if (!clientes.containsKey(idCliente)) {
            throw new ClienteNoEncontradoException("No se puede eliminar. El cliente con ID " + idCliente + " no existe.");
        }
        clientes.remove(idCliente);
    }

    public Collection<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes.values());
    }
}