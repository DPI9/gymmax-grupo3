<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gymmax.model.Sede,java.util.List" %>
<%
String ctx = request.getContextPath();
List<Sede> sedes = (List<Sede>) request.getAttribute("sedes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de sedes — GymMax</title>
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
        <a href="<%= ctx %>/AdminServlet?view=socios">Socios</a>
        <a href="<%= ctx %>/AdminServlet?view=sedes" class="active">Sedes</a>
        <hr><a href="<%= ctx %>/LogoutServlet" class="text-danger">Salir</a>
    </div>
    <div class="flex-grow-1 p-4">
        <h3>Gestión de sedes</h3>
        <table class="table table-striped table-bordered mt-3 shadow-sm bg-white">
            <thead class="table-dark">
                <tr><th>ID</th><th>Nombre</th><th>Distrito</th><th>Horario</th><th>Capacidad</th><th>Acciones</th></tr>
            </thead>
            <tbody>
                <% if (sedes != null) for (Sede s : sedes) { %>
                    <tr>
                        <td><%= s.getIdSede() %></td>
                        <td><%= s.getNombre() %></td>
                        <td><%= s.getDistrito() %></td>
                        <td><%= s.getHoraApertura() %> - <%= s.getHoraCierre() %></td>
                        <td><%= s.getCapacidad() %></td>
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
