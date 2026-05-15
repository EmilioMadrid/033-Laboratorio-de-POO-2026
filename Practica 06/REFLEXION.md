REFLEXION
Práctica 6
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Cuándo preferirías una clase abstracta sobre una interfaz? ¿Y al revés?
* Clase Abstracta: Se prefiere cuando existe una relación de identidad fuerte. Es ideal cuando quieres compartir código y atributos (como `id` o `titulo`) entre clases estrechamente relacionadas.
* Interfaz: Se prefiere cuando quieres definir una "capacidad" o "contrato" que pueden compartir clases que no necesariamente están relacionadas por su identidad. Por ejemplo, tanto una `Cancion` como un `Video` podrían ser `IDescargable`, aunque pertenezcan a jerarquías distintas.

# 2. ¿Una clase puede implementar varias interfaces? ¿Por qué Java permite eso pero no la herencia múltiple de clases?
Sí, Java permite implementar múltiples interfaces. No permite la herencia múltiple de clases para evitar que si una clase hereda de dos padres que tienen un método con el mismo nombre y código distinto, el compilador no sabría cuál ejecutar. Las interfaces evitan esto porque, originalmente, no contenían implementación, solo definían que hacer, dejando el "cómo" a la clase concreta.

# 3. Si agregas un método nuevo a una de tus interfaces, ¿qué clases se ven afectadas? ¿Cómo lo resolverías con un método `default`?
Se ven afectadas todas las clases que implementen esa interfaz, ya que quedarían obligadas a implementar el nuevo método, de lo contrario, el código no compilará.
Para resolverlo sin romper las clases existentes, se usa la palabra reservada `default` en la interfaz. Esto permite proporcionar una implementación estándar dentro de la interfaz, haciendo que el nuevo método sea opcional para las clases que ya la usaban.