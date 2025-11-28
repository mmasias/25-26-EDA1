# Explicación del reto RCCCF

El programa simula cómo funciona la cocina del restaurante RCCCF durante 12 horas (720 minutos).  
En cada minuto puede llegar un pedido nuevo y el cocinero va atendiendo los pedidos uno a uno.

### Pedido
Esta clase representa un pedido.  
Guarda:
- el tipo de producto,
- el tiempo que tarda en hacerse,
- el tiempo que le queda por terminar,
- el minuto en el que llegó,
- y un identificador.

### Lista enlazada
Para almacenar los pedidos en espera he usado una lista enlazada hecha a mano.

La lista está formada por nodos (`NodoPedido`), y cada nodo guarda un pedido y un enlace al siguiente.

La clase `ListaPedidos` permite:
- añadir un pedido al final,
- obtener un pedido por posición,
- eliminar un pedido por posición,
- y saber cuántos pedidos hay en total.

### Cocina
Aquí se gestiona la cola de pedidos y el trabajo del cocinero.

La cocina:
- guarda los pedidos pendientes en la lista enlazada,
- si el cocinero no está preparando nada, busca el pedido que tarda menos para prepararlo primero,
- lleva la cuenta de los pedidos ya terminados,
- y también cuántas comparaciones se han hecho al buscar el pedido más corto.

Cada minuto, si el cocinero está preparando algo, se le resta un minuto al tiempo restante de ese pedido.

### Simulación
Controla los 720 minutos de funcionamiento.

En cada minuto se hace lo siguiente:
1. Se decide si llega un pedido nuevo.
2. Si llega, se crea y se añade a la lista de espera.
3. La cocina intenta elegir un pedido si el cocinero está libre.
4. Se suma el tamaño de la cola para medir el tiempo de espera total.
5. El cocinero trabaja un minuto en su pedido.

Al final de la simulación se muestran:
- los pedidos terminados,
- los pendientes,
- el tiempo total de espera,
- el tiempo medio de espera,
- y las comparaciones hechas al buscar pedidos.

El objetivo es simular el funcionamiento del restaurante usando una lista enlazada sencilla y la lógica necesaria para procesar los pedidos minuto a minuto.
