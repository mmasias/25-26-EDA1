# Justificación Técnica y Estructuras de Datos

1.  **Gestión de Datos (Repositories):** Se encargan _solo_ de cómo se guardan y recuperan los datos.
2.  **Lógica de Negocio (Engine):** Se encarga de _cuándo_ procesar y _cómo_ orquestar.
3.  **Salida (Services):** Se encarga de _cómo_ enviar el resultado al mundo exterior.

Esta separación permite cambiar la estructura de datos subyacente (ej. cambiar de Memoria a Base de Datos) sin tocar una sola línea de la lógica de envío de emails.

## Estructuras de Datos Seleccionadas

### 1. `InMemoryMessageBuffer`

- **Implementación:** `HashMap<String, LinkedList<Message>>`
  - **Clave (Key):** `String` (ID de la Asignatura).
  - **Valor (Value):** `LinkedList<Message>` (Cola de mensajes).
- **Justificación de Eficiencia:**
  - Elegimos `LinkedList` sobre `ArrayList` porque la operación principal es **Insertar** y **Vaciar**. `LinkedList` ofrece inserción constante $O(1)$ al final (tail) sin necesidad de redimensionamiento de arrays (resizing overhead) que ocurre en `ArrayList` cuando se llena la capacidad.
  - Al vaciar el buffer (`pullAndClear`), simplemente desconectamos la referencia de la lista, lo cual es instantáneo en términos de gestión de memoria.

### 2. `InMemorySubscriptionRepo`

- **Implementación:** `HashMap<String, HashSet<User>>`
  - **Clave (Key):** `String` (ID de la Asignatura).
  - **Valor (Value):** `HashSet<User>` (Grupo de suscriptores).
- **Justificación de Eficiencia:**
  - Utilizamos `HashSet` para almacenar a los usuarios. Esto nos da dos ventajas críticas:
    1.  **Unicidad Garantizada:** Matemáticamente, un conjunto no puede tener elementos repetidos. Esto nos ahorra código de validación (`if !list.contains(user)`), reduciendo la complejidad ciclomática.
    2.  **Búsqueda/Borrado Rápido:** Si un alumno se da de baja, encontrarlo en un `Set` es $O(1)$ versus $O(n)$ en una lista.

## Complejidad Algorítmica Global

- **Recepción de Mensaje:** $O(1)$ - Extremadamente eficiente.
- **Procesamiento de Resumen:** $O(m + u)$, donde $m$ es el número de mensajes a resumir y $u$ el número de usuarios a notificar. Es lineal y lo mínimo teóricamente posible (hay que leer los mensajes y hay que enviar los emails).
