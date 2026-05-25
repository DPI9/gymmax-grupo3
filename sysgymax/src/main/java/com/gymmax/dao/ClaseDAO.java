package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Clase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAO {

    public List<Clase> listarPorSede(int idSede) {
        List<Clase> out = new ArrayList<>();
        String sql = "SELECT * FROM CLASE WHERE id_sede = ? ORDER BY dia_semana, hora_inicio";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSede);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapear(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public Clase buscarPorId(int id) {
        String sql = "SELECT * FROM CLASE WHERE id_clase = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int contarReservasActivas(int idClase, java.time.LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM RESERVA WHERE id_clase = ? AND fecha = ? AND estado = 'CONF'";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idClase);
            ps.setDate(2, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private Clase mapear(ResultSet rs) throws SQLException {
        Clase c = new Clase();
        c.setIdClase(rs.getInt("id_clase"));
        c.setIdSede(rs.getInt("id_sede"));
        c.setNombre(rs.getString("nombre"));
        c.setTipo(rs.getString("tipo"));
        c.setInstructor(rs.getString("instructor"));
        c.setDiaSemana(rs.getInt("dia_semana"));
        Time hi = rs.getTime("hora_inicio");
        if (hi != null) c.setHoraInicio(hi.toLocalTime());
        c.setCupoMaximo(rs.getInt("cupo_maximo"));
        return c;
    }
}
