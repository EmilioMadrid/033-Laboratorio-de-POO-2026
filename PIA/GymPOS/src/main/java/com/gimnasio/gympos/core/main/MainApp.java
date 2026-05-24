package com.gimnasio.gympos.core.main;

import com.gimnasio.gympos.controller.ClientesController;
import com.gimnasio.gympos.service.ClienteService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private ClienteService clienteService;

    @Override
    public void init() {
        clienteService = new ClienteService("gimnasio_datos.dat");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gimnasio/gympos/view/MainView.fxml"));
        
        loader.setControllerFactory(tipoControlador -> {
            if (tipoControlador == ClientesController.class) {
                return new ClientesController(clienteService);
            } else {
                try {
                    return tipoControlador.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Fallo al instanciar controlador por defecto", e);
                }
            }
        });

        Parent root = loader.load();
        primaryStage.setTitle("GymPOS - Gestión de Clientes");
        primaryStage.setScene(new Scene(root));
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