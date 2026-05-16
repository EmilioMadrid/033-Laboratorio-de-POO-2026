package javaapplication8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class TiendaVideojuegos {
    private HashMap<String, Videojuego> catalogo = new HashMap<>();
    
    private ArrayList<Videojuego> historialVentas = new ArrayList<>();
    
    private HashSet<Videojuego> wishlist = new HashSet<>();
    
    private LinkedList<Videojuego> colaDescargas = new LinkedList<>();
    
}