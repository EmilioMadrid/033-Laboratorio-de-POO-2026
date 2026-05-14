package javaapplication2;

public class Main {

    public static void main(String[] args) {
        System.out.println("PRUEBAS UNITARIAS");

        // 1. Prueba de Encapsulamiento (Clase Futbolista)
        System.out.println("\n[Prueba 1: Validacion de Edad]");
        Futbolista f1 = new Futbolista();
        f1.setEdad(-5);
        System.out.println("Resultado esperado (14): " + f1.getEdad());

        // 2. Prueba de Composición y Null-Safe
        System.out.println("\n[Prueba 2: Futbolista sin Contrato]");
        Futbolista f2 = new Futbolista("André-Pierre Gignac", 38, 1.87, "Francia", 300000);
        System.out.println(f2.toString());

        // 3. Prueba de Lógica de Negocio (Valor de Mercado)
        System.out.println("\n[Prueba 3: Limite de aumento de valor]");
        System.out.println("Valor inicial: $" + f2.getValorMercado());
        f2.actualizarValorMercado(600000);
        System.out.println("Valor tras intento fallido (debe seguir en $300000): $" + f2.getValorMercado());

        // 4. Prueba de la Clase Contrato
        System.out.println("\n[Prueba 4: Validacion de Salario]");
        Contrato c1 = new Contrato(-250000, 36, 10000000, "Profesional");
        Futbolista f3 = new Futbolista("Ozziel Herrera", 22, 1.78, "Tigres", "México", 6000000, c1);
        System.out.println("Salario esperado ($0.0): $" + f3.getContrato().getSalarioMensual());
    }
}