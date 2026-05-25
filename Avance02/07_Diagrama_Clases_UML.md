# Diagrama de Clases UML — GymMax

Diagrama de clases del sistema basado en la arquitectura por capas (MVC + DAO + Facade) que se implementará en el backend Java EE.

## Organización por paquetes (capas)

```
com.gymmax
├── Conexion                                        (singleton JDBC)
├── controller/                                     (Servlets - capa Controller)
│   ├── AuthServlet
│   ├── SocioServlet
│   ├── ReservaServlet
│   ├── MembresiaServlet
│   └── AdminServlet
├── dao/                                            (interfaces e implementaciones DAO)
│   ├── IUsuarioDAO        / UsuarioDAOImpl
│   ├── ISocioDAO          / SocioDAOImpl
│   ├── IPlanDAO           / PlanDAOImpl
│   ├── IMembresiaDAO      / MembresiaDAOImpl
│   ├── IPagoDAO           / PagoDAOImpl
│   ├── ISedeDAO           / SedeDAOImpl
│   ├── IClaseDAO          / ClaseDAOImpl
│   ├── IReservaDAO        / ReservaDAOImpl
│   └── IAsistenciaDAO     / AsistenciaDAOImpl
├── dto/                                            (Data Transfer Objects)
│   ├── Usuario
│   ├── Socio
│   ├── Plan
│   ├── Membresia
│   ├── Pago
│   ├── Sede
│   ├── Clase
│   ├── HorarioClase
│   ├── Reserva
│   └── Asistencia
└── facade/                                         (lógica de negocio)
    ├── AuthFacade
    ├── SocioFacade
    ├── ReservaFacade
    ├── MembresiaFacade
    └── AdminFacade
```

## Lista completa de clases para el diagrama (con atributos clave y métodos públicos)

### Capa DTO (10 clases)

#### Usuario
- **Atributos:** idUsuario:int, correo:String, password:String, nombres:String, apellidos:String, rol:String, creadoEn:LocalDateTime
- **Métodos:** getters/setters de todos los atributos

#### Socio
- **Atributos:** idSocio:int, idUsuario:int, dni:String, celular:String, fechaNac:LocalDate, genero:char, direccion:String, fechaReg:LocalDate
- **Métodos:** getters/setters

#### Plan
- **Atributos:** idPlan:int, nombre:String, tipo:String, precio:double, duracionDias:int, activo:boolean
- **Métodos:** getters/setters

#### Membresia
- **Atributos:** idMembresia:int, idSocio:int, idPlan:int, fechaInicio:LocalDate, fechaFin:LocalDate, estado:String, monto:double, renovacionAuto:boolean
- **Métodos:** getters/setters, **isVigente():boolean**, **diasParaVencer():int**

#### Pago
- **Atributos:** idPago:int, idMembresia:int, metodo:String, monto:double, fechaPago:LocalDateTime, nroOperacion:String, estado:String, comprobanteUrl:String
- **Métodos:** getters/setters

#### Sede
- **Atributos:** idSede:int, nombre:String, direccion:String, distrito:String, telefono:String, horaApertura:LocalTime, horaCierre:LocalTime, capacidad:int
- **Métodos:** getters/setters

#### Clase
- **Atributos:** idClase:int, idSede:int, nombre:String, tipo:String, instructor:String, diaSemana:int, horaInicio:LocalTime, cupoMaximo:int
- **Métodos:** getters/setters

#### HorarioClase
- **Atributos:** idHorario:int, idClase:int, fechaEspecifica:LocalDate, cupoActual:int, cupoDisponible:int, estado:String
- **Métodos:** getters/setters, **hayCupo():boolean**

#### Reserva
- **Atributos:** idReserva:int, idSocio:int, idClase:int, fecha:LocalDate, hora:LocalTime, estado:String, creadoEn:LocalDateTime, canceladoEn:LocalDateTime
- **Métodos:** getters/setters, **puedeCancelarse():boolean**

#### Asistencia
- **Atributos:** idAsistencia:int, idSocio:int, idSede:int, fechaHora:LocalDateTime, tipo:String, registradoPor:int
- **Métodos:** getters/setters

---

### Capa DAO (9 interfaces + 9 implementaciones)

Cada interfaz define el contrato CRUD para su entidad. Las implementaciones usan JDBC y `Conexion.getConnection()`.

#### IUsuarioDAO (interfaz)
- listar():List<Usuario>
- buscarPorId(id:int):Usuario
- buscarPorCorreo(correo:String):Usuario
- registrar(u:Usuario):int
- actualizar(u:Usuario):boolean
- eliminar(id:int):boolean

#### ISocioDAO
- listar():List<Socio>
- buscarPorId(id:int):Socio
- buscarPorDni(dni:String):Socio
- buscarPorUsuario(idUsuario:int):Socio
- registrar(s:Socio):int
- actualizar(s:Socio):boolean
- eliminar(id:int):boolean
- filtrar(sede:Integer, plan:Integer, estado:String):List<Socio>

#### IPlanDAO
- listar():List<Plan>
- listarActivos():List<Plan>
- buscarPorId(id:int):Plan
- registrar(p:Plan):int
- actualizar(p:Plan):boolean
- desactivar(id:int):boolean

#### IMembresiaDAO
- listarPorSocio(idSocio:int):List<Membresia>
- buscarActivaPorSocio(idSocio:int):Membresia
- registrar(m:Membresia):int
- renovar(idMembresia:int, dias:int):boolean
- listarPorVencer(dias:int):List<Membresia>

#### IPagoDAO
- listarPorMembresia(idMembresia:int):List<Pago>
- listarPorSocio(idSocio:int):List<Pago>
- registrar(p:Pago):int
- sumarIngresos(desde:LocalDate, hasta:LocalDate, idSede:Integer):double

#### ISedeDAO
- listar():List<Sede>
- buscarPorId(id:int):Sede
- filtrarPorDistrito(distrito:String):List<Sede>
- registrar(s:Sede):int
- actualizar(s:Sede):boolean
- eliminar(id:int):boolean

#### IClaseDAO
- listar():List<Clase>
- listarPorSede(idSede:int):List<Clase>
- buscarPorId(id:int):Clase
- listarPorSedeFecha(idSede:int, fecha:LocalDate):List<Clase>
- registrar(c:Clase):int
- actualizar(c:Clase):boolean

#### IReservaDAO
- listarPorSocio(idSocio:int):List<Reserva>
- listarPorClase(idClase:int, fecha:LocalDate):List<Reserva>
- buscarPorId(id:int):Reserva
- registrar(r:Reserva):int
- cancelar(idReserva:int):boolean
- existeReserva(idSocio:int, idClase:int, fecha:LocalDate, hora:LocalTime):boolean

#### IAsistenciaDAO
- listarPorSocio(idSocio:int):List<Asistencia>
- registrar(a:Asistencia):int
- contarPorSede(idSede:int, desde:LocalDate, hasta:LocalDate):int

---

### Capa Facade (5 clases)

Cada Facade aplica las reglas de negocio y orquesta llamadas a uno o más DAOs.

#### AuthFacade
- login(correo:String, password:String):Usuario — valida y retorna usuario o null
- registrarSocio(socio:Socio, password:String):boolean — crea Usuario + Socio
- recuperarPassword(correo:String):boolean — envía enlace por correo

#### SocioFacade
- obtenerDashboard(idSocio:int):DashboardSocioDTO — agrupa membresía + KPIs
- listarSedes():List<Sede>
- listarSedesPorDistrito(distrito:String):List<Sede>
- listarClasesPorSedeFecha(idSede:int, fecha:LocalDate):List<Clase>

#### ReservaFacade
- reservar(idSocio:int, idClase:int, fecha:LocalDate, hora:LocalTime):boolean
- cancelar(idReserva:int):boolean — aplica regla de 2 horas mínimo
- listarMisReservas(idSocio:int, soloFuturas:boolean):List<Reserva>

#### MembresiaFacade
- contratar(idSocio:int, idPlan:int, metodoPago:String):boolean
- renovar(idMembresia:int):boolean
- listarHistorialPagos(idSocio:int):List<Pago>

#### AdminFacade
- obtenerDashboardAdmin():DashboardAdminDTO — KPIs (socios, ingresos, vencimientos)
- crudSocios() — delega a SocioDAO
- crudSedes() — delega a SedeDAO
- generarReporteIngresos(desde:LocalDate, hasta:LocalDate, idSede:Integer):ReporteIngresoDTO
- exportarExcel(reporte:Object):byte[]
- exportarPDF(reporte:Object):byte[]

---

### Capa Controller (5 Servlets)

#### AuthServlet (@WebServlet("/AuthServlet"))
- doGet: muestra login.jsp
- doPost: procesa login o registro (según parámetro `action`)

#### SocioServlet (@WebServlet("/SocioServlet"))
- doGet: carga dashboard del socio, listado de sedes o detalle de sede
- Usa AuthFacade para verificar sesión

#### ReservaServlet (@WebServlet("/ReservaServlet"))
- doGet: muestra formulario o "Mis reservas"
- doPost: procesa nueva reserva o cancelación

#### MembresiaServlet (@WebServlet("/MembresiaServlet"))
- doGet: muestra planes disponibles
- doPost: procesa contratación/renovación

#### AdminServlet (@WebServlet("/AdminServlet"))
- doGet: dashboard, gestión, reportes (según parámetro `view`)
- doPost: procesa CRUDs y solicitudes de reportes

---

### Clase utilitaria

#### Conexion (singleton)
- **Atributos estáticos:** URL:String, USER:String, PASSWORD:String
- **Métodos:** getConnection():Connection (static), cerrar(conn:Connection):void

---

## Guía paso a paso para Lucid Chart (Diagrama de Clases UML)

1. Abrir Lucid Chart → **New** → **Blank diagram**.
2. Activar la librería **"UML"** (panel izquierdo → Shapes → "UML").
3. Crear 4 áreas (paquetes) visuales con rectángulos punteados etiquetados: **dto**, **dao**, **facade**, **controller**.
4. Dentro de cada paquete, arrastrar la forma **"UML Class"** y llenar cada caja con:
   - **Cabecera:** nombre de la clase (ej. `Usuario`, `<<interface>> IUsuarioDAO`).
   - **Sección 1 (atributos):** `- idUsuario : int`, `- correo : String`, etc. (privados con `-`).
   - **Sección 2 (métodos):** `+ getCorreo() : String`, `+ login(...) : Usuario`, etc. (públicos con `+`).
5. Trazar **relaciones UML** apropiadas:
   - **Realización (línea punteada con triángulo):** desde `UsuarioDAOImpl` hacia `IUsuarioDAO` (implementa).
   - **Dependencia (línea punteada con flecha):** desde `AuthFacade` hacia `IUsuarioDAO` (usa).
   - **Asociación (línea sólida):** entre DTOs según el modelo de datos:
     - `Socio` 1 ── 1 `Usuario`
     - `Membresia` * ── 1 `Socio`
     - `Membresia` * ── 1 `Plan`
     - `Pago` * ── 1 `Membresia`
     - `Clase` * ── 1 `Sede`
     - `Reserva` * ── 1 `Socio`
     - `Reserva` * ── 1 `Clase`
     - `Asistencia` * ── 1 `Socio`
     - `Asistencia` * ── 1 `Sede`
     - `HorarioClase` * ── 1 `Clase`
   - **Composición (línea sólida con rombo lleno):** desde Facades hacia los DAOs que tienen como atributo.
6. Agregar título: **"Diagrama de Clases UML — GymMax"**.
7. Exportar como PNG.

---

## Convenciones UML aplicadas

| Símbolo | Significado |
|---|---|
| `+` | Atributo o método público |
| `-` | Atributo o método privado |
| `#` | Atributo o método protegido |
| `<<interface>>` | Estereotipo de interfaz |
| Línea sólida con triángulo hueco | Herencia (extends) |
| Línea punteada con triángulo hueco | Realización (implements) |
| Línea sólida con flecha | Asociación dirigida |
| Línea sólida con rombo hueco | Agregación |
| Línea sólida con rombo lleno | Composición |
| Línea punteada con flecha | Dependencia |
| `1`, `*`, `0..1`, `1..*` | Multiplicidades |

## Tip para reducir tamaño del diagrama

Si el diagrama queda muy grande, **dividirlo en 3 diagramas** y presentarlos en páginas separadas:
1. **Diagrama 1 (DTOs):** las 10 clases del paquete `dto` con sus asociaciones.
2. **Diagrama 2 (DAOs):** las 9 interfaces y sus 9 implementaciones con la relación de realización.
3. **Diagrama 3 (Facades + Controllers):** las 5 facades y los 5 servlets, mostrando qué facades usa cada servlet y qué DAOs usa cada facade.
