package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Membresia;
import com.gymmax.model.Plan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembresiaDAO {

    public Membresia buscarActivaPorSocio(int idSocio) {
        String sql = "SELECT m.*, p.nombre AS plan_nombre, p.tipo AS plan_tipo, p.precio AS plan_precio, " +
                     "p.duracion_dias AS plan_dur, p.activo AS plan_act " +
                     "FROM MEMBRESIA m JOIN PLAN_MEMBRESIA p ON m.id_plan = p.id_plan " +
                     "WHERE m.id_socio = ? AND m.estado = 'ACT' " +
                     "ORDER BY m.fecha_fin DESC LIMIT 1";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearConPlan(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int registrar(Membresia m) {
        String sql = "INSERT INTO MEMBRESIA (id_socio, id_plan, fecha_inicio, fecha_fin, estado, monto, renovacion_auto) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getIdSocio());
            ps.setInt(2, m.getIdPlan());
            ps.setDate(3, Date.valueOf(m.getFechaInicio()));
            ps.setDate(4, Date.valueOf(m.getFechaFin()));
            ps.setString(5, m.getEstado());
            ps.setDouble(6, m.getMonto());
            ps.setBoolean(7, m.isRenovacionAuto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<Membresia> listarPorVencer(int dias) {
        List<Membresia> out = new ArrayList<>();
        String sql = "SELECT * FROM MEMBRESIA WHERE estado = 'ACT' " +
                     "AND fecha_fin BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL ? DAY)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, dias);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapearSimple(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public int contarPorVencer(int dias) {
        return listarPorVencer(dias).size();
    }

    private Membresia mapearSimple(ResultSet rs) throws SQLException {
        Membresia m = new Membresia();
        m.setIdMembresia(rs.getInt("id_membresia"));
        m.setIdSocio(rs.getInt("id_socio"));
        m.setIdPlan(rs.getInt("id_plan"));
        Date fi = rs.getDate("fecha_inicio");
        if (fi != null) m.setFechaInicio(fi.toLocalDate());
        Date ff = rs.getDate("fecha_fin");
        if (ff != null) m.setFechaFin(ff.toLocalDate());
        m.setEstado(rs.getString("estado"));
        m.setMonto(rs.getDouble("monto"));
        m.setRenovacionAuto(rs.getBoolean("renovacion_auto"));
        return m;
    }

    private Membresia mapearConPlan(ResultSet rs) throws SQLException {
        Membresia m = mapearSimple(rs);
        Plan p = new Plan(rs.getInt("id_plan"),
                          rs.getString("plan_nombre"),
                          rs.getString("plan_tipo"),
                          rs.getDouble("plan_precio"),
                          rs.getInt("plan_dur"),
                          rs.getBoolean("plan_act"));
        m.setPlan(p);
        return m;
    }
}
