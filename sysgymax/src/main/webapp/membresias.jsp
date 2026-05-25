<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Plan,com.gymmax.model.Membresia,com.gymmax.model.Pago,java.util.List" %>
<%
String ctx = request.getContextPath();
List<Plan> planes = (List<Plan>) request.getAttribute("planes");
Membresia actual = (Membresia) request.getAttribute("membresiaActual");
List<Pago> historial = (List<Pago>) request.getAttribute("historial");
String mensaje = (String) request.getAttribute("mensaje");
String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membresías — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .header-gym { background:#FF6B00; color:#fff; padding:14px 20px; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .plan-card.recommended { border:2px solid #FF6B00; }
    </style>
</head>
<body>
<div class="header-gym d-flex align-items-center">
    <a href="<%= ctx %>/DashboardServlet" class="text-white text-decoration-none me-2">&larr;</a>
    <h5 class="m-0">Planes y Membresía</h5>
</div>

<div class="container py-3">
    <% if (mensaje != null) { %><div class="alert alert-success"><%= mensaje %></div><% } %>
    <% if (error != null) { %><div class="alert alert-danger"><%= error %></div><% } %>

    <% if (actual != null && actual.getPlan() != null) { %>
        <div class="card mb-3 bg-warning-subtle">
            <div class="card-body">
                <small>Membresía activa</small>
                <h6 class="m-0"><%= actual.getPlan().getNombre() %> — vence el <%= actual.getFechaFin() %></h6>
            </div>
        </div>
    <% } %>

    <h6>Elige un plan</h6>
    <div class="row g-3">
        <% if (planes != null) for (Plan p : planes) { %>
            <div class="col-md-4">
                <div class="card plan-card h-100 shadow-sm <%= "PREM".equals(p.getTipo())?"recommended":"" %>">
                    <div class="card-body text-center">
                        <h5><%= p.getNombre() %></h5>
                        <h3 class="text-warning">S/ <%= String.format("%.2f", p.getPrecio()) %></h3>
                        <p class="text-muted small"><%= p.getDuracionDias() %> días de acceso</p>
                        <form method="post" action="<%= ctx %>/MembresiaServlet">
                            <input type="hidden" name="idPlan" value="<%= p.getIdPlan() %>">
                            <select name="metodo" class="form-select form-select-sm mb-2" required>
                                <option value="YAPE">Yape</option>
                                <option value="PLIN">Plin</option>
                                <option value="TARJ">Tarjeta</option>
                            </select>
                            <button class="btn btn-gym w-100">Contratar</button>
                        </form>
                    </div>
                </div>
            </div>
        <% } %>
    </div>

    <h6 class="mt-4">Historial de pagos</h6>
    <% if (historial == null || historial.isEmpty()) { %>
        <p class="text-muted">Sin pagos registrados.</p>
    <% } else { %>
        <table class="table table-sm table-striped">
            <thead><tr><th>Fecha</th><th>Método</th><th>Operación</th><th class="text-end">Monto</th><th>Estado</th></tr></thead>
            <tbody>
            <% for (Pago p : historial) { %>
                <tr>
                    <td><%= p.getFechaPago() %></td>
                    <td><%= p.getMetodo() %></td>
                    <td><%= p.getNroOperacion() %></td>
                    <td class="text-end">S/ <%= String.format("%.2f", p.getMonto()) %></td>
                    <td><span class="badge bg-success"><%= p.getEstado() %></span></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } %>
</div>
</body>
</html>
