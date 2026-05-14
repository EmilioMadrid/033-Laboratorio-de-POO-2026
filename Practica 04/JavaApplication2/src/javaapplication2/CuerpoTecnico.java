package javaapplication2;

public class CuerpoTecnico extends IntegranteClub {
    private String puesto;

    public CuerpoTecnico(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public void mostrarInformacion() {
        
        System.out.println("Integrante Cuerpo Tecnico: Puesto: " + puesto);
    }

    @Override
    public void realizarAccion() {
        System.out.println("Está dando indicaciones al equipo");
    }

    @Override
    public void trabajar() {
        System.out.println("Está planteando tacticas previas al partido");
    }
}
