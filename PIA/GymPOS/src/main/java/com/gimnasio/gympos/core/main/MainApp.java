package com.gimnasio.gympos.core.main;

import com.gimnasio.gympos.model.*;
import com.gimnasio.gympos.core.util.ManagerPersistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class MainApp extends Application {

    private static final String ARCHIVO_DATOS = "gimnasio_datos.dat";
    private GimnasioData datosSistema;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            datosSistema = ManagerPersistencia.cargarDatos(ARCHIVO_DATOS);
            if (datosSistema.getListaClientes().isEmpty()) {
                precargarDatosDePrueba();
                ManagerPersistencia.guardarDatos(datosSistema, ARCHIVO_DATOS);
            }
        } catch (Exception e) {
            datosSistema = new GimnasioData();
        }

        Parent root = FXMLLoader.load(getClass().getResource("/com/gimnasio/gympos/view/MainView.fxml"));
        primaryStage.setTitle("GymPOS - Sistema de Control");
        primaryStage.setScene(new Scene(root, 1020, 650));
        primaryStage.show();
    }

    private void precargarDatosDePrueba() {
        for (int i = 1; i <= 25; i++) {
            Membresia mem = (i % 2 == 0) 
                ? new MembresiaAnual("M-ANUAL-" + i, LocalDate.now(), 450.0)
                : new MembresiaMensual("M-MENSUAL-" + i, LocalDate.now(), 500.0);
            
            Cliente cliente = new Cliente("ID-" + i, "Cliente de Prueba " + i, "81100022" + i, mem);
            datosSistema.getListaClientes().add(cliente);
        }
    }
}