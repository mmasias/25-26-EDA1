# Simulación de Restaurante (Refactorización RCCCF)

Este proyecto implementa una simulación de la gestión de pedidos en un restaurante, refactorizada para seguir un diseño orientado a objetos minimalista y purista, evitando el uso de colecciones complejas de Java en favor de estructuras de datos propias.

## Descripción del Reto

El objetivo principal ha sido refactorizar una implementación anterior basada en `PriorityQueue` para utilizar un **Árbol Binario de Búsqueda (BST)** personalizado para la gestión de la prioridad de los pedidos (SJF - Shortest Job First).

## Estructura del Proyecto

El código fuente se encuentra en la carpeta `src` y consta de las siguientes clases:

*   **`SimulacionRCCCF.java`**: Clase principal que contiene el `main`. Ejecuta el bucle de simulación, genera pedidos aleatorios y muestra el estado del sistema paso a paso.
*   **`Restaurante.java`**: Representa el sistema de cocina. Gestiona la cola de pedidos y el procesamiento del pedido actual.
*   **`ArbolPedidos.java`**: Estructura de datos personalizada (BST) que sustituye a la cola de prioridad. Almacena los pedidos ordenados por tiempo de preparación.
*   **`Nodo.java`**: Clase auxiliar que representa un nodo dentro del árbol de pedidos.
*   **`Pedido.java`**: Modelo de datos que representa un pedido con su tipo, tiempo de preparación y tiempo de llegada.

## Diagrama de Clases

La arquitectura sigue el siguiente esquema (ver `documentosUML/uml_rcccf.puml` para más detalles):

`SimulacionRCCCF` -> `Restaurante` -> `ArbolPedidos` -> `Nodo` -> `Pedido`

## Cómo Ejecutar

1.  Navegar a la carpeta `src`:
    ```bash
    cd entregas/puenteSergio/src
    ```
2.  Compilar el código:
    ```bash
    javac -d . *.java
    ```
3.  Ejecutar la simulación:
    ```bash
    java entregas.puenteSergio.src.SimulacionRCCCF
    ```

## Detalles de Implementación

*   **Prioridad**: Los pedidos con menor tiempo de preparación se insertan a la izquierda en el árbol.
*   **Extracción**: El método `eliminarMinimo()` del árbol busca y elimina el nodo más a la izquierda, garantizando que siempre se atienda el pedido más rápido disponible (SJF).
