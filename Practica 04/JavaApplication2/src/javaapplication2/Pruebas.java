package javaapplication2;

public class Pruebas {
    public static void main(String[] args) {
        System.out.println("Prueba de jerarquia y herencia\n");

        System.out.println("[Test NIVEL 1: Persona]");
        Persona p1 = new Persona("Emilio", 23, "México");
        p1.mostrarInformacion();
        p1.realizarAccion();
        System.out.println("----------------------------------------------");

        System.out.println("[Test NIVEL 2: Integrante Club]");
        Contrato contratoEntrenador = new Contrato(80000.0, 12, "Cuerpo Tecnico");
        IntegranteClub ic = new IntegranteClub("Guido Pizarro", 36, "Argentina", "Tigres", contratoEntrenador);
        ic.mostrarInformacion();
        System.out.println("----------------------------------------------");

        System.out.println("[Test NIVEL 3: Clase concreta]");
        Contrato contratoProfesional = new Contrato(400000.0, 24, "Futbolista");
        Portero nahuel = new Portero("Nahuel Guzmán", 38, "Argentina", "Tigres", contratoProfesional, 700, 150);
        System.out.print("Portero -> ");
        nahuel.mostrarInformacion();
        nahuel.realizarAccion();
        System.out.println("");

        Delantero gignac = new Delantero("André-Pierre Gignac", 38, "Francia", "Tigres", contratoProfesional, 200, 50);
        System.out.print("Delantero -> ");
        gignac.mostrarInformacion();
        gignac.realizarAccion();
        System.out.println("");

        CuerpoTecnico pauno = new CuerpoTecnico("Guido Pizarro", 36, "Argentina", "Tigres", contratoEntrenador, "DT");
        System.out.print("Cuerpo Técnico -> ");
        pauno.mostrarInformacion();
        pauno.realizarAccion();
        System.out.println("----------------------------------------------");

        System.out.println("\nFin de las pruebas");
    }
}
