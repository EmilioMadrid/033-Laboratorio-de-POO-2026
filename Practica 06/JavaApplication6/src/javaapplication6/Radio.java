package javaapplication6;

public class Radio extends ContenidoAudio implements IReproducible {
    private String emisora;
    private float radiofrecuencia;

    public Radio(String id, String titulo, String emisora, float frecuencia) {
        super(id, titulo);
        this.emisora = emisora;
        this.radiofrecuencia = frecuencia;
    }

    @Override
    public String getTipo() {
        return "Emisora de radio";
    }

    @Override
    public void mostrarDetalles() {
        mostrarInfo();
        System.out.println("Emisora: " + emisora + " | Frecuencia: " + radiofrecuencia);
    }

    @Override
    public void reproducir() {
        System.out.println("Sintonizando señal en vivo de: " + emisora);
    }

}