package javaapplication8;

import java.util.Comparator;

public interface CriteriosOrden {
    
    public static final Comparator<Videojuego> POR_PRECIO_ASC = 
        (v1, v2) -> Double.compare(v1.getPrecio(), v2.getPrecio());

    public static final Comparator<Videojuego> POR_CALIFICACION_DESC = 
        Comparator.comparingDouble(Videojuego::getCalificacion).reversed();
}