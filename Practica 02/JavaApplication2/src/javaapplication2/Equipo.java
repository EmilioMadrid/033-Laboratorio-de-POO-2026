package javaapplication2;

import java.util.ArrayList;

public class Equipo {
    private String nombreEquipo;
    private ArrayList<Futbolista> listaFutbolistas;
    
    public Equipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
        this.listaFutbolistas = new ArrayList<>();
    }
    
    public void agregarFutbolista(Futbolista f) {
        if (f != null) {
            listaFutbolistas.add(f);
            System.out.println(f.getNombre() + " ha sido fichado por " + nombreEquipo);
        } else {
            System.out.println("No se puede agregar un jugador inexistente.");
        }
    }

    public void mostrarPlantilla() {
        for (Futbolista f : listaFutbolistas) {
            f.imprimirFichaTecnica();
        }
    }

    public Futbolista buscarPorNombre(String nombreBusqueda) {
        for (Futbolista f : listaFutbolistas) {
            if (f.getNombre().equalsIgnoreCase(nombreBusqueda)) {
                return f;
            }
        }
        return null;
    }
    
}