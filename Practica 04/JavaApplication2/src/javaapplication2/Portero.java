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
        super.mostrarInformacion();
        System.out.println("Posición: Portero | Atajadas: " + atajadas + "Porterias en cero: " + porteriasImbatidas);
    }
    
}
