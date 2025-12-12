1. Justificación de Estructuras

Cola: Es la cinta transportadora del sistema. Asegura que el mensaje que entró primero (el correo más viejo) sea el primero en salir y ser procesado, manteniendo el orden estricto (FIFO).

Árbol de Decisión Binaria : En este caso, nos sirve como un filtro ultrarrápido. Cuando un correo nuevo llega, miramos el índice: nos preguntamos si estamos suscritos a esa asignatura. Si la respuesta es "Sí", me llega al chat; si no, se descarta.

Lista: Puede hacer la función de registro de las notificaciones que llegan al chat y el mensaje, manteniendo un historial cronológico simple.



| Operación          | Estructura              | Justificación
|----------------------|------------------------------------|----------------------------------------|
|Orden      | Cola (Queue)                       | Esencial para asegurar el estricto orden de procesamiento FIFO , garantizando que los mensajes más antiguos se atiendan primero. |
| Filtrado Rápido      | Árbol de Decisión Binaria (Map)    | Proporciona un filtro rápido para verificar la suscripción, descartando correos irrelevantes de forma eficiente. |
| Registro de Historial| Lista                               | Estructura simple y eficiente para el registro de las notificaciones
| Claridad del Mensaje | ProcesadorMensajes (Lógica)         | Realiza la extracción, limpieza y clasificación de texto, transformando el correo confuso en una notificación clara y útil, aunque esto implique una ligera latencia en el procesamiento. |

2. Compromiso y Coste Aceptado

- Orden de procesamiento:
Se usa una cola FIFO para garantizar que los correos se procesen en el orden en que llegan.
Coste aceptado: menor flexibilidad, pero se obtiene un flujo seguro y mas funcional.

- Filtrado rápido:
Se emplea un árbol para verificar suscripciones con alta velocidad.
Coste aceptado: mayor complejidad interna, a cambio de descartar correos irrelevantes de forma inmediata.

- Claridad del mensaje:
El sistema edita el correo en una notificación comprensible.
Coste aceptado: se añade procesamiento adicional, pero se mejora la utilidad del mensaje final.




| Caso Límite                                      | Mecanismo de Manejo                                                                                                                                               |
|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Asignaturas sin alumnos suscritos                | El sistema consulta el filtro de suscripciones; la verificación devuelve False. El mensaje se descarta en la fase de filtrado.                              |
| Alumnos sin asignatura inscrita / Exalumnos      | El filtro de suscripciones está vacío o inactivo, por lo que cualquier consulta de materia resulta en False, causando el descarte de todos los correos.     |
| Mensajes que no provienen de una asignatura  | El sistema no logra extraer un código de materia válido. La consulta al filtro falla o devuelve False, por lo que el mensaje es rechazado inmediatamente. |
| Mensajes no enviados              | El envío falla en la interfaz de chat. Como el registro solo almacena notificaciones exitosas, el mensaje no se añade al historial.                        |
