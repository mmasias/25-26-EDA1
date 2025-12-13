# ⚛️ pyIris: Justificación de Arquitectura Basada en Nodos Puros

Este documento detalla la elección de estructuras de datos y el análisis del **trabajo** que realiza el sistema pyIris, diseñado bajo la restricción de **no utilizar colecciones nativas** de Java (`Map`, `List`, `Set`, etc.), basándose únicamente en la manipulación de nodos y referencias (punteros).

---

## 2. Justificación de las Estructuras por Operación

La arquitectura se centra en un diseño de direccionamiento manual, donde la velocidad depende de si el sistema puede acceder a la información directamente o si debe recorrer la estructura nodo por nodo.

| Operación | Estructura que la soporta | Tipo de Trabajo (Coste) | Razón Estructural |
| :--- | :--- | :--- | :--- |
| **Búsqueda Global (por ID)** | Lista Doblemente Enlazada (Global) | **Trabajo Proporcional (Lento)** | El sistema tiene que **recorrer** la lista desde el inicio, comparando ID por ID hasta encontrar la coincidencia. El tiempo aumenta si hay más categorías o clientes. |
| **Almacenamiento de Mensaje** | Lista Simple de `MensajeNodo` (en `CategoriaNodo`) | **Trabajo Rápido e Instantáneo** | Una vez se accede a la categoría, solo se requiere un único movimiento de puntero para enlazar el nuevo mensaje al inicio de la lista. |
| **Obtener Suscriptores** | Lista Simple de `EnlaceSuscripcion` (en `CategoriaNodo`) | **Trabajo de Recorrido Lineal** | Se debe **visitar** cada enlace de suscripción para obtener la dirección del cliente y enviarle el resumen. El tiempo depende de cuántos clientes estén suscritos. |
| **Eliminación de Nodos (Bajas)** | Lista Doblemente Enlazada (Global) | **Trabajo Rápido e Instantáneo** | Una vez que el nodo es localizado (lo cual fue un trabajo proporcional), el borrado es instantáneo porque los punteros `nodoAnterior` y `nodoSiguiente` permiten reencadenar la lista con un solo paso. |
| **Verificación de Suscripción** | Lista Simple de `EnlaceSuscripcion` | **Trabajo de Revisión Proporcional** | Se debe **revisar** toda la lista de enlaces de suscripción antes de insertar uno nuevo para asegurar que el cliente no esté ya suscrito. |

---

## 3. Compromisos Identificados y Costes Aceptados

El diseño acepta un compromiso claro entre la flexibilidad de las referencias y el rendimiento, asumiendo los siguientes sacrificios:

| Operación Sacrificada | Impacto Aceptado |
| :--- | :--- |
| **Velocidad de Acceso por ID** | El acceso a cualquier entidad (Categoría o Cliente) es siempre **lento** (Trabajo Proporcional). Se prioriza la implementación pura de nodos sobre la velocidad que ofrecen los índices. |
| **Eficiencia en Desuscribir/Eliminar** | La operación de eliminar un cliente completamente del sistema (Exalumno) requiere un **Trabajo Doble de Barrido**, siendo la tarea más costosa. Se necesita barrer todas las listas de suscripción para limpiar las referencias. |
| **Garantía de Unicidad** | La verificación de que un cliente no esté duplicado requiere **revisar la lista completa** de suscripciones antes de insertar un nuevo enlace. |

---

## 🚨 4. Manejo de Casos Límite

El diseño maneja los casos límite basados en la condición del puntero **nulo** (la ausencia de una dirección válida).

| Caso Límite | Manejo del Diseño Propuesto (Punteros) |
| :--- | :--- |
| **Asignaturas sin alumnos suscritos** | El puntero **`inicioSuscripciones`** del `CategoriaNodo` será **nulo**. El sistema detecta la ausencia de dirección y omite el proceso de envío. |
| **Alumnos sin asignatura inscrita** | El puntero **`inicioSuscripciones`** del `ClienteNodo` será **nulo**. El nodo existe en el registro, pero no tiene enlaces salientes. |
| **Exalumnos** | El proceso implica: 1. Localizar y eliminar el `ClienteNodo` del registro global. 2. Recorrer las listas de `EnlaceSuscripcion` en las categorías suscritas para eliminar las referencias. |
| **Categoría no registrada** | La operación de Búsqueda Global (Trabajo Proporcional) en el `RegistroIris` retornará **nulo**. El proceso de envío se detiene al detectar una fuente de mensaje inválida. |
| **Mensajes no enviados** | Los **`MensajeNodo`** solo se consideran activos si están enlazados a la lista (`inicioMensajes`) de una `CategoriaNodo`. Los borradores (instancias no enlazadas) son ignorados. |