<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String ctx = request.getContextPath();
   String error = (String) request.getAttribute("error"); %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GymMax — Registro de socio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background:#f5f5f5; }
        .brand-gym { color:#FF6B00; font-weight:800; }
        .btn-gym { background:#FF6B00; color:#fff; border:none; }
        .btn-gym:hover { background:#e85f00; color:#fff; }
        .header-gym { background:#FF6B00; color:#fff; padding:12px 20px; }
    </style>
</head>
<body>
<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-md-7">
            <div class="card shadow">
                <div class="header-gym d-flex align-items-center">
                    <a href="<%= ctx %>/login.jsp" class="text-white text-decoration-none me-2">&larr;</a>
                    <h5 class="m-0">Registro de socio</h5>
                </div>
                <div class="card-body">
                    <% if (error != null) { %>
                        <div class="alert alert-danger"><%= error %></div>
                    <% } %>
                    <form method="post" action="<%= ctx %>/RegistroServlet">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Nombres *</label>
                                <input type="text" name="nombres" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Apellidos *</label>
                                <input type="text" name="apellidos" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">DNI *</label>
                                <input type="text" name="dni" class="form-control"
                                       pattern="[0-9]{8}" maxlength="8" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Celular</label>
                                <input type="text" name="celular" class="form-control"
                                       pattern="[0-9]{9}" maxlength="9">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Fecha de nacimiento</label>
                                <input type="date" name="fechaNac" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Género</label>
                                <select name="genero" class="form-select">
                                    <option value="M">Masculino</option>
                                    <option value="F">Femenino</option>
                                    <option value="O">Otro</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label class="form-label">Dirección</label>
                                <input type="text" name="direccion" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Correo *</label>
                                <input type="email" name="correo" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Contraseña *</label>
                                <input type="password" name="password" class="form-control"
                                       minlength="8" required>
                            </div>
                            <div class="col-12 form-check ms-3">
                                <input class="form-check-input" type="checkbox" required id="terms">
                                <label class="form-check-label" for="terms">
                                    Acepto los términos y condiciones
                                </label>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-gym w-100">
                                    Crear cuenta
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
