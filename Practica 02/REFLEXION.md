REFLEXION
Práctica 2
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. ¿Cuál es la diferencia entre una clase y un objeto? Da un ejemplo con tu propio código.
Una clase es el plano o plantilla (el concepto abstracto), mientras que el objeto es la instancia física y real de esa clase que ocupa un lugar en la memoria.
Ejemplo: La clase 'Futbolista' define que todos los jugadores deben tener un nombre, edad y otros atributos. El objeto 'f1' es la representación real de "Nahuel Guzmán" con 38 años. Sin la clase, no sabríamos cómo crear al jugador; sin el objeto, la clase es solo código sin datos reales de algún jugador.

# 2. ¿Por qué usaste 3 constructores distintos? ¿Qué problema resuelve cada uno?
El uso de múltiples constructores ofrece flexibilidad al crear objetos según los datos disponibles:
	1.  Constructor Completo: Resuelve el problema de la integridad de datos. Se usa cuando conocemos toda la información del jugador y queremos aplicar validaciones de seguridad desde el nacimiento del objeto.
	2.  Constructor Parcial (Agente libre): Resuelve el problema de la falta de datos técnicos. Permite registrar a un jugador aunque aún no firme un nuevo contrato.
	3.  Constructor Vacío: Resuelve la necesidad de inicializar objetos genéricos o de "relleno" que serán editados posteriormente.

# 3. ¿Qué pasaría si no tuvieras constructores definidos? ¿Java sigue funcionando? ¿Por qué?
Sí, Java sigue funcionando. Si no defines ningún constructor, el compilador de Java crea automáticamente un constructor por defecto.
Sin embargo, esto es limitado porque:
* No podrías obligar al objeto a recibir datos obligatorios al momento de crearse.
* Todos los atributos se inicializarían en valores nulos o ceros, perdiendo la oportunidad de aplicar validaciones inmediatas como la implementada para la edad y estatura.