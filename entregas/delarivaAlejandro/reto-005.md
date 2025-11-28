# UML Reto005 

```mermaid
classDiagram
direction LR

class Simulacion {
  +main(args: String[]): void
}

class Restaurante {
  -raizArbol: NodoPedido
  -numeroPendientes: int
  -pedidoEnCurso: Pedido
  -tiempoActual: int
  -duracionJornada: int
  -pedidosAtendidos: int
  -tiempoEsperaTotal: int
  -comparacionesTotales: int
  -siguienteId: int
  -PROB_LLEGADA: double

  +Restaurante(duracionJornada: int)
  +simular(): void

  -llegaPedido(): boolean
  -generarPedido(): Pedido
  -generarTipoAleatorio(): TipoPlato
  -insertarPedido(pedido: Pedido): void
  -compararPedidos(p1: Pedido, p2: Pedido): int
  -seleccionarNuevoPedido(): void
  -extraerMinimo(): Pedido
  -procesarMinutoActual(): void
  -mostrarResumen(): void
}

class NodoPedido {
  -pedido: Pedido
  -izquierdo: NodoPedido
  -derecho: NodoPedido

  +NodoPedido(pedido: Pedido)
  +getPedido(): Pedido
  +getIzquierdo(): NodoPedido
  +getDerecho(): NodoPedido
  +setIzquierdo(n: NodoPedido): void
  +setDerecho(n: NodoPedido): void
}

class Pedido {
  -id: int
  -tipo: TipoPlato
  -tiempoTotal: int
  -tiempoRestante: int
  -instanteLlegada: int
  -instanteInicio: int

  +Pedido(id: int, tipo: TipoPlato, tiempoTotal: int, instanteLlegada: int)
  +getId(): int
  +getTipo(): TipoPlato
  +getTiempoTotal(): int
  +getTiempoRestante(): int
  +getInstanteLlegada(): int
  +getInstanteInicio(): int
  +setInstanteInicio(i: int): void
  +decrementarTiempo(): void
}

class TipoPlato {
  <<enumeration>>
  BEBIDA
  CAFE
  COLACAO
  BOCADILLO
  ENSALADA

  -tiempoMinimo: int
  -tiempoMaximo: int
  -nombre: String

  +getNombre(): String
  +generarTiempoPreparacion(): int
}

Simulacion --> Restaurante : «usa»

Restaurante *-- NodoPedido : raizArbol
Restaurante --> Pedido : pedidoEnCurso

NodoPedido *-- NodoPedido : izquierdo
NodoPedido *-- NodoPedido : derecho
NodoPedido --> Pedido : pedido

Pedido --> TipoPlato : tipo
