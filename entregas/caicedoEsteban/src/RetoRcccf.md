# Diagrama de Vista Pública (con descripciones)

## Clase `Pedido`

- **+ Pedido(tipo: String, rango: int[])**  
  Crea un pedido asignando un tiempo de preparación aleatorio dentro del rango.

- **+ getTipo(): String**  
  Devuelve el tipo de plato del pedido.

- **+ getTiempoPrep(): int**  
  Devuelve el tiempo total de preparación inicial del pedido.

- **+ getTiempoRestante(): int**  
  Devuelve el tiempo que falta para completar el pedido.

- **+ getTiempoLlegada(): int**  
  Devuelve el minuto en el que llegó el pedido.

- **+ tick(): void**  
  Reduce en 1 minuto el tiempo restante del pedido.

- **+ isTerminado(): boolean**  
  Indica si el pedido ha terminado de prepararse.

---

## Clase `GeneradorPedidos`

- **+ llegaPedido(): boolean**  
  Devuelve `true` con una probabilidad del 40% indicando que llega un nuevo pedido.

- **+ crearPedido(minuto: int): Pedido**  
  Genera un pedido nuevo con un tipo aleatorio y tiempo de preparación correspondiente.

---

## Clase `Cocina`

- **+ Cocina()**  
  Inicializa la cocina con su cola de prioridad y las métricas.

- **+ agregarPedido(p: Pedido): void**  
  Añade un pedido a la cola de espera (PriorityQueue).

- **+ procesarMinuto(): void**  
  Gestiona el trabajo del cocinero: procesa el pedido actual y selecciona el siguiente si corresponde.

- **+ getPedidoActual(): Pedido**  
  Devuelve el pedido que el cocinero está preparando actualmente.

- **+ getColaSize(): int**  
  Devuelve cuántos pedidos hay esperando.

- **+ getComparaciones(): int**  
  Devuelve cuántas comparaciones ha realizado la estructura para seleccionar mínimos.

- **+ getTotalAtendidos(): int**  
  Devuelve cuántos pedidos se completaron durante la jornada.

- **+ getTiempoEsperaTotal(): int**  
  Devuelve la suma de todos los tiempos de espera acumulados.

- **+ getPendientes(): int**  
  Devuelve cuántos pedidos quedan sin completar al final.

---

## Clase `Simulation`

- **+ main(args: String[]): void**  
  Ejecuta la simulación minuto a minuto, mostrando los eventos y el resumen final.
