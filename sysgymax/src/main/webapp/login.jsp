<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String ctx = request.getContextPath();
   String error = (String) request.getAttribute("error");
   String mensaje = (String) request.getAttribute("mensaje"); %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GymMax — Iniciar sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; min-height:100vh; display:flex; align-items:center; }
        .brand-gym { color:#FF6B00; font-weight:800; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .btn-gym:hover { background:#e85f00; color:#fff; }
        .card-login { box-shadow:0 4px 20px rgba(0,0,0,.1); border:none; }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card card-login">
                <div class="card-body p-4">
                    <h1 class="text-center mb-2"><span class="brand-gym">Gym</span><strong>Max</strong></h1>
                    <p class="text-center text-muted mb-4">Entrena sin límites</p>

                    <% if (error != null) { %>
                        <div class="alert alert-danger"><%= error %></div>
                    <% } %>
                    <% if (mensaje != null) { %>
                        <div class="alert alert-success"><%= mensaje %></div>
                    <% } %>

                    <form method="post" action="<%= ctx %>/LoginServlet">
                        <div class="mb-3">
                            <label class="form-label">Correo electrónico</label>
                            <input type="email" name="correo" class="form-control" required
                                   value="socio@gymmax.com">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="recordar">
                            <label class="form-check-label" for="recordar">Recordarme</label>
                        </div>
                        <button type="submit" class="btn btn-gym w-100">
                            <i class="bi bi-box-arrow-in-right"></i> Ingresar
                        </button>
                    </form>
                    <p class="text-center mt-3 mb-0">
                        ¿No tienes cuenta?
                        <a href="<%= ctx %>/registro.jsp" class="brand-gym text-decoration-none fw-bold">Regístrate</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
