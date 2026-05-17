README
Práctica #8
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia:
Para dar cumplimiento a la especificación de diseño avanzado, se implementó una operación de búsqueda y filtrado compuesta dentro de la lógica del negocio empleando Java Streams.
Filtrar el catálogo de videojuegos combinando simultáneamente el criterio de un género específico y una calificación mínima requerida.

En una plataforma comercial real (como Steam o Epic Games), los usuarios navegan a través de catálogos masivos con miles de títulos disponibles. Permitir búsquedas aisladas por un solo parámetro resulta ineficiente.

La consulta compuesta diseñada en el método filtrarPorGeneroYCalificacion resuelve una de las interacciones de exploración más orgánicas del usuario: refinar el catálogo para aislar únicamente las experiencias de alta calidad dentro de una categoría de interés (ej. "Quiero ver solo videojuegos del género 'RPG' que tengan una calificación de la crítica mayor o igual a 90 puntos").

# 2. Instrucciones de Compilación y Ejecución
Este proyecto fue desarrollado en Apache NetBeans 9.0.

# 3. Requisitos
* JDK 8 o superior instalado.
* NetBeans IDE o cualquier editor compatible con proyectos Java.

# 4. Pasos para Ejecutar
1. Clonar o descargar los archivos `.java` dentro de la carpeta `src/javaapplication2/`.
2. Abrir el proyecto en NetBeans.
3. Hacer clic derecho sobre el proyecto y seleccionar "Clean and Build".
4. Presionar F6 o el botón de "Play" para ejecutar la clase `Main.java`.
5. Los resultados de la creación de los 5 objetos aparecerá en la ventana de output.