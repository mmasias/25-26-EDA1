# Planteamiento del Proyecto RCCCF

En este documento explico cómo he organizado el código para el reto del restaurante y por qué he elegido esta forma de trabajar.

## 1. Organización del Proyecto (Estructura)

Para que el código sea limpio y fácil de entender, he imaginado el programa como si fuera un restaurante real. He diseñado cada clase (archivo) para que represente al "personal" o una "parte" del restaurante:

* **`Simulacion` (El interruptor):** Es simplemente mi punto de partida. Al igual que un interruptor de luz, su único trabajo es encender el sistema para que todo empiece a funcionar.
* **`RestauranteRCCCF` (El gerente):** Es quien controla todo. He definido que esta clase lleve el reloj (minuto a minuto), decida cuándo entran nuevos clientes y le diga al cocinero qué hacer.
* **`Cocinero` (El trabajador):** Representa al empleado. Solo sabe hacer dos cosas: estar ocupado cocinando un pedido o estar libre esperando órdenes.
* **`Pedido` (La comanda):** Es la nota de papel con la información básica: qué plato es (café, ensalada...) y cuánto tiempo tarda en hacerse.
* **`ColaPrioridad` (El riel de comandas):** Aquí está la clave. Es el lugar donde acumulo los pedidos pendientes. Su trabajo es organizar el caos y decidir cuál es el siguiente plato más rápido.

## 2. La decisión clave: ¿Cómo elijo el siguiente pedido?

El reto plantea un problema: **no quiero atender por orden de llegada**, sino por **rapidez de preparación**.

### El problema de la fila normal (Cola FIFO)
En un supermercado, si llegas primero, te atienden primero. Si yo usara esto en la cocina, un cliente que solo quiere un café (2 minutos) tendría que esperar detrás de alguien que pidió una ensalada compleja (8 minutos). Eso no es eficiente para este restaurante.

### Mi solución: "El trabajo más corto primero"
He diseñado una estructura específica (la `ColaPrioridad`) que funciona así:

1.  **Recepción:** Pongo todos los pedidos nuevos en una lista, sin importar el orden.
2.  **Selección:** Cuando el cocinero queda libre, no hago que coja el primer papel que ve. En su lugar, **miro todos los pedidos pendientes**, comparo sus tiempos uno por uno y selecciono el que se tarda menos en hacer.

## 3. ¿Por qué he elegido una Lista y no otra estructura?

Para decidir dónde guardar los pedidos pendientes, analicé varias opciones, pero las descarté por las siguientes razones:

* **Descarté las Colas (FIFO):** Porque funcionan por orden de llegada (el primero en entrar es el primero en salir). Yo necesito priorizar por *tiempo*, no por turno.
* **Descarté las Pilas (LIFO):** Porque atienden al último que llega. Esto sería injusto y caótico en una cocina real.
* **Descarté los Árboles (Montículos/Heaps):** Aunque son muy eficientes para encontrar mínimos, su implementación desde cero es algo complicada. Si usara los automáticos de Java (como `PriorityQueue`), la "magia" ocurriría por dentro y no podría ver ni contar cuántas operaciones cuesta ordenar.

**Mi elección final: La Lista.**
He elegido una lista simple porque es la estructura más "transparente". Al tener los pedidos en una lista, tengo el control total para recorrerlos uno a uno manualmente. Esto es vital para el reto, porque es la única forma sencilla de colocar mi propio contador (`comparaciones++`) dentro del bucle de búsqueda y demostrar numéricamente el esfuerzo que hace el algoritmo para encontrar el plato más rápido.