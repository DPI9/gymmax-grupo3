# Capítulo I — Introducción (completo, listo para Word)

Este archivo trae el Capítulo I completo armado: introducción general, nombre del producto, antecedentes y problemática, objetivo general y específicos, alcance, y características. Puedes pegarlo tal cual al Word y solo formatear (títulos en estilo Título 1/2, etc.).

---

# CAPÍTULO I: INTRODUCCIÓN

## 1.1. Introducción general

En el contexto actual del mercado peruano, el sector de gimnasios y centros de acondicionamiento físico ha experimentado un crecimiento sostenido en los últimos años, especialmente tras la pandemia, donde el cuidado de la salud se convirtió en una prioridad para gran parte de la población. Sin embargo, este crecimiento ha evidenciado las limitaciones de los métodos tradicionales de gestión: registros manuales en cuadernos, control de membresías mediante hojas de cálculo y atención en recepción que genera largas colas en horas pico.

Frente a esta realidad, el presente trabajo propone el desarrollo de **GymMax**, una aplicación web integral construida sobre la arquitectura **Java Enterprise Edition (Jakarta EE)** que permitirá a las cadenas de gimnasios automatizar y centralizar la gestión de sus sedes, socios, membresías, clases, reservas y reportes financieros. La solución se diseña con un enfoque **Mobile First** utilizando Bootstrap 5.3, garantizando que tanto socios como administradores puedan acceder al sistema desde cualquier dispositivo —smartphone, tablet o computadora— de manera ágil y consistente.

El documento que se presenta a continuación detalla el avance del diseño y la arquitectura de la solución, abarcando desde la identificación de los actores y casos de uso hasta el modelado completo de la base de datos, el diagrama de clases UML del backend y el diccionario detallado de cada componente del sistema. Este informe constituye el **Avance 02** del proyecto, conforme a los lineamientos del curso Desarrollo Web Integrado de la Universidad Tecnológica del Perú.

---

## 1.2. El producto

### 1.2.1. Nombre del producto

El producto desarrollado por el equipo se denomina **GymMax — Sistema Integral de Gestión de Gimnasios**.

El nombre combina los términos *Gym* (gimnasio) y *Max* (máximo), transmitiendo la propuesta de valor de "llevar al máximo la gestión del gimnasio". El logotipo manejará una paleta de color naranja vibrante (#FF6B00) sobre fondo oscuro, alineada con la identidad de marca enérgica del rubro fitness.

### 1.2.2. Descripción del producto

GymMax es una plataforma web centralizada que digitaliza el ciclo de vida completo del socio de un gimnasio: desde el registro inicial y la contratación de membresías digitales, hasta la reserva online de clases en cualquiera de las sedes de la cadena. El sistema incorpora también un panel administrativo robusto para los responsables del gimnasio, con KPIs en tiempo real, gestión CRUD de socios y sedes, generación de reportes financieros y exportación a Excel y PDF.

A diferencia de las soluciones tradicionales del mercado peruano —en su mayoría sistemas de escritorio aislados por sede—, GymMax centraliza toda la información en una sola base de datos MySQL y la expone a través de una interfaz web responsive, permitiendo escalabilidad horizontal cuando la cadena de gimnasios decida abrir nuevos locales.

---

## 1.3. Antecedentes y problemática

### 1.3.1. Antecedentes del sector

A nivel nacional, se han documentado varios proyectos académicos y comerciales que abordan parcialmente la problemática de gestión de gimnasios:

**SmartGym Perú (2022) — Lima.** Desarrollado por estudiantes de la Universidad Nacional Mayor de San Marcos (UNMSM), este proyecto consistió en un sistema web de gestión de membresías para una cadena con tres sedes en Lima Metropolitana. Implementado en PHP y MySQL, logró reducir en un 58% el tiempo de registro de nuevos socios y disminuir la morosidad en pagos mensuales en un 35% mediante automatización de recordatorios por correo electrónico y SMS.

**GymSys Trujillo (2020) — La Libertad.** Desarrollado por la Universidad César Vallejo (UCV) como tesis de pregrado, GymSys fue inicialmente un sistema de escritorio convertido posteriormente a web para gestionar reservas de clases grupales (spinning, yoga, crossfit) en un gimnasio de Trujillo. Incrementó la ocupación de horarios pico en un 40% al permitir a los socios reservar cupos desde sus dispositivos móviles, evitando sobrecupos y mejorando la experiencia del usuario.

**BodyTrack (2023) — Callao.** Proyecto desarrollado por egresados de la Universidad Tecnológica del Perú (UTP) para un gimnasio mediano del Callao. BodyTrack implementó un sistema de control de pagos recurrentes y generación de reportes de ingresos, logrando una disminución del 50% en la cartera morosa y un incremento del 25% en la retención de socios mediante notificaciones automáticas de vencimiento de membresía.

### 1.3.2. Problemática identificada

Pese a estos avances aislados, los gimnasios peruanos —especialmente las cadenas pequeñas y medianas— continúan enfrentando los siguientes problemas:

- **Pérdida de ingresos por falta de control de vencimientos.** Sin un sistema automatizado, las membresías vencen sin que el gimnasio detecte oportunamente la oportunidad de renovación, generando fuga de clientes.

- **Sobrecarga administrativa en el registro de pagos.** El personal de recepción dedica horas diarias a registrar pagos manualmente, propenso a errores humanos y duplicidades.

- **Procesos lentos y colas en recepción.** En horarios pico, los socios deben esperar para confirmar su asistencia, comprar planes o reservar clases, deteriorando la experiencia del cliente.

- **Mala experiencia del socio al no poder autogestionarse.** La imposibilidad de revisar el estado de su membresía, reservar una clase o renovar su plan desde el celular obliga al socio a acudir físicamente al gimnasio para trámites simples.

- **Sistemas existentes con bajo rendimiento.** Los pocos sistemas comerciales disponibles suelen presentar lentitud y caídas en horarios de alta demanda, afectando la operación.

Estas problemáticas justifican la necesidad de una solución integral, robusta y accesible desde cualquier dispositivo, que es justamente lo que GymMax propone.

---

## 1.4. Objetivos

### 1.4.1. Objetivo general

Desarrollar una aplicación web integral bajo la arquitectura **Java Enterprise Edition (Jakarta EE)** que centralice la visualización de sedes, el registro de socios, la venta de membresías digitales y la gestión de reservas de clases, garantizando una experiencia **Mobile First** mediante Bootstrap 5.3 y persistencia segura en MySQL.

### 1.4.2. Objetivos específicos

1. **Implementar un módulo de consulta de sedes** que permita a los usuarios visualizar todas las sedes disponibles del gimnasio, incluyendo su ubicación, horarios de atención, servicios ofrecidos y disponibilidad de cupos por clase.

2. **Desarrollar un formulario web intuitivo y responsive** que permita el registro rápido de nuevos socios, con validación de datos en tiempo real y almacenamiento seguro en MySQL.

3. **Crear un sistema de gestión de planes** (Básico, Premium, Anual) que permita a los socios contratar o renovar su membresía de forma autónoma, con cálculo automático de fechas de vencimiento y generación de comprobante digital.

4. **Diseñar una interfaz Mobile First con Bootstrap 5.3** que sea intuitiva, rápida y accesible desde cualquier dispositivo (PC, tablet, smartphone).

5. **Implementar un panel administrativo con KPIs en tiempo real** que permita al personal del gimnasio realizar CRUD de socios y sedes, así como generar y exportar reportes financieros a Excel y PDF.

6. **Aplicar la arquitectura por capas (MVC + DAO + Facade)** para garantizar la mantenibilidad, escalabilidad y separación de responsabilidades del sistema.

---

## 1.5. Alcance

El alcance de GymMax contempla el desarrollo de una plataforma web centralizada que permite la gestión completa del ciclo de vida del socio, desde el registro inicial y la compra de membresías hasta la reserva de clases por sede. El sistema incluye:

**Funcionalidades incluidas:**

- Registro y autenticación de socios y administradores con roles diferenciados.
- Gestión multi-sede con datos independientes por local.
- Catálogo de planes de membresía (Básico, Premium, Anual) con renovación autónoma.
- Reserva y cancelación de clases con control de cupos en tiempo real.
- Procesamiento de pagos digitales (Yape, Plin, Tarjeta) con generación de comprobantes.
- Panel administrativo con dashboard de KPIs, CRUD de entidades maestras y generación de reportes exportables.
- Interfaz responsive Mobile First.

**Limitaciones del alcance (lo que NO se incluye en esta versión):**

- Procesamiento de pagos en efectivo o presenciales (solo digitales).
- Integración con dispositivos biométricos para control de acceso (queda como mejora futura).
- Aplicación móvil nativa (Android/iOS); el acceso es vía navegador.
- Módulo de marketing por correo masivo (solo notificaciones transaccionales).
- Integración con sistemas contables externos.

**Público objetivo:**

El producto está dirigido a cadenas de gimnasios peruanos —entre 2 y 10 sedes— que buscan migrar de una administración manual a una infraestructura digital basada en Jakarta EE, garantizando alta disponibilidad y escalabilidad para futuras expansiones a nivel nacional.

---

## 1.6. Características del producto

GymMax es una plataforma web integral diseñada bajo la arquitectura Java Enterprise Edition (JEE), orientada a la gestión multi-sede de cadenas de gimnasios. Sus principales características son:

### 1.6.1. Características funcionales

**Gestión multi-sede centralizada.** El sistema permite administrar de manera unificada múltiples sedes del gimnasio, manteniendo información independiente de cada local (dirección, horarios, capacidad, servicios) bajo una sola base de datos centralizada.

**Sistema de roles diferenciados.** GymMax implementa dos perfiles de usuario claramente definidos: Socio (cliente final) y Administrador (personal del gimnasio), cada uno con su propio flujo de navegación, dashboard y permisos.

**Registro y autenticación segura.** Formulario responsive con validación en tiempo real, almacenamiento seguro de contraseñas y opción de "recordarme" para mejorar la experiencia del usuario.

**Gestión de membresías digitales.** Soporta tres tipos de planes (Básico, Premium, Anual) con cálculo automático de fechas de vencimiento, generación de comprobantes y notificaciones de renovación.

**Reserva de clases online.** Los socios pueden visualizar la disponibilidad de cupos por clase, horario y sede, realizar reservas en línea y cancelarlas según las políticas del gimnasio.

**Control de asistencia y Kardex.** Registro automatizado de ingresos y salidas de socios, generando un historial consultable tanto por el socio como por el administrador.

**Procesamiento de pagos digitales.** Validación de transacciones digitales mediante múltiples métodos (Yape, Plin, Tarjeta), generación de comprobantes y control de estado del pago.

**Dashboard administrativo con KPIs.** Panel para administradores que muestra en tiempo real: socios activos, ingresos del mes, membresías por vencer, reservas del día, gráficos de tendencia y actividad reciente.

**Reportes y exportación.** Generación de reportes filtrables por sede, fecha y tipo (ingresos, ocupación, morosidad), con exportación a Excel y PDF.

**CRUD completo de entidades maestras.** Operaciones de Crear, Leer, Actualizar y Eliminar sobre socios, sedes, planes, clases y horarios desde el panel administrativo.

### 1.6.2. Características técnicas

**Diseño Mobile First Responsive** con Bootstrap 5.3 que se adapta fluidamente a dispositivos móviles, tablets y escritorio.

**Arquitectura por capas (MVC + DAO + Facade)** que garantiza separación de responsabilidades, bajo acoplamiento y mantenibilidad.

**Persistencia en MySQL** Community Edition con 10 tablas normalizadas hasta 3FN, integridad referencial mediante claves foráneas y restricciones de dominio.

**Despliegue en Apache Tomcat 10** con soporte para Jakarta EE 10.

**Escalabilidad horizontal** que permite agregar nuevas sedes sin modificar la arquitectura.

**Validación en frontend y backend** (doble capa) para garantizar la integridad de datos.

**Seguridad de sesión** con timeout configurable y control de acceso por rol.

**Internacionalización de moneda** en soles peruanos (S/) con formato decimal de dos posiciones.

---

*Fin del Capítulo I — continúa en el Capítulo II: Arquitectura.*
