# Explicación del diseño y razonamiento del Reto RCCCF

En este trabajo he desarrollado una simulación del funcionamiento de la cocina del restaurante **RCCCF**. El objetivo principal era representar cómo llegan los pedidos, cómo se gestionan dentro de la cola y cómo el cocinero elige siempre el pedido con el tiempo de preparación más corto. A partir de ese planteamiento organicé el proyecto en clases separadas para que el funcionamiento quedara claro y fácil de seguir, manteniendo la estructura que pide el enunciado.

## Estructura general del programa

La clase principal que arranca todo el sistema se llama **SimulacionRCCCF**, porque representa directamente el nombre del reto y deja claro que es el punto de inicio del programa. Desde ahí se crea un objeto de la clase **SimulacionCocina**, que es la encargada de llevar prácticamente toda la lógica: controla los minutos de la jornada, detecta si aparece un pedido nuevo, gestiona la cola y decide qué pedido pasa a ser preparado.

En cada minuto del día la simulación comprueba si aparece un pedido nuevo con una probabilidad del **40%**. Si aparece, se crea un plato aleatorio y se construye un objeto de la clase **Pedido**. Ese pedido se guarda en la cola de espera, donde más adelante se seleccionará el que tenga el menor tiempo restante.

## Representación de los platos y los pedidos

Para los distintos tipos de platos utilicé una clase abstracta llamada **Plato**. Esta clase define lo que todos los platos tienen en común: un nombre y un tiempo de preparación.  
Cada tipo de plato concreto (**Bebida, Café, Colacao, Bocadillo, Ensalada**) hereda de esta clase y calcula su tiempo de preparación dentro del rango que indica el enunciado. Esto permite que cada plato tenga su propio comportamiento sin repetir código.

La clase **Pedido** reúne toda la información de un pedido real: qué plato se pidió, cuánto tarda en prepararse, cuándo llegó y cuánto tiempo ha estado esperando antes de que el cocinero lo atendiese. Gracias a esto es posible calcular el tiempo total de espera y el tiempo medio al final de la jornada.

## Gestión de la cola y selección del pedido

La cola de espera la implementé con una clase llamada **ColaPrioridadPedidos**. Aunque internamente utiliza un array, se comporta como una cola de prioridad porque no devuelve el primer elemento que entra, sino el que tiene el menor tiempo de preparación restante.  
Cada vez que se busca el mínimo se cuentan las **comparaciones realizadas**, ya que esta cifra es una métrica importante pedida en el enunciado.

Cuando el cocinero termina un pedido, se selecciona el próximo utilizando precisamente esta función. Al reducir minuto a minuto el tiempo restante del pedido en preparación, también se controla cuándo se completa y se pasa al siguiente.

## Resumen final de la simulación

Cuando termina la jornada se calcula:

- Cuántos pedidos se pudieron completar.
- Cuántos quedaron pendientes.
- El tiempo total de espera acumulado.
- La media de espera por pedido.
- El número total de comparaciones realizadas en la búsqueda del mínimo.
