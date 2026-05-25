<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Sede,java.util.List" %>
<%
String ctx = request.getContextPath();
List<Sede> sedes = (List<Sede>) request.getAttribute("sedes");
String distritoFiltro = (String) request.getAttribute("distrito");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sedes — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .header-gym { background:#FF6B00; color:#fff; padding:14px 20px; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
    </style>
</head>
<body>
<div class="header-gym d-flex align-items-center">
    <a href="<%= ctx %>/DashboardServlet" class="text-white text-decoration-none me-2">&larr;</a>
    <h5 class="m-0">Sedes GymMax</h5>
</div>

<div class="container py-3">
    <form method="get" action="<%= ctx %>/SedeServlet" class="mb-3 d-flex gap-2">
        <input type="text" name="distrito" class="form-control" placeholder="Buscar por distrito..."
               value="<%= distritoFiltro != null ? distritoFiltro : "" %>">
        <button type="submit" class="btn btn-gym">Buscar</button>
    </form>

    <% if (sedes == null || sedes.isEmpty()) { %>
        <div class="alert alert-warning">No se encontraron sedes.</div>
    <% } else { %>
        <% for (Sede s : sedes) { %>
            <div class="card mb-2 shadow-sm">
                <div class="card-body py-3 d-flex align-items-center">
                    <div class="bg-dark text-white rounded p-3 me-3 fw-bold">
                        <%= s.getNombre().substring(s.getNombre().indexOf(" ")+1, s.getNombre().indexOf(" ")+2) %>
                    </div>
                    <div class="flex-grow-1">
                        <h6 class="mb-1"><%= s.getNombre() %></h6>
                        <small class="text-muted">
                            <i class="bi bi-geo-alt"></i> <%= s.getDireccion() %><br>
                            <i class="bi bi-clock"></i> <%= s.getHoraApertura() %> - <%= s.getHoraCierre() %>
                        </small>
                    </div>
                    <a href="<%= ctx %>/SedeServlet?id=<%= s.getIdSede() %>" class="btn btn-sm btn-outline-dark">
                        Ver clases
                    </a>
                </div>
            </div>
        <% } %>
    <% } %>
</div>
</body>
</html>
