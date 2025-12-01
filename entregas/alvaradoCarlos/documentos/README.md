# Propuesta de Solución para RCCCF y la gestión de Pedidos - Reto 005

## Estructura Propuesta

Primero planteamos y consideramos la prioridad: **pedido pendiente con menor tiempo de preparación**.

Que estructura de datos sería idónea para gestionar los pedidos entrantes? (considerando que cada pedido solo solicita un plato y que ese plato tenga un tiempo determinado de preparación).

Si consideramos que el tiempo se define en función al plato que nos piden, una estructura binaria para decidir entre "mayor y menor" puede ser idonea. Lo que nos otorga un **árbol binario**. 

Este arbol binario tendrá una condición antes, no será un árbol normal ya que en este caso implementaremos un árbol de tipo **min-heap** que tiene como única regle:

Regla: ***El de arriba es menor que los de abajo.***

Comparto el [diagrama del árbol](/entregas/alvaradoCarlos/documentosUML/README.md)

## Justificación Técnica

- Eficiencia en Acceso: El cocinero necesita acceso inmediato al elemento de mayor prioridad (menor tiempo).

- Eficiencia Dinámica: Tanto la inserción de nuevos pedidos como la extracción del plato terminado.

- Gestión de Memoria: Al ser un Árbol Binario Completo, no existen huecos en la estructura, optimizando el almacenamiento.

Comparto el [diagrama de estructuración de clases para pedidos](/entregas/alvaradoCarlos/documentosUML/README.md)
