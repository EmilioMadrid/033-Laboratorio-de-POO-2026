package javaapplication6;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class StreamingTest {

    public static void main(String[] args) {
        try {
            Logger msgLogger = Logger.getLogger(StreamingService.class.getName());
            
            FileHandler archivoLog = new FileHandler("streaming_sistema.log", true);
            
            SimpleFormatter formateador = new SimpleFormatter();
            
            archivoLog.setFormatter(formateador);
            
            msgLogger.addHandler(archivoLog);
            
            System.out.println("Sistema: Logger inicializado. Historial en 'streaming_sistema.log'.\n");

        } catch (IOException | SecurityException e) {
            System.err.println("Error: No se pudo configurar el archivo de logs. " + e.getMessage());
        }

        StreamingService servicio = new StreamingService();

        System.out.println("Ejecutanto Prueba 1");
        try {
            System.out.println("Buscando contenido en una biblioteca vacía...");
            servicio.buscarPorId("C-909"); 
            
            System.out.println("Prueba Fallida: El programa continuó sin lanzar excepción.");
        } catch (RecursoNoEncontradoException e) {
            System.out.println("\n[ÉXITO DE PRUEBA 1]");
            System.out.println("La excepción 'RecursoNoEncontradoException' fue atrapada correctamente.");
            System.out.println("Mensaje recibido: " + e.getMessage());
        } catch (StreamingException e) {
            System.err.println("Se atrapó una excepción genérica no esperada: " + e.getMessage());
        }

        System.out.println("\nEjecutando Prueba 2");
        Cancion cancionPremium = new Cancion("C-101", "Nightcall", "Kavinsky", 4.18);
        servicio.agregarContenido(cancionPremium);

        try {
            System.out.println("Intentando descargar contenido Premium siendo usuario gratuito...");
            servicio.procesarDescarga(cancionPremium, false);
            
            System.out.println("Prueba Fallida: Se permitió la descarga ilícita.");
        } catch (AccesoNoAutorizadoException e) {
            System.out.println("\n[ÉXITO DE PRUEBA 2]");
            System.out.println("La excepción 'AccesoNoAutorizadoException' fue atrapada correctamente.");
            System.out.println("Mensaje recibido: " + e.getMessage());
        } catch (StreamingException e) {
            System.err.println("Se atrapó un error inesperado de tipo: " + e.getClass().getName());
        }

        System.out.println("\nEjecutando Prueba 3");
        try {
            System.out.println("Intentando descargar contenido Premium siendo usuario Premium...");
            servicio.procesarDescarga(cancionPremium, true);
            System.out.println("[ÉXITO DE PRUEBA 3] Descarga autorizada y registrada en disco.");
        } catch (AccesoNoAutorizadoException e) {
            System.err.println("Prueba Fallida: Se denegó el acceso a un usuario válido.");
        }

        System.out.println("\nFin de las pruebas unitarias");
    }
}
