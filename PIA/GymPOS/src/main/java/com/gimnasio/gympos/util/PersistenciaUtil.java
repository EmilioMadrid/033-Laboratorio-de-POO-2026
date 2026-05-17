package com.gimnasio.gympos.util;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.exception.PersistenciaException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class PersistenciaUtil {

    private PersistenciaUtil() {
        throw new AssertionError("No se permite la instanciación de esta clase utilitaria.");
    }

    public static void guardarClientes(Map<String, Cliente> clientes, String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            throw new PersistenciaException("Error crítico de E/S al escribir en el disco duro.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Cliente> cargarClientes(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Map<String, Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Error crítico de E/S al leer desde el disco duro.", e);
        }
    }
}