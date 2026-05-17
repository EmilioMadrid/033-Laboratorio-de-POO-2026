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

    public boolean agregarVideojuego(Videojuego juego) {
        if (juego == null || catalogo.containsKey(juego.getId())) {
            return false;
        }
        catalogo.put(juego.getId(), juego);
        return true;
    }

    public boolean eliminarVideojuego(String id) {
        if (id == null || !catalogo.containsKey(id)) {
            return false;
        }
        Videojuego juegoAEliminar = catalogo.get(id);
        catalogo.remove(id);
        wishlist.remove(juegoAEliminar);
        colaDescargas.remove(juegoAEliminar);
        return true;
    }
    
    public Videojuego obtenerVideojuego(String id) {
        if (id == null) {
            return null;
        }
        return catalogo.get(id);
    }

    public boolean actualizarPrecioYCalificacion(String id, double nuevoPrecio, double nuevaCalificacion) {
        if (id == null || nuevoPrecio < 0 || nuevaCalificacion < 0 || nuevaCalificacion > 100) {
            return false;
        }
        Videojuego juego = catalogo.get(id);
        if (juego == null) {
            return false;
        }
        juego.setPrecio(nuevoPrecio);
        juego.setCalificacion(nuevaCalificacion);
        return true;
    }
    
    public void mostrarCatalogoOrdenado(java.util.Comparator<Videojuego> criterio) {
        if (criterio == null) {
            return;
        }
        catalogo.values().stream()
                .sorted(criterio)
                .forEach(Videojuego::mostrarFichaTecnica);
    }
    
    public java.util.List<Videojuego> filtrarPorGeneroYCalificacion(String genero, double calificacionMinima) {
        if (genero == null) {
            return new java.util.ArrayList<>();
        }
        return catalogo.values().stream()
                .filter(juego -> juego.getGenero().equalsIgnoreCase(genero))
                .filter(juego -> juego.getCalificacion() >= calificacionMinima)
                .collect(java.util.stream.Collectors.toList());
    }

    public void mostrarWishlistConIterator() {
        java.util.Iterator<Videojuego> it = wishlist.iterator();
        while (it.hasNext()) {
            Videojuego juego = it.next();
            juego.mostrarFichaTecnica();
        }
    }

    public boolean encolarDescarga(Videojuego juego) {
        if (juego == null || !catalogo.containsKey(juego.getId()) || colaDescargas.contains(juego)) {
            return false;
        }
        colaDescargas.addLast(juego);
        return true;
    }

    public Videojuego procesarSiguienteDescarga() {
        if (colaDescargas.isEmpty()) {
            return null;
        }
        return colaDescargas.removeFirst();
    }
    
}