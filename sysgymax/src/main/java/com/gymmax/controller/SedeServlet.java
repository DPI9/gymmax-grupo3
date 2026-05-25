package com.gymmax.controller;

import com.gymmax.config.AppConstants;
import com.gymmax.dao.ClaseDAO;
import com.gymmax.dao.SedeDAO;
import com.gymmax.model.Clase;
import com.gymmax.model.Sede;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SedeServlet", urlPatterns = {"/SedeServlet"})
public class SedeServlet extends HttpServlet {

    private final SedeDAO sedeDAO = new SedeDAO();
    private final ClaseDAO claseDAO = new ClaseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(AppConstants.SESS_USUARIO) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String idSedeStr = req.getParameter("id");
        if (idSedeStr != null) {
            Sede sede = sedeDAO.buscarPorId(Integer.parseInt(idSedeStr));
            List<Clase> clases = claseDAO.listarPorSede(sede.getIdSede());
            req.setAttribute("sede", sede);
            req.setAttribute("clases", clases);
            req.getRequestDispatcher("/detalleSede.jsp").forward(req, resp);
        } else {
            String distrito = req.getParameter("distrito");
            List<Sede> sedes = (distrito != null && !distrito.isEmpty())
                    ? sedeDAO.filtrarPorDistrito(distrito) : sedeDAO.listar();
            req.setAttribute("sedes", sedes);
            req.setAttribute("distrito", distrito);
            req.getRequestDispatcher("/sedes.jsp").forward(req, resp);
        }
    }
}
