# pyIris: Justificación de Arquitectura Basada en Nodos Puros

## Justificación de las Estructuras por Operación

La arquitectura se centra en un diseño de direccionamiento manual, donde la funcionalidad se obtiene enlazando nodos para crear estructuras dinámicas.

| Operación | Estructura que la soporta | Razón Estructural |
| :--- | :--- | :--- |
| **Búsqueda Global (por ID)** | Lista Doblemente Enlazada (Global) | Para acceder a cualquier entidad, el sistema tiene que **recorrer** la lista desde el puntero `inicio` comparando el ID en cada nodo. |
| **Almacenamiento de Mensaje** | Lista Simple de `MensajeNodo` (en `CategoriaNodo`) | La inserción es inmediata una vez se accede a la categoría, ya que solo se manipula el puntero `inicioMensajes`. Esto garantiza que los mensajes se almacenen en orden cronológico. |
| **Obtener Suscriptores** | Lista Simple de `EnlaceSuscripcion` (en `CategoriaNodo`) | El sistema recorre linealmente esta lista, donde cada nodo proporciona la referencia directa (`refCliente`) al suscriptor para efectuar el envío del resumen. |
| **Eliminación de Nodos (Bajas)** | Lista Doblemente Enlazada (Global) | La estructura doblemente enlazada permite que, una vez localizado el nodo, la eliminación sea directa: se reencadenan los nodos vecinos (`nodoAnterior` y `nodoSiguiente`) con un solo paso. |
| **Verificación de Suscripción** | Lista Simple de `EnlaceSuscripcion` | Se debe **revisar** toda la lista de enlaces de suscripción antes de insertar uno nuevo para asegurar que el cliente no esté ya suscrito (garantizando la unicidad sin usar `Set`). |

---

## Compromisos Identificados

El diseño acepta comprometer la velocidad de ciertas operaciones para cumplir con la restricción de usar solo nodos y referencias.

| Operación Sacrificada | Impacto Aceptado |
| :--- | :--- |
| **Velocidad de Acceso por ID** | El acceso a cualquier entidad (Categoría o Cliente) es siempre lento, requiriendo un recorrido nodo por nodo. Se prioriza la manipulación de referencias sobre la velocidad de indexación. |
| **Eficiencia en Desuscribir/Eliminar** | La operación de eliminar un cliente completamente del sistema requiere un **barrido exhaustivo** de todas las listas de suscripción de las categorías para limpiar las referencias (`EnlaceSuscripcion`). |
| **Garantía de Unicidad** | La verificación de que un cliente no esté duplicado requiere **revisar la lista completa** de suscripciones antes de insertar un nuevo enlace, en lugar de una verificación instantánea. |

---

## Manejo de Casos Límite

El diseño maneja los casos límite basados en la condición del puntero **nulo** (la ausencia de una dirección válida o de un nodo).

| Caso Límite | Manejo del Diseño Propuesto (Punteros) |
| :--- | :--- |
| **Asignaturas sin alumnos suscritos** | El puntero **`inicioSuscripciones`** del `CategoriaNodo` será **nulo**. El sistema detecta la ausencia de dirección y omite el proceso de envío. |
| **Alumnos sin asignatura inscrita** | El puntero **`inicioSuscripciones`** del `ClienteNodo` será **nulo**. El nodo existe en el registro, pero no tiene enlaces salientes. |
| **Exalumnos** | El proceso implica: 1. Localizar y eliminar el `ClienteNodo` del registro global. 2. Recorrer las listas de `EnlaceSuscripcion` en las categorías suscritas para eliminar las referencias. |
| **Categoría no registrada** | Solo se considera activo si está enlazado a la lista de mensajes de una Categoría. Los borradores (instancias no enlazadas) son ignorados.