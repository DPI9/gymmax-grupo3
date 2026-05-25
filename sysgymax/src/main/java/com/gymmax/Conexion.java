package com.gymmax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper Singleton para conexion JDBC a MySQL.
 * Antes de usar: ejecutar el script db/schema.sql en MySQL Workbench y
 * ajustar USER/PASSWORD si tu MySQL local usa otra contrasena.
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/gymmax?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Conexion() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado en el classpath", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void cerrar(Connection conn) {
        if (conn != null) {
            try { conn.close(); } catch (SQLException ignore) {}
        }
    }
}
