## Principales Cambios Realizados

A continuación se detallan las modificaciones aplicadas al código original para llegar a la versión actual:

### 1. Cambio de Estructura de Datos 
* **Ahora:** Se ha implementado un **Árbol Binario** manual mediante las clases `ArbolPedidos` y `Nodo`.

### 2. Algoritmo de Prioridad
* **Antes:** Para encontrar el pedido más rápido, se recorría toda la lista con un bucle `for`.
* **Ahora:** Los pedidos se insertan ordenados. Los de menor tiempo van automáticamente a la izquierda. Extraer el pedido más rápido solo requiere bajar por la rama izquierda hasta el final.

### 3. Eliminación de Lógic
* **Antes:** El cocinero podía pausar un plato a medias si llegaba uno más rápido (Lógica compleja y propensa a errores).
* **Ahora:** El cocinero termina el plato actual antes de coger el siguiente  Esto simplifica drásticamente la clase `Restaurante` y reduce la posibilidad de bugs, manteniendo un código "básico y funcional".

### 4. Simplificación de Clases
* **Clase `TipoPlato`:** Eliminada. La lógica de generación aleatoria de nombres y tiempos se ha integrado directamente en la clase `Pedido` para reducir el número de archivos y la complejidad de las referencias.
* **Clase `Cocinero`:** Se ha limpiado para que solo tenga métodos esenciales (`trabajar`, `estaLibre`, `asignar`).

