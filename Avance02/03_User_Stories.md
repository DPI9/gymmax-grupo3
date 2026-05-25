# User Stories — GymMax (100% completas)

Cada historia sigue el formato estándar de Scrum: **Como [rol] quiero [funcionalidad] para [beneficio]**, acompañada de criterios de aceptación verificables.

---

## Módulo: Autenticación y Registro

### US-01 — Registro de nuevo socio

**Título:** Registro de socio nuevo desde formulario web

**Descripción:** Como visitante del sitio quiero registrarme como nuevo socio del gimnasio ingresando mis datos personales para acceder a los servicios y planes que ofrece GymMax.

**Criterios de aceptación:**
- El formulario solicita: nombres, apellidos, DNI (8 dígitos), correo electrónico, celular (9 dígitos), contraseña (mínimo 8 caracteres) y aceptación de términos.
- Todos los campos marcados con asterisco (*) son obligatorios.
- El DNI y el correo deben ser únicos en el sistema; si ya existen muestra mensaje de error.
- La contraseña debe tener al menos una letra mayúscula, una minúscula y un número.
- Al enviar el formulario con datos válidos, el socio queda registrado y es redirigido al dashboard del socio.
- Se almacena el registro en la base de datos en las tablas USUARIO y SOCIO.

---

### US-02 — Inicio de sesión

**Título:** Inicio de sesión con correo y contraseña

**Descripción:** Como usuario registrado (socio o administrador) quiero iniciar sesión en el sistema con mi correo y contraseña para acceder a las funcionalidades correspondientes a mi rol.

**Criterios de aceptación:**
- El formulario muestra los campos: correo electrónico, contraseña, checkbox "Recordarme" y un enlace "¿Olvidaste tu contraseña?".
- Si las credenciales son válidas y el rol es SOCIO, redirige al dashboard del socio.
- Si las credenciales son válidas y el rol es ADMIN, redirige al dashboard administrativo.
- Si las credenciales son inválidas, muestra el mensaje "Correo o contraseña incorrectos" sin revelar cuál falló.
- Después de 3 intentos fallidos, el sistema muestra una advertencia.
- Si "Recordarme" está marcado, la sesión persiste por 7 días.

---

### US-03 — Recuperación de contraseña

**Título:** Recuperar contraseña olvidada

**Descripción:** Como usuario registrado que olvidó su contraseña quiero recuperarla mediante un enlace enviado a mi correo electrónico para volver a acceder a mi cuenta.

**Criterios de aceptación:**
- El usuario ingresa su correo electrónico en la pantalla de recuperación.
- Si el correo existe en el sistema, se envía un enlace temporal (válido por 30 minutos) al correo.
- Al hacer clic en el enlace, el usuario es redirigido a una pantalla donde puede establecer una nueva contraseña.
- La nueva contraseña debe cumplir las mismas reglas de seguridad que el registro.
- Si el correo no existe en el sistema, se muestra un mensaje genérico sin confirmar la inexistencia (por seguridad).

---

## Módulo: Socio

### US-04 — Dashboard del socio

**Título:** Visualización del dashboard personal del socio

**Descripción:** Como socio quiero ver mi dashboard personal al iniciar sesión para conocer rápidamente el estado de mi membresía, mis estadísticas y las acciones disponibles.

**Criterios de aceptación:**
- Se muestra el nombre del socio en el saludo: "Hola, [Nombre]".
- Se muestra una tarjeta con la membresía activa: nombre del plan y fecha de vencimiento.
- Se muestran contadores de: asistencias acumuladas y reservas activas.
- Se muestra la próxima clase reservada con nombre, hora y sede.
- Se muestran accesos rápidos a: Ver sedes, Reservar clase, Mis reservas.
- Si la membresía está vencida o por vencer (≤ 7 días), se muestra una alerta visual.

---

### US-05 — Listado de sedes

**Título:** Consulta del listado de sedes disponibles

**Descripción:** Como socio quiero ver todas las sedes del gimnasio para elegir cuál visitar según ubicación y servicios.

**Criterios de aceptación:**
- Se muestra una lista o grid con todas las sedes activas.
- Cada sede muestra: nombre, dirección, distrito, horario de atención y un indicador de cupos disponibles del día (con código de color: verde = disponible, naranja = pocos cupos, rojo = sin cupos).
- Al hacer clic en una sede, navega al detalle de esa sede con sus clases y horarios.
- Existe un campo de búsqueda para filtrar sedes por distrito o nombre.

---

### US-06 — Filtro de sedes por distrito

**Título:** Filtrar sedes por distrito

**Descripción:** Como socio quiero filtrar las sedes por distrito para encontrar rápidamente la más cercana a mi ubicación.

**Criterios de aceptación:**
- Existe un campo de búsqueda o dropdown con todos los distritos donde GymMax tiene sedes.
- Al escribir o seleccionar un distrito, la lista se filtra dinámicamente sin recargar la página.
- Si no hay sedes en el distrito buscado, se muestra el mensaje "No se encontraron sedes en este distrito".
- Existe un botón "Limpiar filtros" para resetear la búsqueda.

---

### US-07 — Visualización de clases por sede

**Título:** Ver clases disponibles en una sede

**Descripción:** Como socio quiero ver todas las clases programadas en una sede específica para una fecha dada, para decidir cuál reservar.

**Criterios de aceptación:**
- Se muestra el nombre de la sede en el encabezado.
- Se puede seleccionar la fecha (por defecto la fecha actual).
- Se listan las clases del día: nombre, hora, instructor, tipo (CrossFit, Yoga, Spinning, Zumba, etc.) y cupos disponibles/máximos (ejemplo: "10/15 cupos").
- Cada clase tiene un botón "Reservar" si hay cupos disponibles, o "Sin cupos" si está llena.
- El estado de cupos se actualiza en tiempo real (al menos al refrescar la página).

---

### US-08 — Reserva de clase

**Título:** Reservar una clase

**Descripción:** Como socio con membresía activa quiero reservar una clase específica en una sede y horario para asegurar mi cupo.

**Criterios de aceptación:**
- El sistema verifica que el socio tenga membresía activa antes de permitir la reserva.
- El sistema verifica que haya cupos disponibles en la clase.
- Si todo es válido, la reserva se registra con estado "CONFIRMADA" y se descuenta un cupo de la clase.
- Si no hay cupos, se ofrece la opción "Lista de espera".
- El socio no puede reservar la misma clase dos veces para el mismo horario.
- Se muestra un mensaje de confirmación: "Reserva confirmada para [Clase] el [Fecha] a las [Hora] en [Sede]".

---

### US-09 — Listado de mis reservas

**Título:** Ver mis reservas

**Descripción:** Como socio quiero ver el listado de mis reservas (próximas y pasadas) para llevar el control de mis clases.

**Criterios de aceptación:**
- Existen dos pestañas: "Próximas (N)" y "Pasadas".
- Las próximas muestran: nombre de la clase, fecha, hora, sede, estado (Confirmada / Lista de espera).
- Las pasadas muestran: nombre de la clase, fecha, sede y si el socio asistió o no.
- Las reservas próximas tienen un botón para cancelarlas (si la política lo permite).
- Existe un botón "+ Nueva reserva" para ir directamente a reservar.

---

### US-10 — Cancelación de reserva

**Título:** Cancelar una reserva

**Descripción:** Como socio quiero cancelar una reserva confirmada para liberar el cupo y no afectar mi historial de asistencias.

**Criterios de aceptación:**
- Solo se pueden cancelar reservas con estado "CONFIRMADA" cuya fecha sea futura.
- La cancelación debe hacerse con al menos 2 horas de anticipación a la hora de la clase.
- Si se cumple la condición, la reserva pasa a estado "CANCELADA" y el cupo se devuelve a la clase.
- Si no se cumple, se muestra el mensaje "No se puede cancelar la reserva (menos de 2 horas para la clase)".
- Se solicita confirmación antes de cancelar mediante un modal.

---

## Módulo: Membresías y Pagos

### US-11 — Contratación o renovación de plan

**Título:** Contratar o renovar plan de membresía

**Descripción:** Como socio quiero contratar un nuevo plan o renovar mi membresía actual para mantener acceso a los servicios del gimnasio.

**Criterios de aceptación:**
- Se muestran los planes disponibles: Básico, Premium y Anual con sus precios, beneficios y duración en días.
- Al seleccionar un plan, se procede al checkout con resumen del pedido.
- Si el socio ya tiene una membresía activa, el sistema indica que será renovada y la nueva fecha de vencimiento se calcula sumando la duración al vencimiento actual.
- Si el socio no tiene membresía, la fecha de inicio es la fecha actual.
- Se solicita método de pago (Yape, Plin, Tarjeta).
- Al confirmar el pago, se registra en las tablas MEMBRESIA y PAGO.
- Se genera y muestra un comprobante con número de operación.

---

### US-12 — Historial de pagos

**Título:** Visualización del historial de pagos del socio

**Descripción:** Como socio quiero ver todos mis pagos realizados para llevar el control de mis gastos en el gimnasio.

**Criterios de aceptación:**
- Se lista cada pago con: fecha, plan contratado, método de pago, número de operación, monto y estado (OK, Fallido, Reembolsado).
- Se puede filtrar por rango de fechas y por estado.
- Cada pago tiene un enlace para descargar el comprobante en formato PDF.
- Los registros se ordenan del más reciente al más antiguo por defecto.

---

## Módulo: Administración

### US-13 — Login del administrador

**Título:** Inicio de sesión con rol administrador

**Descripción:** Como administrador quiero iniciar sesión y ser redirigido a mi dashboard administrativo para gestionar el gimnasio.

**Criterios de aceptación:**
- Utiliza el mismo formulario de login que el socio.
- El sistema valida el rol del usuario (ADMIN) tras autenticar.
- Si el rol es ADMIN, redirige a `/adminDashboard.jsp`.
- Si el rol es SOCIO, redirige a `/dashboardSocio.jsp`.
- Un socio NO puede acceder a rutas administrativas (control de acceso por rol).

---

### US-14 — Dashboard administrativo

**Título:** Visualización del dashboard administrativo con KPIs

**Descripción:** Como administrador quiero ver indicadores clave del gimnasio en mi dashboard para tomar decisiones operativas y estratégicas.

**Criterios de aceptación:**
- Se muestran al menos 4 tarjetas KPI: Socios activos, Ingresos del mes (S/), Membresías por vencer en los próximos 7 días, Reservas del día.
- Cada KPI muestra un indicador de variación porcentual vs. el período anterior (ej. ↑ +12%).
- Se muestra un gráfico de líneas con los ingresos de los últimos 6 meses.
- Se muestra una lista de "Actividad reciente" con las últimas 5-10 acciones del sistema (registros, pagos, reservas, vencimientos).
- El dashboard se actualiza con datos reales de la base de datos.

---

### US-15 — Gestión de socios (CRUD)

**Título:** CRUD completo de socios

**Descripción:** Como administrador quiero crear, ver, editar y eliminar socios para mantener actualizado el registro de membresías del gimnasio.

**Criterios de aceptación:**
- Se muestra una tabla paginada con columnas: ID, Nombre completo, DNI, Correo, Plan, Sede, Estado (Activo/Vencido/Por vencer), Vencimiento, Acciones.
- Existen filtros por: nombre/DNI/correo (buscador), Sede, Plan y Estado.
- Botón "Nuevo socio" abre formulario para registrar manualmente un socio.
- Acciones por fila: editar (lápiz) y eliminar (basurero), con confirmación antes de eliminar.
- Al eliminar, el socio queda marcado como inactivo (soft delete) en lugar de borrarse físicamente.
- Los cambios persisten en MySQL.

---

### US-16 — Gestión de sedes (CRUD)

**Título:** CRUD completo de sedes

**Descripción:** Como administrador quiero crear, ver, editar y eliminar sedes para gestionar la expansión y operación de la cadena de gimnasios.

**Criterios de aceptación:**
- Se muestra un listado de sedes con: nombre, dirección, distrito, horarios, capacidad y estado.
- Botón "Nueva sede" abre formulario con campos requeridos.
- Cada sede puede editarse y eliminarse (con validación: no se puede eliminar si tiene socios o reservas activas).
- Al eliminar una sede con dependencias, se ofrece desactivarla en lugar de borrarla.
- Los cambios se reflejan inmediatamente en el listado de sedes visible al socio.

---

### US-17 — Generación de reportes

**Título:** Generar reportes de ingresos y operación

**Descripción:** Como administrador quiero generar reportes filtrables para analizar el desempeño financiero y operativo del gimnasio.

**Criterios de aceptación:**
- Se selecciona el tipo de reporte: Ingresos por sede, Membresías por estado, Reservas por clase, Morosidad.
- Se filtra por rango de fechas (Desde / Hasta) y por sede (Todas o una específica).
- Se muestra un gráfico (barras o líneas) según el tipo de reporte.
- Se muestra un resumen numérico con la tabla de detalle.
- Botones "Generar", "Resetear", "Exportar Excel" y "Exportar PDF" están disponibles.

---

### US-18 — Exportación de reportes

**Título:** Exportar reportes a Excel y PDF

**Descripción:** Como administrador quiero exportar los reportes generados a Excel o PDF para compartirlos con la gerencia o archivarlos.

**Criterios de aceptación:**
- El botón "Excel" descarga un archivo .xlsx con los datos del reporte actual.
- El botón "PDF" descarga un archivo .pdf con formato profesional (logo, fecha, encabezado).
- El nombre del archivo incluye el tipo de reporte y la fecha (ej. `Ingresos_por_sede_2026-05-25.xlsx`).
- Se respetan los filtros aplicados al momento de exportar.

---

## Resumen ejecutivo

| Módulo | US | Total |
|---|---|---|
| Autenticación y Registro | US-01, US-02, US-03 | 3 |
| Socio | US-04, US-05, US-06, US-07 | 4 |
| Reservas | US-08, US-09, US-10 | 3 |
| Membresías y Pagos | US-11, US-12 | 2 |
| Administración | US-13, US-14, US-15, US-16, US-17, US-18 | 6 |
| **TOTAL** | | **18** |
