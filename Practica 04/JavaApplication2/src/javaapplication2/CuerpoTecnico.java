package javaapplication2;

public class CuerpoTecnico extends IntegranteClub {
    private String puesto;

    public CuerpoTecnico(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Integrante Cuerpo Tecnico: Puesto: " + puesto);
    }

}
