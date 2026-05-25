# Diagrama de Base de Datos — Modelo Lógico

El modelo lógico representa las entidades del negocio, sus atributos y las relaciones entre ellas **sin especificar el SGBD ni los tipos de dato físicos**. Es el "qué" del sistema, antes del "cómo" físico.

## Entidades y atributos lógicos

### 1. Usuario
Persona registrada en el sistema con credenciales de acceso.
- **id_usuario** (identificador, PK)
- correo (único)
- contraseña
- nombres
- apellidos
- rol (Socio / Administrador)
- fecha_creación

### 2. Socio
Cliente del gimnasio asociado a un usuario.
- **id_socio** (identificador, PK)
- id_usuario (referencia a Usuario, FK, único)
- dni (único)
- celular
- fecha_nacimiento
- género
- dirección
- fecha_registro

### 3. Plan
Tipo de membresía que ofrece el gimnasio.
- **id_plan** (identificador, PK)
- nombre
- tipo (Básico / Premium / Anual)
- precio
- duración_en_días
- activo (sí/no)

### 4. Membresía
Contrato vigente entre un socio y un plan.
- **id_membresía** (identificador, PK)
- id_socio (referencia a Socio, FK)
- id_plan (referencia a Plan, FK)
- fecha_inicio
- fecha_fin
- estado (Activa / Vencida / Suspendida)
- monto
- renovación_automática (sí/no)

### 5. Pago
Transacción asociada a una membresía.
- **id_pago** (identificador, PK)
- id_membresía (referencia a Membresía, FK)
- método (Yape / Plin / Tarjeta)
- monto
- fecha_pago
- número_operación
- estado (OK / Fallido / Reembolsado)
- URL_comprobante

### 6. Sede
Local físico del gimnasio.
- **id_sede** (identificador, PK)
- nombre
- dirección
- distrito
- teléfono
- hora_apertura
- hora_cierre
- capacidad

### 7. Clase
Actividad que se ofrece en una sede.
- **id_clase** (identificador, PK)
- id_sede (referencia a Sede, FK)
- nombre
- tipo (CrossFit / Yoga / Spinning / Zumba / etc.)
- instructor
- día_semana
- hora_inicio
- cupo_máximo

### 8. Horario_Clase
Instancia específica de una clase en una fecha concreta.
- **id_horario** (identificador, PK)
- id_clase (referencia a Clase, FK)
- fecha_específica
- cupo_actual
- cupo_disponible
- estado (Disponible / Lleno)

### 9. Reserva
Cupo tomado por un socio en una clase para una fecha.
- **id_reserva** (identificador, PK)
- id_socio (referencia a Socio, FK)
- id_clase (referencia a Clase, FK)
- fecha
- hora
- estado (Confirmada / Cancelada / Lista de espera)
- fecha_creación
- fecha_cancelación (opcional)

### 10. Asistencia
Registro del ingreso o salida de un socio a una sede.
- **id_asistencia** (identificador, PK)
- id_socio (referencia a Socio, FK)
- id_sede (referencia a Sede, FK)
- fecha_hora
- tipo (Ingreso / Salida)
- registrado_por (id de empleado/usuario)

---

## Relaciones del modelo lógico

| Relación | Cardinalidad | Descripción |
|---|---|---|
| Usuario → Socio | 1 : 1 | Cada socio es exactamente un usuario con rol "Socio" |
| Socio → Membresía | 1 : N | Un socio puede tener varias membresías a lo largo del tiempo |
| Plan → Membresía | 1 : N | Un mismo plan puede ser contratado en muchas membresías |
| Membresía → Pago | 1 : N | Una membresía puede generar varios pagos (inicial + renovaciones) |
| Sede → Clase | 1 : N | Una sede ofrece múltiples clases |
| Clase → Reserva | 1 : N | Una clase puede tener muchas reservas |
| Socio → Reserva | 1 : N | Un socio puede hacer múltiples reservas |
| Socio → Asistencia | 1 : N | Un socio acumula múltiples asistencias |
| Sede → Asistencia | 1 : N | Una sede recibe múltiples registros de asistencia |
| Clase → Horario_Clase | 1 : N | Una clase puede tener varios horarios programados |

---

## Guía paso a paso para Lucid Chart (Modelo Lógico)

1. Abrir Lucid Chart → **New** → **Blank diagram**.
2. En el panel izquierdo, buscar **"Entity Relationship"** y activar la forma de entidad lógica.
3. Arrastrar **10 entidades** al lienzo, una por cada tabla listada arriba.
4. En cada entidad, escribir:
   - Cabecera con el nombre (ej. `USUARIO`)
   - Atributos en formato: `PK id_usuario`, `correo (único)`, `contraseña`, etc.
   - **No incluir tipos de dato** (eso es del modelo físico).
5. Conectar las entidades con líneas usando la notación **Crow's Foot** (pata de gallo) según las cardinalidades de la tabla anterior:
   - Línea con extremo "1" en un lado y "muchos" (tridente) en el otro para relaciones 1:N
   - Línea con "1" en ambos extremos para 1:1
6. Colocar el nombre de la relación sobre la línea (ej. "contrata", "reserva", "ofrece").
7. Agregar un título: **"Modelo Lógico — GymMax"**.
8. Exportar como PNG o JPG para insertar en el Word.

---

## Capa visual recomendada

- **Color de cabecera:** naranja GymMax (#FF6B00)
- **Color de fondo:** blanco con borde gris
- **Fuente:** Segoe UI o Arial
- **Tamaño de letra:** 11pt para atributos, 13pt bold para nombre de entidad
