                                      +----------------------------------+
                                      |           Simulation             |
                                      +----------------------------------+
                                      | (sin atributos de instancia)     |
                                      +----------------------------------+
                                      | + main(args: String[]): void     |
                                      +----------------------------------+
                                                   |
                                                   | crea / llama a simular()
                                                   v
+----------------------------------------------------------------------------------------------+
|                                      CocinaRCCCF                                             |
+----------------------------------------------------------------------------------------------+
| - tiempoActual: int                                                                         |
| - duracion: int                                                                            |
| - probLlegada: double                                                                      |
| - cola: IPPriorityQueuePedidos                                                             |
| - cocinero: Cocinero                                                                       |
| - estadisticas: EstadisticasJornada                                                        |
+----------------------------------------------------------------------------------------------+
| + CocinaRCCCF(duracion: int, probLlegada: double, capacidadMax: int)                       |
| + simular(): void                                                                          |
| + procesarMinuto(): void                                                                   |
| + mostrarEstado(): void                                                                    |
| + getEstadisticas(): EstadisticasJornada                                                   |
+----------------------------------------------------------------------------------------------+
       |                         | usa cola de prioridad                   | usa estadísticas
       | compone                 |                                        |
       v                         v                                        v
+-----------------------+      +------------------------------------+      +--------------------------+
|       Cocinero        |      |      IPriorityQueuePedidos        |      |   EstadisticasJornada    |
+-----------------------+      +------------------------------------+      +--------------------------+
| - pedidoActual: Pedido|      | (interface)                        |      | - pedidosAtendidos: int  |
| - pedidosAtendidos:int|      +------------------------------------+      | - pedidosPendientes: int |
| - tiempoTotalEspera:int|     | + insertar(p: Pedido): void        |      | - tiempoTotalEspera:int  |
+-----------------------+      | + extraerMin(): Pedido             |      | - tiempoMedioEspera:double|
| + Cocinero()          |      | + estaVacia(): boolean             |      | - comparacionesTotales:int|
| + procesarMinuto(     |      | + tamano(): int                    |      +--------------------------+
|   cola:IPriorityQueue-|      | + getComparaciones(): int          |      | + EstadisticasJornada()  |
|   Pedidos, tiempo:int):void  +------------------------------------+      | + setPedidosAtendidos(int)|
| + hayPedidoActual():bool|                ^ implementa                     | + setPedidosPendientes(int)|
| + getPedidoActual():Pedido|                |                              | + setTiempoTotalEspera(int)|
| + getPedidosAtendidos():int|                |                              | + setComparacionesTotales(int)|
| + getTiempoTotalEspera():int|              |                              | + calcularMedias(): void  |
+-----------------------+      +------------------------------------+      | + getPedidosAtendidos():int|
                               |        ColaPrioridadPedidos        |      | + getPedidosPendientes():int|
   Cocinero extrae de cola -->|------------------------------------|      | + getTiempoTotalEspera():int|
                               | - datos: Pedido[]                  |      | + getTiempoMedioEspera():double|
                               | - numElementos: int                |      | + getComparacionesTotales():int|
                               | - comparaciones: int               |      | + toString(): String      |
                               | - capacidad: int                   |      +--------------------------+
                               +------------------------------------+
                               | + ColaPrioridadPedidos(capacidad:int)     
                               | + insertar(p: Pedido): void               
                               | + extraerMin(): Pedido                    
                               | + estaVacia(): boolean                    
                               | + tamano(): int                           
                               | + getComparaciones(): int                 
                               +-------------------------------------------+
                                               |
                                               | almacena / ordena por tiempoRestante
                                               v
                                   +--------------------------------------------+
                                   |                  Pedido                    |
                                   +--------------------------------------------+
                                   | - id: int                                  |
                                   | - tipo: TipoPlato                          |
                                   | - tiempoTotal: int                         |
                                   | - tiempoRestante: int                      |
                                   | - instanteLlegada: int                     |
                                   | - tiempoEspera: int                        |
                                   +--------------------------------------------+
                                   | + Pedido(id:int, tipo:TipoPlato,          |
                                   |           tiempoTotal:int, instante:int)  |
                                   | + disminuirTiempo(): void                  |
                                   | + incrementarEspera(): void                |
                                   | + estaTerminado(): boolean                |
                                   | + getId(): int                             |
                                   | + getTipo(): TipoPlato                    |
                                   | + getTiempoTotal(): int                   |
                                   | + getTiempoRestante(): int                |
                                   | + getInstanteLlegada(): int               |
                                   | + getTiempoEspera(): int                  |
                                   +--------------------------------------------+
                                                  |
                                                  | usa el tipo para rango de tiempo
                                                  v
                                    +----------------------------------+
                                    |            TipoPlato             |
                                    +----------------------------------+
                                    | (enum) BEBIDA, CAFE, COLACAO,   |
                                    |         BOCADILLO, ENSALADA     |
                                    +----------------------------------+
                                    | - minTiempo: int                |
                                    | - maxTiempo: int                |
                                    +----------------------------------+
                                    | + getMinTiempo(): int          |
                                    | + getMaxTiempo(): int          |
                                    | + generarTiempoAleatorio(): int|
                                    +----------------------------------+
