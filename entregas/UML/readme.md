# ⚛️ pyIris: Justificación de Arquitectura Basada en Nodos Puros

Este documento detalla la elección de estructuras de datos y el análisis de costes para el proyecto pyIris, diseñado bajo la restricción de **no utilizar colecciones nativas** de Java (`Map`, `List`, `Set`, etc.), basándose únicamente en la manipulación de nodos y referencias.

---

## 2. Justificación de las Estructuras por Operación

La arquitectura utiliza la implementación manual de Listas Enlazadas (Simples y Dobles) para garantizar la flexibilidad dinámica y el direccionamiento por referencias.

| Operación | Estructura que la Soporta | Coste Esperado | Razón Estructural |
| :--- | :--- | :--- | :--- |
| **Búsqueda Global (por ID)** | Lista Doblemente Enlazada (Global) | $O(N)$ | Para acceder a cualquier entidad, se debe iniciar el recorrido desde el puntero **`inicio`** en el `RegistroIris`. El coste es lineal, sacrificando el $O(1)$ de los `Mapas`. |
| **Almacenamiento de Mensaje** | Lista Simple de `MensajeNodo` (en `CategoriaNodo`) | $O(1)$ | La inserción del nuevo nodo al inicio de la lista es inmediata, garantizando el orden cronológico de los mensajes de la categoría. |
| **Obtener Suscriptores** | Lista Simple de `EnlaceSuscripcion` (en `CategoriaNodo`) | $O(N_s)$ | El recorrido lineal sobre esta lista proporciona las referencias directas (`refCliente`) a los suscriptores, siendo eficiente respecto al número de suscritos. |
| **Eliminación de Nodos (Bajas)** | Lista Doblemente Enlazada (Global) | $O(1)$ | Una vez que se localiza el nodo (coste $O(N)$), su eliminación del registro es inmediata gracias a que los punteros `nodoAnterior` y `nodoSiguiente` permiten reencadenar la lista en tiempo constante. |
| **Verificación de Suscripción** | Lista Simple de `EnlaceSuscripcion` | $O(N_s)$ | Al no usar `Set`, se debe recorrer esta lista linealmente antes de insertar un nuevo enlace para verificar que el cliente no esté duplicado. |

---

## 3. Compromisos Identificados y Costes Aceptados

El diseño acepta un compromiso claro entre la flexibilidad de las referencias y el rendimiento, asumiendo los siguientes costes:

| Operación Sacrificada | Coste Aceptado / Impacto |
| :--- | :--- |
| **Velocidad de Acceso por ID** | El acceso a cualquier entidad (Categoría o Cliente) es siempre **$O(N)$**. Se acepta el alto coste de búsqueda lineal al priorizar la implementación pura de nodos sobre la indexación. |
| **Eficiencia en Desuscribir/Eliminar** | La operación de eliminar un cliente completamente del sistema (Exalumno) es la más costosa: **$O(N_s \times N_c)$**. Requiere un barrido exhaustivo de las listas de suscripción de todas las categorías para limpiar manualmente cada `EnlaceSuscripcion` que lo referencie. |
| **Garantía de Unicidad** | Se pierde la garantía automática $O(1)$ de unicidad proporcionada por los `Set`. Esta función se implementa manualmente con un costo de verificación de **$O(N_s)$** en cada intento de suscripción. |

---

## 🚨 4. Manejo de Casos Límite

El diseño maneja los casos límite basados en la condición del puntero **`null`**, que indica la ausencia de un nodo o el final de una lista.

| Caso Límite | Manejo del Diseño Propuesto (Punteros) |
| :--- | :--- |
| **Asignaturas sin alumnos suscritos** | El puntero **`inicioSuscripciones`** del `CategoriaNodo` será **`null`**. El sistema detecta la referencia nula y omite la iteración y el proceso de envío. |
| **Alumnos sin asignatura inscrita** | El puntero **`inicioSuscripciones`** del `ClienteNodo` será **`null`**. El nodo existe en el registro global, pero no tiene enlaces salientes de suscripción activos. |
| **Exalumnos** | El proceso implica: 1. Localizar y eliminar el `ClienteNodo` del registro global ($O(N)$). 2. Recorrer las listas de `EnlaceSuscripcion` en las categorías suscritas para eliminar las referencias, manteniendo la integridad referencial. |
| **Categoría no registrada** | La operación de **Búsqueda Global** en el `RegistroIris` recorre toda la Lista Doble. Si no encuentra una coincidencia, retorna **`null`**. El proceso de envío se detiene al detectar una fuente de mensaje inválida. |
| **Mensajes no enviados** | Los **`MensajeNodo`** solo se consideran activos si están enlazados a la lista (`inicioMensajes`) de una `CategoriaNodo`. Los objetos instanciados pero no enlazados son ignorados por el sistema de direccionamiento. |