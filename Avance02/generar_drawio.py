"""
Generador de archivos .drawio para los diagramas del Avance 02 GymMax.

Genera 3 archivos importables en Draw.io / diagrams.net:
  - diagrama_BD_logico.drawio   (Modelo Lógico ER)
  - diagrama_BD_fisico.drawio   (Modelo Físico ER con tipos MySQL)
  - diagrama_clases_UML.drawio  (3 pestañas: DTOs, DAOs, Facades+Servlets+Util)

Cada archivo se abre en https://app.diagrams.net o draw.io desktop.
Las cajas son editables (mover, cambiar texto, agregar líneas de relación).
"""

import os
import html as ihtml

BASE = os.path.dirname(os.path.abspath(__file__))

# ========= Helpers =========

def esc(s):
    """Escape para XML attribute value."""
    return ihtml.escape(s, quote=True)


def box(id_, x, y, w, name, attrs, header="#FF6B00", body_font="Consolas"):
    """Genera una caja (entidad o clase) con header + lista de atributos."""
    header_html = (f'<div style="background:{header};color:#fff;padding:6px;'
                   f'text-align:center;font-weight:bold;font-family:Segoe UI;'
                   f'font-size:13px;">{name}</div>')
    body_html = (f'<div style="padding:6px 10px;font-family:{body_font};'
                 f'font-size:11px;line-height:1.7;text-align:left;">'
                 + '<br/>'.join(attrs) + '</div>')
    full = header_html + body_html
    h = 36 + len(attrs) * 18 + 16
    style = ("rounded=0;whiteSpace=wrap;html=1;align=left;verticalAlign=top;"
             "fillColor=#ffffff;strokeColor=#333333;fontSize=11;")
    return (f'        <mxCell id="{id_}" value="{esc(full)}" '
            f'style="{style}" vertex="1" parent="1">'
            f'<mxGeometry x="{x}" y="{y}" width="{w}" height="{h}" as="geometry" />'
            f'</mxCell>\n'), h


def class_box(id_, x, y, w, name, attrs, methods, stereotype=None,
              header="#FF6B00"):
    """Caja UML: stereotipo + nombre + atributos + métodos."""
    parts = []
    if stereotype:
        parts.append(f'<div style="text-align:center;font-style:italic;'
                     f'font-size:10px;padding:3px 0 0;color:#666;">'
                     f'{stereotype}</div>')
    parts.append(f'<div style="background:{header};color:#fff;padding:6px;'
                 f'text-align:center;font-weight:bold;font-family:Segoe UI;'
                 f'font-size:12px;">{name}</div>')
    if attrs:
        attrs_html = '<br/>'.join(attrs)
    else:
        attrs_html = '&nbsp;'
    parts.append(f'<div style="padding:5px 8px;font-family:Consolas;'
                 f'font-size:10px;line-height:1.6;border-bottom:1px solid #333;">'
                 f'{attrs_html}</div>')
    if methods:
        methods_html = '<br/>'.join(methods)
    else:
        methods_html = '&nbsp;'
    parts.append(f'<div style="padding:5px 8px;font-family:Consolas;'
                 f'font-size:10px;line-height:1.6;">{methods_html}</div>')
    full = ''.join(parts)
    h = (15 if stereotype else 0) + 35
    h += max(20, len(attrs) * 16 + 10)
    h += max(20, len(methods) * 16 + 10)
    style = ("rounded=0;whiteSpace=wrap;html=1;align=left;verticalAlign=top;"
             "fillColor=#ffffff;strokeColor=#333333;fontSize=10;")
    return (f'        <mxCell id="{id_}" value="{esc(full)}" '
            f'style="{style}" vertex="1" parent="1">'
            f'<mxGeometry x="{x}" y="{y}" width="{w}" height="{h}" as="geometry" />'
            f'</mxCell>\n'), h


def make_file(diagrams):
    """diagrams = lista de (page_name, cells_string)."""
    s = '<?xml version="1.0" encoding="UTF-8"?>\n'
    s += '<mxfile host="app.diagrams.net" agent="Python Generator" version="22.0.0">\n'
    for i, (name, cells) in enumerate(diagrams):
        s += f'  <diagram name="{esc(name)}" id="d{i+1}">\n'
        s += ('    <mxGraphModel dx="2000" dy="1500" grid="1" gridSize="10" '
              'guides="1" tooltips="1" connect="1" arrows="1" fold="1" '
              'page="1" pageScale="1" pageWidth="1654" pageHeight="1169" '
              'math="0" shadow="0">\n')
        s += '      <root>\n'
        s += '        <mxCell id="0" />\n'
        s += '        <mxCell id="1" parent="0" />\n'
        s += cells
        s += '      </root>\n'
        s += '    </mxGraphModel>\n'
        s += '  </diagram>\n'
    s += '</mxfile>\n'
    return s


def grid_layout(entities, cols=5, col_w=240, gap_x=30, gap_y=40, x0=40, y0=40):
    """Distribuye entidades en una grilla. Devuelve lista de (x, y) por orden."""
    coords = []
    row_h = 0
    cur_y = y0
    for i, _ in enumerate(entities):
        col = i % cols
        if col == 0 and i > 0:
            cur_y += row_h + gap_y
            row_h = 0
        x = x0 + col * (col_w + gap_x)
        coords.append((x, cur_y))
    return coords


# ========= DATOS =========

# BD Lógico — sin tipos
LOGICO = [
    ("USUARIO", [
        "PK id_usuario", "correo (único)", "contraseña", "nombres",
        "apellidos", "rol", "fecha_creación"]),
    ("SOCIO", [
        "PK id_socio", "FK id_usuario", "dni (único)", "celular",
        "fecha_nacimiento", "género", "dirección", "fecha_registro"]),
    ("PLAN", [
        "PK id_plan", "nombre", "tipo", "precio",
        "duración_días", "activo"]),
    ("MEMBRESÍA", [
        "PK id_membresía", "FK id_socio", "FK id_plan", "fecha_inicio",
        "fecha_fin", "estado", "monto", "renovación_auto"]),
    ("PAGO", [
        "PK id_pago", "FK id_membresía", "método", "monto",
        "fecha_pago", "nro_operación", "estado", "url_comprobante"]),
    ("SEDE", [
        "PK id_sede", "nombre", "dirección", "distrito",
        "teléfono", "hora_apertura", "hora_cierre", "capacidad"]),
    ("CLASE", [
        "PK id_clase", "FK id_sede", "nombre", "tipo",
        "instructor", "día_semana", "hora_inicio", "cupo_máximo"]),
    ("HORARIO_CLASE", [
        "PK id_horario", "FK id_clase", "fecha_específica",
        "cupo_actual", "cupo_disponible", "estado"]),
    ("RESERVA", [
        "PK id_reserva", "FK id_socio", "FK id_clase", "fecha",
        "hora", "estado", "creado_en", "cancelado_en"]),
    ("ASISTENCIA", [
        "PK id_asistencia", "FK id_socio", "FK id_sede",
        "fecha_hora", "tipo", "registrado_por"]),
]

# BD Físico — con tipos MySQL
FISICO = [
    ("USUARIO", [
        "PK id_usuario : INT AI", "correo : VARCHAR(80) UQ",
        "password : VARCHAR(255)", "nombres : VARCHAR(80)",
        "apellidos : VARCHAR(80)", "rol : ENUM",
        "creado_en : TIMESTAMP"]),
    ("SOCIO", [
        "PK id_socio : INT AI", "FK id_usuario : INT UQ",
        "dni : CHAR(8) UQ", "celular : VARCHAR(15)",
        "fecha_nac : DATE", "genero : CHAR(1)",
        "direccion : VARCHAR(150)", "fecha_reg : DATE"]),
    ("PLAN_MEMBRESIA", [
        "PK id_plan : INT AI", "nombre : VARCHAR(40)",
        "tipo : ENUM", "precio : DECIMAL(10,2)",
        "duracion_dias : INT", "activo : BOOLEAN"]),
    ("MEMBRESIA", [
        "PK id_membresia : INT AI", "FK id_socio : INT",
        "FK id_plan : INT", "fecha_inicio : DATE",
        "fecha_fin : DATE", "estado : ENUM",
        "monto : DECIMAL(10,2)", "renovacion_auto : BOOLEAN"]),
    ("PAGO", [
        "PK id_pago : INT AI", "FK id_membresia : INT",
        "metodo : ENUM", "monto : DECIMAL(10,2)",
        "fecha_pago : TIMESTAMP", "nro_operacion : VARCHAR(50)",
        "estado : ENUM", "comprobante_url : VARCHAR(200)"]),
    ("SEDE", [
        "PK id_sede : INT AI", "nombre : VARCHAR(60)",
        "direccion : VARCHAR(150)", "distrito : VARCHAR(50)",
        "telefono : VARCHAR(15)", "hora_apertura : TIME",
        "hora_cierre : TIME", "capacidad : INT"]),
    ("CLASE", [
        "PK id_clase : INT AI", "FK id_sede : INT",
        "nombre : VARCHAR(50)", "tipo : ENUM",
        "instructor : VARCHAR(80)", "dia_semana : TINYINT",
        "hora_inicio : TIME", "cupo_maximo : INT"]),
    ("HORARIO_CLASE", [
        "PK id_horario : INT AI", "FK id_clase : INT",
        "fecha_especifica : DATE", "cupo_actual : INT",
        "cupo_disponible : INT", "estado : ENUM"]),
    ("RESERVA", [
        "PK id_reserva : INT AI", "FK id_socio : INT",
        "FK id_clase : INT", "fecha : DATE", "hora : TIME",
        "estado : ENUM", "creado_en : TIMESTAMP",
        "cancelado_en : TIMESTAMP"]),
    ("ASISTENCIA", [
        "PK id_asistencia : INT AI", "FK id_socio : INT",
        "FK id_sede : INT", "fecha_hora : TIMESTAMP",
        "tipo : ENUM", "registrado_por : INT"]),
]

# UML — DTOs
DTOS = [
    ("Usuario", [
        "- idUsuario : int", "- correo : String", "- password : String",
        "- nombres : String", "- apellidos : String", "- rol : String",
        "- creadoEn : LocalDateTime"], ["+ getters/setters"]),
    ("Socio", [
        "- idSocio : int", "- idUsuario : int", "- dni : String",
        "- celular : String", "- fechaNac : LocalDate", "- genero : char",
        "- direccion : String", "- fechaReg : LocalDate"], ["+ getters/setters"]),
    ("Plan", [
        "- idPlan : int", "- nombre : String", "- tipo : String",
        "- precio : double", "- duracionDias : int", "- activo : boolean"],
        ["+ getters/setters"]),
    ("Membresia", [
        "- idMembresia : int", "- idSocio : int", "- idPlan : int",
        "- fechaInicio : LocalDate", "- fechaFin : LocalDate",
        "- estado : String", "- monto : double",
        "- renovacionAuto : boolean"],
        ["+ isVigente() : boolean", "+ diasParaVencer() : int",
         "+ getters/setters"]),
    ("Pago", [
        "- idPago : int", "- idMembresia : int", "- metodo : String",
        "- monto : double", "- fechaPago : LocalDateTime",
        "- nroOperacion : String", "- estado : String",
        "- comprobanteUrl : String"], ["+ getters/setters"]),
    ("Sede", [
        "- idSede : int", "- nombre : String", "- direccion : String",
        "- distrito : String", "- telefono : String",
        "- horaApertura : LocalTime", "- horaCierre : LocalTime",
        "- capacidad : int"], ["+ getters/setters"]),
    ("Clase", [
        "- idClase : int", "- idSede : int", "- nombre : String",
        "- tipo : String", "- instructor : String", "- diaSemana : int",
        "- horaInicio : LocalTime", "- cupoMaximo : int"],
        ["+ getters/setters"]),
    ("HorarioClase", [
        "- idHorario : int", "- idClase : int",
        "- fechaEspecifica : LocalDate", "- cupoActual : int",
        "- cupoDisponible : int", "- estado : String"],
        ["+ hayCupo() : boolean", "+ getters/setters"]),
    ("Reserva", [
        "- idReserva : int", "- idSocio : int", "- idClase : int",
        "- fecha : LocalDate", "- hora : LocalTime", "- estado : String",
        "- creadoEn : LocalDateTime", "- canceladoEn : LocalDateTime"],
        ["+ puedeCancelarse() : boolean", "+ getters/setters"]),
    ("Asistencia", [
        "- idAsistencia : int", "- idSocio : int", "- idSede : int",
        "- fechaHora : LocalDateTime", "- tipo : String",
        "- registradoPor : int"], ["+ getters/setters"]),
]

# UML — DAOs (interface + impl pairs)
DAOS = [
    ("<<interface>>", "IUsuarioDAO", [], [
        "+ listar() : List<Usuario>", "+ buscarPorId(id) : Usuario",
        "+ buscarPorCorreo(c) : Usuario", "+ registrar(u) : int",
        "+ actualizar(u) : boolean", "+ eliminar(id) : boolean"]),
    (None, "UsuarioDAOImpl", ["- conn : Connection"],
        ["+ implementa IUsuarioDAO", "  con JDBC"]),

    ("<<interface>>", "ISocioDAO", [], [
        "+ listar() : List<Socio>", "+ buscarPorId(id) : Socio",
        "+ buscarPorDni(d) : Socio", "+ buscarPorUsuario(id) : Socio",
        "+ registrar(s) : int", "+ actualizar(s) : boolean",
        "+ eliminar(id) : boolean", "+ filtrar(...) : List<Socio>"]),
    (None, "SocioDAOImpl", ["- conn : Connection"],
        ["+ implementa ISocioDAO"]),

    ("<<interface>>", "IPlanDAO", [], [
        "+ listar() : List<Plan>", "+ listarActivos() : List<Plan>",
        "+ buscarPorId(id) : Plan", "+ registrar(p) : int",
        "+ actualizar(p) : boolean", "+ desactivar(id) : boolean"]),
    (None, "PlanDAOImpl", ["- conn : Connection"],
        ["+ implementa IPlanDAO"]),

    ("<<interface>>", "IMembresiaDAO", [], [
        "+ listarPorSocio(id) : List", "+ buscarActivaPorSocio(id)",
        "+ registrar(m) : int", "+ renovar(id, dias) : boolean",
        "+ listarPorVencer(d) : List"]),
    (None, "MembresiaDAOImpl", ["- conn : Connection"],
        ["+ implementa IMembresiaDAO"]),

    ("<<interface>>", "IPagoDAO", [], [
        "+ listarPorMembresia(id)", "+ listarPorSocio(id) : List",
        "+ registrar(p) : int", "+ sumarIngresos(...) : double"]),
    (None, "PagoDAOImpl", ["- conn : Connection"],
        ["+ implementa IPagoDAO"]),

    ("<<interface>>", "ISedeDAO", [], [
        "+ listar() : List<Sede>", "+ buscarPorId(id) : Sede",
        "+ filtrarPorDistrito(d) : List", "+ registrar(s) : int",
        "+ actualizar(s) : boolean", "+ eliminar(id) : boolean"]),
    (None, "SedeDAOImpl", ["- conn : Connection"],
        ["+ implementa ISedeDAO"]),

    ("<<interface>>", "IClaseDAO", [], [
        "+ listar() : List<Clase>", "+ listarPorSede(id) : List",
        "+ listarPorSedeFecha(...)", "+ buscarPorId(id) : Clase",
        "+ registrar(c) : int", "+ actualizar(c) : boolean"]),
    (None, "ClaseDAOImpl", ["- conn : Connection"],
        ["+ implementa IClaseDAO"]),

    ("<<interface>>", "IReservaDAO", [], [
        "+ listarPorSocio(id) : List", "+ listarPorClase(id, fec)",
        "+ buscarPorId(id) : Reserva", "+ registrar(r) : int",
        "+ cancelar(id) : boolean", "+ existeReserva(...) : boolean"]),
    (None, "ReservaDAOImpl", ["- conn : Connection"],
        ["+ implementa IReservaDAO"]),

    ("<<interface>>", "IAsistenciaDAO", [], [
        "+ listarPorSocio(id) : List", "+ registrar(a) : int",
        "+ contarPorSede(...) : int"]),
    (None, "AsistenciaDAOImpl", ["- conn : Connection"],
        ["+ implementa IAsistenciaDAO"]),
]

# UML — Facades + Servlets + Util
FACADES = [
    ("AuthFacade", [
        "- usuarioDAO : IUsuarioDAO", "- socioDAO : ISocioDAO"], [
        "+ login(correo, pwd) : Usuario",
        "+ registrarSocio(s, pwd) : boolean",
        "+ recuperarPassword(c) : boolean",
        "+ cambiarPassword(id, n) : boolean"]),
    ("SocioFacade", [
        "- sedeDAO : ISedeDAO", "- claseDAO : IClaseDAO",
        "- membresiaDAO : IMembresiaDAO"], [
        "+ obtenerDashboard(id)",
        "+ listarSedes() : List<Sede>",
        "+ listarSedesPorDistrito(d)",
        "+ listarClasesPorSedeFecha(...)"]),
    ("ReservaFacade", [
        "- reservaDAO : IReservaDAO", "- claseDAO : IClaseDAO",
        "- membresiaDAO : IMembresiaDAO"], [
        "+ reservar(idS, idC, fec, h) : boolean",
        "+ cancelar(idR) : boolean",
        "+ listarMisReservas(id, f) : List"]),
    ("MembresiaFacade", [
        "- membresiaDAO : IMembresiaDAO", "- planDAO : IPlanDAO",
        "- pagoDAO : IPagoDAO"], [
        "+ contratar(idS, idP, met) : boolean",
        "+ renovar(idM) : boolean",
        "+ listarHistorialPagos(id) : List"]),
    ("AdminFacade", [
        "- todos los DAOs"], [
        "+ obtenerDashboardAdmin()",
        "+ generarReporteIngresos(...)",
        "+ exportarExcel(r) : byte[]",
        "+ exportarPDF(r) : byte[]"]),
]

SERVLETS = [
    ("AuthServlet", ["- facade : AuthFacade"],
     ["+ doGet(req, resp)", "+ doPost(req, resp)"],
     '@WebServlet("/AuthServlet")'),
    ("SocioServlet", ["- facade : SocioFacade"],
     ["+ doGet(req, resp)"],
     '@WebServlet("/SocioServlet")'),
    ("ReservaServlet", ["- facade : ReservaFacade"],
     ["+ doGet(req, resp)", "+ doPost(req, resp)"],
     '@WebServlet("/ReservaServlet")'),
    ("MembresiaServlet", ["- facade : MembresiaFacade"],
     ["+ doGet(req, resp)", "+ doPost(req, resp)"],
     '@WebServlet("/MembresiaServlet")'),
    ("AdminServlet", ["- facade : AdminFacade"],
     ["+ doGet(req, resp)", "+ doPost(req, resp)"],
     '@WebServlet("/AdminServlet")'),
]


# ========= GENERADORES =========

def build_bd(entities, title_color="#FF6B00", cols=5):
    """Construye el XML de celdas para un diagrama BD."""
    cells = ""
    coords = grid_layout(entities, cols=cols, col_w=240)
    for (name, attrs), (x, y) in zip(entities, coords):
        c, _ = box(f"e-{name}", x, y, 220, name, attrs, header=title_color)
        cells += c
    return cells


def build_dtos():
    cells = ""
    coords = grid_layout(DTOS, cols=5, col_w=240)
    for (name, attrs, methods), (x, y) in zip(DTOS, coords):
        c, _ = class_box(f"d-{name}", x, y, 220, name, attrs, methods)
        cells += c
    return cells


def build_daos():
    """DAOs en una grilla de 3 columnas: cada par interfaz+impl uno al lado del otro."""
    cells = ""
    cols = 4
    col_w = 240
    gap_x = 25
    gap_y = 40
    x0, y0 = 40, 40
    cur_y = y0
    row_h = 0
    for i, (stereo, name, attrs, methods) in enumerate(DAOS):
        col = i % cols
        if col == 0 and i > 0:
            cur_y += row_h + gap_y
            row_h = 0
        x = x0 + col * (col_w + gap_x)
        if stereo:
            header = "#444444"
        else:
            header = "#FF6B00"
        c, h = class_box(f"dao-{name}", x, cur_y, 220, name, attrs, methods,
                         stereotype=stereo, header=header)
        cells += c
        if h > row_h:
            row_h = h
    return cells


def build_facades_servlets():
    """Facades arriba, Servlets en medio, Conexion abajo."""
    cells = ""
    cols = 5
    col_w = 240
    gap_x = 30
    gap_y = 40

    # Facades (fila 1)
    y = 40
    row_h = 0
    for i, (name, attrs, methods) in enumerate(FACADES):
        x = 40 + i * (col_w + gap_x)
        c, h = class_box(f"f-{name}", x, y, 220, name, attrs, methods,
                         header="#1971c2")
        cells += c
        if h > row_h:
            row_h = h

    # Servlets (fila 2)
    y += row_h + gap_y + 60
    row_h = 0
    for i, (name, attrs, methods, stereo) in enumerate(SERVLETS):
        x = 40 + i * (col_w + gap_x)
        c, h = class_box(f"s-{name}", x, y, 220, name, attrs, methods,
                         stereotype=stereo, header="#c92a2a")
        cells += c
        if h > row_h:
            row_h = h

    # Conexion (fila 3, centrada)
    y += row_h + gap_y + 60
    x = 40 + 2 * (col_w + gap_x)
    c, _ = class_box("u-Conexion", x, y, 220, "Conexion",
                     ["- URL : String", "- USER : String", "- PASSWORD : String"],
                     ["+ getConnection() : Connection",
                      "+ cerrar(c : Connection) : void"],
                     stereotype="<<singleton>>", header="#7b1fa2")
    cells += c

    return cells


# ========= MAIN =========

def main():
    print("Generando archivos .drawio...")

    # 1. BD Lógico
    out1 = os.path.join(BASE, "diagrama_BD_logico.drawio")
    with open(out1, "w", encoding="utf-8") as f:
        f.write(make_file([("Modelo Lógico", build_bd(LOGICO))]))
    print(f"  -> {out1}")

    # 2. BD Físico
    out2 = os.path.join(BASE, "diagrama_BD_fisico.drawio")
    with open(out2, "w", encoding="utf-8") as f:
        f.write(make_file([("Modelo Físico (MySQL)", build_bd(FISICO))]))
    print(f"  -> {out2}")

    # 3. UML (3 páginas)
    out3 = os.path.join(BASE, "diagrama_clases_UML.drawio")
    with open(out3, "w", encoding="utf-8") as f:
        f.write(make_file([
            ("1 - DTOs", build_dtos()),
            ("2 - DAOs", build_daos()),
            ("3 - Facades + Servlets + Util", build_facades_servlets()),
        ]))
    print(f"  -> {out3}")

    print("\n=== Listos ===")
    print("Abrir cada .drawio en https://app.diagrams.net o draw.io desktop.")


if __name__ == "__main__":
    main()
