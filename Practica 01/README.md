README
Práctica #1
Estudiante: Emilio Hernandez Madrid  
Matrícula: 2086253

## 1. Proceso de Instalación y Configuración
Para el desarrollo de esta práctica, se configuró un entorno de desarrollo basado en software de libre distribución:

* Apache NetBeans 9.0: Decidí instalar esta version debido a que NetBeans 8.0 actualmente ya no cuenta con fuentes de descarga oficial.
* JDK 11: Instalado como motor principal del IDE para garantizar la compatibilidad con NetBeans 9.0.
* Apache Tomcat 9.0: Vinculado exitosamente en la sección de servicios del IDE para el despliegue de aplicaciones futuras.

## 2. Elemento de Decisión Propia: Calculadora de Máscara de Subred
Decidí implementar un conversor de prefijo CIDR a formato decimal punteado. Mostrando la mascara por default equivalente al prefijo ingresado por el usuario.

Justificación:
Elegí este programa ya que actualmente me encuentro realizando simulaciones de redes en Cisco Packet Tracer, donde la división de subredes es fundamental es algo que se realiza constantemente. Contar con una herramienta propia que automatice la obtención de máscaras de subred facilita la configuración de routers y switches en mis proyectos.