# Casos límite y manejo

## Asignaturas sin alumnos suscritos

- Publicar mensaje: no genera envíos, sistema consistente.

## Alumnos sin asignatura inscrita

- El alumno existe sin Suscripcion, no recibe mensajes, estado válido.

## Exalumnos

- `Alumno.estado = INACTIVO`, suscripciones canceladas, historial preservado.

## Mensajes que no provienen de una asignatura

- Creación bloqueada a nivel modelo, mantiene integridad de dominio.

## Mensajes no enviados

- `Mensaje.estadoEnvio = FALLIDO`, se pueden reintentar vía `ServicioEnvio`, garantiza trazabilidad y recuperación.