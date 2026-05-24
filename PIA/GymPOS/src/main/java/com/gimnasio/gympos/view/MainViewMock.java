package com.gimnasio.gympos.view;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.model.Cliente;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainViewMock {

    public static Parent crearVista(ClientesController controller) {
        TableView<Cliente> tabla = new TableView<>();
        TableColumn<Cliente, String> colId = new TableColumn<>("ID");
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colTelefono = new TableColumn<>("Teléfono");
        tabla.getColumns().addAll(colId, colNombre, colTelefono);

        TextField txtId = new TextField();
        txtId.setPromptText("ID Cliente");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");
        TextField txtBuscarId = new TextField();
        txtBuscarId.setPromptText("Buscar por ID");

        Button btnRegistrar = new Button("Registrar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnBuscar = new Button("Buscar");
        Button btnLimpiar = new Button("Limpiar");
        Button btnAcumularPuntos = new Button("Puntos VIP (10)");

        try {
            java.lang.reflect.Field[] fields = ClientesController.class.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("tablaClientes")) field.set(controller, tabla);
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

        btnRegistrar.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarRegistrar"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });
        btnActualizar.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarActualizar"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });
        btnEliminar.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarEliminar"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });
        btnBuscar.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarBuscar"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });
        btnLimpiar.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarLimpiar"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });
        btnAcumularPuntos.setOnAction(e -> { try { java.lang.reflect.Method m = ClientesController.class.getDeclaredMethod("manejarAcumularPuntos"); m.setAccessible(true); m.invoke(controller); } catch(Exception ex) {} });

        controller.initialize(null, null);

        VBox panelFormulario = new VBox(10, new Label("Datos del Cliente"), txtId, txtNombre, txtTelefono, new HBox(5, btnRegistrar, btnActualizar, btnEliminar, btnLimpiar));
        VBox panelBusqueda = new VBox(10, new Label("Búsqueda y Acciones"), txtBuscarId, new HBox(5, btnBuscar, btnAcumularPuntos));
        VBox panelDerecho = new VBox(20, panelFormulario, panelBusqueda);
        panelDerecho.setPadding(new Insets(10));

        HBox raiz = new HBox(15, tabla, panelDerecho);
        raiz.setPadding(new Insets(15));
        return raiz;
    }
}