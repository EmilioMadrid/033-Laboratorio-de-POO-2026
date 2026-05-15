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

    public void entrenar() {
        System.out.println(nombre + " practica tiros libres.");
    }

    public void entrenar(int repeticiones) {
        System.out.println(nombre + " realiza " + repeticiones + " series de remates de cabeza.");
    }

    public void entrenar(String tipoEjercicio, boolean usaBalon) {
        String conBalon = usaBalon ? "con balón" : "sin balón";
        System.out.println(nombre + " practica " + tipoEjercicio + " " + conBalon);
    }
}

