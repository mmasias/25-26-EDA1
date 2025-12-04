# Razonamiento del cambio a Árbol Binario

## 1. Punto de partida: diseño completo con varias estructuras

Mi primera solución del RCCCF estaba basada en un diseño más grande y genérico:

- Una interfaz `IEstructuraPedidos`
- Dos implementaciones distintas:
  - `ColaPrioridadPedidos` (basada en `PriorityQueue`)
  - `ListaLinealPedidos` (basada en una lista lineal con búsquedas)
- Una clase `Cocina` que usaba esa interfaz
- Una clase `Estadisticas` para almacenar todos los datos de la simulación
- Una clase `Simulation` que coordinaba todo el sistema

La idea de este diseño era poder **comparar distintas estructuras de datos** (lista lineal vs cola de prioridad), midiendo el número de comparaciones y los tiempos de espera.

Aunque la solución funcionaba y cumplía el enunciado, tenía dos problemas:

1. **Mucha complejidad para un reto concreto**: muchas clases, interfaces y capas de abstracción para una funcionalidad relativamente simple.
2. **El árbol binario no era explícito**: aunque `PriorityQueue` internamente usa un heap (estructura relacionada con árboles), el árbol no se veía ni se razonaba de forma clara en el código, que es algo que el profesor quería trabajar.

---

## 2. Objetivo del refactor: simplificar y destacar el árbol

A partir de ahí decidí hacer un refactor con dos objetivos claros:

1. **Simplificar el diseño**  
   Reducir el número de clases y quedarme solo con lo esencial para el reto:
   - un modelo de pedido,
   - una estructura que guarde pedidos,
   - y una simulación que conecte todo.

2. **Usar explícitamente un Árbol Binario de Búsqueda (BST)**  
   En lugar de delegar el orden en una `PriorityQueue` o buscar el mínimo a mano en una lista, quería que el orden y la extracción del mínimo estuvieran **claramente implementados con un árbol binario**.

---

## 3. Nueva solución: 3 clases y un árbol binario

Tras el refactor, la solución se ha reducido a tres clases muy simples:

### `Pedido`
Agrupa toda la información necesaria de cada pedido:

- `nombre` del plato
- `tiempoPreparacion`
- `tiempoRestante`
- `llegada` (minuto en que llega)
- `inicio` (minuto en que el cocinero lo empieza)

Con dos métodos muy claros:

- `terminado()` → indica si ya ha acabado
- `tick()` → reduce en 1 minuto el tiempo restante

---

###  `ArbolPedidos` (Árbol Binario de Búsqueda)

Esta clase es el núcleo del cambio. Implementa un **árbol binario de búsqueda** donde:

- Cada nodo (`Nodo`) guarda un `Pedido`
- Los pedidos con menor `tiempoPreparacion` se insertan a la **izquierda**
- Los pedidos con tiempo mayor o igual, a la **derecha**

Métodos importantes:

- `insertar(Pedido p)`  
  Inserta el pedido en el árbol llamando a `insertarRec`, que es recursivo:
  - si el nodo actual es `null`, se crea uno nuevo,
  - si el tiempo del nuevo pedido es menor, se baja por la rama izquierda,
  - si es mayor o igual, por la rama derecha.

- `extraerMinimo()`  
  Devuelve y elimina el pedido con menor tiempo:
  - recorre hacia la izquierda hasta encontrar el nodo sin hijo izquierdo,
  - ese es el mínimo,
  - reconecta el subárbol derecho (si lo hay) para no perder información.

- `comparaciones`  
  Se incrementa cada vez que se hace una comparación entre tiempos de preparación, lo que permite medir la eficiencia de la estructura al final de la simulación.

---

###  `Simulation`

En la versión final, `Simulation` concentra:

- la lógica de llegada de pedidos,
- el uso del árbol binario (`ArbolPedidos`) como “cola de espera”,
- la lógica del cocinero,
- y el cálculo de estadísticas básicas.

Funciona así:

1. Se simulan **120 minutos**.
2. Cada minuto, con probabilidad **0.4**, llega un pedido nuevo:
   - se elige un plato al azar de: Bebida, Café, Colacao, Bocadillo, Ensalada,
   - se genera un tiempo aleatorio dentro de su rango,
   - se crea un `Pedido` y se inserta en el `ArbolPedidos`.
3. Si el cocinero está libre y hay pedidos en el árbol:
   - se llama a `extraerMinimo()` para obtener el pedido con menor tiempo,
   - se registra el instante de inicio,
   - se suma el tiempo de espera.
4. Cada minuto avanza la preparación del pedido actual (`tick()`).
5. Cuando se termina un pedido, se aumenta el contador de pedidos atendidos.

Al final, `Simulation` muestra un resumen con:

- pedidos atendidos,
- pedidos pendientes,
- tiempo total de espera,
- tiempo medio de espera,
- número de comparaciones realizadas por el árbol.

---

## 4. Conclusión del cambio

El refactor desde la versión original (con interfaz, listas y `PriorityQueue`) a esta versión minimalista con:

- `Pedido`
- `ArbolPedidos`
- `Simulation`

me ha permitido:

- **Simplificar mucho el diseño**, manteniendo el mismo comportamiento general.
- **Hacer visible y explícito el uso de un Árbol Binario de Búsqueda**, en lugar de esconder la estructura dentro de una librería.
- **Relacionar directamente el número de comparaciones con la estructura elegida**, tal y como pedía el enunciado.

De esta forma, la solución final no solo funciona, sino que también está más alineada con el objetivo pedagógico del reto: **reflexionar sobre qué estructura de datos es adecuada cuando necesitamos extraer repetidamente el elemento mínimo de una colección dinámica**.
