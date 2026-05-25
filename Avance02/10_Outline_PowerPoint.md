# Outline del PowerPoint — Avance 02 GymMax

> **¿Qué es un outline?** Es el **esquema** de la presentación, diapositiva por diapositiva, antes de diseñarla. Sirve para que el equipo sepa qué va en cada slide, qué dirá el expositor y en qué orden. Es la "lista de cajas" de la presentación.

**Duración recomendada:** 15-20 minutos de exposición · ~25 diapositivas · ~1 min por slide.

**Reparto sugerido entre los 6 integrantes:** 4 slides por persona aproximadamente.

---

## Slide 1 — Portada

**Título:** GymMax — Sistema Integral de Gestión de Gimnasios
**Subtítulo:** Avance 02 — Diseño y Arquitectura

**Contenido:**
- Logo "GymMax" en naranja
- Universidad Tecnológica del Perú
- Curso: Desarrollo Web Integrado (Sec. 27672)
- Docente: Juan Manuel Rodríguez del Águila
- Mayo 2026

**Quién expone:** —

---

## Slide 2 — Integrantes del equipo

**Título:** Equipo Grupo 3

**Contenido:** Lista con foto de los 6 integrantes
- Choque Anchante Niurka Yasbeth
- Diaz Culqui Nehemias
- Purizaca Ipanaque Dennys Marlon
- Soria Chavez Ian
- Torre Escobar Oliver
- Valladolid Llenque Alexander

**Quién expone:** Uno cualquiera (presentador inicial)

---

## Slide 3 — Agenda

**Título:** Agenda de la presentación

**Contenido (bullets):**
1. Introducción y problemática
2. Objetivos y alcance
3. Características del producto
4. Product Backlog y User Stories
5. Wireframes y flujos de navegación
6. Modelo de Base de Datos (Lógico + Físico)
7. Diagrama de Clases UML
8. Tecnologías utilizadas
9. Próximos pasos

**Notas del expositor:** "En los próximos 15 minutos recorreremos los 9 puntos que definen el diseño completo del sistema GymMax."

---

## Slide 4 — El producto

**Título:** ¿Qué es GymMax?

**Contenido:**
- Imagen del mockup móvil principal (dashboard del socio) a la izquierda
- A la derecha, 3 bullets cortos:
  - Plataforma web **centralizada** para gestión de cadenas de gimnasios
  - Construida sobre **Java EE + MySQL + Bootstrap 5.3**
  - Enfoque **Mobile First**: accesible desde cualquier dispositivo

**Quién expone:** Integrante 1

---

## Slide 5 — Problemática

**Título:** ¿Qué problemas resolvemos?

**Contenido:** Iconos + texto corto (no más de 5 líneas):
- 💸 Pérdida de ingresos por falta de control de vencimientos
- ⏳ Sobrecarga administrativa y procesos manuales
- 📞 Colas largas en recepción en horarios pico
- 📱 Mala experiencia del socio: no puede autogestionarse desde el celular
- 🐢 Sistemas existentes lentos y poco escalables

**Notas:** "Estos 5 problemas fueron validados en investigación de antecedentes del sector peruano (SmartGym, GymSys, BodyTrack)."

**Quién expone:** Integrante 1

---

## Slide 6 — Objetivos del proyecto

**Título:** Objetivo general y específicos

**Contenido:**

**Objetivo general:** Desarrollar una aplicación web JEE que centralice sedes, socios, membresías y reservas, con interfaz Mobile First.

**Objetivos específicos (4):**
1. Módulo de consulta de sedes con cupos por clase
2. Formulario responsive de registro de socios con validación en tiempo real
3. Sistema de planes (Básico/Premium/Anual) con renovación autónoma
4. Diseño Mobile First con Bootstrap 5.3

**Quién expone:** Integrante 2

---

## Slide 7 — Alcance del proyecto

**Título:** ¿Qué incluye y qué no?

**Contenido (dos columnas):**

**SÍ incluye:**
- Multi-sede centralizada
- Roles Socio + Administrador
- Membresías digitales y pagos online
- Dashboard con KPIs y reportes exportables

**NO incluye (esta versión):**
- Pagos en efectivo
- App nativa Android/iOS
- Integración biométrica
- Marketing masivo

**Quién expone:** Integrante 2

---

## Slide 8 — Características destacadas

**Título:** 10 características clave del producto

**Contenido:** Grid de 10 íconos con texto corto (máx 3-4 palabras cada uno):
1. 🏢 Multi-sede
2. 👥 Multi-rol
3. 📱 Mobile First
4. 💳 Pagos digitales
5. 📊 Dashboard KPIs
6. 📅 Reservas online
7. ✅ Validación tiempo real
8. 📑 Reportes Excel/PDF
9. 🔒 Seguridad por rol
10. ⚙️ Arquitectura por capas

**Quién expone:** Integrante 2

---

## Slide 9 — Product Backlog (resumen)

**Título:** Product Backlog — Requerimientos del sistema

**Contenido:**
- **28 Requerimientos Funcionales** + **12 No Funcionales**
- Distribuidos en **5 sprints** (134 Story Points totales)
- Tabla compacta: Sprint | Foco | SP
  - Sprint 1: Auth + Socio base — 22 SP
  - Sprint 2: Sedes + Reservas — 37 SP
  - Sprint 3: Membresías + Pagos — 41 SP
  - Sprint 4: Reportes + Asistencias — 26 SP
  - Sprint 5: Notificaciones — 8 SP

**Notas:** "El backlog completo está en el informe Word con las 40 historias detalladas."

**Quién expone:** Integrante 3

---

## Slide 10 — User Stories destacadas

**Título:** Historias de usuario (extracto)

**Contenido:** 3 historias representativas (una por módulo) con tarjetas tipo Scrum:

**US-08 — Reservar clase**
> Como socio con membresía activa quiero reservar una clase para asegurar mi cupo.
> ✓ Verifica membresía activa
> ✓ Descuenta cupo
> ✓ Mensaje de confirmación

**US-14 — Dashboard administrativo**
> Como administrador quiero ver KPIs en tiempo real para tomar decisiones.
> ✓ 4 KPIs principales
> ✓ Variación % vs período anterior
> ✓ Gráfico de tendencia 6 meses

**US-17 — Generar reportes**
> Como admin quiero generar reportes filtrables para analizar el negocio.
> ✓ 4 tipos de reporte
> ✓ Filtros por sede + fecha
> ✓ Exportar Excel + PDF

**Notas:** "El documento incluye las 18 historias completas con criterios de aceptación."

**Quién expone:** Integrante 3

---

## Slide 11 — Diagrama de flujo de navegación (Socio)

**Título:** Flujo del Socio

**Contenido:** Pegar la imagen del diagrama de flujo del Avance 01 (login → dashboard → sedes/reservas/mis reservas)

**Notas:** "El socio ingresa al login → si sus credenciales son válidas accede al dashboard; si no, puede registrarse. Desde el dashboard puede navegar a sedes, reservar clases y ver sus reservas."

**Quién expone:** Integrante 3

---

## Slide 12 — Diagrama de flujo de navegación (Admin)

**Título:** Flujo del Administrador

**Contenido:** Pegar la imagen del diagrama de flujo del Avance 01 (login → adminDashboard → gestionSocios/gestionSedes/reportes)

**Notas:** "El admin se autentica → el sistema valida el rol → accede al dashboard administrativo desde donde gestiona socios, sedes y genera reportes."

**Quién expone:** Integrante 4

---

## Slide 13 — Wireframes móviles

**Título:** Diseño Mobile (Bootstrap 5.3 — Mobile First)

**Contenido:** Pegar la imagen con 3 mockups móviles del Avance 01:
- Login
- Registro de socio
- Dashboard del socio

**Quién expone:** Integrante 4

---

## Slide 14 — Wireframes móviles (continuación)

**Título:** Wireframes Mobile — Reservas

**Contenido:** Pegar la imagen con los otros 3 mockups móviles del Avance 01:
- Listado de sedes
- Reservar clase
- Mis reservas

**Quién expone:** Integrante 4

---

## Slide 15 — Wireframes Desktop

**Título:** Diseño Escritorio — Panel Administrativo

**Contenido:** Pegar la imagen del mockup escritorio del Avance 01:
- Dashboard admin (KPIs, gráfico, actividad reciente)
- Gestión de socios

**Quién expone:** Integrante 5

---

## Slide 16 — Wireframes Desktop (Reportes)

**Título:** Diseño Escritorio — Reportes

**Contenido:** Pegar el mockup de "Generación de reportes" con gráfico de barras y tabla de resumen numérico

**Quién expone:** Integrante 5

---

## Slide 17 — Modelo de Base de Datos LÓGICO

**Título:** Modelo Lógico de Base de Datos

**Contenido:**
- Captura del diagrama lógico hecho en Lucid Chart (10 entidades sin tipos de dato, con relaciones Crow's Foot)
- Bullet con resumen: **10 entidades · 10 relaciones · normalizado a 3FN**

**Notas:** "El modelo lógico representa las entidades del negocio sin atarse a un SGBD específico."

**Quién expone:** Integrante 5

---

## Slide 18 — Modelo de Base de Datos FÍSICO

**Título:** Modelo Físico — MySQL 8.0

**Contenido:**
- Captura del diagrama físico de Lucid Chart (con tipos de dato, PK, FK, índices)
- Bullets:
  - Motor InnoDB con integridad referencial
  - Charset utf8mb4 (soporte de caracteres especiales)
  - Script SQL listo (en anexos del Word)

**Quién expone:** Integrante 5

---

## Slide 19 — Tecnologías aplicadas

**Título:** Stack tecnológico de GymMax

**Contenido:** Tabla compacta de 2 columnas:

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 11 + Jakarta EE 10 |
| Frontend | JSP + Bootstrap 5.3 + HTML5 |
| Controlador | Servlets (Jakarta 6.0) |
| Persistencia | JDBC + DAO + Conector MySQL |
| Base de datos | MySQL 8.0 Community |
| Servidor | Apache Tomcat 10.1 |
| IDE | NetBeans 21 |
| Diagramas | Lucid Chart |
| Metodología | Scrum (sprints 2 semanas) |
| Versionamiento | Git + GitHub |

**Quién expone:** Integrante 6

---

## Slide 20 — Diagrama de Clases UML (1/2)

**Título:** Arquitectura por Capas — DTOs y DAOs

**Contenido:**
- Captura del diagrama UML mostrando: 10 DTOs + 9 interfaces DAO + 9 implementaciones
- Bullet: "Programación a interfaces para desacoplar persistencia"

**Notas:** "Cada entidad tiene su DTO (objeto de datos), una interfaz de DAO que define el contrato CRUD, y una implementación que usa JDBC."

**Quién expone:** Integrante 6

---

## Slide 21 — Diagrama de Clases UML (2/2)

**Título:** Capa de Negocio y Controladores

**Contenido:**
- Captura del diagrama UML mostrando: 5 Facades + 5 Servlets + clase Conexion
- Bullet: "Facade encapsula reglas de negocio; Servlets coordinan vista y modelo (MVC)"

**Quién expone:** Integrante 6

---

## Slide 22 — Diccionario de Clases (resumen)

**Título:** Diccionario de Clases — Componentes del sistema

**Contenido:** Tabla resumen:

| Capa | Cantidad |
|---|---|
| DTO | 10 clases |
| DAO interfaces | 9 |
| DAO implementaciones | 9 |
| Facades | 5 |
| Controllers (Servlets) | 5 |
| Utilitaria (Conexion) | 1 |
| **TOTAL** | **39 clases** |

**Notas:** "El diccionario completo con atributos, métodos, parámetros y descripciones está detallado en el informe Word del Avance 02."

**Quién expone:** Integrante 6

---

## Slide 23 — Patrones de diseño aplicados

**Título:** Patrones que estructuran el código

**Contenido:** 4 cajas:
- **MVC**: Modelo (DTO) – Vista (JSP) – Controlador (Servlet)
- **DAO**: Aislamiento del acceso a datos
- **Facade**: Encapsulamiento de reglas de negocio
- **Singleton**: Helper `Conexion` único para JDBC

**Notas:** "Estos 4 patrones garantizan separación de responsabilidades, bajo acoplamiento y mantenibilidad del código."

**Quién expone:** Integrante 6

---

## Slide 24 — Próximos pasos (Entrega Final)

**Título:** Hoja de ruta hacia la entrega final

**Contenido:** Timeline horizontal con 4 hitos:

1. **Sprint 1-2 (Semanas 11-12):** Implementación del módulo de autenticación + dashboard socio + listado de sedes
2. **Sprint 3 (Semana 13):** Reservas de clases + membresías y pagos
3. **Sprint 4 (Semana 14):** Panel administrativo + reportes + exportación
4. **Sprint 5 (Semana 15):** Notificaciones + pruebas integrales + documentación final

**Quién expone:** Integrante 4 o 5

---

## Slide 25 — Conclusiones

**Título:** Conclusiones del Avance 02

**Contenido:** 4 bullets concisos:
- ✅ Diseño completo del sistema cubierto: 18 user stories, 40 requerimientos
- ✅ Modelo de datos validado: 10 tablas normalizadas + script SQL listo
- ✅ Arquitectura por capas definida: 39 clases organizadas en 5 paquetes
- ✅ Stack tecnológico consolidado: Java EE + MySQL + Bootstrap + Tomcat

**Quién expone:** Integrante 1 (el que abrió, para cerrar)

---

## Slide 26 — Gracias

**Título:** ¡Gracias!

**Contenido:**
- Logo GymMax centrado
- "¿Preguntas?"
- Datos de contacto del grupo / repositorio GitHub si lo tienen
- "Equipo Grupo 3 — UTP 2026"

**Quién expone:** El que cierra

---

# Tips de diseño para la presentación

- **Tema de color:** mantener la paleta del proyecto (naranja #FF6B00, fondo oscuro #1a1a1a, beige #d4b88f para textos cálidos).
- **Tipografía:** sans-serif moderna (Montserrat, Poppins o Segoe UI). Tamaño mínimo 18pt para que se lea desde el fondo del aula.
- **Imágenes:** todas las capturas de Lucid Chart y mockups en alta resolución (mínimo 1280px de ancho).
- **Animaciones:** evitar transiciones complejas; usar solo aparecer/desaparecer.
- **Máximo 6 bullets por slide.** Si necesitas más, divide en 2 diapositivas.
- **Tiempo por slide:** ~30-60 segundos. 25 slides × 45s = ~18 minutos. Ensayar al menos 2 veces.

# Checklist antes de exponer

- [ ] PowerPoint convertido a PDF como respaldo (por si falla PowerPoint).
- [ ] Diagramas exportados de Lucid Chart en PNG alta resolución insertados en los slides.
- [ ] Capturas del Avance 01 (mockups, flujos) reutilizadas y nítidas.
- [ ] Cada integrante sabe qué slides le tocan exponer.
- [ ] Probar en proyector / aula virtual antes de la sesión.
- [ ] Llevar laptop + cable HDMI + USB con backup.
