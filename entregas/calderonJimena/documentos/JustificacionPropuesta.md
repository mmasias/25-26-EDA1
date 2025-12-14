# Justificación de la Propuesta: Aplicación **Iris**

La aplicación **Iris** responde a la necesidad de notificar de forma oportuna y personalizada a los estudiantes sobre actualizaciones relacionadas con las materias en las que están suscritos, utilizando **WhatsApp**.

## 1. Separación clara de responsabilidades

El diagrama de clases muestra una arquitectura orientada a objetos bien estructurada, en la que cada componente tiene un rol específico:

- `Usuario` y `Materia` definen las entidades principales del sistema.
- `Suscripcion` actúa como una relación intermedia que permite flexibilidad (un usuario puede suscribirse a varias materias y viceversa) y control del estado (activa/inactiva).
- `NotificacionEntrante` representa los correos recibidos del panel académico, mientras que `NotificacionProcesada` encapsula la información relevante extraída de forma estandarizada.
- `ProcesadorNotificaciones` se encarga de interpretar y limpiar los correos entrantes, aislando la lógica de análisis del resto del sistema.
- `ServicioWhatsApp` abstrae la comunicación externa, facilitando futuras actualizaciones o cambios de canal sin afectar el núcleo del sistema.
- **Iris** actúa como el componente central que orquesta todo el flujo: recibe notificaciones, delega su procesamiento y coordina el envío de mensajes, manteniendo un bajo acoplamiento con los demás módulos.

## 2. Flujo eficiente y escalable

El diagrama de secuencia ilustra el ciclo de vida de una notificación dentro de **Iris**:

- Las notificaciones entrantes se **encolan**, lo que permite manejar picos de carga y garantiza que no se pierdan mensajes.
- Solo se procesan las notificaciones para las que **existe al menos un suscriptor activo**, evitando trabajo innecesario.
- El mensaje final se **personaliza y resume** antes de enviarse.
- El uso de colas y procesamiento secuencial facilita la **escalabilidad** y la integración con sistemas externos.
