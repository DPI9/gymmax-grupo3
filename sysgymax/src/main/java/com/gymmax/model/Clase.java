package com.gymmax.model;

import java.time.LocalTime;

public class Clase {

    private int idClase;
    private int idSede;
    private String nombre;
    private String tipo;
    private String instructor;
    private int diaSemana;
    private LocalTime horaInicio;
    private int cupoMaximo;

    private Sede sede;

    public Clase() {}

    public int getIdClase() { return idClase; }
    public void setIdClase(int idClase) { this.idClase = idClase; }
    public int getIdSede() { return idSede; }
    public void setIdSede(int idSede) { this.idSede = idSede; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public int getDiaSemana() { return diaSemana; }
    public void setDiaSemana(int diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }
}
