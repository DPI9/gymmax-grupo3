# Tabla de Tecnologías Aplicadas — GymMax

Resumen de tecnologías, herramientas y frameworks utilizados en el diseño, desarrollo y despliegue de la solución GymMax, organizados por capa arquitectónica.

## Tabla 1 — Lenguajes y frameworks de desarrollo

| Tecnología | Versión | Capa | Propósito |
|---|---|---|---|
| Java | 11 (LTS) | Backend | Lenguaje de programación principal del sistema |
| Jakarta EE | 10.0.0 | Backend | Plataforma empresarial estándar (Servlets, JSP, JPA, CDI, JAX-RS) |
| JSP (JavaServer Pages) | 3.1 | Frontend (server-side) | Páginas dinámicas del lado del servidor para vistas |
| HTML5 | — | Frontend | Estructura semántica de las páginas web |
| CSS3 | — | Frontend | Estilos visuales y diseño responsive |
| JavaScript | ES6+ | Frontend | Interactividad y validación en el cliente |
| Bootstrap | 5.3 | Frontend | Framework CSS Mobile First para diseño responsive |
| Bootstrap Icons | 1.11.3 | Frontend | Librería de íconos consistente con Bootstrap |
| JSTL | 3.0 | Frontend | Etiquetas estándar para JSP (control de flujo y formato) |

## Tabla 2 — Base de datos y persistencia

| Tecnología | Versión | Capa | Propósito |
|---|---|---|---|
| MySQL Community Edition | 8.0 | Base de datos | Sistema gestor de base de datos relacional |
| MySQL Workbench | 8.0 | Herramienta BD | Cliente gráfico para modelado, scripting y administración |
| JDBC | 4.3 | Backend | API estándar de Java para conexión a BD |
| MySQL Connector/J | 8.0.33 | Backend | Driver JDBC oficial de MySQL para Java |

## Tabla 3 — Servidor y despliegue

| Tecnología | Versión | Capa | Propósito |
|---|---|---|---|
| Apache Tomcat | 10.1.x | Servidor de aplicaciones | Contenedor Servlet/JSP compatible con Jakarta EE 10 |
| Apache Maven | 3.9.x | Herramienta de build | Gestión de dependencias y empaquetado WAR |

## Tabla 4 — Patrones arquitectónicos y de diseño

| Patrón | Categoría | Propósito en el sistema |
|---|---|---|
| Arquitectura en capas | Arquitectura | Separación: presentación / controlador / fachada / DAO / datos |
| MVC (Model-View-Controller) | Arquitectura | JSPs como Vista, Servlets como Controller, DTOs como Model |
| DAO (Data Access Object) | Diseño | Aislamiento del acceso a datos mediante interfaces + implementaciones |
| Facade | Diseño | Encapsulamiento de lógica de negocio entre Controller y DAO |
| DTO (Data Transfer Object) | Diseño | Objetos planos para transportar datos entre capas |
| Singleton | Diseño | Helper de conexión a base de datos (Conexion.java) |

## Tabla 5 — Metodología y herramientas de gestión

| Herramienta | Categoría | Propósito |
|---|---|---|
| Scrum | Metodología ágil | Marco de trabajo iterativo con sprints de 2 semanas |
| Trello / Jira (a definir) | Gestión de tareas | Tablero Kanban del Product Backlog y Sprint Backlog |
| Lucid Chart | Modelado | Diagramas de flujo, BD lógico/físico, clases UML |
| Microsoft Word | Documentación | Informes según norma APA 7ma edición |
| Microsoft PowerPoint | Presentación | Diapositivas de avances y entrega final |
| Git + GitHub | Control de versiones | Repositorio del código fuente del grupo |

## Tabla 6 — Entorno de desarrollo

| Tecnología | Versión | Categoría | Propósito |
|---|---|---|---|
| Apache NetBeans | 21+ | IDE | Entorno integrado de desarrollo para Java EE |
| JDK (OpenJDK) | 11+ | Runtime | Kit de desarrollo Java |
| Postman | Cualquiera | Pruebas API | Testing de endpoints REST (si se exponen) |
| Google Chrome / Edge | Última | Navegador | Visualización y pruebas frontend |
| DevTools del navegador | — | Debugging | Inspección de elementos, red y consola |

## Tabla resumen ejecutiva (versión condensada para Word)

| # | Capa / Categoría | Tecnología |
|---|---|---|
| 1 | Lenguaje principal | Java 11 + Jakarta EE 10 |
| 2 | Vista (frontend) | JSP + HTML5 + CSS3 + JavaScript + Bootstrap 5.3 |
| 3 | Controlador | Servlets (Jakarta Servlet 6.0) |
| 4 | Lógica de negocio | Facades (POJO) |
| 5 | Persistencia | JDBC + DAO + MySQL Connector/J |
| 6 | Base de datos | MySQL Community 8.0 + MySQL Workbench |
| 7 | Servidor | Apache Tomcat 10.1 |
| 8 | Build | Apache Maven |
| 9 | IDE | Apache NetBeans 21+ |
| 10 | Modelado de diagramas | Lucid Chart |
| 11 | Metodología | Scrum (sprints de 2 semanas) |
| 12 | Versionamiento | Git + GitHub |
| 13 | Documentación | Word (APA 7ma) + PowerPoint |
