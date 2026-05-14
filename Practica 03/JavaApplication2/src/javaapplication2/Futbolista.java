package javaapplication2;

public class Futbolista {
    private String nombre;
    private int edad;
    private double estatura;
    private String club;
    private String pais;
    private double valorMercado;
    
    public Futbolista(String nombre, int edad, double estatura, String club, String pais, double valorMercado) {
        this.nombre = nombre;
        setEdad(edad);
        setEstatura(estatura);
        this.club = club;
        this.pais = pais;
        this.valorMercado = valorMercado;
    }
    
    public Futbolista(String nombre, int edad, double estatura, String pais, double valorMercado) {
        this(nombre, edad, estatura, "Angente libre", pais, valorMercado);
    }

    public Futbolista() {
        this("Desconocido", 14, 1.20, "Angente libre", "N/A", 0.0);
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
        else {
            this.edad = 14;
            System.out.println("Edad invalida, 14 asignado por defecto");
        }
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        if (estatura>= 1.20 && estatura <= 2.50)
            this.estatura = estatura;
        else {
            this.estatura = 1.20;
            System.out.println("Estatura invalida, 1.20 asignado por defecto");
        }
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
    
    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double nuevoValor) {
    double limiteMaximo = this.valorMercado * 1.5;
    
    if (this.valorMercado == 0 || nuevoValor <= limiteMaximo) {
        this.valorMercado = nuevoValor;
    } else {
        System.out.println("El aumento de valor de mercado no puede exceder el 50%");
    }
}
    
    public void traspaso(String nuevoClub){
        this.club = nuevoClub;
    }

    public void celebrarCumpleanios(){
        setEdad(edad + 1);
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
        return "Nombre: " + nombre + " | Edad: " + edad + " | Estatura: " + estatura + " | Club: " + club + " | Pais: " + pais + " | Valor de mercado: " + valorMercado;
    }
}