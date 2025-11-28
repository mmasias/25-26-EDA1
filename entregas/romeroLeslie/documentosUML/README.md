Simulación de Cocina con árbol binario

-El diagrama representa la simulación del proceso de cocina donde los pedidos se gestionan mediante una estructura de datos eficiente. La clase pedido almacena la información de cada orden, como su tipo y tiempo de preparación. Estos pedidos se insertan en HeapPedidos, que permite que siempre se atienda primero al pedido con menor tiempo de preparación. La Cocina coordina el flujo: recibe pedidos, los almacena en el heap, asigna prioridad según tiemo. Finalmente, la clase simulación une todo el proceso.

-Su lógica se basa en un árbol binario, lo que lo convierte en la estructura ideal para este escenario. Esto ya  nos permite que la operación de obtener el elemento de mayor prioridad (el menor tiempo) se realice en tiempo constante.
