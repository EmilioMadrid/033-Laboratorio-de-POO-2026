package javaapplication6;

import java.time.LocalDateTime;

public class FormatoAudioException extends StreamingException {
    private final String valorErroneo;
    private final String codigoError;
    private final LocalDateTime timestamp;

    public FormatoAudioException(String mensaje, String valorErroneo, String codigoError) {
        super(mensaje);
        this.valorErroneo = valorErroneo;
        this.codigoError = codigoError;
        this.timestamp = LocalDateTime.now();
    }

    public String getValorErroneo() { return valorErroneo; }
    public String getCodigoError() { return codigoError; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] Código: %s | Error: %s | Dato ofensivo: '%s'", 
                timestamp, codigoError, getMessage(), valorErroneo);
    }
}
