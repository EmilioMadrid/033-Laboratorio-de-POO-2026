package javaapplication6;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        GestionAudio gestor = new GestionAudio();

        Cancion c1 = new Cancion("C-101", "Nightcall", "Kavinsky", 4.18);
        
        Podcast p1 = new Podcast("P-202", "Programacion Orientada a Objetos", "UANL Academy", 45.0);
        
        Radio r1 = new Radio("R-303", "Nightcall", "Exa FM", 98.5f);

        gestor.agregarContenido(c1);
        gestor.agregarContenido(p1);
        gestor.agregarContenido(r1);

        gestor.ejecutarServicios();

        System.out.println("\nPrueba de busqueda por ID");
        
        String idExitoso = "C-101";
        String idFallido = "X-909";

        try {
            System.out.println("Buscando ID: " + idExitoso);
            ContenidoAudio encontrado = gestor.buscarPorId(idExitoso);
            System.out.println("Encontrado: " + encontrado.titulo);
        } catch (NoSuchElementException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nBuscando ID: " + idFallido);
            gestor.buscarPorId(idFallido);
        } catch (NoSuchElementException e) {
            System.out.println("\nCaptura de excepcion");
            System.err.println("Mensaje del sistema: " + e.getMessage());
            System.out.println("El programa sigue ejecutandose y estable.");
        }

        System.out.println("\nFin de la prueba");
    }
}
