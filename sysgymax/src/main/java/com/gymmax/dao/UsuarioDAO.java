package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String correo, String password) {
        String sql = "SELECT * FROM USUARIO WHERE correo = ? AND password = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM USUARIO WHERE correo = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int registrar(Usuario u) {
        String sql = "INSERT INTO USUARIO (correo, password, nombres, apellidos, rol) VALUES (?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getNombres());
            ps.setString(4, u.getApellidos());
            ps.setString(5, u.getRol() == null ? "SOCIO" : u.getRol());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<Usuario> listar() {
        List<Usuario> out = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO ORDER BY id_usuario";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setCorreo(rs.getString("correo"));
        u.setPassword(rs.getString("password"));
        u.setNombres(rs.getString("nombres"));
        u.setApellidos(rs.getString("apellidos"));
        u.setRol(rs.getString("rol"));
        Timestamp ts = rs.getTimestamp("creado_en");
        if (ts != null) u.setCreadoEn(ts.toLocalDateTime());
        return u;
    }
}
