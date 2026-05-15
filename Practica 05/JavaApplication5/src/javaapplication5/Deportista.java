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

    public final void mostrarReciboPago() {
        System.out.println("\nRecibo de nomina");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Puesto: " + getPosicion());
        
        double bono = calcularBonoExtra();
        double total = calcularSueldoNeto(sueldoBase);
        
        System.out.println("Sueldo Base: $" + sueldoBase);
        System.out.println("Bono Extra:  $" + bono);
        System.out.println("NETO A PAGAR: $" + total);
        System.out.println("------------------------------------");
        
        realizarAccion(); 
    }

}
