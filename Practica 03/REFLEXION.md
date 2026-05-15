REFLEXION
Práctica 3
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Por qué marcamos atributos como `private`? ¿Qué riesgo evitamos?
Para aplicar el principio de ocultamiento de información. Evitamos que clases externas modifiquen directamente el estado interno de un objeto sin pasar por filtros de seguridad. Por ejemplo, sin el modificador `private`, cualquier clase podría poner el `valorMercado` de un jugador en números negativos, rompiendo la lógica económica del sistema.

# 2. ¿Cuál es la diferencia entre `private`, `protected` y `public`? Ilustra con un ejemplo de tu código.
Los modificadores de acceso definen la visibilidad de los componentes:
* `public`: Acceso total desde cualquier clase. Ejemplo: `public String getNombre()`.
* `private`: Acceso exclusivo dentro de la misma clase. Ejemplo: `private double estatura`.
* `protected`: Acceso permitido para clases en el mismo paquete y subclases. 
    * Ejemplo en el código: En la clase `Contrato`, el atributo `protected String clausulaRescision` permite que futuras extensiones del contrato (como un contrato juvenil) hereden y modifiquen esta cláusula directamente, pero la oculta de clases ajenas a la jerarquía.

# 3. ¿Qué validación incluiste en un setter? ¿Qué pasa si el valor recibido es inválido?
Incluí una validación de límite de aumento en el método `actualizarValorMercado`.
* Lógica: Se calcula un `limiteMaximo` equivalente al valor actual multiplicado por 1.5.
* Resultado ante valor inválido: Si el nuevo valor excede ese límite, el sistema no actualiza el atributo, mantiene el valor anterior y lanza un mensaje en consola: "El aumento de valor de mercado no puede exceder el 50%". Esto garantiza que el objeto no entre en un estado de inflación no autorizada.