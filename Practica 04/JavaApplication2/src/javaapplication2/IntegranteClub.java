package javaapplication2;

public class IntegranteClub extends Persona{
    private String club;
    private Contrato contrato;
    
    public IntegranteClub(String nombre, int edad, String pais, String club, Contrato contrato) {
        super(nombre, edad, pais);
        this.club = club;
        this.contrato = contrato;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Club: " + club + " | Contrato: [" + contrato + "]");
    }
    
    @Override
    public void realizarAccion() {
        System.out.println(getNombre() + " está realizando una accion en las instalaciones de " + club);
    }

    @Override
    public void trabajar() {
        System.out.println(getNombre() + " está cumpliendo con sus obligaciones en " + club);
    }
    
}
