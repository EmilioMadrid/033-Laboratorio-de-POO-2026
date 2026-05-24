package com.gimnasio.gympos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClaseGrupal implements Serializable {
    private static final long serialVersionUID = 2L;

    private String idClase;
    private String nombre;
    private String entrenador;
    private String horario;
    private int cupoMaximo;
    private List<String> idsClientesInscritos;

    public ClaseGrupal(String idClase, String nombre, String entrenador, String horario, int cupoMaximo) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
        this.idsClientesInscritos = new ArrayList<>();
    }

    public String getIdClase() { return idClase; }
    public String getNombre() { return nombre; }
    public String getEntrenador() { return entrenador; }
    public String getHorario() { return horario; }
    public int getCupoMaximo() { return cupoMaximo; }
    public List<String> getIdsClientesInscritos() { return idsClientesInscritos; }

    public int getLugaresDisponibles() {
        return cupoMaximo - idsClientesInscritos.size();
    }

    public boolean estaLlena() {
        return idsClientesInscritos.size() >= cupoMaximo;
    }

    public void inscribirCliente(String idCliente) {
        if (!idsClientesInscritos.contains(idCliente)) {
            idsClientesInscritos.add(idCliente);
        }
    }

    public void deDarBajaCliente(String idCliente) {
        idsClientesInscritos.remove(idCliente);
    }
}