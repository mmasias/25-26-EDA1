# Simulación Cocina RCCCF

Este proyecto es mi versión del reto RCCCF para la clase. La idea era hacer una simulación de cómo funciona un restaurante donde los pedidos no se atienden por orden de llegada, sino según el **tiempo de preparación**: siempre se atiende primero el pedido más rápido.

---

## Cómo funciona la cocina

- Cada minuto hay **40% de probabilidad** de que llegue un nuevo pedido.
- El pedido puede ser cualquiera de estos platos, con igual probabilidad:

| Plato      | Tiempo de preparación |
|------------|---------------------|
| Bebida     | 1–2 min             |
| Café       | 2–3 min             |
| Colacao    | 2–4 min             |
| Bocadillo  | 3–5 min             |
| Ensalada   | 5–8 min             |

- El cocinero procesa **un pedido a la vez**, y siempre elige el que tenga **menos tiempo restante**.
- Cada minuto se resta 1 al tiempo del pedido en proceso.
- Cuando termina, toma inmediatamente el siguiente pedido más corto (si hay).

---

## Qué muestra el programa

Durante la simulación, se imprime:

- Cuando llega un pedido: tipo y tiempo de preparación
- Cuántos pedidos hay en cola
- Qué pedido está procesando el cocinero y su tiempo restante

Al final, imprime un resumen con:

- Pedidos atendidos
- Pedidos pendientes al cierre
- Tiempo total de espera de todos los pedidos
- Tiempo medio de espera por pedido
- Número de comparaciones que hizo el programa para elegir el pedido más corto

---

## Cómo está organizado el código

- **Pedido.java** → representa cada pedido, su tipo, tiempos y cuándo llegó y terminó.
- **Cocina.java** → maneja la cola de pedidos usando un min-heap para siempre sacar el más corto y cuenta comparaciones.
- **Simulation.java** → clase principal que hace la simulación minuto a minuto y muestra todo en pantalla.

---

## Cómo ejecutar

1. Abrir la carpeta del proyecto en Visual Studio Code.
2. Compilar todo:

![Diagrama](<Untitled diagram-2025-11-30-230525.png>)