package javaapplication5;

public class Portero extends Deportista {
    private int atajadas;

    public Portero(String nombre, double sueldoBase, int atajadas) {
        super(nombre, sueldoBase);
        this.atajadas = atajadas;
    }

    @Override
    public double calcularBonoExtra() {
        return atajadas * 200.0;
    }

    @Override
    public double calcularSueldoNeto(double sueldoBase) {
        return sueldoBase + calcularBonoExtra();
    }

    @Override
    public String getPosicion() {
        return "Portero";
    }

    @Override
    public void realizarAccion() {
        System.out.println(nombre + " realizó una atajada.");
    }

    public void entrenar() {
        System.out.println(nombre + " entrena reflejos básicos.");
    }

    public void entrenar(int disparos) {
        System.out.println(nombre + " entrena deteniendo " + disparos + " disparos directos.");
    }

    public void entrenar(String intensidad, int duracionMinutos) {
        System.out.println(nombre + " entrena a intensidad " + intensidad + " durante " + duracionMinutos + " min.");
    }
}