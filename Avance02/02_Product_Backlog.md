# Product Backlog — GymMax

El Product Backlog reúne todos los requerimientos funcionales y no funcionales identificados para el sistema GymMax, priorizados según valor de negocio y complejidad técnica. La estimación se realiza en Story Points utilizando la escala de Fibonacci (1, 2, 3, 5, 8, 13).

## Requerimientos funcionales

| ID | Requerimiento | Módulo | Prioridad | Story Points | Sprint |
|---|---|---|---|---|---|
| RF-01 | Registrar nuevo socio mediante formulario web responsive | Autenticación | Alta | 5 | 1 |
| RF-02 | Iniciar sesión con correo electrónico y contraseña | Autenticación | Alta | 3 | 1 |
| RF-03 | Recuperar contraseña vía correo electrónico | Autenticación | Media | 5 | 3 |
| RF-04 | Validar campos del formulario de registro en tiempo real | Autenticación | Alta | 3 | 1 |
| RF-05 | Visualizar dashboard personal del socio con estado de membresía | Socio | Alta | 5 | 1 |
| RF-06 | Consultar listado de sedes disponibles | Socio | Alta | 3 | 1 |
| RF-07 | Filtrar sedes por distrito | Socio | Media | 2 | 2 |
| RF-08 | Ver detalle de servicios, horarios y cupos de una sede | Socio | Alta | 3 | 2 |
| RF-09 | Reservar una clase en una sede y horario específicos | Reservas | Alta | 8 | 2 |
| RF-10 | Visualizar listado de "Mis reservas" (próximas y pasadas) | Reservas | Alta | 5 | 2 |
| RF-11 | Cancelar una reserva confirmada | Reservas | Media | 3 | 2 |
| RF-12 | Contratar un plan de membresía (Básico/Premium/Anual) | Membresías | Alta | 8 | 3 |
| RF-13 | Renovar membresía vencida o por vencer | Membresías | Alta | 5 | 3 |
| RF-14 | Calcular automáticamente la fecha de vencimiento de la membresía | Membresías | Alta | 2 | 3 |
| RF-15 | Generar comprobante digital del pago | Pagos | Alta | 5 | 3 |
| RF-16 | Procesar pagos mediante Yape, Plin o Tarjeta | Pagos | Alta | 8 | 3 |
| RF-17 | Visualizar historial de pagos del socio | Pagos | Media | 3 | 3 |
| RF-18 | Iniciar sesión como administrador con validación de rol | Administración | Alta | 3 | 1 |
| RF-19 | Visualizar dashboard administrativo con KPIs en tiempo real | Administración | Alta | 8 | 2 |
| RF-20 | Crear, leer, actualizar y eliminar socios (CRUD) | Administración | Alta | 8 | 2 |
| RF-21 | Filtrar listado de socios por sede, plan y estado | Administración | Media | 3 | 2 |
| RF-22 | Crear, leer, actualizar y eliminar sedes (CRUD) | Administración | Alta | 5 | 3 |
| RF-23 | Gestionar clases y horarios por sede | Administración | Media | 5 | 4 |
| RF-24 | Generar reportes de ingresos por sede y período | Reportes | Alta | 8 | 4 |
| RF-25 | Exportar reportes a Excel y PDF | Reportes | Media | 5 | 4 |
| RF-26 | Registrar asistencia del socio al ingresar a la sede | Asistencias | Media | 5 | 4 |
| RF-27 | Notificar al socio sobre vencimiento próximo de membresía | Notificaciones | Baja | 8 | 5 |
| RF-28 | Listar membresías por vencer al administrador | Administración | Media | 3 | 4 |

## Requerimientos no funcionales

| ID | Requerimiento | Categoría | Prioridad |
|---|---|---|---|
| RNF-01 | El sistema debe ser responsive (Mobile, Tablet, Desktop) usando Bootstrap 5.3 | Usabilidad | Alta |
| RNF-02 | Tiempo de respuesta máximo de 2 segundos para operaciones CRUD | Rendimiento | Alta |
| RNF-03 | Disponibilidad del sistema 99% durante horarios de atención del gimnasio | Disponibilidad | Alta |
| RNF-04 | Las contraseñas deben almacenarse encriptadas (BCrypt o equivalente) | Seguridad | Alta |
| RNF-05 | Sesión HTTP con timeout de 30 minutos por inactividad | Seguridad | Alta |
| RNF-06 | El sistema debe soportar al menos 200 usuarios concurrentes | Escalabilidad | Media |
| RNF-07 | Compatibilidad con navegadores modernos (Chrome, Edge, Firefox, Safari) | Compatibilidad | Alta |
| RNF-08 | Toda la información monetaria en soles peruanos con 2 decimales | Localización | Media |
| RNF-09 | Interfaz en español (idioma único en esta versión) | Localización | Alta |
| RNF-10 | Código fuente versionado en repositorio Git | Mantenibilidad | Media |
| RNF-11 | Arquitectura por capas siguiendo el patrón MVC + DAO + Facade | Mantenibilidad | Alta |
| RNF-12 | Validación de datos en frontend y backend (doble capa) | Seguridad | Alta |

## Distribución por Sprints (planificación tentativa)

| Sprint | Foco | RFs incluidos | Total SP |
|---|---|---|---|
| Sprint 1 | Autenticación + base del socio | RF-01, RF-02, RF-04, RF-05, RF-06, RF-18 | 22 |
| Sprint 2 | Sedes, clases y reservas | RF-07, RF-08, RF-09, RF-10, RF-11, RF-19, RF-20, RF-21 | 37 |
| Sprint 3 | Membresías y pagos | RF-03, RF-12, RF-13, RF-14, RF-15, RF-16, RF-17, RF-22 | 41 |
| Sprint 4 | Reportes y asistencias | RF-23, RF-24, RF-25, RF-26, RF-28 | 26 |
| Sprint 5 | Notificaciones y mejoras | RF-27 | 8 |

**Total: 134 Story Points distribuidos en 5 sprints**
