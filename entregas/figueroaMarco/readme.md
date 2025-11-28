# Reto 005 - Propuesta de Solución: Cocina con Prioridad (BST)

## Descripción del Problema
El objetivo es simular el funcionamiento de una cocina de restaurante que optimiza el número de pedidos completados priorizando aquellos con menor tiempo de preparación (**Shortest Job First - SJF**).

A diferencia de una cola FIFO (First In, First Out), aquí el orden de llegada no determina el orden de atención. El cocinero siempre elige el pedido pendiente más rápido de preparar.

## Aproximación: Árbol Binario de Búsqueda (BST)

Para gestionar los pedidos pendientes eficientemente, utilizaremos un **Árbol Binario de Búsqueda (BST)**.

### ¿Por qué un BST?
En un BST, los nodos se ordenan tal que para cualquier nodo, los valores en su subárbol izquierdo son menores y los del derecho son mayores.
- **Criterio de Ordenación**: Tiempo de preparación del pedido.
- **Eficiencia**:
    - **Insertar pedido**: $O(\log n)$ en promedio. Permite añadir nuevos pedidos manteniendo el orden.
    - **Encontrar mínimo**: $O(\log n)$ en promedio. El pedido con menor tiempo siempre estará en el nodo más a la izquierda del árbol.
    - **Eliminar mínimo**: $O(\log n)$ en promedio. Necesario para extraer el pedido que el cocinero va a preparar.

### Estructuras de Datos

#### 1. Clase `Pedido`
Representa una orden individual.
- **Atributos**:
    - `ID`: Identificador único.
    - `Tipo`: (Bebida, Café, Colacao, Bocadillo, Ensalada).
    - `TiempoTotal`: Tiempo asignado aleatoriamente según el tipo.
    - `TiempoRestante`: Tiempo que falta para completar.
- **Comparación**: Se compara principalmente por `TiempoTotal`.

#### 2. Clase `BST` (Cola de Prioridad)
Estructura para almacenar los pedidos pendientes.
- **Operaciones**:
    - `insertar(Pedido)`: Coloca el pedido en la posición correcta según su tiempo.
        - *Nota*: Si hay tiempos iguales, se pueden colocar a la derecha para mantener una consistencia, actuando como FIFO para pedidos de igual duración.
    - `extraerMinimo()`: Retorna y elimina el pedido más rápido (nodo más a la izquierda).
    - `estaVacio()`: Indica si hay pedidos esperando.

#### 3. Simulador (`Cocina`)
Controla el flujo del tiempo minuto a minuto.

### Algoritmo de Simulación

El ciclo de vida de la simulación (por ejemplo, 120 minutos) sigue estos pasos en cada minuto:

1.  **Llegada de Pedidos**:
    - Se evalúa la probabilidad de llegada (40%).
    - Si llega un pedido:
        - Se determina el tipo y tiempo de preparación aleatoriamente.
        - Se inserta en el **BST**.

2.  **Asignación de Trabajo**:
    - Si el cocinero está **libre** y el BST **no está vacío**:
        - El cocinero extrae el pedido mínimo del BST (`extraerMinimo()`).
        - Comienza a procesarlo.

3.  **Procesamiento**:
    - Si hay un pedido en curso:
        - Se reduce su `TiempoRestante` en 1 minuto.
        - Si `TiempoRestante` llega a 0:
            - El pedido se marca como **completado**.
            - El cocinero queda libre (y puede tomar otro inmediatamente en el mismo instante si la lógica lo permite, o esperar al siguiente ciclo).

### Ejemplo de Traza

```text
Minuto 10:
- Llega pedido: Café (3 min). BST: [Café(3)]
- Cocinero libre -> Toma Café(3). BST: []

Minuto 11:
- No llega pedido.
- Cocinero procesando Café. Restante: 2 min.

Minuto 12:
- Llega pedido: Ensalada (8 min). BST: [Ensalada(8)]
- Cocinero procesando Café. Restante: 1 min.

Minuto 13:
- Llega pedido: Bebida (1 min). BST: [Bebida(1), Ensalada(8)] (Bebida a la izq de Ensalada)
- Cocinero termina Café.
- Cocinero busca siguiente -> Extrae Bebida(1) (es menor que Ensalada).
- Cocinero procesando Bebida. Restante: 1 min.
```

Esta estructura garantiza que siempre se atienda lo más rápido posible, maximizando el throughput (pedidos/hora) de la cocina.
