package javaapplication6;

public class Cancion extends ContenidoAudio implements IReproducible, IDescargable, IVendible {
    private String artista;
    private double duracionMinutos;

    public Cancion(String id, String titulo, String artista, double duracion) {
        super(id, titulo);
        this.artista = artista;
        this.duracionMinutos = duracion;
    }

    @Override
    public String getTipo() {
        return "Canción de Streaming";
    }

    @Override
    public void mostrarDetalles() {
        mostrarInfo();
        System.out.println("Artista: " + artista + " | Duracion: " + duracionMinutos);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo audio de: " + titulo);
    }

    @Override
    public void descargar() {
        System.out.println("Descargando archivo .mp3 de: " + titulo);
    }
    
    @Override
    public double getPrecio() { 
        return 19.90;
    }
}