# Gestión de Casos Límite

## A. Asignaturas sin alumnos

- **Capa responsable:** `IrisEngine`.
- **Lógica:**
  1. El Engine solicita `buffer.pullAndClear(category)`. Obtiene mensajes.
  2. El Engine solicita `repo.getSubscribers(category)`.
  3. **Condición de Borde:** Si el Set devuelto está vacío (`isEmpty()`), el Engine detiene el flujo inmediatamente.
- **Resultado:** No se llama al servicio de IA para resumir ni al servicio de Email. Los mensajes se descartan silenciosamente tras haber sido extraídos.

## B. Alumnos sin asignaturas

- **Capa responsable:** `ISubscriptionRepository`.
- **Lógica:** El alumno existe como objeto `User`, pero su referencia no está almacenada en ningún `HashSet` dentro del Mapa de asignaturas.
- **Resultado:** Estructuralmente imposible enviarles nada. No requieren validaciones especiales ("null checks") en el momento del envío porque simplemente no aparecen en las colecciones iterables.

## C. Exalumnos

- **Capa responsable:** `InMemorySubscriptionRepo`.
- **Caso:** Un alumno intenta desuscribirse de una asignatura en la que no está.
- **Manejo:** La operación `HashSet.remove(user)` devuelve `false` si el elemento no existía, pero no lanza excepción.
- **Resultado:** Operación idempotente y segura. El sistema es robusto ante peticiones redundantes.

## D. Mensajes sin categoria

- **Capa responsable:** `IrisEngine`.
- **Lógica:** Al recibir `receive(category, msg)`, si `category` es nula o vacía.
- **Acción:** Se lanza una `IllegalArgumentException` inmediatamente. Siguiendo la técnica "Fail Fast", no permitimos que datos corruptos entren a las estructuras de datos, protegiendo la integridad del buffer.
