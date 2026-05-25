package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.ClaseDAO;
import com.gymmax.dao.MembresiaDAO;
import com.gymmax.dao.ReservaDAO;
import com.gymmax.dao.SedeDAO;
import com.gymmax.model.Clase;
import com.gymmax.model.Membresia;
import com.gymmax.model.Reserva;
import com.gymmax.model.Sede;
import com.gymmax.model.Socio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {

    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final ClaseDAO claseDAO = new ClaseDAO();
    private final SedeDAO sedeDAO = new SedeDAO();
    private final MembresiaDAO membresiaDAO = new MembresiaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(AppConstants.SESS_SOCIO) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        Socio socio = (Socio) session.getAttribute(AppConstants.SESS_SOCIO);
        String accion = req.getParameter("accion");

        if ("misReservas".equals(accion)) {
            List<Reserva> proximas = reservaDAO.listarPorSocio(socio.getIdSocio(), true);
            List<Reserva> pasadas = reservaDAO.listarPorSocio(socio.getIdSocio(), false);
            pasadas.removeAll(proximas);
            req.setAttribute("proximas", proximas);
            req.setAttribute("pasadas", pasadas);
            req.getRequestDispatcher("/misReservas.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("sedes", sedeDAO.listar());
        String idSedeStr = req.getParameter("sede");
        if (idSedeStr != null && !idSedeStr.isEmpty()) {
            int idSede = Integer.parseInt(idSedeStr);
            Sede sede = sedeDAO.buscarPorId(idSede);
            List<Clase> clases = claseDAO.listarPorSede(idSede);
            req.setAttribute("sedeSel", sede);
            req.setAttribute("clases", clases);
        }
        req.getRequestDispatcher("/reservarClase.jsp").forward(req, resp);
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
        String accion = req.getParameter("accion");

        if ("cancelar".equals(accion)) {
            int idReserva = Integer.parseInt(req.getParameter("idReserva"));
            Reserva r = reservaDAO.buscarPorId(idReserva);
            if (r != null && r.puedeCancelarse()) {
                reservaDAO.cancelar(idReserva);
                req.getSession().setAttribute("flash", "Reserva cancelada");
            } else {
                req.getSession().setAttribute("flashError",
                    "No se puede cancelar (faltan menos de 2 horas)");
            }
            resp.sendRedirect(req.getContextPath() + "/ReservaServlet?accion=misReservas");
            return;
        }

        try {
            int idClase = Integer.parseInt(req.getParameter("idClase"));
            LocalDate fecha = LocalDate.parse(req.getParameter("fecha"));
            LocalTime hora = LocalTime.parse(req.getParameter("hora"));

            Membresia m = membresiaDAO.buscarActivaPorSocio(socio.getIdSocio());
            if (m == null || !m.isVigente()) {
                req.setAttribute("error", "Necesitas una membresía activa para reservar");
                doGet(req, resp);
                return;
            }

            if (reservaDAO.existeReserva(socio.getIdSocio(), idClase, fecha)) {
                req.setAttribute("error", "Ya tienes una reserva para esta clase en esta fecha");
                doGet(req, resp);
                return;
            }

            Clase cl = claseDAO.buscarPorId(idClase);
            int actuales = claseDAO.contarReservasActivas(idClase, fecha);
            if (actuales >= cl.getCupoMaximo()) {
                req.setAttribute("error", "Sin cupos disponibles para esta clase");
                doGet(req, resp);
                return;
            }

            Reserva r = new Reserva();
            r.setIdSocio(socio.getIdSocio());
            r.setIdClase(idClase);
            r.setFecha(fecha);
            r.setHora(hora);
            r.setEstado(AppConstants.ESTADO_RESERVA_CONFIRMADA);
            reservaDAO.registrar(r);

            req.getSession().setAttribute("flash",
                "Reserva confirmada para " + cl.getNombre() + " el " + fecha + " a las " + hora);
            resp.sendRedirect(req.getContextPath() + "/ReservaServlet?accion=misReservas");
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
