-- =====================================================
-- GymMax — Esquema fisico de la base de datos
-- Motor: MySQL 8.0
-- Charset: utf8mb4
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

-- 3. PLAN_MEMBRESIA
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
    id_membresia    INT AUTO_INCREMENT PRIMARY KEY,
    id_socio        INT NOT NULL,
    id_plan         INT NOT NULL,
    fecha_inicio    DATE NOT NULL,
    fecha_fin       DATE NOT NULL,
    estado          ENUM('ACT','VEN','SUS') NOT NULL DEFAULT 'ACT',
    monto           DECIMAL(10,2) NOT NULL,
    renovacion_auto BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_memb_socio FOREIGN KEY (id_socio) REFERENCES SOCIO(id_socio),
    CONSTRAINT fk_memb_plan  FOREIGN KEY (id_plan)  REFERENCES PLAN_MEMBRESIA(id_plan),
    INDEX idx_memb_estado (estado),
    INDEX idx_memb_fechafin (fecha_fin)
) ENGINE=InnoDB;

-- 5. PAGO
CREATE TABLE PAGO (
    id_pago         INT AUTO_INCREMENT PRIMARY KEY,
    id_membresia    INT NOT NULL,
    metodo          ENUM('YAPE','PLIN','TARJ') NOT NULL,
    monto           DECIMAL(10,2) NOT NULL,
    fecha_pago      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nro_operacion   VARCHAR(50) NOT NULL,
    estado          ENUM('OK','FAIL','REF') NOT NULL DEFAULT 'OK',
    comprobante_url VARCHAR(200),
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
    id_horario       INT AUTO_INCREMENT PRIMARY KEY,
    id_clase         INT NOT NULL,
    fecha_especifica DATE NOT NULL,
    cupo_actual      INT NOT NULL DEFAULT 0,
    cupo_disponible  INT NOT NULL,
    estado           ENUM('DISP','LLENO') NOT NULL DEFAULT 'DISP',
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
    CONSTRAINT fk_res_clase FOREIGN KEY (id_clase) REFERENCES CLASE(id_clase)
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
    CONSTRAINT fk_asis_sede  FOREIGN KEY (id_sede)  REFERENCES SEDE(id_sede)
) ENGINE=InnoDB;

-- =====================================================
-- DATOS INICIALES (semilla)
-- =====================================================

-- Planes
INSERT INTO PLAN_MEMBRESIA (nombre, tipo, precio, duracion_dias) VALUES
('Plan Basico',   'BASIC', 49.00,  30),
('Plan Premium',  'PREM',  99.00,  30),
('Plan Anual',    'ANUAL', 899.00, 365);

-- Sedes
INSERT INTO SEDE (nombre, direccion, distrito, telefono, hora_apertura, hora_cierre, capacidad) VALUES
('Sede Miraflores', 'Av. Larco 345',           'Miraflores', '01-4451111', '06:00:00', '23:00:00', 150),
('Sede San Isidro', 'Av. Javier Prado 2345',   'San Isidro', '01-4452222', '05:30:00', '23:30:00', 180),
('Sede Surco',      'Av. Caminos del Inca 1890','Surco',     '01-4453333', '06:00:00', '22:00:00', 120),
('Sede Lince',      'Av. Arequipa 2040',       'Lince',      '01-4454444', '06:00:00', '22:00:00', 100);

-- Clases de prueba (sede 1 = Miraflores)
INSERT INTO CLASE (id_sede, nombre, tipo, instructor, dia_semana, hora_inicio, cupo_maximo) VALUES
(1, 'CrossFit Manana',  'CROSS', 'Carlos Mendoza', 1, '07:00:00', 15),
(1, 'Yoga Vespertina',  'YOGA',  'Ana Ramos',      1, '19:00:00', 20),
(1, 'Spinning Power',   'SPIN',  'Luis Perez',     2, '20:00:00', 15),
(1, 'Zumba Fitness',    'ZUMBA', 'Maria Lopez',    3, '20:30:00', 18),
(2, 'CrossFit San Isidro','CROSS','Diego Torres',   2, '18:00:00', 15),
(2, 'Pilates Avanzado', 'PILATES','Sofia Vargas',  4, '19:30:00', 12);

-- Usuario admin de prueba (password: admin123)
INSERT INTO USUARIO (correo, password, nombres, apellidos, rol) VALUES
('admin@gymmax.com', 'admin123', 'Admin', 'GymMax', 'ADMIN');

-- Socio de prueba (password: socio123)
INSERT INTO USUARIO (correo, password, nombres, apellidos, rol) VALUES
('socio@gymmax.com', 'socio123', 'Dennys', 'Purizaca', 'SOCIO');
INSERT INTO SOCIO (id_usuario, dni, celular, fecha_nac, genero, direccion) VALUES
(LAST_INSERT_ID(), '72345678', '987654321', '1995-05-15', 'M', 'Av. Larco 123, Miraflores');

-- Membresia activa para el socio de prueba
INSERT INTO MEMBRESIA (id_socio, id_plan, fecha_inicio, fecha_fin, estado, monto) VALUES
(1, 2, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY), 'ACT', 99.00);
INSERT INTO PAGO (id_membresia, metodo, monto, nro_operacion, estado) VALUES
(1, 'YAPE', 99.00, 'OP-INICIAL01', 'OK');

SELECT '=== BD gymmax creada con datos iniciales ===' AS mensaje;
