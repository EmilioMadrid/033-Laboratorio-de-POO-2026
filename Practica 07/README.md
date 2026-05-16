README
Práctica #7
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia: Patrón Template Method
Este módulo extiende el sistema de streaming de audio mediante una arquitectura tolerante a fallos. Se implementó una jerarquía de excepciones personalizadas, un sistema de logging persistente mediante la API nativa de Java (`java.util.logging`) y el aseguramiento en la liberación de descriptores de archivos usando la estructura `try-with-resources`.

la excepción `FormatoAudioException` fue dotada de un estado robusto que transporta información de contexto adicional a través de tres atributos específicos:
* valorErroneo (String): El dato exacto o bandera de entrada que provocó el fallo (ej. "NULL_OR_EMPTY").
* codigoError (String): Un identificador alfanumérico interno único (ej. "ERR-AUDIO-001").
* timestamp (LocalDateTime): La estampa de tiempo con precisión de nanosegundos del momento de la falla.

Aplicación en un Sistema Real para Diagnóstico:
* El codigoError permite automatizar alertas. Si el sistema detecta que el código "ERR-AUDIO-001" se dispara un gran numero de veces en un periodo esspecifico, puede levantar un ticket de soporte crítico de forma automática.
* El timestamp exacto permite cruzar la falla con otros eventos del ecosistema, como caídas de red o picos de uso de CPU, aislando si el error fue del software o de la infraestructura.
* Al capturar el valorErroneo, el equipo puede replicar exactamente el flujo que rompió el sistema sin necesidad de adivinar las entradas del usuario.

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