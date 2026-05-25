package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Asistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {

    public int contarPorSocio(int idSocio) {
        String sql = "SELECT COUNT(*) FROM ASISTENCIA WHERE id_socio = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int registrar(Asistencia a) {
        String sql = "INSERT INTO ASISTENCIA (id_socio, id_sede, tipo, registrado_por) VALUES (?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, a.getIdSocio());
            ps.setInt(2, a.getIdSede());
            ps.setString(3, a.getTipo());
            ps.setInt(4, a.getRegistradoPor());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }
}
