package javaapplication8;

public class ExpansionDLC extends Videojuego {
    private String juegoRequeridoId;
    private boolean esCosmetico;

    public ExpansionDLC(String id, String titulo, String desarrollador, double precio, double calificacion, String juegoRequeridoId, boolean esCosmetico) {
        super(id, titulo, desarrollador, precio, calificacion);
        this.juegoRequeridoId = juegoRequeridoId;
        this.esCosmetico = esCosmetico;
    }

    @Override
    public String getGenero() {
        return "DLC / Expansión";
    }

    public String getJuegoRequeridoId() {
        return this.juegoRequeridoId;
    }

    public boolean isCosmetico() {
        return this.esCosmetico;
    }

    @Override
    public void mostrarFichaTecnica() {
        System.out.println("\t\tCONTENIDO DESCARGABLE (DLC)");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Desarrollador: " + desarrollador);
        System.out.println("Juego Requerido (ID): " + juegoRequeridoId);
        System.out.println("Es estético/cosmético: " + (esCosmetico ? "Sí" : "No"));
        System.out.println("Precio: $" + precio);
        System.out.println("Calificación: " + calificacion + "/100");
    }
}
