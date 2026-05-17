package javaapplication8;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JavaApplication8 {

    public static void main(String[] args) {
        TiendaVideojuegos tienda = new TiendaVideojuegos();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n\t\t MENU TIENDA DE VIDEOJUEGOS");
            System.out.println("1. Agregar Videojuego (Juego Base)");
            System.out.println("2. Agregar Videojuego (Expansion DLC)");
            System.out.println("3. Eliminar Videojuego del Catalogo");
            System.out.println("4. Buscar Videojuego por ID");
            System.out.println("5. Actualizar Precio y Calificacion");
            System.out.println("6. Mostrar Catalogo Ordenado por ID");
            System.out.println("7. Mostrar Catalogo Ordenado por Precio (Ascendente)");
            System.out.println("8. Mostrar Catalogo Ordenado por Calificacion (Descendente)");
            System.out.println("9. Filtrar por Genero y Calificacion Minima (Streams)");
            System.out.println("10. Mostrar Wishlist (Iterator)");
            System.out.println("11. Encolar Videojuego para Descarga");
            System.out.println("12. Procesar Siguiente Descarga (FIFO)");
            System.out.println("13. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        System.out.print("ID: ");
                        String idB = scanner.nextLine();
                        System.out.print("Titulo: ");
                        String tituloB = scanner.nextLine();
                        System.out.print("Desarrollador: ");
                        String desB = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precioB = scanner.nextDouble();
                        System.out.print("Calificacion (0-100): ");
                        double calB = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Genero: ");
                        String genB = scanner.nextLine();
                        System.out.print("Tamaño en GB: ");
                        double tamB = scanner.nextDouble();
                        scanner.nextLine();

                        Videojuego nuevoBase = new JuegoBase(idB, tituloB, desB, precioB, calB, genB, tamB);
                        if (tienda.agregarVideojuego(nuevoBase)) {
                            System.out.println("Sistema: Juego base agregado exitosamente.");
                        } else {
                            System.out.println("Error: El ID ya existe o los datos son invalidos.");
                        }
                        break;

                    case 2:
                        System.out.print("ID: ");
                        String idD = scanner.nextLine();
                        System.out.print("Titulo: ");
                        String tituloD = scanner.nextLine();
                        System.out.print("Desarrollador: ");
                        String desD = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precioD = scanner.nextDouble();
                        System.out.print("Calificacion (0-100): ");
                        double calD = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("ID del Juego Requerido: ");
                        String reqD = scanner.nextLine();
                        System.out.print("¿Es estetico? (true/false): ");
                        boolean cosD = scanner.nextBoolean();
                        scanner.nextLine();

                        Videojuego nuevoDlc = new ExpansionDLC(idD, tituloD, desD, precioD, calD, reqD, cosD);
                        if (tienda.agregarVideojuego(nuevoDlc)) {
                            System.out.println("Sistema: Expansion DLC agregada exitosamente.");
                        } else {
                            System.out.println("Error: El ID ya existe o los datos son invalidos.");
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese el ID del juego a eliminar: ");
                        String idEliminar = scanner.nextLine();
                        if (tienda.eliminarVideojuego(idEliminar)) {
                            System.out.println("Sistema: Videojuego retirado del catalogo y colecciones dependientes.");
                        } else {
                            System.out.println("Error: El ID no existe en el catalogo.");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el ID a buscar: ");
                        String idBuscar = scanner.nextLine();
                        Videojuego juegoEncontrado = tienda.obtenerVideojuego(idBuscar);
                        if (juegoEncontrado != null) {
                            juegoEncontrado.mostrarFichaTecnica();
                        } else {
                            System.out.println("Error: Videojuego no encontrado.");
                        }
                        break;

                    case 5:
                        System.out.print("ID del juego a actualizar: ");
                        String idAct = scanner.nextLine();
                        System.out.print("Nuevo Precio: ");
                        double nPre = scanner.nextDouble();
                        System.out.print("Nueva Calificacion (0-100): ");
                        double nCal = scanner.nextDouble();
                        scanner.nextLine();

                        if (tienda.actualizarPrecioYCalificacion(idAct, nPre, nCal)) {
                            System.out.println("Sistema: Datos modificados respetando el encapsulamiento.");
                        } else {
                            System.out.println("Validacion fallida o ID inexistente.");
                        }
                        break;

                    case 6:
                        System.out.println("\n--- CATALOGO ORDENADO POR ID (ORDEN NATURAL) ---");
                        tienda.mostrarCatalogoOrdenado(Videojuego::compareTo);
                        break;

                    case 7:
                        System.out.println("\n--- CATALOGO ORDENADO POR PRECIO (ASCENDENTE) ---");
                        tienda.mostrarCatalogoOrdenado(CriteriosOrden.POR_PRECIO_ASC);
                        break;

                    case 8:
                        System.out.println("\n--- CATALOGO ORDENADO POR CALIFICACION (DESCENDENTE) ---");
                        tienda.mostrarCatalogoOrdenado(CriteriosOrden.POR_CALIFICACION_DESC);
                        break;

                    case 9:
                        System.out.print("Ingrese el genero a filtrar: ");
                        String genFiltro = scanner.nextLine();
                        System.out.print("Ingrese la calificacion minima: ");
                        double calFiltro = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("\n--- RESULTADOS DEL FILTRADO CON STREAMS ---");
                        List<Videojuego> filtrados = tienda.filtrarPorGeneroYCalificacion(genFiltro, calFiltro);
                        if (filtrados.isEmpty()) {
                            System.out.println("No se encontraron coincidencias.");
                        } else {
                            filtrados.forEach(Videojuego::mostrarFichaTecnica);
                        }
                        break;

                    case 10:
                        System.out.println("\n--- ELEMENTOS EN WISHLIST (RECORRIDO CON ITERATOR) ---");
                        tienda.mostrarWishlistConIterator();
                        break;

                    case 11:
                        System.out.print("ID del juego para encolar descarga: ");
                        String idCola = scanner.nextLine();
                        Videojuego juegoCola = tienda.obtenerVideojuego(idCola);
                        if (tienda.encolarDescarga(juegoCola)) {
                            System.out.println("Sistema: Añadido a la LinkedList de descargas.");
                        } else {
                            System.out.println("Error: No se pudo encolar (juego inexistente o ya en cola).");
                        }
                        break;

                    case 12:
                        System.out.println("\nProcesando siguiente elemento en cola...");
                        Videojuego procesado = tienda.procesarSiguienteDescarga();
                        if (procesado != null) {
                            System.out.println("Descarga finalizada de: " + procesado.getTitulo());
                        } else {
                            System.out.println("Sistema: La cola de descargas esta vacia.");
                        }
                        break;

                    case 13:
                        System.out.println("Finalizando la ejecucion de la Practica 8.");
                        break;

                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                }

            } catch (InputMismatchException e) {
                System.err.println("\n[ERROR DE ENTRADA]: Se esperaba un dato numerico. Limpiando el buffer...");
                scanner.nextLine();
                opcion = 0; 
            }
        } while (opcion != 13);

        scanner.close();
    }
}
