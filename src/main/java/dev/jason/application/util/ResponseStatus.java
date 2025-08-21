package dev.jason.application.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    SUCCESS(200, "SUCCESS", "Tipo de cambio para el cliente con DNI: %s es: S/%.3f"),
    ERROR(429, "ERROR", "El cliente con DNI: %s superó el límite de cotizaciones"),
    BAD_REQUEST(400, "BAD_REQUEST", "");

    private final int code;
    private final String status;
    private final String messageTemplate;

    public String formatMessage(Object... args) {
        return String.format(messageTemplate, args);
    }
}
