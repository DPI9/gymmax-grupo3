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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final SocioDAO socioDAO = new SocioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String password = req.getParameter("password");

        Usuario u = usuarioDAO.login(correo, password);

        if (u == null) {
            req.setAttribute("error", "Correo o contraseña incorrectos");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute(AppConstants.SESS_USUARIO, u);

        if (AppConstants.ROL_ADMIN.equals(u.getRol())) {
            resp.sendRedirect(req.getContextPath() + "/AdminServlet");
        } else {
            Socio s = socioDAO.buscarPorUsuario(u.getIdUsuario());
            session.setAttribute(AppConstants.SESS_SOCIO, s);
            resp.sendRedirect(req.getContextPath() + "/DashboardServlet");
        }
    }
}
