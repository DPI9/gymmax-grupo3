# sysgymax — Sistema Integral de Gestión de Gimnasios

Proyecto Java EE / Jakarta EE 10 del grupo 3 — UTP Desarrollo Web Integrado.

## Stack
- Java 11 + Jakarta EE 10
- JSP + Bootstrap 5.3 (Mobile First)
- Apache Tomcat 10.1
- MySQL 8.0
- Maven

## Estructura

```
sysgymax/
├── pom.xml
├── nb-configuration.xml
├── db/
│   └── schema.sql                       # Script SQL: crear BD + tablas + datos
├── src/main/java/com/gymmax/
│   ├── Conexion.java                    # Helper JDBC
│   ├── config/AppConstants.java         # Constantes globales
│   ├── controller/                      # 8 Servlets (Login, Registro, Dashboard, Sede, Reserva, Membresia, Admin, Logout)
│   ├── dao/                             # 9 DAOs (un archivo por entidad, sin interfaces)
│   ├── model/                           # 10 POJOs del dominio
│   └── resources/                       # (vacio, listo para REST)
└── src/main/webapp/
    ├── index.jsp                        # redirige a login
    ├── login.jsp
    ├── registro.jsp
    ├── dashboardSocio.jsp
    ├── sedes.jsp
    ├── reservarClase.jsp
    ├── misReservas.jsp
    ├── membresias.jsp
    ├── adminDashboard.jsp
    ├── gestionSocios.jsp
    ├── gestionSedes.jsp
    ├── META-INF/context.xml             # path /sysgymax
    └── WEB-INF/web.xml                  # config web
```

## Pasos para levantarlo

1. **Crear la BD en MySQL Workbench:**
   - Abrir `db/schema.sql`
   - Ejecutar todo (botón rayo)
   - Esto crea la BD `gymmax` con 10 tablas + datos iniciales

2. **Configurar la conexión (si tu MySQL usa otra contraseña):**
   - Editar `src/main/java/com/gymmax/Conexion.java`
   - Cambiar `USER` y `PASSWORD` si no son `root` / vacío

3. **Abrir en NetBeans:**
   - File → Open Project → seleccionar carpeta `sysgymax`
   - Click derecho proyecto → **Clean and Build**
   - Click derecho proyecto → **Run** (despliega en Tomcat)

4. **Abrir en navegador:** `http://localhost:8080/sysgymax/`

## Cuentas de prueba (creadas por el script SQL)

| Tipo | Correo | Contraseña |
|---|---|---|
| Admin | `admin@gymmax.com` | `admin123` |
| Socio | `socio@gymmax.com` | `socio123` |

El socio ya tiene una membresía Premium activa, así que puede reservar clases inmediatamente.

## Flujos disponibles

**Socio:**
- Login → Dashboard (membresía + asistencias + próximas reservas + acciones rápidas)
- Ver sedes / filtrar por distrito
- Reservar clase (selecciona sede → ve clases → elige fecha → reserva)
- Mis reservas (próximas y pasadas, cancelar)
- Membresías y pagos (contratar plan + historial)

**Admin:**
- Login con rol ADMIN → Dashboard con 4 KPIs en vivo (socios activos, ingresos del mes, por vencer, reservas hoy)
- Gestión de socios (tabla)
- Gestión de sedes (tabla)
