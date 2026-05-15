README
Práctica #3
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia: Reglas No Triviales
Se implementaron dos reglas de validación lógica dentro de los métodos setter para evitar estados inconsistentes en el dominio:

* Validación de Rango de edad y estatura: 
    Regla: La edad debe estar entre 14 y 100 años; la estatura entre 1.20m y 2.50m.
    Justificación: Asegura que los datos de la persona sean físicamente posibles y cumplan con la normativa legal de contratación juvenil. Si el valor es inválido, el sistema asigna el límite mínimo por defecto y notifica el error.
* Control de Inflación (Aumento de Valor de Mercado):
    Regla: El método `actualizarValorMercado` rechaza cualquier incremento que supere el 50% del valor actual del jugador.
    Justificación: Simula un control de "Fair Play Financiero", evitando que errores de entrada de datos o intentos de manipulación inflen artificialmente el costo de un activo del club.

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