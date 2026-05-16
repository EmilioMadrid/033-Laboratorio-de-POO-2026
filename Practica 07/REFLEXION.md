REFLEXION
Práctica 7
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Cuál es la diferencia entre una excepción chequeada (checked) y una no chequeada (unchecked)?
* Excepciones Chequeadas: Son aquellas que heredan directamente de `Exception`. El compilador de Java obliga explícitamente al desarrollador a manejarlas o a delegarlas. Se utilizan para condiciones de error recuperables y fuera del control directo del programa, como fallas en el sistema de archivos o caídas de red.
* Excepciones No Chequeadas: Son aquellas que heredan de `RuntimeException`. El compilador no verifica si se han manejado o declarado. Generalmente representan errores de lógica de programación (bugs), tales como un `NullPointerException` o `ArrayIndexOutOfBoundsException`, los cuales deberían ser prevenidos mediante condiciones lógicas en lugar de capturarse.

# 2. ¿Por qué creaste una jerarquía de excepciones en lugar de usar `Exception` directamente?
Usar la clase genérica `Exception` degrada la semántica del código y genera capturas ambiguas. Crear una jerarquía personalizada aporta dos ventajas fundamentales:
* Especificación del Catch: Permite al programa reaccionar de manera diferenciada para cada escenario de falla. No se debe tomar la misma acción ante un problema de seguridad (`AccesoNoAutorizadoException`) que ante un elemento inexistente (`RecursoNoEncontradoException`).
* Autodocumentación de la Arquitectura: El código se vuelve legible por sí mismo. Cualquier desarrollador que analice la firma de los métodos del negocio entiende instantáneamente cuáles son las reglas y los riesgos operacionales del dominio de streaming sin tener que inspeccionar la lógica interna.

# 3. ¿Qué ventaja tiene `try-with-resources` sobre un bloque `finally` tradicional?
La ventaja primordial es la mitigación de fugas de recursos mediante la automatización segura. 
En un bloque `finally` tradicional, el programador debe invocar manualmente el método `.close()`, lo que obliga a añadir validaciones de nulidad y envolver dicha llamada en otro bloque `try-catch` anidado, lo que genera código redundante y propenso a errores. 

`try-with-resources` garantiza que cualquier recurso que implemente la interfaz `AutoCloseable` se cerrará de manera implícita al salir del bloque `try`, independientemente de si el flujo terminó con éxito o interrupción catastrófica. Además, maneja correctamente las excepciones suprimidas, asegurando que la excepción del negocio no sea enmascarada por un fallo secundario durante el cierre del archivo.