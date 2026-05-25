<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Sede,com.gymmax.model.Clase,java.util.List,java.time.LocalDate" %>
<%
String ctx = request.getContextPath();
List<Sede> sedes = (List<Sede>) request.getAttribute("sedes");
Sede sedeSel = (Sede) request.getAttribute("sedeSel");
List<Clase> clases = (List<Clase>) request.getAttribute("clases");
String error = (String) request.getAttribute("error");
String flash = (String) session.getAttribute("flash");
if (flash != null) session.removeAttribute("flash");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservar clase — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .header-gym { background:#FF6B00; color:#fff; padding:14px 20px; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .btn-gym:hover { background:#e85f00; color:#fff; }
    </style>
</head>
<body>
<div class="header-gym d-flex align-items-center">
    <a href="<%= ctx %>/DashboardServlet" class="text-white text-decoration-none me-2">&larr;</a>
    <h5 class="m-0">Reservar clase</h5>
</div>

<div class="container py-3">
    <% if (flash != null) { %><div class="alert alert-success"><%= flash %></div><% } %>
    <% if (error != null) { %><div class="alert alert-danger"><%= error %></div><% } %>

    <form method="get" action="<%= ctx %>/ReservaServlet" class="mb-3">
        <label class="form-label">Sede</label>
        <select name="sede" class="form-select" onchange="this.form.submit()">
            <option value="">-- Seleccionar --</option>
            <% if (sedes != null) for (Sede s : sedes) { %>
                <option value="<%= s.getIdSede() %>"
                    <%= sedeSel != null && sedeSel.getIdSede()==s.getIdSede()?"selected":"" %>>
                    <%= s.getNombre() %>
                </option>
            <% } %>
        </select>
    </form>

    <% if (sedeSel != null && clases != null) { %>
        <h6 class="mt-3">Clases disponibles en <%= sedeSel.getNombre() %></h6>
        <% if (clases.isEmpty()) { %>
            <p class="text-muted">No hay clases registradas en esta sede.</p>
        <% } else { %>
            <% for (Clase cl : clases) { %>
                <div class="card mb-2 shadow-sm">
                    <div class="card-body py-3">
                        <div class="d-flex justify-content-between align-items-start">
                            <div>
                                <h6 class="m-0"><%= cl.getNombre() %></h6>
                                <small class="text-muted">
                                    <%= cl.getHoraInicio() %> · Prof. <%= cl.getInstructor() %><br>
                                    Cupo: <%= cl.getCupoMaximo() %>
                                </small>
                            </div>
                            <form method="post" action="<%= ctx %>/ReservaServlet" class="d-flex flex-column gap-1">
                                <input type="hidden" name="idClase" value="<%= cl.getIdClase() %>">
                                <input type="hidden" name="hora" value="<%= cl.getHoraInicio() %>">
                                <input type="date" name="fecha" class="form-control form-control-sm"
                                       value="<%= LocalDate.now().plusDays(1) %>" min="<%= LocalDate.now() %>" required>
                                <button class="btn btn-gym btn-sm">Reservar</button>
                            </form>
                        </div>
                    </div>
                </div>
            <% } %>
        <% } %>
    <% } %>
</div>
</body>
</html>
