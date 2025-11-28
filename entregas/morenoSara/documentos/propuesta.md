# Simulación del Restaurante 

Simulamos el funcionamiento de una cocina donde los pedidos **no se atienden en orden de llegada**, sino **por menor tiempo de preparación**, para mejorar la eficiencia del cocinero.

La simulación dura **120 minutos**. Cada minuto, hay un **40% de probabilidad** de que llegue un nuevo pedido. El cocinero siempre trabaja en el pedido pendiente más rápido y, al terminar, selecciona el siguiente de menor duración.


##  Arquitectura del sistema

El sistema está compuesto por cinco clases principales:

- **`Pedido`**: plato solicitado, con su tipo, tiempo total, tiempo restante y minuto de llegada.
- **`FilaPedidos`**: lista interna del cocinero, **ordenada por prioridad**. Cuenta las comparaciones realizadas al insertar.
- **`Recepcionista`**: recibe los pedidos  y los entrega **uno a uno** al cocinero.
- **`Cocinero`**: gestiona su propia cola de pedidos, los prioriza, los procesa minuto a minuto y genera el estado actual para la salida.
- **`RCCCF`**: realiza la simulación completa y genera el resumen final.


##  Salida del programa

El simulador muestra en tiempo real:

- Llegada de nuevos pedidos  
- Número de pedidos pendientes (**"COLA"**)  
- Pedido actual en preparación  

Al final, imprime un resumen con:
- Pedidos atendidos y pendientes  
- Tiempo total y medio de espera  
- **Número total de comparaciones** (métrica de eficiencia algorítmica)

