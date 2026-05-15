README
Práctica #5
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia: Patrón Template Method
En la clase abstracta `Deportista`, se implementó el método concreto `mostrarReciboPago()`. Este método funciona bajo el patrón de diseño Template Method.

# 2. ¿Qué hace este método?
Define el "esqueleto" o flujo de pasos necesarios para generar un recibo de pago: 
1. Imprime el encabezado.
2. Recupera el nombre (llamada a método abstracto `getNombre`).
3. Recupera la posición (llamada a método abstracto `getPosicion`).
4. Muestra el total neto.

# 3. ¿Por qué se diseñó así?
Se diseñó para garantizar que el proceso de nómina sea uniforme. Al marcarlo como `final`, ninguna clase hija puede alterar el orden de los pasos o saltarse la impresión de datos.

# 4. ¿Qué ventaja ofrece sobre delegar la lógica a las hijas?
* Evita la duplicación de código: No hace falta escribir la lógica de impresión de recibos en cada una de las 3 clases concretas.
* Facilita el mantenimiento: Si el formato del recibo cambia, solo se modifica en la clase padre y el cambio se refleja en todo el club automáticamente.
* Control de flujo: Obliga a que la lógica de negocio siga un estándar, delegando a las hijas solo la "especialización" del cálculo matemático, pero no la estructura del proceso.

# 5. Instrucciones de Compilación y Ejecución
Este proyecto fue desarrollado en Apache NetBeans 9.0.

# 6. Requisitos
* JDK 8 o superior instalado.
* NetBeans IDE o cualquier editor compatible con proyectos Java.

# 7. Pasos para Ejecutar
1. Clonar o descargar los archivos `.java` dentro de la carpeta `src/javaapplication2/`.
2. Abrir el proyecto en NetBeans.
3. Hacer clic derecho sobre el proyecto y seleccionar "Clean and Build".
4. Presionar F6 o el botón de "Play" para ejecutar la clase `Main.java`.
5. Los resultados de la creación de los 5 objetos aparecerá en la ventana de output.