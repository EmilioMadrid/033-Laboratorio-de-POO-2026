package com.gimnasio.gympos.core.main;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.service.ClienteService;
import com.gimnasio.gympos.service.ClaseService;
import com.gimnasio.gympos.view.MainViewMock;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppPrueba extends Application {

    private ClienteService clienteService;
    private ClaseService claseService;

    @Override
    public void init() {
        clienteService = new ClienteService("gimnasio_test.dat");
        claseService = new ClaseService();
    }

    @Override
    public void start(Stage primaryStage) {
        ClientesController controller = new ClientesController(clienteService, claseService);

        Scene scene = new Scene(MainViewMock.crearVista(controller), 1000, 500);
        
        primaryStage.setTitle("GymPOS - Prueba de Integración de Componentes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (clienteService != null) {
            clienteService.apagarServicio();
        }
        if (claseService != null) {
            claseService.apagarServicio();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}