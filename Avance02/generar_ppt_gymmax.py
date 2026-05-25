"""
Generador de PowerPoint con el estilo visual del Avance 01 de GymMax:
- Fondo negro (#1a1a1a)
- Títulos beige italic bold (#d4a574)
- Texto blanco
- Decoraciones: círculos beige en esquinas, marco redondeado en portada
- Tipografía Calibri / sans serif

Ejecutar: python generar_ppt_gymmax.py
"""

import os
from pptx import Presentation
from pptx.util import Inches, Pt, Emu
from pptx.dml.color import RGBColor
from pptx.enum.shapes import MSO_SHAPE
from pptx.enum.text import PP_ALIGN, MSO_ANCHOR

BASE = os.path.dirname(os.path.abspath(__file__))
OUT = os.path.join(BASE, "GymMax_Avance02.pptx")

# Paleta del Avance 01
BG_DARK   = RGBColor(0x1a, 0x1a, 0x1a)   # fondo casi negro
BEIGE     = RGBColor(0xd4, 0xa5, 0x74)   # beige cálido principal
BEIGE_L   = RGBColor(0xe8, 0xc8, 0x96)   # beige claro
WHITE     = RGBColor(0xff, 0xff, 0xff)
GRAY      = RGBColor(0xcc, 0xcc, 0xcc)
RED_UTP   = RGBColor(0xe6, 0x1c, 0x1c)

FONT_TITLE = "Calibri"
FONT_BODY  = "Calibri"


# =========================================================
#                      HELPERS DE ESTILO
# =========================================================

def set_bg(slide, color=BG_DARK):
    """Pinta el fondo de un slide."""
    bg = slide.background
    bg.fill.solid()
    bg.fill.fore_color.rgb = color


def add_dot(slide, x, y, size=0.3, color=BEIGE):
    """Pequeño círculo decorativo."""
    s = slide.shapes.add_shape(MSO_SHAPE.OVAL,
        Inches(x), Inches(y), Inches(size), Inches(size))
    s.fill.solid()
    s.fill.fore_color.rgb = color
    s.line.fill.background()
    return s


def add_corner_decorations(slide):
    """Círculos decorativos: 1 arriba-izquierda + 3 abajo-derecha + flecha."""
    add_dot(slide, 0.5, 0.5, 0.35, BEIGE)
    add_dot(slide, 12.7, 5.7, 0.25, BEIGE)
    add_dot(slide, 12.7, 6.2, 0.25, BEIGE)
    add_dot(slide, 12.7, 6.7, 0.25, BEIGE)
    # Flecha pequeña hacia derecha
    arrow = slide.shapes.add_shape(MSO_SHAPE.RIGHT_ARROW,
        Inches(12.0), Inches(6.5), Inches(0.7), Inches(0.15))
    arrow.fill.solid()
    arrow.fill.fore_color.rgb = BEIGE
    arrow.line.fill.background()


def add_text(slide, text, x, y, w, h, *, size=18, bold=False, italic=False,
             color=WHITE, font=FONT_BODY, align=PP_ALIGN.LEFT,
             anchor=MSO_ANCHOR.TOP):
    """Caja de texto formateada."""
    tb = slide.shapes.add_textbox(Inches(x), Inches(y), Inches(w), Inches(h))
    tf = tb.text_frame
    tf.word_wrap = True
    tf.vertical_anchor = anchor
    tf.margin_left = Pt(0)
    tf.margin_right = Pt(0)
    tf.margin_top = Pt(0)
    tf.margin_bottom = Pt(0)
    p = tf.paragraphs[0]
    p.alignment = align
    run = p.add_run()
    run.text = text
    run.font.size = Pt(size)
    run.font.bold = bold
    run.font.italic = italic
    run.font.name = font
    run.font.color.rgb = color
    return tb


def add_bullets(slide, items, x, y, w, h, *, size=18, color=WHITE,
                bullet_color=BEIGE, line_spacing=1.2):
    """Lista de bullets con guion estilizado."""
    tb = slide.shapes.add_textbox(Inches(x), Inches(y), Inches(w), Inches(h))
    tf = tb.text_frame
    tf.word_wrap = True
    tf.margin_left = Pt(0)
    tf.margin_right = Pt(0)
    tf.margin_top = Pt(0)

    for i, it in enumerate(items):
        if i == 0:
            p = tf.paragraphs[0]
        else:
            p = tf.add_paragraph()
        p.alignment = PP_ALIGN.LEFT
        p.line_spacing = line_spacing
        # Bullet
        b = p.add_run()
        b.text = "• "
        b.font.size = Pt(size + 2)
        b.font.bold = True
        b.font.name = FONT_BODY
        b.font.color.rgb = bullet_color
        # Texto
        r = p.add_run()
        r.text = it
        r.font.size = Pt(size)
        r.font.name = FONT_BODY
        r.font.color.rgb = color
    return tb


def add_section_title(slide, text):
    """Título grande arriba del slide, beige italic bold."""
    return add_text(slide, text, 0.6, 0.5, 12.1, 1.3,
                    size=36, bold=True, italic=True,
                    color=BEIGE, font=FONT_TITLE,
                    align=PP_ALIGN.LEFT)


def add_footer(slide, slide_num, total=26):
    """Pie con numeración y marca."""
    add_text(slide, "GymMax — Avance 02", 0.4, 7.1, 5, 0.3,
             size=10, color=GRAY, italic=True)
    add_text(slide, f"{slide_num} / {total}", 12.2, 7.1, 1, 0.3,
             size=10, color=GRAY, align=PP_ALIGN.RIGHT)


def add_rounded_border(slide, x, y, w, h, color=BEIGE, weight=Pt(2)):
    """Marco redondeado decorativo (como el de la portada del Avance 01)."""
    s = slide.shapes.add_shape(MSO_SHAPE.ROUNDED_RECTANGLE,
        Inches(x), Inches(y), Inches(w), Inches(h))
    s.fill.background()
    s.line.color.rgb = color
    s.line.width = weight
    s.adjustments[0] = 0.05
    return s


# =========================================================
#                    BUILDERS DE SLIDES
# =========================================================

def new_blank(prs):
    """Slide en blanco con fondo negro."""
    slide_layout = prs.slide_layouts[6]  # blank
    slide = prs.slides.add_slide(slide_layout)
    set_bg(slide)
    return slide


def slide_portada(prs):
    slide = new_blank(prs)
    # Marco redondeado central
    add_rounded_border(slide, 1.3, 1.5, 10.7, 5, BEIGE, Pt(2.5))
    # Etiqueta superior
    add_text(slide, "DESARROLLO WEB INTEGRADO", 1.5, 1.9, 10.3, 0.5,
             size=14, color=BEIGE_L, italic=True,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)
    # Título grande
    add_text(slide, "INFORME DE AVANCE 02 — GYMMAX", 1.5, 2.7, 10.3, 1.3,
             size=44, bold=True, italic=True, color=BEIGE,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)
    add_text(slide, "Diseño y avance de funcionalidad", 1.5, 4.2, 10.3, 0.5,
             size=18, italic=True, color=WHITE,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)
    # Pie del recuadro
    add_text(slide, "UTP · Sec. 27672 · Mayo 2026",
             1.5, 5.5, 10.3, 0.5,
             size=14, italic=True, color=BEIGE_L,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)
    # Decoración
    add_dot(slide, 0.7, 0.7, 0.4, BEIGE)
    add_dot(slide, 12.2, 6.5, 0.4, BEIGE)
    # Marca UTP (texto)
    add_text(slide, "U T P", 11.5, 0.4, 1.4, 0.7,
             size=24, bold=True, color=RED_UTP,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)


def slide_integrantes(prs):
    slide = new_blank(prs)
    add_section_title(slide, "INTEGRANTES")
    integrantes = [
        "Choque Anchante, Niurka Yasbeth",
        "Diaz Culqui, Nehemias",
        "Purizaca Ipanaque, Dennys Marlon",
        "Soria Chavez, Ian",
        "Torre Escobar, Oliver",
        "Valladolid Llenque, Alexander",
    ]
    # Lista en beige bold como en el Avance 01
    tb = slide.shapes.add_textbox(Inches(1.5), Inches(2.3),
                                  Inches(10), Inches(4))
    tf = tb.text_frame
    tf.word_wrap = True
    for i, n in enumerate(integrantes):
        if i == 0:
            p = tf.paragraphs[0]
        else:
            p = tf.add_paragraph()
        p.alignment = PP_ALIGN.LEFT
        p.line_spacing = 1.5
        r = p.add_run()
        r.text = f"-  {n}"
        r.font.size = Pt(24)
        r.font.bold = True
        r.font.color.rgb = BEIGE
        r.font.name = FONT_TITLE
    add_corner_decorations(slide)
    add_footer(slide, 2)


def slide_agenda(prs):
    slide = new_blank(prs)
    add_section_title(slide, "AGENDA")
    temas = [
        "1.  Introducción y problemática",
        "2.  Objetivos y alcance",
        "3.  Características del producto",
        "4.  Product Backlog y User Stories",
        "5.  Wireframes y flujos de navegación",
        "6.  Modelo de Base de Datos (Lógico + Físico)",
        "7.  Diagrama de Clases UML",
        "8.  Tecnologías utilizadas",
        "9.  Próximos pasos",
    ]
    tb = slide.shapes.add_textbox(Inches(1.5), Inches(2.0),
                                  Inches(11), Inches(5))
    tf = tb.text_frame
    tf.word_wrap = True
    for i, t in enumerate(temas):
        if i == 0:
            p = tf.paragraphs[0]
        else:
            p = tf.add_paragraph()
        p.alignment = PP_ALIGN.LEFT
        p.line_spacing = 1.4
        r = p.add_run()
        r.text = t
        r.font.size = Pt(22)
        r.font.italic = True
        r.font.color.rgb = WHITE
        r.font.name = FONT_TITLE
    add_corner_decorations(slide)
    add_footer(slide, 3)


def slide_content(prs, num, title, bullets=None, paragraph=None,
                  notes=None, font_size=18):
    """Slide estándar de contenido."""
    slide = new_blank(prs)
    add_section_title(slide, title)
    if paragraph:
        add_text(slide, paragraph, 1.0, 2.0, 11.3, 4.5,
                 size=font_size, italic=True, color=WHITE,
                 align=PP_ALIGN.LEFT)
    if bullets:
        add_bullets(slide, bullets, 1.0, 2.0, 11.3, 4.8,
                    size=font_size, color=WHITE)
    if notes:
        slide.notes_slide.notes_text_frame.text = notes
    add_corner_decorations(slide)
    add_footer(slide, num)


def slide_two_columns(prs, num, title, col1_title, col1_items,
                       col2_title, col2_items, notes=None):
    """Slide con dos columnas comparativas."""
    slide = new_blank(prs)
    add_section_title(slide, title)
    # Columna 1
    add_text(slide, col1_title, 0.7, 2.0, 5.8, 0.6,
             size=20, bold=True, italic=True, color=BEIGE)
    add_bullets(slide, col1_items, 0.7, 2.7, 5.8, 4.2, size=15)
    # Columna 2
    add_text(slide, col2_title, 6.8, 2.0, 5.8, 0.6,
             size=20, bold=True, italic=True, color=BEIGE)
    add_bullets(slide, col2_items, 6.8, 2.7, 5.8, 4.2, size=15)
    if notes:
        slide.notes_slide.notes_text_frame.text = notes
    add_corner_decorations(slide)
    add_footer(slide, num)


def slide_imagen_placeholder(prs, num, title, descripcion, notes=None):
    """Slide para pegar imágenes (mockups, diagramas)."""
    slide = new_blank(prs)
    add_section_title(slide, title)
    # Rectángulo placeholder
    box = slide.shapes.add_shape(MSO_SHAPE.RECTANGLE,
        Inches(1.5), Inches(2.0), Inches(10.3), Inches(4.3))
    box.fill.solid()
    box.fill.fore_color.rgb = RGBColor(0x33, 0x33, 0x33)
    box.line.color.rgb = BEIGE
    box.line.width = Pt(1.5)
    add_text(slide, "[ INSERTAR IMAGEN AQUÍ ]", 1.5, 3.7, 10.3, 0.5,
             size=18, bold=True, italic=True, color=BEIGE_L,
             align=PP_ALIGN.CENTER)
    add_text(slide, descripcion, 1.5, 4.4, 10.3, 0.6,
             size=13, italic=True, color=GRAY,
             align=PP_ALIGN.CENTER)
    if notes:
        slide.notes_slide.notes_text_frame.text = notes
    add_corner_decorations(slide)
    add_footer(slide, num)


def slide_tabla(prs, num, title, headers, rows, notes=None):
    """Slide con tabla."""
    slide = new_blank(prs)
    add_section_title(slide, title)
    n_rows = len(rows) + 1
    n_cols = len(headers)
    table_shape = slide.shapes.add_table(n_rows, n_cols,
        Inches(0.8), Inches(2.0), Inches(11.7), Inches(4.5))
    tbl = table_shape.table

    for c, h in enumerate(headers):
        cell = tbl.cell(0, c)
        cell.text = h
        cell.fill.solid()
        cell.fill.fore_color.rgb = BEIGE
        for p in cell.text_frame.paragraphs:
            for r in p.runs:
                r.font.bold = True
                r.font.size = Pt(13)
                r.font.color.rgb = BG_DARK
                r.font.name = FONT_BODY

    for r_idx, row in enumerate(rows, start=1):
        for c_idx, val in enumerate(row):
            cell = tbl.cell(r_idx, c_idx)
            cell.text = str(val)
            cell.fill.solid()
            cell.fill.fore_color.rgb = RGBColor(0x2a, 0x2a, 0x2a)
            for p in cell.text_frame.paragraphs:
                for run in p.runs:
                    run.font.size = Pt(12)
                    run.font.color.rgb = WHITE
                    run.font.name = FONT_BODY
    if notes:
        slide.notes_slide.notes_text_frame.text = notes
    add_corner_decorations(slide)
    add_footer(slide, num)


def slide_gracias(prs, num):
    slide = new_blank(prs)
    add_text(slide, "GRACIAS", 0, 2.7, 13.33, 2,
             size=120, bold=True, italic=True, color=BEIGE,
             align=PP_ALIGN.CENTER, font=FONT_TITLE)
    add_text(slide, "Equipo Grupo 3 — UTP 2026",
             0, 5.0, 13.33, 0.5,
             size=18, italic=True, color=WHITE,
             align=PP_ALIGN.CENTER)
    add_dot(slide, 0.5, 0.5, 0.4, BEIGE)
    add_dot(slide, 12.4, 6.6, 0.4, BEIGE)


# =========================================================
#                       MAIN
# =========================================================

def main():
    print("Generando PowerPoint estilo Avance 01...")
    prs = Presentation()
    prs.slide_width = Inches(13.333)
    prs.slide_height = Inches(7.5)

    # 1 — Portada
    slide_portada(prs)

    # 2 — Integrantes
    slide_integrantes(prs)

    # 3 — Agenda
    slide_agenda(prs)

    # 4 — ¿Qué es GymMax?
    slide_content(prs, 4, "¿QUÉ ES GYMMAX?", bullets=[
        "Plataforma web centralizada para gestión de cadenas de gimnasios",
        "Construida sobre Java EE + MySQL + Bootstrap 5.3",
        "Enfoque Mobile First: accesible desde cualquier dispositivo",
        "Centraliza: sedes, socios, membresías, reservas, pagos y reportes",
    ])

    # 5 — Problemática
    slide_content(prs, 5, "¿QUÉ PROBLEMAS RESOLVEMOS?", bullets=[
        "Pérdida de ingresos por falta de control de vencimientos",
        "Sobrecarga administrativa y procesos manuales",
        "Colas largas en recepción en horarios pico",
        "Mala experiencia del socio: no puede autogestionarse desde el celular",
        "Sistemas existentes lentos y poco escalables",
    ], notes="Estos 5 problemas fueron validados en investigación de antecedentes del sector peruano (SmartGym, GymSys, BodyTrack).")

    # 6 — Objetivos
    slide_content(prs, 6, "OBJETIVO GENERAL Y ESPECÍFICOS", bullets=[
        "GENERAL: aplicación web JEE que centralice sedes, socios, membresías y reservas con interfaz Mobile First",
        "1. Módulo de consulta de sedes con cupos por clase",
        "2. Formulario responsive de registro con validación tiempo real",
        "3. Sistema de planes (Básico / Premium / Anual) con renovación autónoma",
        "4. Diseño Mobile First con Bootstrap 5.3",
        "5. Panel admin con KPIs y reportes exportables",
        "6. Arquitectura por capas MVC + DAO + Facade",
    ], font_size=15)

    # 7 — Alcance (2 columnas)
    slide_two_columns(prs, 7, "ALCANCE DEL PROYECTO",
        "SÍ INCLUYE", [
            "Multi-sede centralizada",
            "Roles Socio + Administrador",
            "Membresías digitales (3 planes)",
            "Reservas con control de cupos",
            "Pagos digitales (Yape/Plin/Tarjeta)",
            "Dashboard KPIs + reportes Excel/PDF",
        ],
        "NO INCLUYE (esta versión)", [
            "Pagos en efectivo",
            "App nativa Android/iOS",
            "Biometría de acceso",
            "Marketing por correo masivo",
            "Integración contable externa",
        ])

    # 8 — Características
    slide_content(prs, 8, "10 CARACTERÍSTICAS CLAVE", bullets=[
        "Multi-sede centralizada",
        "Sistema de roles Socio / Admin",
        "Mobile First responsive (Bootstrap 5.3)",
        "Pagos digitales (Yape, Plin, Tarjeta)",
        "Dashboard administrativo con KPIs",
        "Reservas online con control de cupos en tiempo real",
        "Validación en frontend y backend",
        "Reportes exportables a Excel y PDF",
        "Seguridad y control de sesión por rol",
        "Arquitectura por capas MVC + DAO + Facade",
    ], font_size=15)

    # 9 — Product Backlog
    slide_tabla(prs, 9, "PRODUCT BACKLOG — SPRINTS",
        ["Sprint", "Foco", "Story Points"],
        [
            ["Sprint 1", "Autenticación + base del socio", "22 SP"],
            ["Sprint 2", "Sedes + clases + reservas", "37 SP"],
            ["Sprint 3", "Membresías + pagos", "41 SP"],
            ["Sprint 4", "Reportes + asistencias", "26 SP"],
            ["Sprint 5", "Notificaciones + cierre", "8 SP"],
            ["TOTAL", "28 RF + 12 RNF", "134 SP"],
        ],
        notes="El backlog completo está en el informe Word con las 40 historias detalladas.")

    # 10 — User Stories destacadas
    slide_content(prs, 10, "USER STORIES DESTACADAS", bullets=[
        "US-08 Reservar clase: como socio con membresía activa quiero reservar una clase para asegurar mi cupo (valida membresía, descuenta cupo, confirma)",
        "US-14 Dashboard Admin: como admin quiero ver KPIs en tiempo real para decidir (4 KPIs, variación %, gráfico de 6 meses)",
        "US-17 Reportes: como admin quiero generar reportes filtrables (4 tipos, filtros sede + fecha, exportar Excel/PDF)",
    ], font_size=15,
       notes="El documento incluye las 18 historias completas con criterios de aceptación verificables.")

    # 11 — Flujo Socio
    slide_imagen_placeholder(prs, 11, "FLUJO DE NAVEGACIÓN — SOCIO",
        "Captura del diagrama de flujo del Socio (Avance 01: login → dashboard → sedes/reservas/mis reservas)",
        notes="El socio ingresa al login; si sus credenciales son válidas accede al dashboard; si no, puede registrarse.")

    # 12 — Flujo Admin
    slide_imagen_placeholder(prs, 12, "FLUJO DE NAVEGACIÓN — ADMINISTRADOR",
        "Captura del diagrama de flujo del Admin (Avance 01: login → adminDashboard → gestionSocios/gestionSedes/reportes)")

    # 13 — Mockups Mobile 1
    slide_imagen_placeholder(prs, 13, "WIREFRAMES MOBILE (1/2)",
        "Capturas del Avance 01: Login del socio · Registro de socio · Dashboard del socio")

    # 14 — Mockups Mobile 2
    slide_imagen_placeholder(prs, 14, "WIREFRAMES MOBILE (2/2)",
        "Capturas del Avance 01: Listado de sedes · Reservar clase · Mis reservas")

    # 15 — Desktop Admin
    slide_imagen_placeholder(prs, 15, "WIREFRAMES DESKTOP — ADMIN",
        "Capturas del Avance 01: Dashboard administrativo (KPIs, gráficos) · Gestión de socios (tabla CRUD)")

    # 16 — Desktop Reportes
    slide_imagen_placeholder(prs, 16, "WIREFRAMES DESKTOP — REPORTES",
        "Captura del mockup: Generación de reportes (gráfico de barras + tabla de resumen numérico)")

    # 17 — BD Lógico
    slide_imagen_placeholder(prs, 17, "MODELO DE BASE DE DATOS — LÓGICO",
        "Captura del diagrama lógico hecho en Draw.io: 10 entidades sin tipos de dato, con relaciones",
        notes="El modelo lógico representa las entidades del negocio sin atarse a un SGBD específico.")

    # 18 — BD Físico
    slide_imagen_placeholder(prs, 18, "MODELO DE BASE DE DATOS — FÍSICO",
        "Captura del diagrama físico Draw.io: tipos MySQL (VARCHAR, INT, ENUM), PK, FK, restricciones",
        notes="Motor InnoDB con integridad referencial, charset utf8mb4, script SQL en anexos del Word.")

    # 19 — Tecnologías
    slide_tabla(prs, 19, "STACK TECNOLÓGICO",
        ["Capa", "Tecnología"],
        [
            ["Lenguaje", "Java 11 + Jakarta EE 10"],
            ["Frontend", "JSP + Bootstrap 5.3 + HTML5"],
            ["Controlador", "Servlets (Jakarta 6.0)"],
            ["Persistencia", "JDBC + DAO + MySQL Connector"],
            ["Base de datos", "MySQL 8.0 Community"],
            ["Servidor", "Apache Tomcat 10.1"],
            ["IDE", "Apache NetBeans 21"],
            ["Diagramas", "Draw.io / Lucid Chart"],
            ["Metodología", "Scrum (sprints de 2 semanas)"],
        ])

    # 20 — UML DTOs
    slide_imagen_placeholder(prs, 20, "DIAGRAMA DE CLASES UML (1/2)",
        "Capa DTO: 10 clases POJO + Capa DAO: 9 interfaces I*DAO + 9 implementaciones *DAOImpl",
        notes="Programación a interfaces (I*DAO) para desacoplar la persistencia de la lógica de negocio.")

    # 21 — UML Facades
    slide_imagen_placeholder(prs, 21, "DIAGRAMA DE CLASES UML (2/2)",
        "Capa Facade (5 clases) + Capa Controller (5 Servlets) + clase Conexion (singleton)",
        notes="Facade encapsula reglas de negocio; Servlets coordinan vista y modelo (patrón MVC).")

    # 22 — Diccionario
    slide_tabla(prs, 22, "DICCIONARIO DE CLASES — RESUMEN",
        ["Capa", "Cantidad"],
        [
            ["DTO", "10 clases"],
            ["DAO interfaces", "9"],
            ["DAO implementaciones", "9"],
            ["Facades", "5"],
            ["Controllers (Servlets)", "5"],
            ["Utilitaria (Conexion)", "1"],
            ["TOTAL", "39 clases"],
        ],
        notes="El diccionario completo con atributos, métodos, parámetros y descripciones está en el informe Word.")

    # 23 — Patrones
    slide_content(prs, 23, "PATRONES DE DISEÑO APLICADOS", bullets=[
        "MVC — Modelo (DTO) · Vista (JSP) · Controlador (Servlet)",
        "DAO — Aislamiento del acceso a datos (interface + impl)",
        "Facade — Encapsulamiento de reglas de negocio",
        "Singleton — Helper Conexion único para JDBC",
        "DTO — Objetos planos para transportar datos entre capas",
    ])

    # 24 — Próximos pasos
    slide_content(prs, 24, "HOJA DE RUTA — ENTREGA FINAL", bullets=[
        "Sprint 1-2 (Sem 11-12): autenticación + dashboard socio + listado de sedes",
        "Sprint 3 (Sem 13): reservas de clases + membresías y pagos",
        "Sprint 4 (Sem 14): panel administrativo + reportes + exportación",
        "Sprint 5 (Sem 15): notificaciones + pruebas integrales + documentación final",
    ], font_size=16)

    # 25 — Conclusiones
    slide_content(prs, 25, "CONCLUSIONES DEL AVANCE 02", bullets=[
        "Diseño completo: 18 user stories, 40 requerimientos identificados",
        "Modelo de datos validado: 10 tablas normalizadas + script SQL ejecutable",
        "Arquitectura por capas definida: 39 clases organizadas en 5 paquetes",
        "Stack consolidado: Java EE + MySQL + Bootstrap + Tomcat",
        "Repositorio Git público creado y documentación versionada",
    ])

    # 26 — Gracias
    slide_gracias(prs, 26)

    prs.save(OUT)
    print(f"  -> {OUT}")
    print("\n=== PowerPoint generado ===")


if __name__ == "__main__":
    main()
