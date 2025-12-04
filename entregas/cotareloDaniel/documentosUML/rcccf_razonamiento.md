# Razonamiento del cambio a Árbol Binario

## Antes: cola de prioridad con arrays

La primera versión del código organizaba los pedidos usando una estructura parecida a una **cola de prioridad hecha con un array**.  
Cada vez que llegaba un pedido lo guardaba al final del array y, cuando el cocinero quedaba libre, el programa buscaba en ese array el pedido con el menor tiempo de preparación.

El problema de esa versión es que no se estaba usando un **árbol**, que es precisamente el enfoque que el profesor quería para este ejercicio.  
Además, el array obligaba a recorrer todo para encontrar el mínimo, lo cual no aprovecha ninguna estructura de búsqueda.

---

## Ahora: un árbol binario sencillo

Cambié la estructura por un **árbol binario de búsqueda**, donde:

- Cada nodo contiene un pedido.  
- Los pedidos con tiempo menor van a la izquierda.  
- Los pedidos con tiempo mayor o igual van a la derecha.  
- El árbol no usa recursividad (todo se hace con bucles).  
- No se usa herencia, solo composición (el árbol contiene nodos, los nodos contienen pedidos).  

---

## Cómo funciona el árbol en esta versión

### Insertar
Para meter un pedido nuevo:
1. Se compara el tiempo del pedido con el nodo actual.  
2. Si es menor, intenta ir a la izquierda.  
3. Si es mayor o igual, intenta ir a la derecha.  
4. Se avanza por el árbol hasta encontrar un hueco.  

### Extraer mínimo
Para escoger el pedido a cocinar:
1. El pedido con menor tiempo está en la zona más a la izquierda del árbol.  
2. Se recorre hacia la izquierda con un bucle hasta encontrar el nodo sin hijo izquierdo.  
3. Ese es el pedido que se entrega al cocinero.  
4. Se recoloca el árbol moviendo su subárbol derecho, si lo tiene.  

De esta forma siempre se obtiene el pedido más rápido disponible.

---

## Qué partes del código usan el árbol

- La clase **ArbolPedidos** gestiona toda la estructura.  
- La clase **SimulacionRCCCF** lo usa para insertar pedidos y sacar el mínimo.  
- La lógica del cocinero ya no busca en un array y no compara manualmente todos los tiempos, lo hace el propio árbol.