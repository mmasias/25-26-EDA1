## Lógica de envío a WhatsApp

El sistema no solo almacena y organiza los correos electrónicos, sino que también permite **enviar los mensajes procesados a los usuarios por WhatsApp**.  

El flujo general es el siguiente:

1. Cada correo recibido se almacena en la entidad **MENSAJE_CORREO**.
2. El sistema genera un registro en **MENSAJE_PROCESADO**, asociando:
   - el correo original,
   - el profesor que lo envió,
   - la asignatura correspondiente.
3. El mensaje final que se enviará por WhatsApp se construye combinando estos datos, de manera que cada usuario recibe únicamente la información relevante para las asignaturas a las que está **suscrito**.
4. La suscripción se gestiona mediante la entidad **SUSCRIPCION**, que relaciona usuarios y asignaturas. Esto garantiza que:
   - cada usuario solo recibe mensajes de sus asignaturas,
   - se evita saturar con información irrelevante.
5. El envío de los mensajes puede ser automatizado mediante herramientas de integración (como **n8n**) que leen los registros de **MENSAJE_PROCESADO** y los envían a WhatsApp.

### Ventajas de este enfoque

- **Personalización**: cada usuario recibe solo la información que le interesa.
- **Automatización**: no requiere intervención manual para reenviar los correos.
- **Claridad**: los mensajes se construyen de forma estandarizada, indicando profesor y asignatura.
- **Escalabilidad**: se pueden añadir nuevas asignaturas o usuarios sin modificar la lógica del sistema.
