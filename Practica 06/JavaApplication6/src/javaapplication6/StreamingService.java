package javaapplication6;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StreamingService {
    private static final Logger logger = Logger.getLogger(StreamingService.class.getName());
    
    private ArrayList<ContenidoAudio> biblioteca;

    public StreamingService() {
        this.biblioteca = new ArrayList<>();
    }

    public void agregarContenido(ContenidoAudio contenido) {
        if (contenido != null) {
            biblioteca.add(contenido);
            logger.log(Level.INFO, "Contenido agregado exitosamente: {0}", contenido.titulo);
        }
    }

    public ContenidoAudio buscarPorId(String id) throws RecursoNoEncontradoException {
        logger.log(Level.INFO, "Iniciando búsqueda de contenido con ID: {0}", id);
        
        for (ContenidoAudio c : biblioteca) {
            if (c.id.equalsIgnoreCase(id)) {
                return c;
            }
        }
        
        String msgError = "El ID '" + id + "' no corresponde a ningún elemento en el sistema.";
        logger.log(Level.SEVERE, "Fallo en búsqueda: {0}", msgError);
        
        throw new RecursoNoEncontradoException(msgError);
    }

    public void procesarDescarga(ContenidoAudio contenido, boolean esUsuarioPremium) throws AccesoNoAutorizadoException {
        logger.log(Level.INFO, "Procesando solicitud de descarga para: {0}", contenido.titulo);
        
        if (contenido instanceof IVendible && !esUsuarioPremium) {
            String msgError = "El usuario actual no tiene una suscripción válida para descargar: " + contenido.titulo;
            logger.log(Level.WARNING, "Intento de descarga no autorizado: {0}", msgError);
            
            throw new AccesoNoAutorizadoException(msgError);
        }
        
        System.out.println("Descarga completada con éxito para: " + contenido.titulo);
    }

    public void validarMetadatosAudio(ContenidoAudio contenido) throws FormatoAudioException {
        logger.log(Level.INFO, "Validando integridad de metadatos para: {0}", contenido.titulo);
        
        if (contenido.id == null || contenido.id.trim().isEmpty()) {
            throw new FormatoAudioException(
                "Estructura de metadatos corrupta: ID ausente.", 
                "NULL_OR_EMPTY", 
                "ERR-AUDIO-001"
            );
        }
        
        System.out.println("Metadatos validados para: " + contenido.titulo);
    }
}
