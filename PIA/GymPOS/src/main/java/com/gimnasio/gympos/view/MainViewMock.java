package com.gimnasio.gympos.view;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.ClaseGrupal;
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
        tablaClientes.setPrefWidth(300);

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
        tablaClases.setPrefWidth(350);

        Button btnReservarClase = new Button("Reservar Cupo en Clase");
        btnReservarClase.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

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

        btnRegistrar.setOnAction(e -> { execMeth(controller, "manejarRegistrar"); });
        btnActualizar.setOnAction(e -> { execMeth(controller, "manejarActualizar"); });
        btnEliminar.setOnAction(e -> { execMeth(controller, "manejarEliminar"); });
        btnBuscar.setOnAction(e -> { execMeth(controller, "manejarBuscar"); });
        btnLimpiar.setOnAction(e -> { execMeth(controller, "manejarLimpiar"); });
        btnAcumularPuntos.setOnAction(e -> { execMeth(controller, "manejarAcumularPuntos"); });

        final ClaseService finalClaseService = claseServiceInstancia;
        btnReservarClase.setOnAction(e -> {
            Cliente socioSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            ClaseGrupal claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();

            if (socioSeleccionado == null || claseSeleccionada == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un Socio de la lista Y una Clase Grupal.");
                alert.showAndWait();
                return;
            }

            try {
                finalClaseService.agendarReserva(socioSeleccionado, claseSeleccionada.getIdClase());
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "¡Reserva Exitosa! Cupo asegurado para " + socioSeleccionado.getNombre());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        });

        controller.initialize(null, null);

        VBox panelFormulario = new VBox(10, new Label("Datos del Cliente"), txtId, txtNombre, txtTelefono, new HBox(5, btnRegistrar, btnActualizar, btnEliminar, btnLimpiar));
        VBox panelBusqueda = new VBox(10, new Label("Búsqueda y Puntos"), txtBuscarId, new HBox(5, btnBuscar, btnAcumularPuntos));
        VBox panelAccionesClientes = new VBox(20, panelFormulario, panelBusqueda);
        panelAccionesClientes.setPadding(new Insets(10));

        VBox panelIzquierdo = new VBox(10, new Label("Socios del Gimnasio"), tablaClientes);
        VBox panelCentralClases = new VBox(10, new Label("Clases Grupales Disponibles"), tablaClases, btnReservarClase);
        panelCentralClases.setPadding(new Insets(0, 0, 0, 15));

        HBox raiz = new HBox(15, panelIzquierdo, panelCentralClases, panelAccionesClientes);
        raiz.setPadding(new Insets(15));
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