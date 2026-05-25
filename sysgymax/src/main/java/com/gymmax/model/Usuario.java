package com.gymmax.model;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String correo;
    private String password;
    private String nombres;
    private String apellidos;
    private String rol;
    private LocalDateTime creadoEn;

    public Usuario() {}

    public Usuario(int idUsuario, String correo, String password, String nombres,
                   String apellidos, String rol, LocalDateTime creadoEn) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.creadoEn = creadoEn;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }

    public String getNombreCompleto() { return nombres + " " + apellidos; }
}
