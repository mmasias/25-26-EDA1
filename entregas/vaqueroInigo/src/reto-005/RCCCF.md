# RCCCF – Simulación de Cocina

Este proyecto modela la cocina del RCCCF, donde los pedidos no se atienden por orden de llegada, sino según su tiempo de preparación. El objetivo es comparar cómo cambia la eficiencia del sistema según la estructura de datos utilizada para seleccionar el pedido con menor tiempo.

## Funcionamiento

Cada minuto puede llegar un pedido nuevo con una probabilidad del 40%. Cada pedido tiene un tipo de plato y un tiempo de preparación dentro de su rango. Los pedidos pendientes se almacenan y el cocinero toma siempre el de menor tiempo. Al finalizar la jornada se registran métricas como pedidos atendidos, pendientes, tiempos de espera y comparaciones totales.

## Diseño

El sistema se organiza en varias clases:

- **Pedido**: datos del pedido y su tiempo restante.
- **ColaPedidos**: almacena los pedidos pendientes y permite extraer el mínimo.
- **Cocinero**: procesa el pedido asignado.
- **SimulacionContexto**: mantiene los valores globales de la simulación.
- **SimuladorRCCCF**: coordina la ejecución completa.



## Resultados

El simulador ofrece un resumen final que muestra:

- Pedidos atendidos y pendientes  
- Tiempo total y medio de espera  
- Comparaciones necesarias para elegir el pedido mínimo  



