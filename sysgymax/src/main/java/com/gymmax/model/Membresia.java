package com.gymmax.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Membresia {

    private int idMembresia;
    private int idSocio;
    private int idPlan;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private double monto;
    private boolean renovacionAuto;

    private Plan plan;

    public Membresia() {}

    public boolean isVigente() {
        return "ACT".equals(estado) && fechaFin != null
                && !LocalDate.now().isAfter(fechaFin);
    }

    public long diasParaVencer() {
        if (fechaFin == null) return 0;
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaFin);
    }

    public int getIdMembresia() { return idMembresia; }
    public void setIdMembresia(int idMembresia) { this.idMembresia = idMembresia; }
    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdPlan() { return idPlan; }
    public void setIdPlan(int idPlan) { this.idPlan = idPlan; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public boolean isRenovacionAuto() { return renovacionAuto; }
    public void setRenovacionAuto(boolean renovacionAuto) { this.renovacionAuto = renovacionAuto; }
    public Plan getPlan() { return plan; }
    public void setPlan(Plan plan) { this.plan = plan; }
}
