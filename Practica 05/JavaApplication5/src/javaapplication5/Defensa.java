package javaapplication5;

public class Defensa extends Deportista {
    private int recuperaciones;

    public Defensa(String nombre, double sueldoBase, int recuperaciones) {
        super(nombre, sueldoBase);
        this.recuperaciones = recuperaciones;
    }

    @Override
    public double calcularBonoExtra() {
        return recuperaciones * 100.0;
    }

    @Override
    public double calcularSueldoNeto(double sueldoBase) {
        return sueldoBase + calcularBonoExtra();
    }

    @Override
    public String getPosicion() {
        return "Defensa Central";
    }

    @Override
    public void realizarAccion() {
        System.out.println(nombre + " está barriéndose para recuperar la posesión.");
    }

}