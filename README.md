# GymMax — Sistema Integral de Gestión de Gimnasios

Proyecto académico desarrollado por el **Grupo 3** del curso **Desarrollo Web Integrado** (Sec. 27672) de la **Universidad Tecnológica del Perú** — 2026.

**Docente:** Juan Manuel Rodríguez del Águila

## Integrantes

- Choque Anchante, Niurka Yasbeth
- Diaz Culqui, Nehemias
- Purizaca Ipanaque, Dennys Marlon
- Soria Chavez, Ian
- Torre Escobar, Oliver
- Valladolid Llenque, Alexander

## Descripción

GymMax es una plataforma web integral construida sobre **Java Enterprise Edition (Jakarta EE 10)** que permite a cadenas de gimnasios automatizar y centralizar la gestión de:

- Sedes multi-distrito
- Registro y autenticación de socios
- Membresías digitales (Básico / Premium / Anual)
- Reservas de clases con control de cupos
- Pagos digitales (Yape, Plin, Tarjeta)
- Panel administrativo con KPIs y reportes exportables (Excel / PDF)

Diseño **Mobile First** con Bootstrap 5.3.

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 11 + Jakarta EE 10 |
| Frontend | JSP + Bootstrap 5.3 + HTML5 |
| Controlador | Servlets (Jakarta 6.0) |
| Persistencia | JDBC + DAO + MySQL Connector |
| Base de datos | MySQL 8.0 Community |
| Servidor | Apache Tomcat 10.1 |
| IDE | Apache NetBeans 21+ |
| Metodología | Scrum (sprints de 2 semanas) |

## Arquitectura por capas

```
JSP (Vista) → Servlet (Controller) → Facade (Negocio) → DAO (Datos) → MySQL
```

Patrones aplicados: **MVC + DAO + Facade + DTO + Singleton**.

## Estructura del repositorio

```
.
├── Avance 01.docx                  # Informe Avance 01
├── PPT Avance 1.pdf                # Presentación Avance 01
├── GymMax_Mockups.html             # Wireframes y mockups Mobile + Desktop
├── Presentación de Avance 02.pdf   # Especificación del avance 02 (profe)
└── Avance02/                       # Entregables del Avance 02
    ├── GymMax_Avance02.docx        # Informe Word completo
    ├── GymMax_Avance02.pptx        # Presentación PowerPoint
    ├── diagramas_BD.html           # Diagramas BD Lógico + Físico
    ├── diagrama_UML.html           # Diagrama de Clases UML
    ├── generar_documentos.py       # Script generador Word/PPT
    └── *.md                        # Borradores por sección
```

## Cronograma de Sprints

| Sprint | Semanas | Foco | Story Points |
|---|---|---|---|
| 1 | 11-12 | Autenticación + dashboard socio | 22 |
| 2 | 13 | Sedes + clases + reservas | 37 |
| 3 | 14 | Membresías + pagos | 41 |
| 4 | 15 | Reportes + asistencias | 26 |
| 5 | 16 | Notificaciones + cierre | 8 |

Total: **134 SP** en 5 sprints.

## Avances

- ✅ **Avance 01** (entregado): introducción, problemática, objetivos, alcance, wireframes, modelo ER
- 🔄 **Avance 02** (en curso, deadline 25/05/2026): Product Backlog, User Stories, BD Lógico+Físico, UML, Diccionario, Tecnologías
- 📅 **Entrega Final**: código backend completo en Java EE + MySQL
