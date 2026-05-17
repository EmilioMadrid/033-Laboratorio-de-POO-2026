package com.gimnasio.gympos.model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Membresia implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioBase;

    public Membresia(String idMembresia, LocalDate fechaInicio, LocalDate fechaFin, double precioBase) {
        this.idMembresia = idMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        validarPrecio(precioBase);
        this.precioBase = precioBase;
    }

    public abstract double calcularCostoRenovacion();
    public abstract boolean tieneAccesoAClasesGrupales();

    private void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo.");
        }
    }

    public String getIdMembresia() { return idMembresia; }
    public void setIdMembresia(String idMembresia) { this.idMembresia = idMembresia; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) {
        validarPrecio(precioBase);
        this.precioBase = precioBase;
    }
}