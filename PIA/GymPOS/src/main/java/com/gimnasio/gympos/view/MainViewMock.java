package com.gimnasio.gympos.view;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.ClaseGrupal;
import com.gimnasio.gympos.model.Reserva;
import com.gimnasio.gympos.service.ClaseService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainViewMock {

    public static Parent crearVista(ClientesController controller) {
        TableView<Cliente> tablaClientes = new TableView<>();
        TableColumn<Cliente, String> colId = new TableColumn<>("ID Socio");
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colTelefono = new TableColumn<>("Teléfono");
        tablaClientes.getColumns().addAll(colId, colNombre, colTelefono);
        tablaClientes.setPrefWidth(250);

        TextField txtId = new TextField(); txtId.setPromptText("ID Cliente");
        TextField txtNombre = new TextField(); txtNombre.setPromptText("Nombre");
        TextField txtTelefono = new TextField(); txtTelefono.setPromptText("Teléfono");
        TextField txtBuscarId = new TextField(); txtBuscarId.setPromptText("Buscar por ID");

        Button btnRegistrar = new Button("Registrar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnBuscar = new Button("Buscar");
        Button btnLimpiar = new Button("Limpiar");
        Button btnAcumularPuntos = new Button("Puntos VIP (+10)");

        TableView<ClaseGrupal> tablaClases = new TableView<>();
        TableColumn<ClaseGrupal, String> colClaseId = new TableColumn<>("ID");
        TableColumn<ClaseGrupal, String> colClaseNom = new TableColumn<>("Clase");
        TableColumn<ClaseGrupal, String> colHorario = new TableColumn<>("Horario");
        TableColumn<ClaseGrupal, Integer> colCupo = new TableColumn<>("Disponibles");
        
        colClaseId.setCellValueFactory(new PropertyValueFactory<>("idClase"));
        colClaseNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colCupo.setCellValueFactory(new PropertyValueFactory<>("lugaresDisponibles"));
        tablaClases.getColumns().addAll(colClaseId, colClaseNom, colHorario, colCupo);
        tablaClases.setPrefWidth(320);

        TextField txtClaseId = new TextField(); txtClaseId.setPromptText("ID Clase (Ej: C04)");
        TextField txtClaseNombre = new TextField(); txtClaseNombre.setPromptText("Nombre de la Clase");
        TextField txtClaseInstructor = new TextField(); txtClaseInstructor.setPromptText("Instructor");
        TextField txtClaseHorario = new TextField(); txtClaseHorario.setPromptText("Horario");
        TextField txtClaseCupo = new TextField(); txtClaseCupo.setPromptText("Cupo Máximo");

        Button btnAddClase = new Button("Añadir Clase");
        Button btnEditClase = new Button("Modificar Clase");
        Button btnDelClase = new Button("Eliminar Clase");

        Button btnReservarClase = new Button("Reservar Cupo en Clase");
        btnReservarClase.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        ListView<String> listaInscritos = new ListView<>();
        listaInscritos.setPrefHeight(150);
        Button btnBajaCliente = new Button("Dar de Baja Alumno");
        btnBajaCliente.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        try {
            java.lang.reflect.Field[] fields = ClientesController.class.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("tablaClientes")) field.set(controller, tablaClientes);
                if (field.getName().equals("colId")) field.set(controller, colId);
                if (field.getName().equals("colNombre")) field.set(controller, colNombre);
                if (field.getName().equals("colTelefono")) field.set(controller, colTelefono);
                if (field.getName().equals("txtId")) field.set(controller, txtId);
                if (field.getName().equals("txtNombre")) field.set(controller, txtNombre);
                if (field.getName().equals("txtTelefono")) field.set(controller, txtTelefono);
                if (field.getName().equals("txtBuscarId")) field.set(controller, txtBuscarId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClaseService claseServiceInstancia = null;
        try {
            java.lang.reflect.Field csField = ClientesController.class.getDeclaredField("claseService");
            csField.setAccessible(true);
            claseServiceInstancia = (ClaseService) csField.get(controller);
            tablaClases.setItems(FXCollections.observableArrayList(claseServiceInstancia.obtenerTodasLasClases()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ClaseService finalClaseService = claseServiceInstancia;

        tablaClases.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtClaseId.setText(newSelection.getIdClase());
                txtClaseNombre.setText(newSelection.getNombre());
                txtClaseInstructor.setText(newSelection.getEntrenador());
                txtClaseHorario.setText(newSelection.getHorario());
                txtClaseCupo.setText(String.valueOf(newSelection.getCupoMaximo()));
                listaInscritos.setItems(FXCollections.observableArrayList(newSelection.getIdsClientesInscritos()));
            }
        });

        btnRegistrar.setOnAction(e -> execMeth(controller, "manejarRegistrar"));
        btnActualizar.setOnAction(e -> execMeth(controller, "manejarActualizar"));
        
        btnEliminar.setOnAction(e -> {
            String idEliminado = txtId.getText();
            
            execMeth(controller, "manejarEliminar");
            
            if (finalClaseService != null && idEliminado != null && !idEliminado.trim().isEmpty()) {
                finalClaseService.limpiarInscripcionesPorClienteEliminado(idEliminado);
                
                tablaClases.setItems(javafx.collections.FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
                
                ClaseGrupal claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
                if (claseSeleccionada != null) {
                    listaInscritos.setItems(javafx.collections.FXCollections.observableArrayList(claseSeleccionada.getIdsClientesInscritos()));
                } else {
                    listaInscritos.getItems().clear();
                }
            }
        });
        
        btnBuscar.setOnAction(e -> execMeth(controller, "manejarBuscar"));
        btnLimpiar.setOnAction(e -> execMeth(controller, "manejarLimpiar"));
        btnAcumularPuntos.setOnAction(e -> execMeth(controller, "manejarAcumularPuntos"));

        btnAddClase.setOnAction(e -> {
            try {
                ClaseGrupal nc = new ClaseGrupal(txtClaseId.getText(), txtClaseNombre.getText(), txtClaseInstructor.getText(), txtClaseHorario.getText(), Integer.parseInt(txtClaseCupo.getText()));
                finalClaseService.crearClaseGrupal(nc);
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Datos inválidos: " + ex.getMessage()).showAndWait();
            }
        });

        btnEditClase.setOnAction(e -> {
            try {
                ClaseGrupal ce = new ClaseGrupal(txtClaseId.getText(), txtClaseNombre.getText(), txtClaseInstructor.getText(), txtClaseHorario.getText(), Integer.parseInt(txtClaseCupo.getText()));
                ClaseGrupal original = tablaClases.getSelectionModel().getSelectedItem();
                if (original != null) {
                    for (String id : original.getIdsClientesInscritos()) {
                        ce.inscribirCliente(id);
                    }
                }
                finalClaseService.actualizarClaseGrupal(ce);
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        btnDelClase.setOnAction(e -> {
            finalClaseService.eliminarClaseGrupal(txtClaseId.getText());
            tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
            listaInscritos.getItems().clear();
        });

        btnReservarClase.setOnAction(e -> {
            Cliente socioSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            ClaseGrupal claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
            if (socioSeleccionado == null || claseSeleccionada == null) {
                new Alert(Alert.AlertType.WARNING, "Selecciona un Socio Y una Clase Grupal.").showAndWait();
                return;
            }
            try {
                finalClaseService.agendarReserva(socioSeleccionado, claseSeleccionada.getIdClase());
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
                listaInscritos.setItems(FXCollections.observableArrayList(claseSeleccionada.getIdsClientesInscritos()));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        btnBajaCliente.setOnAction(e -> {
            ClaseGrupal claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
            String idClienteSeleccionado = listaInscritos.getSelectionModel().getSelectedItem();
            if (claseSeleccionada == null || idClienteSeleccionado == null) {
                new Alert(Alert.AlertType.WARNING, "Selecciona una clase y un alumno inscrito.").showAndWait();
                return;
            }
            
            for (Reserva r : finalClaseService.obtenerTodasLasReservas()) {
                if (r.getIdClase().equals(claseSeleccionada.getIdClase()) && r.getIdCliente().equals(idClienteSeleccionado)) {
                    finalClaseService.cancelarReserva(r.getIdReserva());
                    break;
                }
            }
            
            tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
            tablaClases.refresh();
            listaInscritos.setItems(FXCollections.observableArrayList(claseSeleccionada.getIdsClientesInscritos()));
        });

        controller.initialize(null, null);

        VBox formClientes = new VBox(5, new Label("Socio: ID, Nombre, Tel"), txtId, txtNombre, txtTelefono, new HBox(3, btnRegistrar, btnActualizar, btnEliminar, btnLimpiar), txtBuscarId, new HBox(3, btnBuscar, btnAcumularPuntos));
        VBox panelClientes = new VBox(10, new Label("Socios"), tablaClientes, formClientes);
        panelClientes.setPadding(new Insets(5));

        VBox formClases = new VBox(5, new Label("Clase: ID, Nombre, Instructor, Horario, Cupo"), txtClaseId, txtClaseNombre, txtClaseInstructor, txtClaseHorario, txtClaseCupo, new HBox(5, btnAddClase, btnEditClase, btnDelClase));
        VBox panelClases = new VBox(10, new Label("Clases Disponibles"), tablaClases, formClases);
        panelClases.setPadding(new Insets(5));

        VBox panelInscritos = new VBox(10, new Label("Alumnos en la Clase Seleccionada"), listaInscritos, btnBajaCliente, btnReservarClase);
        panelInscritos.setPadding(new Insets(5));
        panelInscritos.setPrefWidth(240);

        HBox raiz = new HBox(15, panelClientes, panelClases, panelInscritos);
        raiz.setPadding(new Insets(10));
        return raiz;
    }

    private static void execMeth(Object target, String methodName) {
        try {
            java.lang.reflect.Method m = target.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(target);
        } catch(Exception ex) {}
    }
}