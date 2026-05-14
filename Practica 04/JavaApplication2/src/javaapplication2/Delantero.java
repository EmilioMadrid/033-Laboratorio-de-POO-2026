package javaapplication2;

public class Delantero extends IntegranteClub {
    private int goles;
    private int asistencias;

    public Delantero(int goles, int asistencias) {
        this.goles = goles;
        this.asistencias = asistencias;
    }

    @Override
    public void mostrarInformacion() {
        
        System.out.println(" | Rol: Delantero | Goles: " + goles);
    }
    
    @Override
    public void realizarAccion() {
        System.out.println("Ha marcado un gol");
    }

    @Override
    public void trabajar() {
        System.out.println("Está entrenando remates de cabeza");
    }

}