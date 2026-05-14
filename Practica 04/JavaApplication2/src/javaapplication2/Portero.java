package javaapplication2;

public class Portero extends IntegranteClub{
    private int atajadas;
    private int porteriasImbatidas;

    public Portero(String nombre, int edad, String pais, String club, Contrato contrato, int atajadas, int porteriasImbatidas) {
        super(nombre, edad, pais, club, contrato);
        this.atajadas = atajadas;
        this.porteriasImbatidas = porteriasImbatidas;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Posición: Portero | Atajadas: " + atajadas + "Porterias en cero: " + porteriasImbatidas);
    }
    
    @Override
    public void realizarAccion() {
        System.out.println("Ha realizado una atajada");
    }

    @Override
    public void trabajar() {
        System.out.println("Está entrenando coordinacion y reflejos");
    }
    
}
