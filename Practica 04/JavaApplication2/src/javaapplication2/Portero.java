package javaapplication2;

public class Portero extends IntegranteClub{
    private int atajadas;
    private int porteriasImbatidas;

    public Portero(int atajadas, int porteriasImbatidas) {
        this.atajadas = atajadas;
        this.porteriasImbatidas = porteriasImbatidas;
    }

    @Override
    public void mostrarInformacion() {
        
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
