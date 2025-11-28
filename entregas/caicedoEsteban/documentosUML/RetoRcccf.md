# Diagrama de Vista Pública

## Clase `Pedido`

- **+ Pedido(tipo: String, rango: int[])**  
  Constructor público para crear nuevos pedidos.

- **+ getTipo(): String**  
  Devuelve el tipo del plato.

- **+ getTiempoPrep(): int**  
  Devuelve el tiempo total de preparación asignado.

- **+ getTiempoRestante(): int**  
  Devuelve el tiempo que falta para completar el pedido.

- **+ getTiempoLlegada(): int**  
  Devuelve el minuto en que llegó el pedido.

- **+ tick(): void**  
  Reduce en 1 el tiempo restante del pedido.

- **+ isTerminado(): boolean**  
  Indica si el pedido ya está terminado.

---

## Clase `GeneradorPedidos`

- **+ llegaPedido(): boolean**  
  Determina si llega un nuevo pedido (40% de probabilidad).

- **+ crearPedido(minuto: int): Pedido**  
  Genera un pedido según tiempo y tipo aleatorio.

- **- random: Random**  
  Generador aleatorio interno (no expuesto).

---

## Clase `Cocina`

- **+ Cocina()**  
  Inicializa la cocina y las métricas.

- **+ agregarPedido(p: Pedido): void**  
  Añade un pedido a la cola de prioridad.

- **+ procesarMinuto(): void**  
  Procesa el pedido actual y selecciona el siguiente si corresponde.

- **+ getPedidoActual(): Pedido**  
  Devuelve el pedido que está siendo procesado.

- **+ getColaSize(): int**  
  Retorna cuántos pedidos están en espera.

- **+ getComparaciones(): int**  
  Devuelve el número total de comparaciones usadas por la estructura.

- **+ getTotalAtendidos(): int**  
  Retorna la cantidad total de pedidos completados.

- **+ getTiempoEsperaTotal(): int**  
  Tiempo total acumulado de espera de todos los pedidos.

- **+ getPendientes(): int**  
  Cuántos pedidos quedan en la cola al finalizar.

- **- seleccionarSiguientePedido(): void**  
  Lógica interna para extraer de la cola el mínimo (no público).

- **- actualizarEstadisticas(pedido: Pedido): void**  
  Suma espera y métricas internas.

- **- cola: PriorityQueue<Pedido>**  
  Estructura interna donde se almacenan los pedidos.

- **- pedidoActual: Pedido**  
  Pedido que se está cocinando.

- **- comparaciones, totalAtendidos, tiempoEsperaTotal: int**  
  Métricas internas de eficiencia.

---

## Clase `Simulation`

- **+ main(args: String[]): void**  
  Ejecuta la simulación completa.
