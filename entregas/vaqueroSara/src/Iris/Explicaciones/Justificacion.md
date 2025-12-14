# Justificación de estructuras y operaciones

## Publicar mensaje en una asignatura

### Estructuras que se han usado: `Asignatura`, `Mensaje`

> El mensaje pertenece a la asignatura, permite filtrar y validar contexto, y mantiene coherencia académica.

## Suscribir alumno a asignatura

### Estructuras usadas: `Alumno`, `Suscripcion`

> Por qué: La clase intermedia permite estado activo/cancelado, historial de exalumnos y evita duplicación.

## Envío de mensajes

### Estructuras usadas: `Mensaje`, `ServicioEnvio`

> Por qué: Separación de envío evita acoplamiento con canales externos y permite reintentos y manejo de errores.