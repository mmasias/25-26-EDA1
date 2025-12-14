# Gestión de Casos Límite

El diseño propuesto maneja las excepciones de la siguiente manera:

## Asignaturas sin alumnos suscritos

- Comportamiento: El sistema encuentra el NodoAsignatura correctamente en el árbol. Al acceder a su listaSuscriptores, esta está vacía (size = 0).

- Resultado: El bucle de envío no realiza ninguna iteración. El proceso termina correctamente sin errores ni envíos fantasmas.

## Alumnos sin asignatura inscrita

- Comportamiento: El objeto Estudiante existe en el sistema, pero no aparece en ninguna lista de ningún NodoAsignatura. Su lista interna misAsignaturas está vacía.

- Resultado: El alumno nunca recibe mensajes, pero el sistema lo mantiene activo esperando futuras suscripciones.

## Exalumnos (Eliminación del sistema)

- Comportamiento: Gracias a la lista interna del estudiante, la eliminación es eficiente. Se lee misAsignaturas del estudiante para saber dónde está. Se va directamente a esos NodoAsignatura en el árbol. Se elimina al estudiante de esas listas específicas. Finalmente, se elimina el objeto Estudiante.

- Ventaja: No es necesario buscar al alumno en todas las asignaturas del campus.

## Mensajes que no provienen de una asignatura (Código inválido)

- Comportamiento: El sistema busca el código en el Árbol BST.

- Resultado: La búsqueda llega a un puntero null (no encuentra el nodo). El sistema captura este evento, registra un error ("Asignatura no encontrada") y descarta el mensaje de la cola para no bloquear el flujo.

## Mensajes no enviados (Fallo en WhatsApp)

- Comportamiento: El objeto Mensaje o una estructura de log auxiliar debe tener un atributo de estado.

- Resultado: Si la iteración sobre la lista de estudiantes falla en un envío específico, se marca ese envío como fallido, pero el bucle continúa con el siguiente estudiante. No se aborta el proceso global por un fallo individual.