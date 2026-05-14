package javaapplication2;

public class Futbolista {
    private String nombre;
    private int edad;
    private double estatura;
    private String club;
    private String pais;
    
    public Futbolista(String nombre, int edad, double estatura, String club, String pais) {
        this.nombre = nombre;
        setEdad(edad);
        setEstatura(estatura);
        this.club = club;
        this.pais = pais;
    }
    
    public Futbolista(String nombre, int edad, double estatura, String pais) {
        this(nombre, edad, estatura, "Angente libre", pais);
    }

    public Futbolista() {
        this.nombre = "Desconocido";
        this.edad = 0;
        this.estatura = 0.0;
        this.club = "Agente libre";
        this.pais = "N/A";
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
        if (edad >= 14 && edad <= 100)
            this.edad = edad;
        else
            System.out.println("Edad invalida");
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        if (estatura>= 1.20 && estatura <= 2.50)
            this.estatura = estatura;
        else
            System.out.println("Estatura invalida");
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public void traspaso(String nuevoClub){
        this.club = nuevoClub;
    }

    public void celebrarCumpleanios(){
        setEdad(edad + 1);
    }

    public void imprimirFichaTecnica(){
        System.out.println(this.toString());
    }

    public boolean verificarRetiro(){
        if (edad >= 50)
            return true;
        else
            return false;
    }

    public void aniadirNacionalidad(String nuevaNacionalidad){
        this.pais += ", " +nuevaNacionalidad;
    }
    
    @Override public String toString(){
        return "Nombre: " + nombre + " | Edad: " + edad + " | Estatura: " + estatura + " | Club: " + club + " | Pais: " + pais;
    }
}