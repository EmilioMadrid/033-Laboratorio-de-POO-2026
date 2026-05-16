package javaapplication6;

import java.util.NoSuchElementException;

import java.util.ArrayList;

public class GestionAudio {
    private ArrayList<ContenidoAudio> biblioteca;

    public GestionAudio() {
        this.biblioteca = new ArrayList<>();
    }

    public void agregarContenido(ContenidoAudio contenido) {
        biblioteca.add(contenido);
        System.out.println("Sistema: '" + contenido.getTipo() + "' añadido a la biblioteca.");
    }

    public void ejecutarServicios() {
        System.out.println("\nIniciando servicio de streaming");
        for (ContenidoAudio c : biblioteca) {
            System.out.println("\n-------------------------------------------");
            c.mostrarDetalles();
            
            if (c instanceof IReproducible) {
                ((IReproducible) c).reproducir();
            }
            
            if (c instanceof IDescargable) {
                ((IDescargable) c).descargar();
            }
            
            if (c instanceof IVendible) {
                System.out.println("Costo de adquisición: $" + ((IVendible) c).getPrecio());
            }
        }
        System.out.println("\n-------------------------------------------");
    }
    
    public ContenidoAudio buscarPorId(String id) {
        for (ContenidoAudio c : biblioteca) {
            if (c.id.equalsIgnoreCase(id))
                return c;
        }
        throw new NoSuchElementException("No existe contenido con el ID '" + id + "'.");
    }

    public ArrayList<ContenidoAudio> getBiblioteca() {
        return biblioteca;
    }
}
