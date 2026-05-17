package com.gimnasio.gympos.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idCliente;
    private String nombre;
    private String telefono;
    private Membresia membresia;

    public Cliente(String idCliente, String nombre, String telefono, Membresia membresia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.membresia = membresia;
    }

    public double obtenerCostoDeRenovacion() {
        if (this.membresia == null) {
            throw new IllegalStateException("El cliente no tiene una membresía asignada.");
        }
        return this.membresia.calcularCostoRenovacion();
    }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }
}