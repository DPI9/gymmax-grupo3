# Diccionario de Clases — GymMax

Descripción detallada de cada clase del sistema GymMax, incluyendo atributos, métodos, parámetros, valores de retorno y responsabilidades.

---

## Capa DTO (`com.gymmax.dto`)

### Clase: Usuario

**Descripción:** Representa a una persona registrada en el sistema con credenciales de acceso. Es la entidad base para cualquier usuario (socio o administrador).

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idUsuario | int | private | Identificador único autoincremental |
| correo | String | private | Correo electrónico único usado para login |
| password | String | private | Contraseña encriptada |
| nombres | String | private | Nombres del usuario |
| apellidos | String | private | Apellidos del usuario |
| rol | String | private | Rol del usuario: "SOCIO" o "ADMIN" |
| creadoEn | LocalDateTime | private | Timestamp de creación del registro |

**Métodos públicos:** Getters y setters de cada atributo. Constructor vacío y constructor con todos los parámetros.

---

### Clase: Socio

**Descripción:** Cliente del gimnasio. Extiende la información personal del Usuario con datos específicos del socio.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idSocio | int | private | Identificador único autoincremental |
| idUsuario | int | private | FK al Usuario asociado (relación 1:1) |
| dni | String | private | DNI peruano de 8 dígitos, único |
| celular | String | private | Número celular de 9 dígitos |
| fechaNac | LocalDate | private | Fecha de nacimiento |
| genero | char | private | 'M', 'F' u 'O' |
| direccion | String | private | Dirección de residencia |
| fechaReg | LocalDate | private | Fecha de registro como socio |

**Métodos públicos:** Getters y setters. Constructor vacío y constructor parametrizado.

---

### Clase: Plan

**Descripción:** Tipo de membresía ofrecido por GymMax (Básico, Premium, Anual).

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idPlan | int | private | Identificador único |
| nombre | String | private | Nombre comercial (ej. "Plan Premium") |
| tipo | String | private | Categoría: "BASIC", "PREM", "ANUAL" |
| precio | double | private | Precio en soles peruanos |
| duracionDias | int | private | Duración del plan en días (30 o 365) |
| activo | boolean | private | Indica si el plan está disponible para contratar |

**Métodos públicos:** Getters y setters.

---

### Clase: Membresia

**Descripción:** Contrato vigente entre un socio y un plan, con fechas y estado.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idMembresia | int | private | Identificador único |
| idSocio | int | private | FK al Socio titular |
| idPlan | int | private | FK al Plan contratado |
| fechaInicio | LocalDate | private | Fecha de inicio de la vigencia |
| fechaFin | LocalDate | private | Fecha de vencimiento |
| estado | String | private | "ACT" (activa), "VEN" (vencida), "SUS" (suspendida) |
| monto | double | private | Monto pagado por esta membresía |
| renovacionAuto | boolean | private | Si se renovará automáticamente al vencer |

**Métodos públicos:**

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| isVigente() | — | boolean | true si fecha actual ≤ fechaFin y estado = "ACT" |
| diasParaVencer() | — | int | Días que faltan para vencer (negativo si ya venció) |
| Getters/Setters | — | — | Acceso a todos los atributos |

---

### Clase: Pago

**Descripción:** Transacción financiera asociada a una membresía (compra inicial o renovación).

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idPago | int | private | Identificador único |
| idMembresia | int | private | FK a la Membresia relacionada |
| metodo | String | private | "YAPE", "PLIN" o "TARJ" |
| monto | double | private | Monto pagado |
| fechaPago | LocalDateTime | private | Timestamp del pago |
| nroOperacion | String | private | Código de operación generado |
| estado | String | private | "OK", "FAIL", "REF" (reembolsado) |
| comprobanteUrl | String | private | URL del comprobante digital |

**Métodos públicos:** Getters y setters.

---

### Clase: Sede

**Descripción:** Local físico del gimnasio.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idSede | int | private | Identificador único |
| nombre | String | private | Nombre de la sede (ej. "Sede Miraflores") |
| direccion | String | private | Dirección completa |
| distrito | String | private | Distrito de Lima |
| telefono | String | private | Teléfono fijo de contacto |
| horaApertura | LocalTime | private | Hora de apertura diaria |
| horaCierre | LocalTime | private | Hora de cierre diaria |
| capacidad | int | private | Capacidad máxima total |

**Métodos públicos:** Getters y setters.

---

### Clase: Clase

**Descripción:** Actividad programada en una sede (CrossFit, Yoga, Spinning, etc.).

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idClase | int | private | Identificador único |
| idSede | int | private | FK a la Sede donde se dicta |
| nombre | String | private | Nombre de la clase |
| tipo | String | private | Categoría: "CROSS", "YOGA", "SPIN", etc. |
| instructor | String | private | Nombre del instructor |
| diaSemana | int | private | 1-7 (1=Lunes, 7=Domingo) |
| horaInicio | LocalTime | private | Hora de inicio de la clase |
| cupoMaximo | int | private | Cupo máximo de la clase |

**Métodos públicos:** Getters y setters.

---

### Clase: HorarioClase

**Descripción:** Instancia específica de una clase en una fecha concreta, con control de cupos disponibles.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idHorario | int | private | Identificador único |
| idClase | int | private | FK a la Clase |
| fechaEspecifica | LocalDate | private | Fecha exacta del horario |
| cupoActual | int | private | Cupos ya reservados |
| cupoDisponible | int | private | Cupos restantes |
| estado | String | private | "DISP" (disponible) o "LLENO" |

**Métodos públicos:**

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| hayCupo() | — | boolean | true si cupoDisponible > 0 |
| Getters/Setters | — | — | Acceso a todos los atributos |

---

### Clase: Reserva

**Descripción:** Cupo tomado por un socio para una clase en una fecha y hora específicas.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idReserva | int | private | Identificador único |
| idSocio | int | private | FK al Socio que reserva |
| idClase | int | private | FK a la Clase reservada |
| fecha | LocalDate | private | Fecha de la clase |
| hora | LocalTime | private | Hora de la clase |
| estado | String | private | "CONF" (confirmada), "CAN" (cancelada), "LE" (lista de espera) |
| creadoEn | LocalDateTime | private | Timestamp de la reserva |
| canceladoEn | LocalDateTime | private | Timestamp de cancelación (puede ser null) |

**Métodos públicos:**

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| puedeCancelarse() | — | boolean | true si la reserva es futura y faltan ≥ 2 horas |
| Getters/Setters | — | — | Acceso a todos los atributos |

---

### Clase: Asistencia

**Descripción:** Registro del ingreso o salida de un socio a una sede.

**Atributos:**

| Atributo | Tipo | Visibilidad | Descripción |
|---|---|---|---|
| idAsistencia | int | private | Identificador único |
| idSocio | int | private | FK al Socio |
| idSede | int | private | FK a la Sede |
| fechaHora | LocalDateTime | private | Timestamp del registro |
| tipo | String | private | "ING" (ingreso) o "SAL" (salida) |
| registradoPor | int | private | ID del usuario que registró |

**Métodos públicos:** Getters y setters.

---

## Capa DAO (`com.gymmax.dao`)

### Interfaz: IUsuarioDAO

**Descripción:** Contrato CRUD para Usuario.

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| listar() | — | List\<Usuario\> | Devuelve todos los usuarios |
| buscarPorId | id:int | Usuario | Busca por PK |
| buscarPorCorreo | correo:String | Usuario | Para validar login |
| registrar | u:Usuario | int | Inserta y retorna el ID generado |
| actualizar | u:Usuario | boolean | true si actualizó al menos 1 fila |
| eliminar | id:int | boolean | true si eliminó al menos 1 fila |

### Clase: UsuarioDAOImpl (implements IUsuarioDAO)

**Descripción:** Implementación JDBC. Usa `Conexion.getConnection()` para abrir conexiones y `PreparedStatement` para ejecutar SQL parametrizado.

### Interfaz: ISocioDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listar() | — | List\<Socio\> |
| buscarPorId | id:int | Socio |
| buscarPorDni | dni:String | Socio |
| buscarPorUsuario | idUsuario:int | Socio |
| registrar | s:Socio | int |
| actualizar | s:Socio | boolean |
| eliminar | id:int | boolean |
| filtrar | sede:Integer, plan:Integer, estado:String | List\<Socio\> |

### Interfaz: IPlanDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listar() | — | List\<Plan\> |
| listarActivos() | — | List\<Plan\> |
| buscarPorId | id:int | Plan |
| registrar | p:Plan | int |
| actualizar | p:Plan | boolean |
| desactivar | id:int | boolean |

### Interfaz: IMembresiaDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listarPorSocio | idSocio:int | List\<Membresia\> |
| buscarActivaPorSocio | idSocio:int | Membresia |
| registrar | m:Membresia | int |
| renovar | idMembresia:int, dias:int | boolean |
| listarPorVencer | dias:int | List\<Membresia\> |

### Interfaz: IPagoDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listarPorMembresia | idMembresia:int | List\<Pago\> |
| listarPorSocio | idSocio:int | List\<Pago\> |
| registrar | p:Pago | int |
| sumarIngresos | desde:LocalDate, hasta:LocalDate, idSede:Integer | double |

### Interfaz: ISedeDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listar() | — | List\<Sede\> |
| buscarPorId | id:int | Sede |
| filtrarPorDistrito | distrito:String | List\<Sede\> |
| registrar | s:Sede | int |
| actualizar | s:Sede | boolean |
| eliminar | id:int | boolean |

### Interfaz: IClaseDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listar() | — | List\<Clase\> |
| listarPorSede | idSede:int | List\<Clase\> |
| listarPorSedeFecha | idSede:int, fecha:LocalDate | List\<Clase\> |
| buscarPorId | id:int | Clase |
| registrar | c:Clase | int |
| actualizar | c:Clase | boolean |

### Interfaz: IReservaDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listarPorSocio | idSocio:int | List\<Reserva\> |
| listarPorClase | idClase:int, fecha:LocalDate | List\<Reserva\> |
| buscarPorId | id:int | Reserva |
| registrar | r:Reserva | int |
| cancelar | idReserva:int | boolean |
| existeReserva | idSocio:int, idClase:int, fecha:LocalDate, hora:LocalTime | boolean |

### Interfaz: IAsistenciaDAO

| Método | Parámetros | Retorna |
|---|---|---|
| listarPorSocio | idSocio:int | List\<Asistencia\> |
| registrar | a:Asistencia | int |
| contarPorSede | idSede:int, desde:LocalDate, hasta:LocalDate | int |

---

## Capa Facade (`com.gymmax.facade`)

### Clase: AuthFacade

**Descripción:** Encapsula la lógica de autenticación y registro de socios.

**Atributos privados:**
- usuarioDAO: IUsuarioDAO
- socioDAO: ISocioDAO

**Métodos públicos:**

| Método | Parámetros | Retorna | Reglas de negocio |
|---|---|---|---|
| login | correo:String, password:String | Usuario | Valida credenciales; retorna null si no coinciden |
| registrarSocio | s:Socio, password:String | boolean | Crea registro en USUARIO y luego en SOCIO de forma transaccional |
| recuperarPassword | correo:String | boolean | Genera token temporal y envía correo |
| cambiarPassword | idUsuario:int, nueva:String | boolean | Actualiza la contraseña encriptada |

---

### Clase: SocioFacade

**Descripción:** Lógica de consultas y servicios disponibles para el socio.

**Atributos privados:**
- sedeDAO: ISedeDAO
- claseDAO: IClaseDAO
- membresiaDAO: IMembresiaDAO

**Métodos públicos:**

| Método | Parámetros | Retorna | Reglas de negocio |
|---|---|---|---|
| obtenerDashboard | idSocio:int | DashboardSocioDTO | Agrega membresía vigente + asistencias + próxima clase |
| listarSedes | — | List\<Sede\> | Listado completo de sedes |
| listarSedesPorDistrito | distrito:String | List\<Sede\> | Filtro por distrito |
| listarClasesPorSedeFecha | idSede:int, fecha:LocalDate | List\<Clase\> | Clases del día con cupos |

---

### Clase: ReservaFacade

**Descripción:** Orquesta la creación, cancelación y consulta de reservas, aplicando las reglas de negocio (cupos, membresía activa, ventana de cancelación).

**Atributos privados:**
- reservaDAO: IReservaDAO
- claseDAO: IClaseDAO
- membresiaDAO: IMembresiaDAO

**Métodos públicos:**

| Método | Parámetros | Retorna | Reglas |
|---|---|---|---|
| reservar | idSocio:int, idClase:int, fecha:LocalDate, hora:LocalTime | boolean | Verifica membresía activa + cupos + duplicado |
| cancelar | idReserva:int | boolean | Solo si faltan ≥ 2h y estado = "CONF" |
| listarMisReservas | idSocio:int, soloFuturas:boolean | List\<Reserva\> | Filtra según el flag |

---

### Clase: MembresiaFacade

**Descripción:** Gestión de contratación, renovación y consulta de membresías y pagos.

**Atributos privados:**
- membresiaDAO: IMembresiaDAO
- planDAO: IPlanDAO
- pagoDAO: IPagoDAO

**Métodos públicos:**

| Método | Parámetros | Retorna | Reglas |
|---|---|---|---|
| contratar | idSocio:int, idPlan:int, metodoPago:String | boolean | Crea Membresia + Pago en transacción; calcula fechaFin |
| renovar | idMembresia:int | boolean | Suma duracionDias al vencimiento actual |
| listarHistorialPagos | idSocio:int | List\<Pago\> | Historial ordenado por fecha desc |

---

### Clase: AdminFacade

**Descripción:** Operaciones administrativas: dashboard, CRUDs, reportes y exportación.

**Atributos privados:**
- socioDAO, sedeDAO, planDAO, membresiaDAO, pagoDAO, asistenciaDAO

**Métodos públicos:**

| Método | Parámetros | Retorna | Descripción |
|---|---|---|---|
| obtenerDashboardAdmin | — | DashboardAdminDTO | KPIs: socios activos, ingresos, vencimientos, reservas hoy |
| generarReporteIngresos | desde:LocalDate, hasta:LocalDate, idSede:Integer | ReporteDTO | Agregación de pagos por sede |
| exportarExcel | reporte:Object | byte[] | Genera archivo .xlsx con Apache POI |
| exportarPDF | reporte:Object | byte[] | Genera archivo .pdf con iText |

---

## Capa Controller (`com.gymmax.controller`)

### Clase: AuthServlet (extends HttpServlet)

**Mapeo:** `@WebServlet("/AuthServlet")`

| Método | Descripción |
|---|---|
| doGet | Forward a login.jsp o registro.jsp según parámetro `view` |
| doPost | Procesa login o registro; setea sesión y redirige según rol |

**Dependencia:** AuthFacade

---

### Clase: SocioServlet (extends HttpServlet)

**Mapeo:** `@WebServlet("/SocioServlet")`

| Método | Descripción |
|---|---|
| doGet | Forward al dashboard, listado de sedes o detalle de sede |

**Dependencia:** SocioFacade

---

### Clase: ReservaServlet (extends HttpServlet)

**Mapeo:** `@WebServlet("/ReservaServlet")`

| Método | Descripción |
|---|---|
| doGet | Muestra reservarClase.jsp o misReservas.jsp |
| doPost | Procesa nueva reserva o cancelación |

**Dependencia:** ReservaFacade

---

### Clase: MembresiaServlet (extends HttpServlet)

**Mapeo:** `@WebServlet("/MembresiaServlet")`

| Método | Descripción |
|---|---|
| doGet | Muestra planes disponibles |
| doPost | Procesa contratación/renovación de plan |

**Dependencia:** MembresiaFacade

---

### Clase: AdminServlet (extends HttpServlet)

**Mapeo:** `@WebServlet("/AdminServlet")`

| Método | Descripción |
|---|---|
| doGet | Renderiza dashboard, gestión de socios/sedes o reportes (según parámetro `view`) |
| doPost | Procesa CRUDs y generación/exportación de reportes |

**Dependencia:** AdminFacade

---

## Clase utilitaria (`com.gymmax`)

### Clase: Conexion

**Descripción:** Singleton que centraliza la creación de conexiones JDBC a MySQL.

**Atributos estáticos:**
- URL: String = "jdbc:mysql://localhost:3306/gymmax"
- USER: String = "root"
- PASSWORD: String = "..." (configurable)

**Métodos públicos estáticos:**

| Método | Retorna | Descripción |
|---|---|---|
| getConnection() | Connection | Devuelve una nueva conexión JDBC |
| cerrar(c:Connection) | void | Cierra la conexión silenciosamente |

---

## Resumen del diccionario

| Capa | Cantidad de clases/interfaces |
|---|---|
| DTO | 10 clases |
| DAO interfaces | 9 |
| DAO implementaciones | 9 |
| Facades | 5 |
| Controllers (Servlets) | 5 |
| Utilitarias | 1 (Conexion) |
| **TOTAL** | **39** |
