package javaapplication6;

public class Podcast extends ContenidoAudio implements IReproducible, IDescargable {
    private String autor;
    private double duracionMinutos;

    public Podcast(String id, String titulo, String autor, double duracion) {
        super(id, titulo);
        this.autor = autor;
        this.duracionMinutos = duracion;
    }

    @Override
    public String getTipo() {
        return "Podcast";
    }

    @Override
    public void mostrarDetalles() {
        mostrarInfo();
        System.out.println("Autor: " + autor + " | Duracion: " + duracionMinutos);
    }

    @Override
    public void reproducir() {
        System.out.println("Escuchando episodio: " + titulo);
    }

    @Override
    public void descargar() {
        System.out.println("Guardando episodio para escuchar offline.");
    }
}
