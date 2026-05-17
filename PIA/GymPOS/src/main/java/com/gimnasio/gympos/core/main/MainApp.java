package com.gimnasio.gympos.core.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/gimnasio/gympos/view/MainView.fxml"));

        primaryStage.setTitle("GymPOS - Sistema de Control");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}