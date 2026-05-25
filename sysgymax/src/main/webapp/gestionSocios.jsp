<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Socio,java.util.List" %>
<%
String ctx = request.getContextPath();
List<Socio> socios = (List<Socio>) request.getAttribute("socios");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de socios — GymMax</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .sidebar { background:#1a1a1a; color:#fff; min-height:100vh; padding:20px; }
        .sidebar a { color:#aaa; text-decoration:none; display:block; padding:10px; border-radius:5px; }
        .sidebar a.active, .sidebar a:hover { background:#FF6B00; color:#fff; }
        .brand-gym { color:#FF6B00; font-weight:800; }
    </style>
</head>
<body>
<div class="d-flex">
    <div class="sidebar" style="width:240px;">
        <h4><span class="brand-gym">Gym</span><strong style="color:#fff;">Max</strong></h4>
        <a href="<%= ctx %>/AdminServlet">Dashboard</a>
        <a href="<%= ctx %>/AdminServlet?view=socios" class="active">Socios</a>
        <a href="<%= ctx %>/AdminServlet?view=sedes">Sedes</a>
        <hr><a href="<%= ctx %>/LogoutServlet" class="text-danger">Salir</a>
    </div>
    <div class="flex-grow-1 p-4">
        <h3>Gestión de socios</h3>
        <table class="table table-striped table-bordered mt-3 shadow-sm bg-white">
            <thead class="table-dark">
                <tr><th>ID</th><th>DNI</th><th>Celular</th><th>Fecha registro</th><th>Acciones</th></tr>
            </thead>
            <tbody>
                <% if (socios != null) for (Socio s : socios) { %>
                    <tr>
                        <td>S-<%= s.getIdSocio() %></td>
                        <td><%= s.getDni() %></td>
                        <td><%= s.getCelular() != null ? s.getCelular() : "-" %></td>
                        <td><%= s.getFechaReg() %></td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary">Editar</button>
                            <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
