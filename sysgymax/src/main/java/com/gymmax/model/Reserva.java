package com.gymmax.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reserva {

    private int idReserva;
    private int idSocio;
    private int idClase;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private LocalDateTime creadoEn;
    private LocalDateTime canceladoEn;

    private Clase clase;
    private Socio socio;

    public Reserva() {}

    public boolean puedeCancelarse() {
        if (!"CONF".equals(estado)) return false;
        if (fecha == null || hora == null) return false;
        LocalDateTime fechaHoraClase = LocalDateTime.of(fecha, hora);
        Duration restante = Duration.between(LocalDateTime.now(), fechaHoraClase);
        return restante.toHours() >= 2;
    }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdClase() { return idClase; }
    public void setIdClase(int idClase) { this.idClase = idClase; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }
    public LocalDateTime getCanceladoEn() { return canceladoEn; }
    public void setCanceladoEn(LocalDateTime canceladoEn) { this.canceladoEn = canceladoEn; }
    public Clase getClase() { return clase; }
    public void setClase(Clase clase) { this.clase = clase; }
    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }
}
