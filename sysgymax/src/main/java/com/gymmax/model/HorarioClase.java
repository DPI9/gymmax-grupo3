package com.gymmax.model;

import java.time.LocalDate;

public class HorarioClase {

    private int idHorario;
    private int idClase;
    private LocalDate fechaEspecifica;
    private int cupoActual;
    private int cupoDisponible;
    private String estado;

    public HorarioClase() {}

    public boolean hayCupo() { return cupoDisponible > 0; }

    public int getIdHorario() { return idHorario; }
    public void setIdHorario(int idHorario) { this.idHorario = idHorario; }
    public int getIdClase() { return idClase; }
    public void setIdClase(int idClase) { this.idClase = idClase; }
    public LocalDate getFechaEspecifica() { return fechaEspecifica; }
    public void setFechaEspecifica(LocalDate fechaEspecifica) { this.fechaEspecifica = fechaEspecifica; }
    public int getCupoActual() { return cupoActual; }
    public void setCupoActual(int cupoActual) { this.cupoActual = cupoActual; }
    public int getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(int cupoDisponible) { this.cupoDisponible = cupoDisponible; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
