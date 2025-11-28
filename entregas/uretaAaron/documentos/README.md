📝 Explicación del Reto RCCCF

En este reto se simula la cocina de un restaurante donde los pedidos llegan minuto a minuto con una probabilidad del 40%. Cada pedido corresponde a un plato diferente, y cada plato tiene su propio rango de tiempo de preparación.

Al principio pensé que, igual que en el supermercado del reto anterior (CCCF), los pedidos debían atenderse en orden de llegada. Pero analizándolo mejor, eso no tendría sentido en una cocina real: no vas a dejar esperando un café de 2 minutos solo porque antes llegó una ensalada de 7 minutos.

Llegué entonces a la conclusión correcta:
👉 la cocina debe trabajar con el plato más rápido primero.

Este enfoque se llama Shortest Job First (SJF) y permite:

terminar más pedidos,

reducir la espera promedio,

y evitar colas eternas por platos largos.

Por eso mi diseño usa una cola de prioridad, donde siempre se elige el pedido con el menor tiempo de preparación. El cocinero procesa ese pedido, y cuando termina, toma el siguiente más corto.

En resumen:
✔ Los pedidos llegan por orden
❌ pero no se atienden por orden
✔ se atienden por eficiencia usando SJF
