README
Práctica #4
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

# 1. Elemento de Decisión Propia:
El proyecto modela la estructura de un club de fútbol profesional. Se utiliza una jerarquía de tres niveles para organizar a los integrantes del club según sus responsabilidades, permitiendo un manejo centralizado de la nómina y acciones específicas por cada rol.

# 2. Estructura de la Jerarquía:
* Persona: Clase base con datos biográficos.
* IntegranteClub: Clase intermedia que añade contexto laboral y de contrato.
* Clases Hijas (Portero, Delantero, CuerpoTecnico): Clases concretas con atributos y comportamientos especializados.

# 3. Situación Crítica de Diseño:
Problema: Inicialmente se planteó usar ArrayList<Persona>, pero esto generaba un acoplamiento débil. Semánticamente, una nómina no debe aceptar cualquier "Persona" (como un civil o turista), sino solo aquellos con un vínculo contractual formal. Además, obligaba al uso de instanceof y casting manual para acceder a datos del contrato.

Resolución: Se refinó el diseño utilizando ArrayList<IntegranteClub>.

Mitigación: Con este cambio se asegura la coherencia del dominio (solo entran sujetos con club y contrato) y se optimiza el acceso a métodos laborales sin perder el polimorfismo para las clases hijas (Portero, Delantero, CuerpoTecnico).

# 4. Instrucciones de Compilación y Ejecución
Este proyecto fue desarrollado en Apache NetBeans 9.0.

# 5. Requisitos
* JDK 8 o superior instalado.
* NetBeans IDE o cualquier editor compatible con proyectos Java.

# 6. Pasos para Ejecutar
	1. Clonar o descargar los archivos `.java` dentro de la carpeta `src/javaapplication2/`.
	2. Abrir el proyecto en NetBeans.
	3. Hacer clic derecho sobre el proyecto y seleccionar "Clean and Build".
	4. Presionar F6 o el botón de "Play" para ejecutar la clase `Main.java`.
	5. Los resultados de la creación de los 5 objetos aparecerá en la ventana de output.