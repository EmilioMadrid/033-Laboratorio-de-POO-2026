package javaapplication2;

public class Persona {
    private String nombre;
    private int edad;
    private String nacionalidad;

    public Persona(String nombre, int edad, String pais) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String pais) {
        this.nacionalidad = pais;
    }
    
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad);
    }
    
    public void realizarAccion() {
        System.out.println(nombre + " está realizando una actividad.");
    }
    
    public void trabajar() {
        System.out.println(nombre + " está trabajando.");
    }
    
}
