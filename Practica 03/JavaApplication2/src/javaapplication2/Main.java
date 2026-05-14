package javaapplication2;

public class Main {

    public static void main(String[] args) {
        Equipo miEquipo = new Equipo("Tigres UANL");
        
        Futbolista f1 = new Futbolista("Nahuel Guzmán", 38, 1.93, "Tigres", "Argentina", 250000);
        
        Futbolista f2 = new Futbolista("Ozziel Herrera", 22, 1.78, "Tigres", "México", 6000000);
        
        Futbolista f3 = new Futbolista("Guido Pizarro", 36, 1.85, "Argentina", 0);
        
        Futbolista f4 = new Futbolista("André-Pierre Gignac", 38, 1.87, "Francia", 300000);
        
        Futbolista f5 = new Futbolista();

        miEquipo.agregarFutbolista(f1);
        miEquipo.agregarFutbolista(f2);
        miEquipo.agregarFutbolista(f3);
        miEquipo.agregarFutbolista(f4);
        miEquipo.agregarFutbolista(f5);
    }
}