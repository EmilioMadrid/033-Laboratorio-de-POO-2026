package javaapplication2;

public class IntegranteClub extends Persona{
    private String club;
    private Contrato contrato;
    
    public IntegranteClub(String club, Contrato contrato) {
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
        
        System.out.println("Club: " + club + " | Contrato: [" + contrato + "]");
    }
    
    @Override
    public void realizarAccion() {
        System.out.println("Está realizando una accion en las instalaciones de " + club);
    }

    @Override
    public void trabajar() {
        System.out.println("Está cumpliendo con sus obligaciones en " + club);
    }
    
}
