package com.gimnasio.gympos.model;

import java.time.LocalDate;

public class MembresiaMensual extends Membresia {
    private static final long serialVersionUID = 1L;

    public MembresiaMensual(String idMembresia, LocalDate fechaInicio, double precioBase) {
        super(idMembresia, fechaInicio, fechaInicio.plusMonths(1), precioBase);
    }

    @Override
    public double calcularCostoRenovacion() {
        return getPrecioBase();
    }

    @Override
    public boolean tieneAccesoAClasesGrupales() {
        return false;
    }
}