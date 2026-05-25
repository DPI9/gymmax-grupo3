package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Sede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SedeDAO {

    public List<Sede> listar() {
        List<Sede> out = new ArrayList<>();
        String sql = "SELECT * FROM SEDE ORDER BY nombre";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public List<Sede> filtrarPorDistrito(String distrito) {
        List<Sede> out = new ArrayList<>();
        String sql = "SELECT * FROM SEDE WHERE distrito LIKE ? ORDER BY nombre";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + distrito + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapear(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public Sede buscarPorId(int id) {
        String sql = "SELECT * FROM SEDE WHERE id_sede = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int registrar(Sede s) {
        String sql = "INSERT INTO SEDE (nombre, direccion, distrito, telefono, hora_apertura, hora_cierre, capacidad) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDireccion());
            ps.setString(3, s.getDistrito());
            ps.setString(4, s.getTelefono());
            ps.setTime(5, Time.valueOf(s.getHoraApertura()));
            ps.setTime(6, Time.valueOf(s.getHoraCierre()));
            ps.setInt(7, s.getCapacidad());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    private Sede mapear(ResultSet rs) throws SQLException {
        Sede s = new Sede();
        s.setIdSede(rs.getInt("id_sede"));
        s.setNombre(rs.getString("nombre"));
        s.setDireccion(rs.getString("direccion"));
        s.setDistrito(rs.getString("distrito"));
        s.setTelefono(rs.getString("telefono"));
        Time ha = rs.getTime("hora_apertura");
        if (ha != null) s.setHoraApertura(ha.toLocalTime());
        Time hc = rs.getTime("hora_cierre");
        if (hc != null) s.setHoraCierre(hc.toLocalTime());
        s.setCapacidad(rs.getInt("capacidad"));
        return s;
    }
}
