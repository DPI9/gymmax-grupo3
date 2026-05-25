package com.gymmax.dao;

import com.gymmax.Conexion;
import com.gymmax.model.Pago;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public int registrar(Pago p) {
        String sql = "INSERT INTO PAGO (id_membresia, metodo, monto, nro_operacion, estado) VALUES (?,?,?,?,?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getIdMembresia());
            ps.setString(2, p.getMetodo());
            ps.setDouble(3, p.getMonto());
            ps.setString(4, p.getNroOperacion());
            ps.setString(5, p.getEstado() == null ? "OK" : p.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<Pago> listarPorSocio(int idSocio) {
        List<Pago> out = new ArrayList<>();
        String sql = "SELECT p.* FROM PAGO p JOIN MEMBRESIA m ON p.id_membresia = m.id_membresia " +
                     "WHERE m.id_socio = ? ORDER BY p.fecha_pago DESC";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapear(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }

    public double sumarIngresosDelMes() {
        String sql = "SELECT COALESCE(SUM(monto),0) FROM PAGO " +
                     "WHERE estado = 'OK' AND MONTH(fecha_pago)=MONTH(CURRENT_DATE) " +
                     "AND YEAR(fecha_pago)=YEAR(CURRENT_DATE)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private Pago mapear(ResultSet rs) throws SQLException {
        Pago p = new Pago();
        p.setIdPago(rs.getInt("id_pago"));
        p.setIdMembresia(rs.getInt("id_membresia"));
        p.setMetodo(rs.getString("metodo"));
        p.setMonto(rs.getDouble("monto"));
        Timestamp ts = rs.getTimestamp("fecha_pago");
        if (ts != null) p.setFechaPago(ts.toLocalDateTime());
        p.setNroOperacion(rs.getString("nro_operacion"));
        p.setEstado(rs.getString("estado"));
        p.setComprobanteUrl(rs.getString("comprobante_url"));
        return p;
    }
}
