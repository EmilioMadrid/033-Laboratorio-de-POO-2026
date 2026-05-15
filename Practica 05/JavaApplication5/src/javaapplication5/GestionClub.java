package javaapplication5;

import java.util.ArrayList;

public class GestionClub {
    private ArrayList<Deportista> listaDeportistas;

    public GestionClub() {
        this.listaDeportistas = new ArrayList<>();
    }

    public void contratarDeportista(Deportista d) {
        if (d != null) {
            listaDeportistas.add(d);
            System.out.println(d.getNombre() + " ha sido dado de alta en el sistema.");
        }
    }

    public void avanzarJornada() {
        System.out.println("\n Iniciando jornada");
        for (Deportista d : listaDeportistas) {
            d.getPosicion();
            d.realizarAccion();
            System.out.println("----------------------------------------------------");
        }
        System.out.println("Fin de la jornada");
    }

    public ArrayList<Deportista> getListaDeportistas() {
        return listaDeportistas;
    }
}
