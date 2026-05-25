package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.MembresiaDAO;
import com.gymmax.dao.PagoDAO;
import com.gymmax.dao.ReservaDAO;
import com.gymmax.dao.SedeDAO;
import com.gymmax.dao.SocioDAO;
import com.gymmax.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private final SocioDAO socioDAO = new SocioDAO();
    private final PagoDAO pagoDAO = new PagoDAO();
    private final MembresiaDAO membresiaDAO = new MembresiaDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final SedeDAO sedeDAO = new SedeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        Usuario u = (Usuario) session.getAttribute(AppConstants.SESS_USUARIO);
        if (u == null || !AppConstants.ROL_ADMIN.equals(u.getRol())) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String view = req.getParameter("view");
        if ("socios".equals(view)) {
            req.setAttribute("socios", socioDAO.listar());
            req.getRequestDispatcher("/gestionSocios.jsp").forward(req, resp);
            return;
        }
        if ("sedes".equals(view)) {
            req.setAttribute("sedes", sedeDAO.listar());
            req.getRequestDispatcher("/gestionSedes.jsp").forward(req, resp);
            return;
        }

        // dashboard por defecto
        req.setAttribute("kpiSociosActivos", socioDAO.contarActivos());
        req.setAttribute("kpiIngresosMes", pagoDAO.sumarIngresosDelMes());
        req.setAttribute("kpiPorVencer", membresiaDAO.contarPorVencer(7));
        req.setAttribute("kpiReservasHoy", reservaDAO.contarReservasHoy());
        req.getRequestDispatcher("/adminDashboard.jsp").forward(req, resp);
    }
}
