# Iris: Propuesta de Diseño

Este documento describe la propuesta de diseño para **Iris**, un sistema redirector de mensajes estructurados. La solución se centra en la eficiencia del enrutamiento de mensajes y la gestión de suscripciones utilizando estructuras de datos estándar.

## 1. Estructuras de Datos Propuestas

Para modelar las relaciones entre Asignaturas (Subject), Alumnos (Student) y Mensajes (Message), utilizaremos las siguientes estructuras:

### 1.1. Registro Principal: `Map<String, Asignatura>`
*   **Descripción:** Un mapa hash (`HashMap`) que almacena todas las asignaturas registradas.
*   **Clave:** ID de la asignatura (String).
*   **Valor:** Objeto `Asignatura`.
*   **Justificación:** Permite el acceso O(1) a cualquier asignatura para publicar mensajes o gestionar suscripciones. Es fundamental para el rendimiento del redirector.

### 1.2. Suscriptores por Asignatura: `Set<Alumno>`
*   **Ubicación:** Dentro de la clase `Asignatura`.
*   **Descripción:** Un conjunto (`HashSet`) que contiene a los alumnos suscritos a esa asignatura en particular.
*   **Justificación:**
    *   Evita duplicados automáticamente (un alumno no puede estar suscrito dos veces).
    *   Permite añadir (`subscribe`) y eliminar (`unsubscribe`) alumnos en tiempo O(1).
    *   Iterar sobre el conjunto para enviar resúmenes es eficiente.

### 1.3. Cola de Mensajes: `Queue<Mensaje>`
*   **Ubicación:** Dentro de la clase `Asignatura`.
*   **Descripción:** Una cola (`LinkedList` o `ArrayDeque`) que almacena temporalmente los mensajes enviados a esa asignatura hasta que se genera el resumen.
*   **Justificación:**
    *   Mantiene el orden cronológico de llegada de los mensajes (FIFO).
    *   Permite inserción y extracción en O(1).
    *   Facilita la operación de "resumir" procesando los mensajes en orden.

## 2. Diagramas UML

### Diagrama de Clases

El diagrama de clases se encuentra disponible en el archivo adjunto: [Iris_Diagrama.puml](Iris_Diagrama.puml).

## 3. Justificación y Análisis

### Operaciones Clave

| Operación | Estructura de Soporte | Complejidad Temporal | Justificación |
| :--- | :--- | :--- | :--- |
| **Suscribir Alumno** | `Map` (buscar asig.) + `Set` (añadir) | O(1) promedio | Acceso directo a la asignatura y gestión eficiente de unicidad en el Set. |
| **Recibir Mensaje** | `Map` (buscar asig.) + `Queue` (encolar) | O(1) promedio | Inserción rápida al final de la cola sin bloquear el sistema. |
| **Generar Resumen** | `Queue` (iterar) + `Set` (iterar) | O(M + N) | Donde M es # mensajes en cola y N es # suscriptores. Es necesario recorrer ambos para construir el resumen y enviarlo. |

### Compromisos (Trade-offs)

1.  **Latencia vs. Eficiencia (Batching):**
    *   *Sacrificio:* Se sacrifica la entrega inmediata de mensajes (tiempo real).
    *   *Coste Aceptado:* Los mensajes se retienen en memoria (`Queue`) hasta el envío del resumen. Existe riesgo de pérdida de datos si el sistema cae antes de enviar el resumen (a menos que se use persistencia, que no se detalla en este diseño en memoria).
    *   *Beneficio:* Reducción drástica del "ruido" para el alumno y optimización de conexiones de red al enviar un solo paquete grande en lugar de muchos pequeños.

2.  **Memoria:**
    *   Almacenar mensajes completos en memoria puede ser costoso si las colas crecen mucho. Se asume que los resúmenes se generan con frecuencia suficiente o que los mensajes no son excesivamente grandes ("payload" de texto).

## 4. Gestión de Casos Límite

| Caso Límite | Manejo Propuesto |
| :--- | :--- |
| **Asignaturas sin alumnos suscritos** | Los mensajes se siguen acumulando en la `Queue` de la asignatura. **Riesgo:** Fuga de memoria. **Mitigación:** Implementar un límite de tamaño (capacidad) para la cola o una política de expiración (TTL) para mensajes viejos si nadie los consume. |
| **Alumnos sin asignatura inscrita** | El objeto `Alumno` existe en el sistema pero no está referenciado en ningún `Set` de `Asignatura`. No recibe nada. Es un estado válido y no consume recursos de procesamiento en los envíos. |
| **Exalumnos** | Cuando un alumno deja la institución, se debe invocar `desuscribirAlumno` para todas sus asignaturas. Si no se hace, el sistema intentará enviarle correos (fallando o rebotando). El diseño soporta la baja eficiente O(1) por asignatura. |
| **Mensajes sin asignatura válida** | Si llega un mensaje con un `idAsignatura` que no existe en el `Map`, el `IrisManager` debe rechazarlo inmediatamente (lanzar excepción o log de error). No se crea una asignatura "fantasma" automáticamente para evitar ensuciar el registro. |
| **Mensajes no enviados** | Si falla el envío del resumen a un alumno específico (p.ej. fallo de red): <br>1. **Opción Simple (MVP):** Log del error y continuar con el siguiente alumno. El alumno pierde ese resumen. <br>2. **Opción Robusta:** Implementar una cola de reintentos ("Dead Letter Queue") para fallos temporales, reintentando más tarde. |
