package com.gimnasio.gympos.view;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.ClaseGrupal;
import com.gimnasio.gympos.model.Reserva;
import com.gimnasio.gympos.model.Producto;
import com.gimnasio.gympos.model.DetalleVenta;
import com.gimnasio.gympos.service.ClaseService;
import com.gimnasio.gympos.service.InventarioService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainViewMock {

    private static List<DetalleVenta> carritoFlujo = new ArrayList<>();

    public static Parent crearVista(ClientesController controller) {
        // --- SECCIÓN 1: CLIENTES ---
        TableView<Cliente> tablaClientes = new TableView<>();
        TableColumn<Cliente, String> colId = new TableColumn<>("ID Socio");
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colTelefono = new TableColumn<>("Teléfono");
        tablaClientes.getColumns().addAll(colId, colNombre, colTelefono);
        tablaClientes.setPrefWidth(210);

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

        // --- SECCIÓN 2: CLASES GRUPALES ---
        TableView<ClaseGrupal> tablaClases = new TableView<>();
        TableColumn<ClaseGrupal, String> colClaseId = new TableColumn<>("ID");
        TableColumn<ClaseGrupal, String> colClaseNom = new TableColumn<>("Clase");
        TableColumn<ClaseGrupal, String> colHorario = new TableColumn<>("Horario");
        TableColumn<ClaseGrupal, Integer> colCupo = new TableColumn<>("Disp.");
        
        colClaseId.setCellValueFactory(new PropertyValueFactory<>("idClase"));
        colClaseNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colCupo.setCellValueFactory(new PropertyValueFactory<>("lugaresDisponibles"));
        tablaClases.getColumns().addAll(colClaseId, colClaseNom, colHorario, colCupo);
        tablaClases.setPrefWidth(230);

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
        listaInscritos.setPrefHeight(100);
        Button btnBajaCliente = new Button("Dar de Baja Alumno");
        btnBajaCliente.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        // --- SECCIÓN 3: INVENTARIO Y PUNTO DE VENTA (NUEVO) ---
        TableView<Producto> tablaInventario = new TableView<>();
        TableColumn<Producto, String> colProdId = new TableColumn<>("ID");
        TableColumn<Producto, String> colProdNom = new TableColumn<>("Producto");
        TableColumn<Producto, Double> colProdPre = new TableColumn<>("Precio");
        TableColumn<Producto, Integer> colProdStk = new TableColumn<>("Stock");
        
        colProdId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colProdNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProdPre.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colProdStk.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablaInventario.getColumns().addAll(colProdId, colProdNom, colProdPre, colProdStk);
        tablaInventario.setPrefWidth(260);

        TextField txtProdId = new TextField(); txtProdId.setPromptText("ID Prod (Ej: P05)");
        TextField txtProdNombre = new TextField(); txtProdNombre.setPromptText("Nombre Producto");
        TextField txtProdPrecio = new TextField(); txtProdPrecio.setPromptText("Precio");
        TextField txtProdStock = new TextField(); txtProdStock.setPromptText("Stock Inicial");
        TextField txtProdMin = new TextField(); txtProdMin.setPromptText("Stock Mínimo");
        
        Button btnAbastecerProd = new Button("Abastecer / Añadir");

        ListView<String> listaCarritoVisual = new ListView<>();
        listaCarritoVisual.setPrefHeight(100);
        
        Spinner<Integer> spinCantidad = new Spinner<>(1, 10, 1);
        Button btnAgregarCarrito = new Button("Agregar al Carrito");
        Button btnVaciarCarrito = new Button("Vaciar");
        Button btnCobrarVenta = new Button("Cobrar Carrito");
        btnCobrarVenta.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        // Vincular componentes con el ClientesController por reflexión
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
        InventarioService inventarioServiceInstancia = null;
        try {
            java.lang.reflect.Field csField = ClientesController.class.getDeclaredField("claseService");
            csField.setAccessible(true);
            claseServiceInstancia = (ClaseService) csField.get(controller);
            tablaClases.setItems(FXCollections.observableArrayList(claseServiceInstancia.obtenerTodasLasClases()));

            java.lang.reflect.Field isField = ClientesController.class.getDeclaredField("inventarioService");
            isField.setAccessible(true);
            inventarioServiceInstancia = (InventarioService) isField.get(controller);
            tablaInventario.setItems(FXCollections.observableArrayList(inventarioServiceInstancia.obtenerTodoElInventario()));
        } catch (Exception e) {
            // Si inventarioService aún no está declarado en ClientesController, lo instanciamos directo para el mock
            if (inventarioServiceInstancia == null) {
                inventarioServiceInstancia = new InventarioService();
                tablaInventario.setItems(FXCollections.observableArrayList(inventarioServiceInstancia.obtenerTodoElInventario()));
            }
        }

        final ClaseService finalClaseService = claseServiceInstancia;
        final InventarioService finalInventarioService = inventarioServiceInstancia;

        // Listeners visuales de selección
        tablaClases.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtClaseId.setText(newSel.getIdClase());
                txtClaseNombre.setText(newSel.getNombre());
                txtClaseInstructor.setText(newSel.getEntrenador());
                txtClaseHorario.setText(newSel.getHorario());
                txtClaseCupo.setText(String.valueOf(newSel.getCupoMaximo()));
                listaInscritos.setItems(FXCollections.observableArrayList(newSel.getIdsClientesInscritos()));
            }
        });

        tablaInventario.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtProdId.setText(newSel.getIdProducto());
                txtProdNombre.setText(newSel.getNombre());
                txtProdPrecio.setText(String.valueOf(newSel.getPrecio()));
                txtProdStock.setText(String.valueOf(newSel.getStock()));
                txtProdMin.setText(String.valueOf(newSel.getStockMinimo()));
            }
        });

        // Acciones Clientes
        btnRegistrar.setOnAction(e -> execMeth(controller, "manejarRegistrar"));
        btnActualizar.setOnAction(e -> execMeth(controller, "manejarActualizar"));
        btnEliminar.setOnAction(e -> {
            String idEliminado = txtId.getText();
            execMeth(controller, "manejarEliminar");
            if (finalClaseService != null && idEliminado != null && !idEliminado.trim().isEmpty()) {
                finalClaseService.limpiarInscripcionesPorClienteEliminado(idEliminado);
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
                ClaseGrupal cs = tablaClases.getSelectionModel().getSelectedItem();
                if (cs != null) listaInscritos.setItems(FXCollections.observableArrayList(cs.getIdsClientesInscritos()));
                else listaInscritos.getItems().clear();
            }
        });
        btnBuscar.setOnAction(e -> execMeth(controller, "manejarBuscar"));
        btnLimpiar.setOnAction(e -> execMeth(controller, "manejarLimpiar"));
        btnAcumularPuntos.setOnAction(e -> execMeth(controller, "manejarAcumularPuntos"));

        // Acciones Clases
        btnAddClase.setOnAction(e -> {
            try {
                ClaseGrupal nc = new ClaseGrupal(txtClaseId.getText(), txtClaseNombre.getText(), txtClaseInstructor.getText(), txtClaseHorario.getText(), Integer.parseInt(txtClaseCupo.getText()));
                finalClaseService.crearClaseGrupal(nc);
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        btnEditClase.setOnAction(e -> {
            try {
                ClaseGrupal ce = new ClaseGrupal(txtClaseId.getText(), txtClaseNombre.getText(), txtClaseInstructor.getText(), txtClaseHorario.getText(), Integer.parseInt(txtClaseCupo.getText()));
                ClaseGrupal orig = finalClaseService.obtenerTodasLasClases().stream().filter(c -> c.getIdClase().equals(ce.getIdClase())).findFirst().orElse(null);
                if (orig != null) {
                    for (String id : orig.getIdsClientesInscritos()) ce.inscribirCliente(id);
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
            Cliente socio = tablaClientes.getSelectionModel().getSelectedItem();
            ClaseGrupal clase = tablaClases.getSelectionModel().getSelectedItem();
            if (socio == null || clase == null) {
                new Alert(Alert.AlertType.WARNING, "Selecciona un Socio Y una Clase.").showAndWait();
                return;
            }
            try {
                finalClaseService.agendarReserva(socio, clase.getIdClase());
                tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
                tablaClases.refresh();
                listaInscritos.setItems(FXCollections.observableArrayList(clase.getIdsClientesInscritos()));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        btnBajaCliente.setOnAction(e -> {
            ClaseGrupal clase = tablaClases.getSelectionModel().getSelectedItem();
            String idClie = listaInscritos.getSelectionModel().getSelectedItem();
            if (clase == null || idClie == null) return;
            for (Reserva r : finalClaseService.obtenerTodasLasReservas()) {
                if (r.getIdClase().equals(clase.getIdClase()) && r.getIdCliente().equals(idClie)) {
                    finalClaseService.cancelarReserva(r.getIdReserva());
                    break;
                }
            }
            tablaClases.setItems(FXCollections.observableArrayList(finalClaseService.obtenerTodasLasClases()));
            tablaClases.refresh();
            listaInscritos.setItems(FXCollections.observableArrayList(clase.getIdsClientesInscritos()));
        });

        // --- ACCIONES INVENTARIO / POS ---
        btnAbastecerProd.setOnAction(e -> {
            try {
                Producto np = new Producto(txtProdId.getText(), txtProdNombre.getText(), Double.parseDouble(txtProdPrecio.getText()), Integer.parseInt(txtProdStock.getText()), Integer.parseInt(txtProdMin.getText()));
                finalInventarioService.agregarOAbastecerProducto(np);
                tablaInventario.setItems(FXCollections.observableArrayList(finalInventarioService.obtenerTodoElInventario()));
                tablaInventario.refresh();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Campos inválidos: " + ex.getMessage()).showAndWait();
            }
        });

        btnAgregarCarrito.setOnAction(e -> {
            Producto prodSel = tablaInventario.getSelectionModel().getSelectedItem();
            if (prodSel == null) {
                new Alert(Alert.AlertType.WARNING, "Selecciona un producto del catálogo.").showAndWait();
                return;
            }
            int cant = spinCantidad.getValue();
            DetalleVenta item = new DetalleVenta(prodSel.getIdProducto(), prodSel.getNombre(), cant, prodSel.getPrecio());
            carritoFlujo.add(item);
            listaCarritoVisual.getItems().add(item.getNombreProducto() + " x" + item.getCantidad() + " ($" + item.getSubtotal() + ")");
        });

        btnVaciarCarrito.setOnAction(e -> {
            carritoFlujo.clear();
            listaCarritoVisual.getItems().clear();
        });

        btnCobrarVenta.setOnAction(e -> {
            Cliente socioSel = tablaClientes.getSelectionModel().getSelectedItem();
            String idSocio = (socioSel != null) ? socioSel.getIdCliente() : "MOSTRADOR-ANONIMO";
            
            if (carritoFlujo.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "El carrito de compras está vacío.").showAndWait();
                return;
            }

            try {
                finalInventarioService.procesarTransaccionVenta(idSocio, new ArrayList<>(carritoFlujo));
                
                // Si el producto está con stock bajo, lanzar una advertencia visual preventiva
                StringBuilder alertas = new StringBuilder();
                for (DetalleVenta dv : carritoFlujo) {
                    Producto p = finalInventarioService.obtenerTodoElInventario().stream().filter(prod -> prod.getIdProducto().equals(dv.getIdProducto())).findFirst().orElse(null);
                    if (p != null && p.esStockBajo()) {
                        alertas.append("- ").append(p.getNombre()).append(" (Quedan: ").append(p.getStock()).append(")\n");
                    }
                }

                new Alert(Alert.AlertType.INFORMATION, "¡Venta Procesada con éxito!\nTicket generado para: " + idSocio).showAndWait();
                
                if (alertas.length() > 0) {
                    new Alert(Alert.AlertType.WARNING, "ALERTAS DE STOCK MÍNIMO:\n" + alertas.toString()).showAndWait();
                }

                carritoFlujo.clear();
                listaCarritoVisual.getItems().clear();
                tablaInventario.setItems(FXCollections.observableArrayList(finalInventarioService.obtenerTodoElInventario()));
                tablaInventario.refresh();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        controller.initialize(null, null);

        // --- DISTRIBUCIÓN DE CONTENEDORES ---
        VBox formClientes = new VBox(3, new Label("Campos Socio"), txtId, txtNombre, txtTelefono, new HBox(2, btnRegistrar, btnActualizar, btnEliminar, btnLimpiar), txtBuscarId, new HBox(2, btnBuscar, btnAcumularPuntos));
        VBox panelClientes = new VBox(5, new Label("Socios del Gym"), tablaClientes, formClientes);
        panelClientes.setPadding(new Insets(3));

        VBox formClases = new VBox(3, new Label("Campos Clase"), txtClaseId, txtClaseNombre, txtClaseInstructor, txtClaseHorario, txtClaseCupo, new HBox(2, btnAddClase, btnEditClase, btnDelClase));
        VBox panelClases = new VBox(5, new Label("Clases Disponibles"), tablaClases, formClases, new Label("Inscritos"), listaInscritos, new HBox(2, btnBajaCliente, btnReservarClase));
        panelClases.setPadding(new Insets(3));

        VBox formInventario = new VBox(3, new Label("Campos Producto"), txtProdId, txtProdNombre, txtProdPrecio, txtProdStock, txtProdMin, btnAbastecerProd);
        VBox panelInventario = new VBox(5, new Label("Inventario Tienda"), tablaInventario, formInventario);
        panelInventario.setPadding(new Insets(3));

        VBox panelCarrito = new VBox(5, new Label("Carrito de Compras"), listaCarritoVisual, new HBox(5, new Label("Cant:"), spinCantidad, btnAgregarCarrito), new HBox(5, btnVaciarCarrito, btnCobrarVenta));
        panelCarrito.setPadding(new Insets(3));
        panelCarrito.setPrefWidth(210);

        HBox raiz = new HBox(10, panelClientes, panelClases, panelInventario, panelCarrito);
        raiz.setPadding(new Insets(5));
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