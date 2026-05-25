package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Clase;
import com.gymmax.model.Reserva;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public int registrar(Reserva r) {
        String sql = "INSERT INTO RESERVA (id_socio, id_clase, fecha, hora, estado) VALUES (?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getIdSocio());
            ps.setInt(2, r.getIdClase());
            ps.setDate(3, Date.valueOf(r.getFecha()));
            ps.setTime(4, Time.valueOf(r.getHora()));
            ps.setString(5, r.getEstado() == null ? "CONF" : r.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public boolean cancelar(int idReserva) {
        String sql = "UPDATE RESERVA SET estado='CAN', cancelado_en = CURRENT_TIMESTAMP WHERE id_reserva = ? AND estado='CONF'";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean existeReserva(int idSocio, int idClase, LocalDate fecha) {
        String sql = "SELECT 1 FROM RESERVA WHERE id_socio=? AND id_clase=? AND fecha=? AND estado='CONF'";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            ps.setInt(2, idClase);
            ps.setDate(3, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public Reserva buscarPorId(int id) {
        String sql = "SELECT * FROM RESERVA WHERE id_reserva=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Reserva> listarPorSocio(int idSocio, boolean soloFuturas) {
        List<Reserva> out = new ArrayList<>();
        String sql = "SELECT r.*, cl.nombre AS clase_nombre, cl.tipo AS clase_tipo, " +
                     "cl.instructor AS clase_instructor, cl.id_sede AS clase_sede " +
                     "FROM RESERVA r JOIN CLASE cl ON r.id_clase = cl.id_clase " +
                     "WHERE r.id_socio = ?" +
                     (soloFuturas ? " AND r.fecha >= CURRENT_DATE" : "") +
                     " ORDER BY r.fecha DESC, r.hora DESC";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reserva r = mapear(rs);
                    Clase cl = new Clase();
                    cl.setIdClase(r.getIdClase());
                    cl.setNombre(rs.getString("clase_nombre"));
                    cl.setTipo(rs.getString("clase_tipo"));
                    cl.setInstructor(rs.getString("clase_instructor"));
                    cl.setIdSede(rs.getInt("clase_sede"));
                    r.setClase(cl);
                    out.add(r);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public int contarReservasHoy() {
        String sql = "SELECT COUNT(*) FROM RESERVA WHERE fecha = CURRENT_DATE AND estado='CONF'";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private Reserva mapear(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setIdReserva(rs.getInt("id_reserva"));
        r.setIdSocio(rs.getInt("id_socio"));
        r.setIdClase(rs.getInt("id_clase"));
        Date f = rs.getDate("fecha");
        if (f != null) r.setFecha(f.toLocalDate());
        Time h = rs.getTime("hora");
        if (h != null) r.setHora(h.toLocalTime());
        r.setEstado(rs.getString("estado"));
        Timestamp ts = rs.getTimestamp("creado_en");
        if (ts != null) r.setCreadoEn(ts.toLocalDateTime());
        Timestamp tc = rs.getTimestamp("cancelado_en");
        if (tc != null) r.setCanceladoEn(tc.toLocalDateTime());
        return r;
    }
}
