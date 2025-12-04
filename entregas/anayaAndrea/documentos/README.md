# Simulación RCCCF - Restaurante con Cocina Optimizada

## Descripción

Este proyecto simula la cocina del RCCCF, donde los pedidos se procesan según **el tiempo de preparación**, no por orden de llegada.

Se ha utilizado un **árbol binario de búsqueda (BST)** para gestionar los pedidos pendientes, optimizando la selección del pedido con menor tiempo.

## Clases principales

- **Plato:** representa un tipo de plato con nombre y tiempo de preparación.
- **Pedido:** encapsula un Plato y guarda tiempo restante y minuto de llegada.
- **NodoPedido:** nodo del árbol que contiene un Pedido y referencias a hijos izquierdo y derecho.
- **ArbolPedidos:** BST que almacena pedidos según tiempo restante y permite extraer siempre el pedido más rápido.
- **Registro:** almacena pedidos completados y calcula métricas finales.
- **Cocinero:** procesa pedidos, gestiona ArbolPedidos y mantiene estadísticas de la jornada.
- **Main:** ejecuta la simulación minuto a minuto, genera pedidos aleatorios y muestra resultados.

## Cambios respecto a la versión anterior

- Antes se usaba **lista ordenada** para pedidos pendientes (`FilaPedidos.java`).  
- Ahora se usa **ArbolPedidos + NodoPedido**, mejorando eficiencia y claridad.
- Se mantiene la lógica de simulación y probabilidad de llegada (40% por minuto).
- Se añaden métricas: comparaciones realizadas, tiempo de espera total y medio, pedidos pendientes.

## Ejecución

```bash
javac *.java
java Main
