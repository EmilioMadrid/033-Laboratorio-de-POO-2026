REFLEXION
Práctica 8
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Por qué usaste cada estructura de colección para cada tipo de dato? ¿Qué pasaría si usaras `ArrayList` para todo?
Cada estructura de colección fue seleccionada estratégicamente con base en el análisis de rendimiento de sus operaciones fundamentales:
* `HashMap` (Catálogo Principal): Se utilizó para indexar los videojuegos por su ID único. Nos permite realizar búsquedas, inserciones y eliminaciones en un tiempo constante de O(1), ya que calcula la posición de almacenamiento mediante funciones hash en lugar de iterar la colección.
* `HashSet` (Wishlist): Se empleó para la lista de deseos debido a su propiedad nativa de unicidad. Basado en el contrato de los métodos `equals` y `hashCode`, impide que un usuario guarde el mismo videojuego múltiples veces, resolviendo la duplicación en tiempo O(1) sin requerir validaciones lógicas manuales.
* `LinkedList` (Cola de Descargas): Se eligió para modelar la cola de instalación debido a su comportamiento secuencial FIFO (First In, First Out). Al estar compuesta por nodos interconectados, permite insertar al final (`addLast`) y remover al inicio (`removeFirst`) de forma eficiente en O(1), ya que solo manipula punteros de memoria y no desplaza elementos indexados.

# ¿Qué pasaría si usáramos `ArrayList` para todo?
Si utilizáramos `ArrayList` de manera indiscriminada para todas las funciones de la tienda, el sistema sufriría una degradación severa de rendimiento a medida que el volumen de datos creciera:
* Las búsquedas por ID o las comprobaciones de duplicados en la Wishlist pasarían de una complejidad de O(1) a una complejidad lineal O(n), obligando al procesador a ejecutar ciclos `for` completos para comprobar elemento por elemento.
* Las operaciones de eliminación en el catálogo o de procesamiento en la cola de descargas requerirían desplazar en memoria todos los elementos posteriores al nodo afectado, transformando una operación instantánea en un proceso costoso de O(n).

# 2. ¿Qué diferencia hay entre `Comparable` y `Comparator`? ¿Cuándo usarías cada uno?

* `Comparable`: Es una interfaz intrínseca al dominio de la clase. Define el ordenamiento natural del objeto mediante el método `compareTo`. Modifica la estructura de la clase para que esta sepa cómo compararse a sí misma frente a otra instancia del mismo tipo. Se utiliza cuando el criterio de ordenamiento es único, permanente y define la identidad por defecto del objeto (en nuestro caso, ordenar alfabéticamente por el campo `id`).
* `Comparator`: Es una interfaz extrínseca que define un criterio de ordenamiento independiente y externo a la clase de dominio. A través de su método `compare`, permite establecer múltiples lógicas de ordenación bajo demanda sin alterar el código fuente del objeto original. Se utiliza cuando el negocio exige flexibilidad para organizar los datos bajo diferentes contextos variables (por ejemplo, ordenar dinámicamente por `precio` de menor a mayor o por `calificacion` de forma descendente según la interacción del usuario en la interfaz).

# 3. Explica con tus palabras qué hace una operación Stream. ¿Por qué es más legible que un bucle `for`?

Una operación Stream en Java es una secuencia de elementos que fluye sobre un canal de procesamiento de datos proveniente de una fuente (como una colección), permitiendo aplicar transformaciones algorítmicas de manera encadenada a través de un pipeline. Los Streams no almacenan los datos en memoria por sí mismos ni modifican la colección original; simplemente operan sobre el flujo mediante operaciones intermedias (filtros, transformaciones, ordenamientos) y operaciones terminales (reducciones, recolecciones).

# ¿Por qué es más legible que un bucle `for`?
Un bucle `for` tradicional sigue el paradigma imperativo, lo que significa que el programador debe especificar minuciosamente cómo se debe realizar la operación: declarar variables de control, manejar los índices, gestionar banderas de estado y estructurar condicionales anidados dentro del bucle. Esto genera un código denso, propenso a errores de desbordamiento (off-by-one) y difícil de mantener.

Por el contrario, la API de Streams adopta el paradigma declarativo. En lugar de describir los pasos mecánicos de la máquina, el código expresa directamente qué resultado se desea obtener. Mediante abstracciones semánticas como `.filter()` o `.sorted()`, la lectura del código se asemeja al lenguaje natural o al álgebra relacional, ocultando la complejidad subyacente de la iteración y facilitando la intención técnica del algoritmo a simple vista.