package com.gimnasio.gympos.service;

import com.gimnasio.gympos.exception.AccesoDenegadoException;
import com.gimnasio.gympos.exception.ClaseLlenaException;
import com.gimnasio.gympos.model.ClaseGrupal;
import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.model.Reserva;
import com.gimnasio.gympos.util.PersistenciaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClaseService {

    private final Map<String, ClaseGrupal> clases;
    private final Map<String, Reserva> reservas;

    public ClaseService() {
        this.clases = new ConcurrentHashMap<>();
        this.reservas = new ConcurrentHashMap<>();
        cargarDatosInicialesDemo();
    }

    private void cargarDatosInicialesDemo() {
        clases.put("C01", new ClaseGrupal("C01", "Spinning", "Entrenador Alan", "08:00 AM", 2));
        clases.put("C02", new ClaseGrupal("C02", "Yoga", "Entrenadora Sofia", "10:00 AM", 15));
        clases.put("C03", new ClaseGrupal("C03", "Crossfit", "Entrenador Carlos", "07:00 PM", 1));
    }

    public List<ClaseGrupal> obtenerTodasLasClases() {
        return new ArrayList<>(clases.values());
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return new ArrayList<>(reservas.values());
    }

    public void agendarReserva(Cliente cliente, String idClase) throws ClaseLlenaException, AccesoDenegadoException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (!cliente.getMembresia().tieneAccesoAClasesGrupales()) {
            throw new AccesoDenegadoException("El cliente " + cliente.getNombre() + " no tiene acceso a clases grupales con su membresía actual.");
        }

        ClaseGrupal clase = clases.get(idClase);
        if (clase == null) {
            throw new IllegalArgumentException("La clase grupal seleccionada no existe.");
        }

        synchronized (clase) {
            if (clase.estaLlena()) {
                throw new ClaseLlenaException("No se pudo agendar: La clase '" + clase.getNombre() + "' ha alcanzado su cupo máximo.");
            }

            clase.inscribirCliente(cliente.getIdCliente());

            String idReserva = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Reserva nuevaReserva = new Reserva(idReserva, cliente.getIdCliente(), idClase);
            reservas.put(idReserva, nuevaReserva);
        }
    }

    public void cancelarReserva(String idReserva) {
        Reserva reserva = reservas.remove(idReserva);
        if (reserva != null) {
            ClaseGrupal clase = clases.get(reserva.getIdClase());
            if (clase != null) {
                synchronized (clase) {
                    clase.deDarBajaCliente(reserva.getIdCliente());
                }
            }
        }
    }
}