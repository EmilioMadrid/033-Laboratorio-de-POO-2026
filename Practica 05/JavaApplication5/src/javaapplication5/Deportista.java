package javaapplication5;

public abstract class Deportista implements ICalculable {
    protected String nombre;
    protected double sueldoBase;

    public Deportista(String nombre, double sueldoBase) {
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract String getPosicion();
    public abstract void realizarAccion();

}
