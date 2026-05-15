REFLEXION
Práctica 4
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Qué ventaja concreta te dio la herencia en este ejercicio? ¿Qué código evitaste repetir?
La ventaja principal fue la reutilización de código y la extensibilidad. 
* Código evitado: Evité repetir la declaración de atributos (nombre, edad, nacionalidad, club) y métodos (getters/setters) en cada una de las tres clases hijas. Al definirlos una sola vez en `Persona` e `IntegranteClub`, las clases hijas se enfocan exclusivamente en lo que las hace únicas (goles, atajadas o puestos técnicos).

# 2. ¿Cuándo es apropiado usar `super()` y cuándo no es necesario?
* En los constructores de las clases hijas para invocar al constructor del padre y asegurar que los atributos heredados se inicialicen correctamente y tambien cuando sobrescribimos un método pero queremos conservar la lógica original y solo añadirle algo más.
* No se necesita cuando el método o atributo que queremos usar no tiene un conflicto de nombre en la clase hija (no está sobrescrito) o cuando queremos reemplazar completamente el comportamiento del padre sin usar nada de su lógica previa.

# 3. ¿Qué pasa si una clase hija no sobrescribe un método de la clase padre? ¿Cuál versión se ejecuta?
Si la clase hija no sobrescribe el método, se ejecuta la versión de la clase padre más cercana en la jerarquía hacia arriba. Por ejemplo, si `Portero` no tuviera el método `realizarAccion()`, al llamarlo se ejecutaría el código definido en `IntegranteClub` o, en su defecto, el de `Persona`. Esto garantiza que el objeto siempre tenga un comportamiento definido.