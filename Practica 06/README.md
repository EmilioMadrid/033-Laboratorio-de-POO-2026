README
Práctica #6
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia: Patrón Template Method
Para este dominio, se diseñaron combinaciones de interfaces que reflejan el comportamiento real de los objetos musicales. No todos los contenidos tienen las mismas capacidades técnicas o comerciales.

# 2. Combinaciones por Clase:
1. `Cancion` (Streaming):
   Interfaces: `IReproducible`, `IDescargable`, `IVendible`.
   Justificación: Es el contenido más completo. El usuario puede escucharlo, descargarlo para modo offline y representa una unidad con valor de costo.

2. `Podcast`:
    Interfaces: `IReproducible`, `IDescargable`.
    Justificación: Los podcasts se pueden escuchar y descargar, pero en este modelo de negocio son gratuitos (no implementa `IVendible`), diferenciándose así de una canción comercial.

3. `Radio`:
   Interfaces: `IReproducible`.
   Justificación: Una emisora de radio es un flujo en vivo. Por su naturaleza, no se puede "descargar" un stream infinito ni se paga una licencia por sintonizar una frecuencia abierta.

# 3. Instrucciones de Compilación y Ejecución
Este proyecto fue desarrollado en Apache NetBeans 9.0.

# 4. Requisitos
* JDK 8 o superior instalado.
* NetBeans IDE o cualquier editor compatible con proyectos Java.

# 5. Pasos para Ejecutar
1. Clonar o descargar los archivos `.java` dentro de la carpeta `src/javaapplication2/`.
2. Abrir el proyecto en NetBeans.
3. Hacer clic derecho sobre el proyecto y seleccionar "Clean and Build".
4. Presionar F6 o el botón de "Play" para ejecutar la clase `Main.java`.
5. Los resultados de la creación de los 5 objetos aparecerá en la ventana de output.