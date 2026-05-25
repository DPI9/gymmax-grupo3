package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.MembresiaDAO;
import com.gymmax.dao.PagoDAO;
import com.gymmax.dao.PlanDAO;
import com.gymmax.model.Membresia;
import com.gymmax.model.Pago;
import com.gymmax.model.Plan;
import com.gymmax.model.Socio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet(name = "MembresiaServlet", urlPatterns = {"/MembresiaServlet"})
public class MembresiaServlet extends HttpServlet {

    private final PlanDAO planDAO = new PlanDAO();
    private final MembresiaDAO membresiaDAO = new MembresiaDAO();
    private final PagoDAO pagoDAO = new PagoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(AppConstants.SESS_SOCIO) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        Socio socio = (Socio) session.getAttribute(AppConstants.SESS_SOCIO);

        req.setAttribute("planes", planDAO.listarActivos());
        req.setAttribute("membresiaActual", membresiaDAO.buscarActivaPorSocio(socio.getIdSocio()));
        req.setAttribute("historial", pagoDAO.listarPorSocio(socio.getIdSocio()));
        req.getRequestDispatcher("/membresias.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(AppConstants.SESS_SOCIO) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        Socio socio = (Socio) session.getAttribute(AppConstants.SESS_SOCIO);

        try {
            int idPlan = Integer.parseInt(req.getParameter("idPlan"));
            String metodo = req.getParameter("metodo");
            Plan plan = planDAO.buscarPorId(idPlan);

            Membresia actual = membresiaDAO.buscarActivaPorSocio(socio.getIdSocio());
            LocalDate inicio = (actual != null && actual.isVigente())
                    ? actual.getFechaFin().plusDays(1) : LocalDate.now();
            LocalDate fin = inicio.plusDays(plan.getDuracionDias());

            Membresia m = new Membresia();
            m.setIdSocio(socio.getIdSocio());
            m.setIdPlan(idPlan);
            m.setFechaInicio(inicio);
            m.setFechaFin(fin);
            m.setEstado(AppConstants.ESTADO_MEMBRESIA_ACTIVA);
            m.setMonto(plan.getPrecio());
            m.setRenovacionAuto(false);
            int idMembresia = membresiaDAO.registrar(m);

            Pago p = new Pago();
            p.setIdMembresia(idMembresia);
            p.setMetodo(metodo);
            p.setMonto(plan.getPrecio());
            p.setNroOperacion("OP-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
            p.setEstado(AppConstants.ESTADO_PAGO_OK);
            pagoDAO.registrar(p);

            req.setAttribute("mensaje", "Membresía contratada exitosamente. Válida hasta " + fin);
            doGet(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Error al contratar: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
