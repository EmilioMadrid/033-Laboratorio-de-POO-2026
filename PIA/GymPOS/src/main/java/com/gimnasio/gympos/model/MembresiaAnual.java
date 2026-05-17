package com.gimnasio.gympos.model;

import java.time.LocalDate;

public class MembresiaAnual extends Membresia {
    private static final long serialVersionUID = 1L;
    private static final double DESCUENTO_ANUAL = 0.15;

    public MembresiaAnual(String idMembresia, LocalDate fechaInicio, double precioBaseMensual) {
        super(idMembresia, fechaInicio, fechaInicio.plusYears(1), precioBaseMensual);
    }

    @Override
    public double calcularCostoRenovacion() {
        return (getPrecioBase() * 12) * (1.0 - DESCUENTO_ANUAL);
    }

    @Override
    public boolean tieneAccesoAClasesGrupales() {
        return true;
    }
}
