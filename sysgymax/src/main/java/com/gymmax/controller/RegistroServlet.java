package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.SocioDAO;
import com.gymmax.dao.UsuarioDAO;
import com.gymmax.model.Socio;
import com.gymmax.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final SocioDAO socioDAO = new SocioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/registro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String correo = req.getParameter("correo");
            String password = req.getParameter("password");
            String nombres = req.getParameter("nombres");
            String apellidos = req.getParameter("apellidos");
            String dni = req.getParameter("dni");
            String celular = req.getParameter("celular");
            String fechaNacStr = req.getParameter("fechaNac");
            String generoStr = req.getParameter("genero");

            if (usuarioDAO.buscarPorCorreo(correo) != null) {
                req.setAttribute("error", "El correo ya está registrado");
                req.getRequestDispatcher("/registro.jsp").forward(req, resp);
                return;
            }
            if (socioDAO.buscarPorDni(dni) != null) {
                req.setAttribute("error", "El DNI ya está registrado");
                req.getRequestDispatcher("/registro.jsp").forward(req, resp);
                return;
            }

            Usuario u = new Usuario();
            u.setCorreo(correo);
            u.setPassword(password);
            u.setNombres(nombres);
            u.setApellidos(apellidos);
            u.setRol(AppConstants.ROL_SOCIO);
            int idUsuario = usuarioDAO.registrar(u);
            if (idUsuario <= 0) {
                req.setAttribute("error", "Error al crear el usuario");
                req.getRequestDispatcher("/registro.jsp").forward(req, resp);
                return;
            }

            Socio s = new Socio();
            s.setIdUsuario(idUsuario);
            s.setDni(dni);
            s.setCelular(celular);
            if (fechaNacStr != null && !fechaNacStr.isEmpty())
                s.setFechaNac(LocalDate.parse(fechaNacStr));
            s.setGenero(generoStr != null && !generoStr.isEmpty() ? generoStr.charAt(0) : 'O');
            s.setDireccion(req.getParameter("direccion"));
            socioDAO.registrar(s);

            req.setAttribute("mensaje", "Registro exitoso. Ya puedes iniciar sesión.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Error en el registro: " + e.getMessage());
            req.getRequestDispatcher("/registro.jsp").forward(req, resp);
        }
    }
}
