package com.gimnasio.gympos.service;

import com.gimnasio.gympos.model.Cliente;
import com.gimnasio.gympos.util.PersistenciaUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BackupService implements Runnable {

    private final ClienteService clienteService;
    private final String carpetaBackup;
    private final long intervaloMiliseconds;
    private final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");

    public BackupService(ClienteService clienteService, String carpetaBackup, long intervaloMinutes) {
        this.clienteService = clienteService;
        this.carpetaBackup = carpetaBackup;
        this.intervaloMiliseconds = intervaloMinutes * 60 * 1000;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(intervaloMiliseconds);

                Map<String, Cliente> snapshot = new HashMap<>();
                for (Cliente cliente : clienteService.obtenerTodos()) {
                    snapshot.put(cliente.getIdCliente(), cliente);
                }

                if (!snapshot.isEmpty()) {
                    String marcaTiempo = LocalDateTime.now().format(formateador);
                    String rutaDinamica = carpetaBackup + "/backup_" + marcaTiempo + ".dat";
                    PersistenciaUtil.guardarClientes(snapshot, rutaDinamica);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}