# Extensión del reto RCCCF con código minimalista y árbol binario

En esta extensión vuelvo a resolver el mismo problema del restaurante, pero cambiando dos cosas importantes respecto al código anterior:

1. La estructura donde se guardan los pedidos pendientes.
2. El estilo del código, intentando que sea más simple y directo.

## Cambios en la estructura de datos

En la primera versión usaba una lista enlazada (`NodoPedido` + `ListaPedidos`).  
Ahora esa parte se sustituye por un **árbol binario de búsqueda**:

- `NodoArbolPedido`: guarda un `Pedido` y dos referencias, `izquierdo` y `derecho`.
- `ArbolPedidos`: tiene la `raiz` del árbol y el tamaño.  
  Sus operaciones básicas son:
  - `insertar(Pedido p)`: inserta el pedido en el árbol ordenando por tiempo de preparación.
  - `extraerMinimo()`: baja por la rama izquierda hasta encontrar el pedido con menor tiempo y lo saca del árbol.
  - `estaVacio()` y `getTamano()`: para saber si hay pedidos pendientes y cuántos.

De esta forma, la cola de pedidos pendientes ya no es una lista que se recorre entera, sino un árbol que está pensado para localizar más rápido el mínimo.

## Cambios en la lógica de la cocina

La antigua clase `Cocina` pasa a ser `CocinaConArbol`:

- `pedidosPendientes` ahora es un `ArbolPedidos`.
- Cuando llega un nuevo pedido se llama a `insertar` del árbol.
- Para elegir el siguiente pedido se usa `extraerMinimo()`, que devuelve el pedido más corto.
- Se siguen contando comparaciones, pero ahora asociadas al trabajo con el árbol (inserción y extracción).

La parte de procesar un minuto, controlar si el cocinero está libre y contar cuántos pedidos se han atendido se mantiene, pero el código queda más corto porque delega casi todo en el árbol.

## Cambios en la simulación

`SimulacionRCCCF` se convierte en `SimulacionRCCCFConArbol`, pero la idea general es la misma:

- 720 minutos de jornada.
- Cada minuto se decide si llega un pedido y, si llega, se crea y se envía a `CocinaConArbol`.
- Se va acumulando el tiempo de espera según los pedidos que quedan pendientes.
- Al final se muestra el resumen con pedidos atendidos, pendientes, tiempos de espera y comparaciones.

La ventaja es que la simulación no necesita saber cómo está implementada la cola de pedidos.  
En la primera versión usaba una lista enlazada y ahora usa un árbol binario, pero la estructura del bucle principal apenas cambia.

## Por qué es una versión más minimalista

- Se han reducido las clases al mínimo necesario: `Pedido`, `NodoArbolPedido`, `ArbolPedidos`, `CocinaConArbol` y `SimulacionRCCCFConArbol`.
- Cada clase tiene pocos métodos y con responsabilidad clara.
- El cambio de lista enlazada a árbol binario se concentra en una única parte del código (`ArbolPedidos`), lo que hace más fácil comparar ambas soluciones sin tener que reescribir toda la simulación.
