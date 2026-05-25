package com.gymmax.model;

public class Plan {

    private int idPlan;
    private String nombre;
    private String tipo;
    private double precio;
    private int duracionDias;
    private boolean activo;

    public Plan() {}

    public Plan(int idPlan, String nombre, String tipo, double precio,
                int duracionDias, boolean activo) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.duracionDias = duracionDias;
        this.activo = activo;
    }

    public int getIdPlan() { return idPlan; }
    public void setIdPlan(int idPlan) { this.idPlan = idPlan; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getDuracionDias() { return duracionDias; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
