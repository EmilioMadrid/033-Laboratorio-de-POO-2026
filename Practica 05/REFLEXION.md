REFLEXION
Práctica 5
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Qué diferencia hay entre sobrescritura (override) y sobrecarga (overload)? Da un ejemplo de cada una desde tu código.
* Sobrescritura: Ocurre cuando una clase hija redefine un método que ya existe en su clase padre para darle un comportamiento específico. 
  * Ejemplo el método `realizarAccion()` es abstracto en `Deportista` y cada posición (Portero, Delantero) lo sobrescribe para atajar un balón o rematar a gol.
* Sobrecarga: Ocurre dentro de la misma clase cuando se definen varios métodos con el mismo nombre pero diferentes parámetros.
  * Ejemplo el método `entrenar()`, `entrenar(int repeticiones)` y `entrenar(String intensidad, int min)` en la clase `Portero`.

# 2. ¿Por qué usaste =instanceof= antes de hacer un cast? ¿Qué excepción previene?
Se utilizó `instanceof` para verificar dinámicamente el tipo real del objeto antes de intentar la conversión. En un array de tipo `Deportista`, no todos los elementos son Porteros. Intentar tratar a un `Delantero` como un `Portero` sin verificarlo causaría una error el cual detendría el programa abruptamente.

# 3. ¿Podrías instanciar tu clase abstracta directamente? ¿Por qué sí o por qué no?
No. Por definición de Java, una clase marcada como `abstract` no puede ser instanciada.
Porque las clases abstractas representan conceptos incompletos. En el dominio del fútbol, no existe una entidad física que sea "solo un deportista" sin tener una posición o rol; siempre debe ser un Portero, un Delantero, etc. La abstracción sirve para heredar estructura, no para crear objetos genéricos.