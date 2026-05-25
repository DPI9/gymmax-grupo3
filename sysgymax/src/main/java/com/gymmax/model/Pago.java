package com.gymmax.model;

import java.time.LocalDateTime;

public class Pago {

    private int idPago;
    private int idMembresia;
    private String metodo;
    private double monto;
    private LocalDateTime fechaPago;
    private String nroOperacion;
    private String estado;
    private String comprobanteUrl;

    public Pago() {}

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }
    public int getIdMembresia() { return idMembresia; }
    public void setIdMembresia(int idMembresia) { this.idMembresia = idMembresia; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public String getNroOperacion() { return nroOperacion; }
    public void setNroOperacion(String nroOperacion) { this.nroOperacion = nroOperacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getComprobanteUrl() { return comprobanteUrl; }
    public void setComprobanteUrl(String comprobanteUrl) { this.comprobanteUrl = comprobanteUrl; }
}
