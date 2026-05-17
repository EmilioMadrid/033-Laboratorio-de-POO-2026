package com.gimnasio.gympos.exception;

public class ClienteDuplicadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }
}