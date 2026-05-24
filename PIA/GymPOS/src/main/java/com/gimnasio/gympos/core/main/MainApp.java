package com.gimnasio.gympos.core.main;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.service.ClienteService;
import com.gimnasio.gympos.service.ClaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private ClienteService clienteService;
    private ClaseService claseService;

    @Override
    public void init() {
        clienteService = new ClienteService("gimnasio_datos.dat");
        claseService = new ClaseService();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientesController controller = new ClientesController(clienteService, claseService);

        javafx.scene.Scene scene = new javafx.scene.Scene(
            com.gimnasio.gympos.view.MainViewMock.crearVista(controller), 1000, 500
        );
        
        primaryStage.setTitle("GymPOS - Gestión de Clientes y Reservas (Modo Prueba)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (clienteService != null) {
            clienteService.apagarServicio();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}