package javaapplication8;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class JavaApplication8 {
    
    public static void main(String[] args) {
        TiendaVideojuegos tienda = new TiendaVideojuegos();
        
        precargarDatos(tienda);
        
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
    
    private static void precargarDatos(TiendaVideojuegos tienda) {
    Videojuego j1 = new JuegoBase("JB-01", "The Witcher 3", "CD Projekt Red", 39.99, 93.0, "RPG", 50.0);
    Videojuego j2 = new JuegoBase("JB-02", "Cyberpunk 2077", "CD Projekt Red", 59.99, 86.0, "RPG", 70.0);
    Videojuego j3 = new JuegoBase("JB-03", "Elden Ring", "FromSoftware", 59.99, 96.0, "Action RPG", 60.0);
    Videojuego j4 = new JuegoBase("JB-04", "Hades", "Supergiant Games", 24.99, 93.0, "Roguelike", 15.0);
    Videojuego j5 = new JuegoBase("JB-05", "Doom Eternal", "id Software", 19.99, 88.0, "FPS", 80.0);
    Videojuego j6 = new JuegoBase("JB-06", "Hollow Knight", "Team Cherry", 14.99, 90.0, "Metroidvania", 9.0);
    Videojuego j7 = new JuegoBase("JB-07", "Red Dead Redemption 2", "Rockstar Games", 59.99, 97.0, "Accion", 120.0);
    Videojuego j8 = new JuegoBase("JB-08", "Grand Theft Auto V", "Rockstar Games", 29.99, 96.0, "Accion", 100.0);
    Videojuego j9 = new JuegoBase("JB-09", "Minecraft", "Mojang", 26.95, 93.0, "Sandbox", 4.0);
    Videojuego j10 = new JuegoBase("JB-10", "Stardew Valley", "ConcernedApe", 14.99, 89.0, "Simulacion", 2.0);

    Videojuego d1 = new ExpansionDLC("DLC-01", "Blood and Wine", "CD Projekt Red", 19.99, 92.0, "JB-01", false);
    Videojuego d2 = new ExpansionDLC("DLC-02", "Phantom Liberty", "CD Projekt Red", 29.99, 89.0, "JB-02", false);
    Videojuego d3 = new ExpansionDLC("DLC-03", "Shadow of the Erdtree", "FromSoftware", 34.99, 95.0, "JB-03", false);
    Videojuego d4 = new ExpansionDLC("DLC-04", "Doom Skin Pack", "id Software", 4.99, 60.0, "JB-05", true);
    Videojuego d5 = new ExpansionDLC("DLC-05", "Stardew OST", "ConcernedApe", 4.99, 99.0, "JB-10", true);

    tienda.agregarVideojuego(j1);
    tienda.agregarVideojuego(j2);
    tienda.agregarVideojuego(j3);
    tienda.agregarVideojuego(j4);
    tienda.agregarVideojuego(j5);
    tienda.agregarVideojuego(j6);
    tienda.agregarVideojuego(j7);
    tienda.agregarVideojuego(j8);
    tienda.agregarVideojuego(j9);
    tienda.agregarVideojuego(j10);
    tienda.agregarVideojuego(d1);
    tienda.agregarVideojuego(d2);
    tienda.agregarVideojuego(d3);
    tienda.agregarVideojuego(d4);
    tienda.agregarVideojuego(d5);

    tienda.encolarDescarga(j3);
    tienda.encolarDescarga(j6);
    tienda.encolarDescarga(d2);
    }
}
