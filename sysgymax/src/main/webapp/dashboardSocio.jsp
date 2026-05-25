<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Socio,com.gymmax.model.Membresia,com.gymmax.model.Reserva,java.util.List" %>
<%
String ctx = request.getContextPath();
Socio socio = (Socio) session.getAttribute("socioLogueado");
Membresia membresia = (Membresia) request.getAttribute("membresia");
Integer asistencias = (Integer) request.getAttribute("asistencias");
Reserva proxima = (Reserva) request.getAttribute("proximaReserva");
List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .navbar-gym { background:#1a1a1a; }
        .brand-gym { color:#FF6B00; font-weight:800; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .btn-gym:hover { background:#e85f00; color:#fff; }
        .card-stat { text-align:center; padding:18px; }
        .card-stat .num { font-size:2rem; font-weight:bold; color:#FF6B00; }
        .card-membresia { background:linear-gradient(135deg,#FF6B00,#e85f00); color:#fff; }
    </style>
</head>
<body>

<nav class="navbar navbar-gym">
    <div class="container">
        <span class="navbar-brand text-white">
            <i class="bi bi-trophy text-warning"></i>
            <span class="brand-gym">Gym</span><strong style="color:#fff;">Max</strong>
        </span>
        <div>
            <span class="text-white-50 me-3">Hola, <strong><%= socio != null ? socio.getDni() : "" %></strong></span>
            <a href="<%= ctx %>/LogoutServlet" class="btn btn-sm btn-outline-light">Salir</a>
        </div>
    </div>
</nav>

<div class="container py-4">
    <h3 class="mb-4">Bienvenido al panel del socio</h3>

    <!-- Membresía -->
    <div class="card card-membresia mb-3 shadow-sm">
        <div class="card-body">
            <small class="opacity-75">Mi membresía</small>
            <% if (membresia != null && membresia.getPlan() != null) { %>
                <h4 class="m-0"><%= membresia.getPlan().getNombre() %></h4>
                <small><i class="bi bi-check-circle"></i> Activa hasta <%= membresia.getFechaFin() %>
                       (<%= membresia.diasParaVencer() %> días restantes)</small>
            <% } else { %>
                <h4 class="m-0">Sin membresía activa</h4>
                <a href="<%= ctx %>/MembresiaServlet" class="btn btn-light btn-sm mt-2">Contratar plan</a>
            <% } %>
        </div>
    </div>

    <!-- Stats -->
    <div class="row g-3 mb-3">
        <div class="col-6 col-md-3">
            <div class="card card-stat shadow-sm">
                <div class="num"><%= asistencias != null ? asistencias : 0 %></div>
                <div class="text-muted">Asistencias</div>
            </div>
        </div>
        <div class="col-6 col-md-3">
            <div class="card card-stat shadow-sm">
                <div class="num"><%= reservas != null ? reservas.size() : 0 %></div>
                <div class="text-muted">Reservas</div>
            </div>
        </div>
    </div>

    <!-- Próxima clase -->
    <% if (proxima != null && proxima.getClase() != null) { %>
        <div class="card mb-3 shadow-sm">
            <div class="card-body">
                <small class="text-muted">Próxima clase</small>
                <h5 class="m-0"><%= proxima.getClase().getNombre() %> · <%= proxima.getHora() %></h5>
                <small class="text-muted"><i class="bi bi-geo-alt"></i> <%= proxima.getFecha() %></small>
            </div>
        </div>
    <% } %>

    <!-- Acciones rápidas -->
    <h6 class="text-muted mt-4 mb-2">Acciones rápidas</h6>
    <div class="d-grid gap-2">
        <a href="<%= ctx %>/SedeServlet" class="btn btn-outline-dark text-start">
            <i class="bi bi-geo-alt-fill"></i> Ver sedes</a>
        <a href="<%= ctx %>/ReservaServlet" class="btn btn-outline-dark text-start">
            <i class="bi bi-calendar-plus"></i> Reservar clase</a>
        <a href="<%= ctx %>/ReservaServlet?accion=misReservas" class="btn btn-outline-dark text-start">
            <i class="bi bi-list-check"></i> Mis reservas</a>
        <a href="<%= ctx %>/MembresiaServlet" class="btn btn-outline-dark text-start">
            <i class="bi bi-credit-card"></i> Membresías y pagos</a>
    </div>
</div>
</body>
</html>
