<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Reserva,java.util.List" %>
<%
String ctx = request.getContextPath();
List<Reserva> proximas = (List<Reserva>) request.getAttribute("proximas");
List<Reserva> pasadas = (List<Reserva>) request.getAttribute("pasadas");
String flash = (String) session.getAttribute("flash");
String flashError = (String) session.getAttribute("flashError");
if (flash != null) session.removeAttribute("flash");
if (flashError != null) session.removeAttribute("flashError");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis reservas — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .header-gym { background:#FF6B00; color:#fff; padding:14px 20px; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .nav-pills .nav-link.active { background-color:#FF6B00; }
    </style>
</head>
<body>
<div class="header-gym d-flex align-items-center">
    <a href="<%= ctx %>/DashboardServlet" class="text-white text-decoration-none me-2">&larr;</a>
    <h5 class="m-0">Mis reservas</h5>
</div>

<div class="container py-3">
    <% if (flash != null) { %><div class="alert alert-success"><%= flash %></div><% } %>
    <% if (flashError != null) { %><div class="alert alert-warning"><%= flashError %></div><% } %>

    <ul class="nav nav-pills mb-3">
        <li class="nav-item">
            <a class="nav-link active" data-bs-toggle="tab" href="#prox">
                Próximas (<%= proximas != null ? proximas.size() : 0 %>)
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="tab" href="#pas">Pasadas</a>
        </li>
    </ul>

    <div class="tab-content">
        <div class="tab-pane active" id="prox">
            <% if (proximas == null || proximas.isEmpty()) { %>
                <p class="text-muted">No tienes reservas próximas.</p>
            <% } else for (Reserva r : proximas) { %>
                <div class="card mb-2 shadow-sm">
                    <div class="card-body py-3 d-flex justify-content-between align-items-center">
                        <div>
                            <h6 class="m-0"><%= r.getClase() != null ? r.getClase().getNombre() : "Clase" %></h6>
                            <small class="text-muted">
                                <%= r.getFecha() %> · <%= r.getHora() %>
                            </small>
                            <span class="badge bg-success ms-2"><%= r.getEstado() %></span>
                        </div>
                        <% if (r.puedeCancelarse()) { %>
                            <form method="post" action="<%= ctx %>/ReservaServlet"
                                  onsubmit="return confirm('¿Cancelar esta reserva?');">
                                <input type="hidden" name="accion" value="cancelar">
                                <input type="hidden" name="idReserva" value="<%= r.getIdReserva() %>">
                                <button class="btn btn-sm btn-outline-danger">Cancelar</button>
                            </form>
                        <% } %>
                    </div>
                </div>
            <% } %>
        </div>

        <div class="tab-pane" id="pas">
            <% if (pasadas == null || pasadas.isEmpty()) { %>
                <p class="text-muted">No tienes reservas pasadas.</p>
            <% } else for (Reserva r : pasadas) { %>
                <div class="card mb-2 shadow-sm bg-light">
                    <div class="card-body py-3">
                        <h6 class="m-0"><%= r.getClase() != null ? r.getClase().getNombre() : "Clase" %></h6>
                        <small class="text-muted">
                            <%= r.getFecha() %> · <%= r.getEstado() %>
                        </small>
                    </div>
                </div>
            <% } %>
        </div>
    </div>

    <a href="<%= ctx %>/ReservaServlet" class="btn btn-gym w-100 mt-3">+ Nueva reserva</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
