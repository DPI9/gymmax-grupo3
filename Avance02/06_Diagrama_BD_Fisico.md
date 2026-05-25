# Diagrama de Base de Datos — Modelo Físico

El modelo físico es la implementación concreta del modelo lógico en MySQL 8.0, especificando tipos de dato, claves primarias (PK), claves foráneas (FK), restricciones (UNIQUE, NOT NULL, CHECK), e índices.

## Script SQL DDL completo (listo para ejecutar en MySQL Workbench)

```sql
-- =====================================================
-- GymMax — Esquema físico de la base de datos
-- Motor: MySQL 8.0
-- Charset: utf8mb4 (soporte de emojis y caracteres especiales)
-- =====================================================

DROP DATABASE IF EXISTS gymmax;
CREATE DATABASE gymmax CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gymmax;

-- 1. USUARIO
CREATE TABLE USUARIO (
    id_usuario      INT AUTO_INCREMENT PRIMARY KEY,
    correo          VARCHAR(80) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    nombres         VARCHAR(80) NOT NULL,
    apellidos       VARCHAR(80) NOT NULL,
    rol             ENUM('SOCIO','ADMIN') NOT NULL DEFAULT 'SOCIO',
    creado_en       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 2. SOCIO
CREATE TABLE SOCIO (
    id_socio        INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario      INT NOT NULL UNIQUE,
    dni             CHAR(8) NOT NULL UNIQUE,
    celular         VARCHAR(15),
    fecha_nac       DATE,
    genero          CHAR(1) CHECK (genero IN ('M','F','O')),
    direccion       VARCHAR(150),
    fecha_reg       DATE NOT NULL DEFAULT (CURRENT_DATE),
    CONSTRAINT fk_socio_usuario FOREIGN KEY (id_usuario)
        REFERENCES USUARIO(id_usuario) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 3. PLAN
CREATE TABLE PLAN_MEMBRESIA (
    id_plan         INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(40) NOT NULL,
    tipo            ENUM('BASIC','PREM','ANUAL') NOT NULL,
    precio          DECIMAL(10,2) NOT NULL,
    duracion_dias   INT NOT NULL,
    activo          BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

-- 4. MEMBRESIA
CREATE TABLE MEMBRESIA (
    id_membresia       INT AUTO_INCREMENT PRIMARY KEY,
    id_socio           INT NOT NULL,
    id_plan            INT NOT NULL,
    fecha_inicio       DATE NOT NULL,
    fecha_fin          DATE NOT NULL,
    estado             ENUM('ACT','VEN','SUS') NOT NULL DEFAULT 'ACT',
    monto              DECIMAL(10,2) NOT NULL,
    renovacion_auto    BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_memb_socio FOREIGN KEY (id_socio) REFERENCES SOCIO(id_socio),
    CONSTRAINT fk_memb_plan  FOREIGN KEY (id_plan)  REFERENCES PLAN_MEMBRESIA(id_plan),
    INDEX idx_memb_estado (estado),
    INDEX idx_memb_fechafin (fecha_fin)
) ENGINE=InnoDB;

-- 5. PAGO
CREATE TABLE PAGO (
    id_pago             INT AUTO_INCREMENT PRIMARY KEY,
    id_membresia        INT NOT NULL,
    metodo              ENUM('YAPE','PLIN','TARJ') NOT NULL,
    monto               DECIMAL(10,2) NOT NULL,
    fecha_pago          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nro_operacion       VARCHAR(50) NOT NULL,
    estado              ENUM('OK','FAIL','REF') NOT NULL DEFAULT 'OK',
    comprobante_url     VARCHAR(200),
    CONSTRAINT fk_pago_memb FOREIGN KEY (id_membresia) REFERENCES MEMBRESIA(id_membresia),
    INDEX idx_pago_fecha (fecha_pago)
) ENGINE=InnoDB;

-- 6. SEDE
CREATE TABLE SEDE (
    id_sede         INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(60) NOT NULL,
    direccion       VARCHAR(150) NOT NULL,
    distrito        VARCHAR(50) NOT NULL,
    telefono        VARCHAR(15),
    hora_apertura   TIME NOT NULL,
    hora_cierre     TIME NOT NULL,
    capacidad       INT NOT NULL DEFAULT 100
) ENGINE=InnoDB;

-- 7. CLASE
CREATE TABLE CLASE (
    id_clase        INT AUTO_INCREMENT PRIMARY KEY,
    id_sede         INT NOT NULL,
    nombre          VARCHAR(50) NOT NULL,
    tipo            ENUM('CROSS','YOGA','SPIN','ZUMBA','FUNC','BOX','PILATES') NOT NULL,
    instructor      VARCHAR(80),
    dia_semana      TINYINT NOT NULL CHECK (dia_semana BETWEEN 1 AND 7),
    hora_inicio     TIME NOT NULL,
    cupo_maximo     INT NOT NULL DEFAULT 15,
    CONSTRAINT fk_clase_sede FOREIGN KEY (id_sede) REFERENCES SEDE(id_sede)
) ENGINE=InnoDB;

-- 8. HORARIO_CLASE
CREATE TABLE HORARIO_CLASE (
    id_horario          INT AUTO_INCREMENT PRIMARY KEY,
    id_clase            INT NOT NULL,
    fecha_especifica    DATE NOT NULL,
    cupo_actual         INT NOT NULL DEFAULT 0,
    cupo_disponible     INT NOT NULL,
    estado              ENUM('DISP','LLENO') NOT NULL DEFAULT 'DISP',
    CONSTRAINT fk_hor_clase FOREIGN KEY (id_clase) REFERENCES CLASE(id_clase),
    UNIQUE KEY uk_horario_fecha (id_clase, fecha_especifica)
) ENGINE=InnoDB;

-- 9. RESERVA
CREATE TABLE RESERVA (
    id_reserva      INT AUTO_INCREMENT PRIMARY KEY,
    id_socio        INT NOT NULL,
    id_clase        INT NOT NULL,
    fecha           DATE NOT NULL,
    hora            TIME NOT NULL,
    estado          ENUM('CONF','CAN','LE') NOT NULL DEFAULT 'CONF',
    creado_en       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cancelado_en    TIMESTAMP NULL,
    CONSTRAINT fk_res_socio FOREIGN KEY (id_socio) REFERENCES SOCIO(id_socio),
    CONSTRAINT fk_res_clase FOREIGN KEY (id_clase) REFERENCES CLASE(id_clase),
    UNIQUE KEY uk_res_socio_clase_fecha (id_socio, id_clase, fecha, hora)
) ENGINE=InnoDB;

-- 10. ASISTENCIA
CREATE TABLE ASISTENCIA (
    id_asistencia   INT AUTO_INCREMENT PRIMARY KEY,
    id_socio        INT NOT NULL,
    id_sede         INT NOT NULL,
    fecha_hora      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo            ENUM('ING','SAL') NOT NULL,
    registrado_por  INT,
    CONSTRAINT fk_asis_socio FOREIGN KEY (id_socio) REFERENCES SOCIO(id_socio),
    CONSTRAINT fk_asis_sede  FOREIGN KEY (id_sede)  REFERENCES SEDE(id_sede),
    INDEX idx_asis_fecha (fecha_hora)
) ENGINE=InnoDB;

-- =====================================================
-- DATOS INICIALES (semilla)
-- =====================================================

-- Planes
INSERT INTO PLAN_MEMBRESIA (nombre, tipo, precio, duracion_dias) VALUES
('Plan Básico',   'BASIC', 49.00, 30),
('Plan Premium',  'PREM',  99.00, 30),
('Plan Anual',    'ANUAL', 899.00, 365);

-- Sedes
INSERT INTO SEDE (nombre, direccion, distrito, telefono, hora_apertura, hora_cierre, capacidad) VALUES
('Sede Miraflores', 'Av. Larco 345',           'Miraflores', '01-4451111', '06:00:00', '23:00:00', 150),
('Sede San Isidro', 'Av. Javier Prado 2345',   'San Isidro', '01-4452222', '05:30:00', '23:30:00', 180),
('Sede Surco',      'Av. Caminos del Inca 1890','Surco',     '01-4453333', '06:00:00', '22:00:00', 120),
('Sede Lince',      'Av. Arequipa 2040',       'Lince',      '01-4454444', '06:00:00', '22:00:00', 100);

-- Usuario admin de prueba
INSERT INTO USUARIO (correo, password, nombres, apellidos, rol) VALUES
('admin@gymmax.com', 'admin123', 'Admin', 'GymMax', 'ADMIN');
```

---

## Guía paso a paso para Lucid Chart (Modelo Físico)

1. Abrir Lucid Chart → **New** → **Blank diagram**.
2. Activar la librería **"Entity Relationship"** (panel izquierdo → Shapes → "Entity Relationship").
3. Arrastrar **10 tablas** al lienzo, una por cada `CREATE TABLE` del script SQL.
4. En cada tabla, escribir:
   - **Cabecera** con el nombre de tabla (USUARIO, SOCIO, etc.).
   - **Fila por columna** con: `PK/FK · nombre_columna · TIPO_SQL`
   - Marcar con **🔑 (PK)** las claves primarias.
   - Marcar con **🔗 (FK)** las claves foráneas y anotar a qué tabla referencian.
5. Conectar las tablas con **líneas Crow's Foot** según las restricciones FK del script:
   - SOCIO ──┤── USUARIO (1:1)
   - MEMBRESIA ──┤<── SOCIO (N:1)
   - MEMBRESIA ──┤<── PLAN_MEMBRESIA (N:1)
   - PAGO ──┤<── MEMBRESIA (N:1)
   - CLASE ──┤<── SEDE (N:1)
   - HORARIO_CLASE ──┤<── CLASE (N:1)
   - RESERVA ──┤<── SOCIO (N:1)
   - RESERVA ──┤<── CLASE (N:1)
   - ASISTENCIA ──┤<── SOCIO (N:1)
   - ASISTENCIA ──┤<── SEDE (N:1)
6. Agregar título: **"Modelo Físico — GymMax — MySQL 8.0"**.
7. Exportar como PNG/JPG para insertar en el Word.

---

## Diferencia entre Lógico y Físico (para defender en exposición)

| Aspecto | Lógico | Físico |
|---|---|---|
| Tipos de dato | No (solo conceptos) | Sí (VARCHAR, INT, DATE, etc.) |
| Restricciones SQL | No | Sí (UNIQUE, NOT NULL, CHECK) |
| SGBD específico | No | Sí (sintaxis MySQL) |
| Índices | No | Sí (INDEX, UNIQUE KEY) |
| Nombre técnico | "Entidad" | "Tabla" |
| Audiencia | Analistas, usuarios | DBA, desarrolladores |
| Modificable | Fácilmente | Requiere migraciones |

---

## Convenciones usadas en el modelo físico

- **Nombres en MAYÚSCULAS** para tablas.
- **Nombres en minúsculas** para columnas, separadas por guion bajo si es compuesto.
- **PK** con sufijo `id_` (ej. `id_socio`).
- **FK** con el mismo nombre que la PK referenciada.
- **TIMESTAMPS automáticos** con `DEFAULT CURRENT_TIMESTAMP`.
- **Enums** para valores cerrados (estados, tipos, métodos).
- **Soft delete** mediante columna `activo BOOLEAN` cuando aplica (PLAN_MEMBRESIA).
- **Charset UTF-8** completo (`utf8mb4`) para soportar emojis y caracteres especiales.
- **Motor InnoDB** para soporte transaccional e integridad referencial.
