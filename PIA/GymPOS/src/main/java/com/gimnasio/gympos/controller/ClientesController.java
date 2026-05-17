package com.gimnasio.gympos.controller;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.service.ClienteService;
import java.util.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientesController {

    @FXML
    private TableView<Cliente> tablaClientes;

    private ClienteService clienteService;

    public void inicializarDatos(ClienteService service) {
        this.clienteService = service;
        cargarTabla();
    }

    private void cargarTabla() {
        Collection<Cliente> clientesBackend = clienteService.obtenerTodos();
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList(clientesBackend);
        tablaClientes.setItems(clientesObservable);
    }
}