package javaapplication6;

public abstract class ContenidoAudio {
    protected String id;
    protected String titulo;

    public ContenidoAudio(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public void mostrarInfo() {
        System.out.println("[" + id + "] " + titulo);
    }

    public abstract String getTipo();
    public abstract void mostrarDetalles();
}
