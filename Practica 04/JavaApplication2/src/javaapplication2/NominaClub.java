package javaapplication2;

import java.util.ArrayList;

public class NominaClub {
    private ArrayList<IntegranteClub> integrantes;

    public NominaClub() {
        this.integrantes = new ArrayList<>();
    }

    public void agregarIntegrante(IntegranteClub ic) {
        if (ic != null) {
            integrantes.add(ic);
        }
    }
    
    public void mostrarNomina() {
        for (IntegranteClub ic : integrantes)
            ic.mostrarInformacion(); 
    }

    public void ejecutarJornada() {
        System.out.println("\nEjecutando labores de la jornada");
        for (IntegranteClub ic : integrantes) {
            ic.trabajar();
            
            System.out.println("------------------------------------------------");
        }
    }

}
