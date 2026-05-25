package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Socio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO {

    public int registrar(Socio s) {
        String sql = "INSERT INTO SOCIO (id_usuario, dni, celular, fecha_nac, genero, direccion, fecha_reg) " +
                     "VALUES (?,?,?,?,?,?, CURRENT_DATE)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getIdUsuario());
            ps.setString(2, s.getDni());
            ps.setString(3, s.getCelular());
            if (s.getFechaNac() != null) ps.setDate(4, Date.valueOf(s.getFechaNac()));
            else ps.setNull(4, Types.DATE);
            ps.setString(5, String.valueOf(s.getGenero()));
            ps.setString(6, s.getDireccion());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public Socio buscarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM SOCIO WHERE id_usuario = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Socio buscarPorDni(String dni) {
        String sql = "SELECT * FROM SOCIO WHERE dni = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Socio> listar() {
        List<Socio> out = new ArrayList<>();
        String sql = "SELECT * FROM SOCIO ORDER BY id_socio";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public int contarActivos() {
        String sql = "SELECT COUNT(DISTINCT s.id_socio) FROM SOCIO s " +
                     "JOIN MEMBRESIA m ON m.id_socio = s.id_socio " +
                     "WHERE m.estado = 'ACT' AND m.fecha_fin >= CURRENT_DATE";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private Socio mapear(ResultSet rs) throws SQLException {
        Socio s = new Socio();
        s.setIdSocio(rs.getInt("id_socio"));
        s.setIdUsuario(rs.getInt("id_usuario"));
        s.setDni(rs.getString("dni"));
        s.setCelular(rs.getString("celular"));
        Date fn = rs.getDate("fecha_nac");
        if (fn != null) s.setFechaNac(fn.toLocalDate());
        String g = rs.getString("genero");
        if (g != null && !g.isEmpty()) s.setGenero(g.charAt(0));
        s.setDireccion(rs.getString("direccion"));
        Date fr = rs.getDate("fecha_reg");
        if (fr != null) s.setFechaReg(fr.toLocalDate());
        return s;
    }
}
