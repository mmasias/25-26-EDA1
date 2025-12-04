# Reto 005 refactor

## ¿Qué buscabamos en esta refactorización del reto? 

Lo que buscabamos era una estructura más optima que las listas y arrays para este enunciado. La estructura `ArbolPedidos` ha sido diseñada específicamente para cumplir con los requisitos del enunciado.

Se destaca:
-  Criterio de Ordenación
    El árbol organiza los nodos basándose en el atributo `tiempoTotal` del pedido:
    Rama Izquierda: Contiene los pedidos con tiempos estrictamente menores que el nodo actual.
    Rama Derecha: Contiene los pedidos con tiempos mayores o iguales.

Esto garantiza que el pedido con la mayor prioridad (menor tiempo) se encuentre siempre en el extremo inferior izquierdo del árbol.

## Arquitectura del Sistema

El código se ha dividido en cinco clases con funciones delimitadas:

### `Simulacion` 
En él se encuentra el Main. Su responsabilidad se limita a la simulación del tiempo. Ejecuta el bucle principal que representa la jornada (120 iteraciones/minutos) y delega las operaciones lógicas a la clase `Restaurante`. 


**Nota**-> He declarado 120 minutos a la duración de la jornada para poder ver visualmente mejor el funcionamiento del codigo.



### `Restaurante` 
Actúa como controlador del sistema. Encapsula las reglas del dominio:
- Gestiona la probabilidad de llegada de clientes (40%).
- Administra al cocinero.
- Coordina la interacción entre la simulación temporal y la estructura de datos, solicitando al árbol la extracción del pedido  prioritario cuando el cocinero está disponible.


### `ArbolPedidos` 
Implementa la lógica del Árbol Binario de Búsqueda. Abstrae las operaciones de inserción y eliminación. Incluye un contador de comparaciones como métrica de rendimiento para evaluar la eficiencia del algoritmo.


### `Nodo` 
Clase  que conforma el árbol. Mantiene la referencia al objeto de datos (`Pedido`) y los punteros a los subárboles (`izquierdo` y `derecho`).

### `Pedido` 
Clase  que representa la entidad básica del problema. Almacena el estado del pedido (tipo, tiempo total, tiempo restante). 

