# Razonamiento de la Refactorización (RCCCF)

## 1. Objetivo del Cambio
El objetivo principal de esta refactorización ha sido transformar una implementación procedural/híbrida que dependía fuertemente de colecciones de la librería estándar de Java (`PriorityQueue`) hacia una arquitectura **orientada a objetos pura** y **minimalista**, basada en estructuras de datos propias, tal como se especificaba en el diagrama UML proporcionado.

## 2. Cambios Estructurales Realizados

### De `PriorityQueue` a `ArbolPedidos`
La modificación más significativa ha sido la sustitución de la clase `java.util.PriorityQueue` por una estructura de datos personalizada llamada `ArbolPedidos`.

*   **Situación Anterior**: Se delegaba la ordenación de los pedidos a la implementación interna de Java (Heap Binario).
*   **Nueva Implementación**: Se ha creado un **Árbol Binario de Búsqueda (BST)**.
    *   **Clase `Nodo`**: Encapsula el dato (`Pedido`) y las referencias a sus hijos (`izquierdo`, `derecho`).
    *   **Clase `ArbolPedidos`**: Gestiona la lógica de inserción y extracción.
    *   **Lógica de Prioridad**: Al insertar, los pedidos con menor tiempo de preparación (mayor prioridad según la lógica SJF - Shortest Job First) se colocan a la izquierda. El método `eliminarMinimo()` recorre el árbol siempre hacia la izquierda para encontrar y extraer el pedido prioritario.

### Renombrado y Responsabilidades
Se han renombrado las clases para ajustarse a un dominio más genérico y al diagrama:
*   `Cocina` $\rightarrow$ **`Restaurante`**: Actúa como la clase gestora que orquesta el flujo de pedidos y el tiempo.
*   `Main` $\rightarrow$ **`SimulacionRCCCF`**: Contiene el punto de entrada y el bucle principal de la simulación.

## 3. Análisis de Complejidad

Al cambiar de un Heap (PriorityQueue) a un BST (`ArbolPedidos`), las implicaciones teóricas son:

*   **Inserción**:
    *   *PriorityQueue*: $O(\log n)$
    *   *ArbolPedidos (BST)*: $O(\log n)$ en caso promedio, pero podría degradarse a $O(n)$ si los pedidos llegaran ordenados (lo cual generaría una lista enlazada). Dado que los pedidos se generan aleatoriamente, el caso promedio es el esperado.
*   **Extracción del Mínimo**:
    *   *PriorityQueue*: $O(\log n)$
    *   *ArbolPedidos (BST)*: $O(h)$ donde $h$ es la altura. En un árbol balanceado es $O(\log n)$, pero en el peor caso (desbalanceado) es $O(n)$.

## 4. Conclusión
Esta refactorización cumple con el requisito de "minimalismo usando clases" al eliminar dependencias de estructuras complejas de Java y demostrar el funcionamiento interno de la gestión de datos mediante nodos y referencias manuales, alineándose estrictamente con el diseño UML solicitado.
