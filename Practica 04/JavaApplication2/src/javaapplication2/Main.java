package javaapplication2;

public class Main {

    public static void main(String[] args) {
        NominaClub miClub = new NominaClub();

        Contrato contratoProfesional = new Contrato(400000.0, 24, "Futbolista");
        Contrato contratoJuvenil = new Contrato(30000.0, 12, "Fuerzas basicas");
        Contrato contratoDirector = new Contrato(250000.0, 12, "Cuerpo Tecnico");
        Contrato contratoEntrenador = new Contrato(80000.0, 12, "Cuerpo Tecnico");

        Portero portero = new Portero("Nahuel Guzmán", 38, "Argentina", "Tigres", contratoProfesional, 700, 150);
        Delantero delantero = new Delantero("André-Pierre Gignac", 38, "Francia", "Tigres", contratoProfesional, 200, 80);
        CuerpoTecnico dt = new CuerpoTecnico("Guido Pizarro", 36, "Argentina", "Tigres", contratoDirector, "Director Técnico");

        miClub.agregarIntegrante(portero);
        miClub.agregarIntegrante(delantero);
        miClub.agregarIntegrante(dt);

        System.out.println("\nIntentando agregar un integrante nulo");
        miClub.agregarIntegrante(null); 

        System.out.println("\nPrueba de polimorfismo");
        miClub.ejecutarJornada();
        
        System.out.println("\nFin del programa");
    }
}