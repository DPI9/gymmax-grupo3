# GymMax — Sistema Integral de Gestión de Gimnasios

Proyecto académico del **Grupo 3** del curso **Desarrollo Web Integrado** (Sec. 27672) — UTP 2026.

**Docente:** Juan Manuel Rodríguez del Águila

## Integrantes

- Choque Anchante, Niurka Yasbeth
- Diaz Culqui, Nehemias
- Purizaca Ipanaque, Dennys Marlon
- Soria Chavez, Ian
- Torre Escobar, Oliver
- Valladolid Llenque, Alexander

## Estructura del repositorio

```
.
└── sysgymax/                ← Sistema completo Java EE
    ├── pom.xml              ← Proyecto Maven
    ├── README.md            ← Cómo levantarlo paso a paso
    ├── db/schema.sql        ← Script SQL (BD + datos iniciales)
    ├── src/main/java/com/gymmax/
    │   ├── Conexion.java
    │   ├── config/          ← Constantes
    │   ├── controller/      ← 8 Servlets
    │   ├── dao/             ← 9 DAOs (JDBC)
    │   ├── model/           ← 10 POJOs
    │   └── resources/
    └── src/main/webapp/
        ├── index.jsp, login.jsp, registro.jsp
        ├── dashboardSocio.jsp, sedes.jsp
        ├── reservarClase.jsp, misReservas.jsp
        ├── membresias.jsp
        ├── adminDashboard.jsp, gestionSocios.jsp, gestionSedes.jsp
        └── WEB-INF/, META-INF/
```

## Stack tecnológico

- **Lenguaje:** Java 11 + Jakarta EE 10
- **Vista:** JSP + Bootstrap 5.3 (Mobile First)
- **Controlador:** Servlets (Jakarta Servlet 6.0)
- **Modelo:** POJOs
- **Persistencia:** JDBC + patrón DAO + MySQL Connector/J
- **Base de datos:** MySQL 8 (WAMP)
- **Servidor:** Apache Tomcat 10.1
- **Build:** Apache Maven
- **IDE:** Apache NetBeans 21+

## Cómo levantar el sistema

Ver instrucciones detalladas en [sysgymax/README.md](sysgymax/README.md).

**Resumen rápido:**
1. Importar `sysgymax/db/schema.sql` en MySQL (phpMyAdmin de WAMP o MySQL Workbench)
2. Abrir el proyecto `sysgymax/` en NetBeans
3. Clean and Build → Run
4. Acceder a `http://localhost:8080/sysgymax/`

## Credenciales de prueba

| Rol | Correo | Contraseña |
|---|---|---|
| Socio | `socio@gymmax.com` | `socio123` |
| Admin | `admin@gymmax.com` | `admin123` |
