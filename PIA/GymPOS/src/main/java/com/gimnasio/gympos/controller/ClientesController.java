package com.gimnasio.gympos.controller;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.MembresiaVIP;
import com.gimnasio.gympos.service.ClienteService;
import com.gimnasio.gympos.exception.ClienteDuplicadoException;
import com.gimnasio.gympos.exception.ClienteNoEncontradoException;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtBuscarId;

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnAcumularPuntos;

    private final ClienteService clienteService;
    private final ObservableList<Cliente> datosTabla = FXCollections.observableArrayList();

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        
        tablaClientes.setItems(datosTabla);
        
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getIdCliente());
                txtNombre.setText(newSelection.getNombre());
                txtTelefono.setText(newSelection.getTelefono());
                txtId.setEditable(false);
            }
        });
        
        refrescarTabla();
    }

    private void refrescarTabla() {
        Collection<Cliente> modeloActualizado = clienteService.obtenerTodos();
        datosTabla.setAll(modeloActualizado);
    }

    @FXML
    private void manejarRegistrar() {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (id.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos requeridos", "Por favor llene los campos de ID y Nombre.");
                return;
            }

            Cliente nuevo = new Cliente(id, nombre, telefono, null);
            clienteService.registrarCliente(nuevo);
            refrescarTabla();
            manejarLimpiar();
            
        } catch (ClienteDuplicadoException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de registro", e.getMessage());
        }
    }

    @FXML
    private void manejarActualizar() {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();

            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un cliente de la tabla.");
                return;
            }

            Cliente modificado = new Cliente(id, nombre, telefono, seleccionado.getMembresia());
            clienteService.actualizarCliente(modificado);
            refrescarTabla();
            manejarLimpiar();
            
        } catch (ClienteNoEncontradoException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de actualización", e.getMessage());
        }
    }

    @FXML
    private void manejarEliminar() {
        try {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione el cliente que desea eliminar.");
                return;
            }

            clienteService.eliminarCliente(seleccionado.getIdCliente());
            refrescarTabla();
            manejarLimpiar();
            
        } catch (ClienteNoEncontradoException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al eliminar", e.getMessage());
        }
    }

    @FXML
    private void manejarBuscar() {
        try {
            String idBusqueda = txtBuscarId.getText().trim();
            if (idBusqueda.isEmpty()) {
                refrescarTabla();
                return;
            }

            Cliente encontrado = clienteService.buscarCliente(idBusqueda);
            datosTabla.setAll(encontrado);
            
        } catch (ClienteNoEncontradoException e) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Búsqueda", e.getMessage());
        }
    }

    @FXML
    private void manejarLimpiar() {
        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtBuscarId.clear();
        txtId.setEditable(true);
        tablaClientes.getSelectionModel().clearSelection();
        refrescarTabla();
    }

    @FXML
    private void manejarAcumularPuntos() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Seleccione un cliente de la tabla.");
            return;
        }

        if (seleccionado.getMembresia() instanceof MembresiaVIP) {
            MembresiaVIP vip = (MembresiaVIP) seleccionado.getMembresia();
            vip.acumularPuntos(10);
            clienteService.actualizarCliente(seleccionado);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Puntos VIP", "Se han acumulado 10 puntos al cliente. Total: " + vip.getPuntosAcumulados());
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Operación no permitida", "El cliente seleccionado no cuenta con una Membresía VIP.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}