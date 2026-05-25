# Características del Producto — GymMax

GymMax es una plataforma web integral diseñada bajo la arquitectura Java Enterprise Edition (JEE), orientada a la gestión multi-sede de cadenas de gimnasios. Sus principales características son:

## Características funcionales

**1. Gestión multi-sede centralizada**
El sistema permite administrar de manera unificada múltiples sedes del gimnasio, manteniendo información independiente de cada local (dirección, horarios, capacidad, servicios) bajo una sola base de datos centralizada.

**2. Sistema de roles diferenciados**
GymMax implementa dos perfiles de usuario claramente definidos: Socio (cliente final) y Administrador (personal del gimnasio), cada uno con su propio flujo de navegación, dashboard y permisos.

**3. Registro y autenticación segura**
Formulario responsive con validación en tiempo real, almacenamiento seguro de contraseñas y opción de "recordarme" para mejorar la experiencia del usuario.

**4. Gestión de membresías digitales**
Soporta tres tipos de planes (Básico, Premium, Anual) con cálculo automático de fechas de vencimiento, generación de comprobantes y notificaciones de renovación.

**5. Reserva de clases online**
Los socios pueden visualizar la disponibilidad de cupos por clase, horario y sede, realizar reservas en línea y cancelarlas según las políticas del gimnasio.

**6. Control de asistencia y Kardex**
Registro automatizado de ingresos y salidas de socios, generando un historial consultable tanto por el socio como por el administrador.

**7. Procesamiento de pagos digitales**
Validación de transacciones digitales mediante múltiples métodos (Yape, Plin, Tarjeta), generación de comprobantes digitales y control de estado del pago (OK, Fallido, Reembolsado).

**8. Dashboard administrativo con KPIs**
Panel para administradores que muestra en tiempo real: socios activos, ingresos del mes, membresías por vencer, reservas del día, gráficos de tendencia y actividad reciente.

**9. Reportes y exportación**
Generación de reportes filtrables por sede, fecha y tipo (ingresos, ocupación, morosidad), con exportación a Excel y PDF.

**10. CRUD completo de entidades maestras**
Operaciones de Crear, Leer, Actualizar y Eliminar sobre socios, sedes, planes, clases y horarios desde el panel administrativo.

## Características técnicas

**11. Diseño Mobile First Responsive**
Interfaz construida con Bootstrap 5.3 que se adapta fluidamente a dispositivos móviles, tablets y escritorio, priorizando la experiencia en celulares dado el perfil del usuario socio.

**12. Arquitectura por capas (MVC + DAO + Facade)**
El backend implementa el patrón Modelo-Vista-Controlador combinado con el patrón DAO (Data Access Object) y Facade para garantizar separación de responsabilidades, bajo acoplamiento y mantenibilidad.

**13. Persistencia en MySQL**
Base de datos relacional MySQL Community Edition con 10 tablas normalizadas hasta 3FN, integridad referencial mediante claves foráneas y restricciones de dominio.

**14. Despliegue en Apache Tomcat 10**
El sistema corre sobre el servidor de aplicaciones Apache Tomcat 10 con soporte para Jakarta EE 10, garantizando compatibilidad con tecnologías modernas.

**15. Escalabilidad horizontal**
El diseño permite agregar nuevas sedes sin modificar la arquitectura, soportando el crecimiento de la cadena a nivel nacional.

**16. Validación en frontend y backend**
Doble capa de validación: en el navegador (HTML5 + JavaScript) para retroalimentación inmediata y en el servidor (Servlets/Beans) para garantizar la integridad de datos.

**17. Seguridad de sesión**
Manejo de sesiones HTTP con timeout configurable (30 minutos por defecto), protección contra acceso no autorizado por rol y validación de credenciales en cada petición sensible.

**18. Internacionalización de moneda**
Toda la información monetaria se maneja en soles peruanos (S/) con formato decimal de dos posiciones, alineado al contexto del mercado objetivo.
