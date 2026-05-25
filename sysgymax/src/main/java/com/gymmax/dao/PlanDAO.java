package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Plan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO {

    public List<Plan> listarActivos() {
        List<Plan> out = new ArrayList<>();
        String sql = "SELECT * FROM PLAN_MEMBRESIA WHERE activo = TRUE ORDER BY precio";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public Plan buscarPorId(int id) {
        String sql = "SELECT * FROM PLAN_MEMBRESIA WHERE id_plan = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Plan mapear(ResultSet rs) throws SQLException {
        return new Plan(
            rs.getInt("id_plan"),
            rs.getString("nombre"),
            rs.getString("tipo"),
            rs.getDouble("precio"),
            rs.getInt("duracion_dias"),
            rs.getBoolean("activo")
        );
    }
}
