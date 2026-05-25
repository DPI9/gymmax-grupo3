package com.gymmax.model;

import java.time.LocalDateTime;

public class Asistencia {

    private int idAsistencia;
    private int idSocio;
    private int idSede;
    private LocalDateTime fechaHora;
    private String tipo;
    private int registradoPor;

    public Asistencia() {}

    public int getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(int idAsistencia) { this.idAsistencia = idAsistencia; }
    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdSede() { return idSede; }
    public void setIdSede(int idSede) { this.idSede = idSede; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(int registradoPor) { this.registradoPor = registradoPor; }
}
