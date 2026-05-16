package javaapplication8;

import java.util.Objects;

public abstract class Videojuego implements Comparable<Videojuego> {
    protected String id;
    protected String titulo;
    protected String desarrollador;
    protected double precio;
    protected double calificacion;

    public Videojuego(String id, String titulo, String desarrollador, double precio, double calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.precio = precio;
        this.calificacion = calificacion;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDesarrollador() { return desarrollador; }
    public double getPrecio() { return precio; }
    public double getCalificacion() { return calificacion; }

    public abstract String getGenero();
    public abstract void mostrarFichaTecnica();

    @Override
    public int compareTo(Videojuego otro) {
        return this.id.compareToIgnoreCase(otro.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Optimización por referencia idéntica
        if (obj == null || getClass() != obj.getClass()) return false; // Validación de tipo
        Videojuego other = (Videojuego) obj;
        return Objects.equals(id, other.id); // Comparación del identificador único
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}