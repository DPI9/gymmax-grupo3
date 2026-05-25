package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.AsistenciaDAO;
import com.gymmax.dao.MembresiaDAO;
import com.gymmax.dao.ReservaDAO;
import com.gymmax.model.Membresia;
import com.gymmax.model.Reserva;
import com.gymmax.model.Socio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

    private final MembresiaDAO membresiaDAO = new MembresiaDAO();
    private final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(AppConstants.SESS_SOCIO) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        Socio socio = (Socio) session.getAttribute(AppConstants.SESS_SOCIO);

        Membresia membresia = membresiaDAO.buscarActivaPorSocio(socio.getIdSocio());
        int asistencias = asistenciaDAO.contarPorSocio(socio.getIdSocio());
        List<Reserva> proximas = reservaDAO.listarPorSocio(socio.getIdSocio(), true);

        req.setAttribute("membresia", membresia);
        req.setAttribute("asistencias", asistencias);
        req.setAttribute("reservas", proximas);
        req.setAttribute("proximaReserva", proximas.isEmpty() ? null : proximas.get(0));

        req.getRequestDispatcher("/dashboardSocio.jsp").forward(req, resp);
    }
}
