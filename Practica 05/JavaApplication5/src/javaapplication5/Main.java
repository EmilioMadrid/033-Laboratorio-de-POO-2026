package javaapplication5;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        GestionClub tigresUANL = new GestionClub();

        Deportista nahu = new Portero("Nahuel Guzmán", 55000.0, 12);
        Deportista gignac = new Delantero("André-Pierre Gignac", 90000.0, 8);
        Deportista guido = new Defensa("Guido Pizarro", 45000.0, 25);

        tigresUANL.contratarDeportista(nahu);
        tigresUANL.contratarDeportista(gignac);
        tigresUANL.contratarDeportista(guido);

        tigresUANL.avanzarJornada();
        
        System.out.println("\nejecutando entrenamientos especificos");
        
        ArrayList<Deportista> lista = tigresUANL.getListaDeportistas();
        
        for (Deportista d : lista) {
            if (d instanceof Portero) {
                Portero p = (Portero) d;
                System.out.print("[Identificado como Portero] -> ");
                p.entrenar(50);
            } 
            else if (d instanceof Delantero) {
                Delantero del = (Delantero) d;
                System.out.print("[Identificado como Delantero] -> ");
                del.entrenar("Tiro a gol", true);
            } 
            else if (d instanceof Defensa) {
                Defensa def = (Defensa) d;
                System.out.print("[Identificado como Defensa] -> ");
                def.entrenar(5.5);
            }
        }
        
        System.out.println("\nFin del programas.");
    }
}