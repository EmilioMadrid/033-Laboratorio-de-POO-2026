package javaapplication8;

public class JuegoBase extends Videojuego {
    private String genero;
    private double tamanoGigas;

    public JuegoBase(String id, String titulo, String desarrollador, double precio, double calificacion, String genero, double tamanoGigas) {
        super(id, titulo, desarrollador, precio, calificacion);
        this.genero = genero;
        this.tamanoGigas = tamanoGigas;
    }

    @Override
    public String getGenero() {
        return this.genero;
    }

    public double getTamanoGigas() {
        return this.tamanoGigas;
    }

    @Override
    public void mostrarFichaTecnica() {
        System.out.println("\t\tJUEGO BASE");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Desarrollador: " + desarrollador);
        System.out.println("Género: " + genero);
        System.out.println("Tamaño: " + tamanoGigas + " GB");
        System.out.println("Precio: $" + precio);
        System.out.println("Calificación: " + calificacion + "/100");
        System.out.println("--------------------------------------");
    }
}