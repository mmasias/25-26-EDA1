# Propuesta de mejora: Resumen por alumno y por ventana temporal

En el diseño original de Iris, los mensajes se envían de forma inmediata y por asignatura. La mejora que propongo no cambia cómo se reciben ni cómo se clasifican los mensajes, sino **cómo se agrupan y cómo se consultan después**.

En lugar de mandar un WhatsApp cada vez que llega un mensaje, el sistema **acumula los mensajes por alumno** y los envía todos juntos al final de una **ventana temporal** (por ejemplo, al final del día). Además, estos mensajes resumidos **se guardan en un historial**, de forma que el alumno pueda consultarlos cuando quiera y no dependa únicamente del WhatsApp.

De esta manera, el alumno recibe menos mensajes, los recibe más claros y no pierde la información aunque borre una notificación o un chat.

## Estructuras de datos utilizadas

Se utiliza una **cola** para recibir los mensajes en orden de llegada (FIFO), un **árbol de búsqueda binaria** para localizar rápidamente la asignatura asociada a cada mensaje, y **listas** para gestionar los suscriptores, los buzones de alumnos y el almacenamiento del historial.

## Funcionamiento del sistema

El funcionamiento completo del sistema es el siguiente. Primero, el mensaje llega a Iris y se inserta en la cola de mensajes, respetando el orden de llegada. A continuación, el sistema extrae el mensaje de la cola y busca su asignatura en el árbol de asignaturas usando el código correspondiente.

Una vez identificada la asignatura, el mensaje se distribuye a los alumnos suscritos. Para ello, cada alumno dispone de un **buzón**, que es simplemente una lista donde se van acumulando los mensajes que le corresponden durante la ventana temporal. Si el buzón del alumno ya existe, se añade el mensaje; si no existe, se crea uno nuevo y se incorpora a la lista de buzones.

Cuando finaliza la ventana temporal, el sistema recorre la lista de buzones, genera un **resumen por alumno** con todos los mensajes acumulados y envía un único WhatsApp. Ese resumen se guarda además en el **historial de mensajes**, y el buzón del alumno se vacía para comenzar un nuevo periodo.

## Ventajas y compromisos

Este diseño reduce notablemente el número de mensajes enviados, mejora la claridad de la información recibida y se adapta mejor al uso real de WhatsApp. Además, al mantener un historial, el alumno puede consultar mensajes antiguos sin depender del canal de notificación.

Como contrapartida, el sistema utiliza algo más de memoria, ya que los mensajes se almacenan temporalmente en los buzones y de forma permanente en el historial. También se pierde algo de inmediatez, ya que los mensajes no se envían uno a uno, aunque a cambio la información llega mejor organizada. Estos compromisos se consideran aceptables para mejorar la experiencia del usuario.

## Gestión de casos límite

Si una asignatura no tiene alumnos suscritos, no se añade ningún mensaje a ningún buzón ni al historial. Los alumnos sin asignaturas inscritas no tienen buzón activo ni mensajes almacenados. Cuando un alumno se da de baja, se elimina de las listas de suscriptores y se elimina su buzón; el historial puede mantenerse o eliminarse según la política del sistema.

En el caso de mensajes sin asignatura válida, el mensaje se descarta o se envía a una categoría genérica. Si el envío por WhatsApp falla, el resumen permanece guardado en el historial, lo que permite reintentar el envío más adelante sin pérdida de información.
