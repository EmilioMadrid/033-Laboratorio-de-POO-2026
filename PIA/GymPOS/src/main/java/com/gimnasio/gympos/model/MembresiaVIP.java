package com.gimnasio.gympos.model;

import java.time.LocalDate;

public class MembresiaVIP extends Membresia {
    private static final long serialVersionUID = 1L;
    private static final double VALOR_PUNTO = 5.0;
    private static final double MAX_DESCUENTO_PORCENTAJE = 0.50;

    private int puntosAcumulados;

    public MembresiaVIP(String idMembresia, LocalDate fechaInicio, double precioBase, int puntosAcumulados) {
        super(idMembresia, fechaInicio, fechaInicio.plusYears(1), precioBase);
        if (puntosAcumulados < 0) {
            throw new IllegalArgumentException("Los puntos acumulados no pueden ser negativos.");
        }
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public double calcularCostoRenovacion() {
        double descuentoPotencial = puntosAcumulados * VALOR_PUNTO;
        double topeMaximo = getPrecioBase() * MAX_DESCUENTO_PORCENTAJE;
        double descuentoFinal = Math.min(descuentoPotencial, topeMaximo);
        return getPrecioBase() - descuentoFinal;
    }

    @Override
    public boolean tieneAccesoAClasesGrupales() {
        return true;
    }

    public void acumularPuntos(int puntos) {
        if (puntos < 0) {
            throw new IllegalArgumentException("No se pueden acumular puntos negativos.");
        }
        this.puntosAcumulados += puntos;
    }

    public void restablecerPuntos() {
        this.puntosAcumulados = 0;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        if (puntosAcumulados < 0) {
            throw new IllegalArgumentException("Los puntos acumulados no pueden ser negativos.");
        }
        this.puntosAcumulados = puntosAcumulados;
    }
}