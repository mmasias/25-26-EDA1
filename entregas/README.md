# Reto-005 

## Descripción general del reto
El reto consiste en simular el funcionamiento de una cocina durante una jornada laboral de 240 minutos, gestionando pedidos de distintos tipos de platos.
Cada pedido tiene un tiempo de preparación diferente y llegan de forma aleatoria con una probabilidad del 40% por minuto.
El objetivo principal es: 
- Atender siempre primero el pedido con menor tiempo de preparación
- Calcular el tiempo medio de espera de los pedidos
- Contabilizar el número de comparaciones necesarias para seleccionar el siguiente pedido

## ¿Como funcionará?
La simulación avanza minuto a minuto siguiendo estas reglas:

- Cada minuto puede llegar un nuevo pedido con un 40% de probabilidad.

- Los pedidos se almacenan en una estructura de datos.

- Si el cocinero queda libre:

Se selecciona el pedido con menor tiempo de preparación.

- El cocinero trabaja 1 minuto por iteración.

- Cuando un pedido termina:

- Se contabiliza como pedido atendido.

- Se calcula su tiempo de espera.

El proceso continúa hasta completar los 240 minutos.

## Apuntes
### Tipos de platos:
|Tipo| Tiempo de preparación|
|---|---|
|Bebida| 1-2 minuto|
|Café| 2-3 minutos|
|Colacao| 2-3 minutos|
|Bocadillo| 3-5 minutos|
|Ensalada| 5-8 minutos|

- Cuando llega un pedido, el cliente puede pedir entre uno cualquiera de estos tipos de plato con la misma probabilidad aleatoria.
- El tiempo específico de preparación se genera aleatoriamente dentro del rango correspondiente.

