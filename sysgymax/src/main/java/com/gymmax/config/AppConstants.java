package com.gymmax.config;

/**
 * Constantes globales del sistema GymMax.
 */
public class AppConstants {
    public static final String ROL_SOCIO = "SOCIO";
    public static final String ROL_ADMIN = "ADMIN";

    public static final String SESS_USUARIO = "usuarioLogueado";
    public static final String SESS_SOCIO = "socioLogueado";

    public static final String ESTADO_RESERVA_CONFIRMADA = "CONF";
    public static final String ESTADO_RESERVA_CANCELADA = "CAN";
    public static final String ESTADO_RESERVA_LISTAESPERA = "LE";

    public static final String ESTADO_MEMBRESIA_ACTIVA = "ACT";
    public static final String ESTADO_MEMBRESIA_VENCIDA = "VEN";
    public static final String ESTADO_MEMBRESIA_SUSPENDIDA = "SUS";

    public static final String ESTADO_PAGO_OK = "OK";
    public static final String ESTADO_PAGO_FAIL = "FAIL";
    public static final String ESTADO_PAGO_REEMBOLSADO = "REF";

    private AppConstants() {}
}
