package com.gimnasio.gympos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idVenta;
    private LocalDateTime fechaHora;
    private String idCliente;
    private List<DetalleVenta> detalles;
    private double total;

    public Venta(String idVenta, String idCliente, List<DetalleVenta> detalles) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.detalles = detalles;
        this.fechaHora = LocalDateTime.now();
        this.total = calcularTotalVenta();
    }

    private double calcularTotalVenta() {
        if (detalles == null) return 0.0;
        return detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
    }

    public String getIdVenta() {
        return idVenta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }
}