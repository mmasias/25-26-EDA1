# pylris 

Este UML representa un sistema tipo Iris que recibe correos, extrae la información relevante y envía un mensaje estructurado a los usuarios suscritos a una asignatura.

## Clases principales

Usuario  
Almacena los datos del usuario y la lista de asignaturas a las que está suscrito.

Asignatura  
Define la categoría del mensaje (código y nombre).

MensajeIris  
Mensaje limpio y resumido generado a partir del correo original. Solo guarda información esencial y puede incluir una fecha límite.

GestorIris  
Componente central que gestiona el sistema: procesa correos, filtra usuarios por suscripción, envía mensajes y los guarda.

## Estructuras de datos

- Cola: para procesar los correos en orden de llegada .
- Listas: para usuarios, asignaturas y suscripciones.

## Relaciones

- Un usuario puede estar suscrito a varias asignaturas.
- El GestorIris gestiona usuarios y genera mensajes.
- Cada mensaje está asociado a una asignatura.
