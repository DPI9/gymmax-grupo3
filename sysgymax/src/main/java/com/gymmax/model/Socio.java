package com.gymmax.model;

import java.time.LocalDate;

public class Socio {

    private int idSocio;
    private int idUsuario;
    private String dni;
    private String celular;
    private LocalDate fechaNac;
    private char genero;
    private String direccion;
    private LocalDate fechaReg;

    private Usuario usuario;

    public Socio() {}

    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public LocalDate getFechaNac() { return fechaNac; }
    public void setFechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; }
    public char getGenero() { return genero; }
    public void setGenero(char genero) { this.genero = genero; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public LocalDate getFechaReg() { return fechaReg; }
    public void setFechaReg(LocalDate fechaReg) { this.fechaReg = fechaReg; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
