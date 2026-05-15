package javaapplication6;

public abstract class ContenidoMusical {
    protected String id;
    protected String titulo;
    protected double duracionMinutos;

    public ContenidoMusical(String id, String titulo, double duracionMinutos) {
        this.id = id;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
    }

    public void mostrarInfo() {
        System.out.println("[" + id + "] " + titulo + " (" + duracionMinutos + " min)");
    }

    public abstract String getTipo();
    public abstract void mostrarDetalles();
}