package com.gimnasio.gympos.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reserva implements Serializable {
    private static final long serialVersionUID = 3L;

    private String idReserva;
    private String idCliente;
    private String idClase;
    private LocalDateTime fechaReserva;

    public Reserva(String idReserva, String idCliente, String idClase) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idClase = idClase;
        this.fechaReserva = LocalDateTime.now();
    }

    public String getIdReserva() { return idReserva; }
    public String getIdCliente() { return idCliente; }
    public String getIdClase() { return idClase; }
    public LocalDateTime getFechaReserva() { return fechaReserva; }
}