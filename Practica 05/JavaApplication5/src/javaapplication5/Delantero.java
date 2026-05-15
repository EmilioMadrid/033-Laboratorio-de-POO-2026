package javaapplication5;

public class Delantero extends Deportista {
    private int goles;

    public Delantero(String nombre, double sueldoBase, int goles) {
        super(nombre, sueldoBase);
        this.goles = goles;
    }

    @Override
    public double calcularBonoExtra() {
        return goles * 1000.0;
    }

    @Override
    public double calcularSueldoNeto(double sueldoBase) {
        return sueldoBase + calcularBonoExtra();
    }

    @Override
    public String getPosicion() {
        return "Delantero";
    }

    @Override
    public void realizarAccion() {
        System.out.println(nombre + " está rematando a portería.");
    }

}

