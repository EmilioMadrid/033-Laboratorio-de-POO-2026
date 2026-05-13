package javaapplication1;

import java.util.Scanner;

public class JavaApplication1 {

    public static void main(String[] args) {
        
        Scanner read = new Scanner(System.in);
        int prefijo, octeto;
        double[] mascara = {0, 0, 0, 0};
        
        do {
            System.out.print("Introduce el prefijo CIDR: /");
            prefijo = read.nextInt();

            if (prefijo < 0 || prefijo > 32) {
                System.out.println("El prefijo debe ser un numero entre 0 y 32");
            }
        } while (prefijo < 0 || prefijo > 32);
        
        octeto = (int) prefijo/8;
        
        for (int i = 0 ; i <= octeto ; i++){
            
            for (int j = 7 ; j >= 8-prefijo ; j--){
                mascara[i] += Math.pow(2, j);
            }
            
            prefijo -= 8;
            
        }
        
        System.out.print("La mascara por default es: ");
        for (int i = 0 ; i <= 3 ; i++){
            System.out.print((int)mascara[i] + ".");
        }
        
    }

}
