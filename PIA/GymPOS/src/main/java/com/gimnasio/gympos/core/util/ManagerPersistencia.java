package com.gimnasio.gympos.core.util;

import com.gimnasio.gympos.model.GimnasioData;
import java.io.*;

public class ManagerPersistencia {

    public static void guardarDatos(GimnasioData data, String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(data);
        }
    }

    public static GimnasioData cargarDatos(String rutaArchivo) throws IOException, ClassNotFoundException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new GimnasioData();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (GimnasioData) ois.readObject();
        }
    }
}