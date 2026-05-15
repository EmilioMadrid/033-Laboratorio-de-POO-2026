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
    public abstract void realizarAccionEspecial();

    public final void procesarNomina() {
        System.out.println("\n--- GENERANDO RECIBO DE PAGO ---");
        System.out.println("Deportista: " + nombre);
        System.out.println("Posición: " + getPosicion());
        
        double bono = calcularBonoExtra(sueldoBase);
        double total = calcularSueldoNeto();
        
        System.out.println("Sueldo Base: $" + sueldoBase);
        System.out.println("Bono Extra:  $" + bono);
        System.out.println("NETO A PAGAR: $" + total);
        System.out.println("--------------------------------");
    }
}
