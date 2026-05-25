package com.gymmax.model;

import java.time.LocalTime;

public class Sede {

    private int idSede;
    private String nombre;
    private String direccion;
    private String distrito;
    private String telefono;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private int capacidad;

    public Sede() {}

    public Sede(int idSede, String nombre, String direccion, String distrito,
                String telefono, LocalTime horaApertura, LocalTime horaCierre,
                int capacidad) {
        this.idSede = idSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.telefono = telefono;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.capacidad = capacidad;
    }

    public int getIdSede() { return idSede; }
    public void setIdSede(int idSede) { this.idSede = idSede; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalTime getHoraApertura() { return horaApertura; }
    public void setHoraApertura(LocalTime horaApertura) { this.horaApertura = horaApertura; }
    public LocalTime getHoraCierre() { return horaCierre; }
    public void setHoraCierre(LocalTime horaCierre) { this.horaCierre = horaCierre; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
