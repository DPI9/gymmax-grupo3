<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Usuario" %>
<%
String ctx = request.getContextPath();
Usuario admin = (Usuario) session.getAttribute("usuarioLogueado");
Integer sociosActivos = (Integer) request.getAttribute("kpiSociosActivos");
Double ingresosMes = (Double) request.getAttribute("kpiIngresosMes");
Integer porVencer = (Integer) request.getAttribute("kpiPorVencer");
Integer reservasHoy = (Integer) request.getAttribute("kpiReservasHoy");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .sidebar { background:#1a1a1a; color:#fff; min-height:100vh; padding:20px; }
        .sidebar a { color:#aaa; text-decoration:none; display:block; padding:10px; border-radius:5px; }
        .sidebar a.active, .sidebar a:hover { background:#FF6B00; color:#fff; }
        .brand-gym { color:#FF6B00; font-weight:800; }
        .stat-card { border-left:4px solid #FF6B00; }
        .stat-card .num { font-size:1.8rem; font-weight:bold; }
    </style>
</head>
<body>
<div class="d-flex">
    <div class="sidebar" style="width:240px;">
        <h4 class="mb-4"><span class="brand-gym">Gym</span><strong style="color:#fff;">Max</strong></h4>
        <a href="<%= ctx %>/AdminServlet" class="active"><i class="bi bi-speedometer2"></i> Dashboard</a>
        <a href="<%= ctx %>/AdminServlet?view=socios"><i class="bi bi-people"></i> Socios</a>
        <a href="<%= ctx %>/AdminServlet?view=sedes"><i class="bi bi-geo-alt"></i> Sedes</a>
        <hr>
        <a href="<%= ctx %>/LogoutServlet" class="text-danger"><i class="bi bi-box-arrow-right"></i> Salir</a>
    </div>

    <div class="flex-grow-1 p-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="m-0">Dashboard administrativo</h3>
            <small class="text-muted">
                <i class="bi bi-person-circle"></i>
                <%= admin != null ? admin.getNombreCompleto() : "Admin" %>
            </small>
        </div>

        <div class="row g-3">
            <div class="col-md-3">
                <div class="card stat-card shadow-sm">
                    <div class="card-body">
                        <small class="text-muted">Socios activos</small>
                        <div class="num"><%= sociosActivos != null ? sociosActivos : 0 %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card shadow-sm">
                    <div class="card-body">
                        <small class="text-muted">Ingresos del mes</small>
                        <div class="num">S/ <%= ingresosMes != null ? String.format("%.0f", ingresosMes) : "0" %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card shadow-sm">
                    <div class="card-body">
                        <small class="text-muted">Membresías por vencer (7 días)</small>
                        <div class="num"><%= porVencer != null ? porVencer : 0 %></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card shadow-sm">
                    <div class="card-body">
                        <small class="text-muted">Reservas hoy</small>
                        <div class="num"><%= reservasHoy != null ? reservasHoy : 0 %></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mt-4 shadow-sm">
            <div class="card-body">
                <h6>Bienvenido al panel administrativo</h6>
                <p class="text-muted m-0">Usa el menú izquierdo para gestionar socios y sedes.</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
