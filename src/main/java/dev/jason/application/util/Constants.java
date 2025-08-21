package dev.jason.application.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Constants {

    public static final String DNI_NULL_MESSAGE = "El DNI no puede ser nulo";
    public static final String DNI_REGEX = "\\d{8}";
    public static final String DNI_REGEX_MESSAGE = "El DNI debe tener 8 dígitos";
}
