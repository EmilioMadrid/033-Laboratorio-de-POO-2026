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
        super.mostrarInformacion();
        System.out.println(" | Rol: Delantero | Goles: " + goles);
    }

}