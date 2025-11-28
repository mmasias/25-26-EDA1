```mermaid
classDiagram
direction LR

class Simulation {
  +main(String[] args) void
}

class CocinaRCCCF {
  - int tiempoActual
  - int duracion
  - double probLlegada
  - IPriorityQueuePedidos cola
  - Cocinero cocinero
  - EstadisticasJornada estadisticas

  +CocinaRCCCF(int duracion, double probLlegada, int capacidadMax)
  +void simular()
  +void procesarMinuto()
  +void mostrarEstado()
  +EstadisticasJornada getEstadisticas()
}

class Cocinero {
  - Pedido pedidoActual
  - int pedidosAtendidos
  - int tiempoTotalEspera

  +Cocinero()
  +void procesarMinuto(IPriorityQueuePedidos cola, int tiempoActual)
  +boolean hayPedidoActual()
  +Pedido getPedidoActual()
  +int getPedidosAtendidos()
  +int getTiempoTotalEspera()
}

class Pedido {
  - int id
  - TipoPlato tipo
  - int tiempoTotal
  - int tiempoRestante
  - int instanteLlegada
  - int tiempoEspera

  +Pedido(int id, TipoPlato tipo, int tiempoTotal, int instanteLlegada)
  +void disminuirTiempo()
  +void incrementarEspera()
  +boolean estaTerminado()
  +int getId()
  +TipoPlato getTipo()
  +int getTiempoTotal()
  +int getTiempoRestante()
  +int getInstanteLlegada()
  +int getTiempoEspera()
}

class TipoPlato {
  <<enumeration>>
  BEBIDA
  CAFE
  COLACAO
  BOCADILLO
  ENSALADA

  - int minTiempo
  - int maxTiempo

  +int getMinTiempo()
  +int getMaxTiempo()
  +int generarTiempoAleatorio()
}

class IPriorityQueuePedidos {
  <<interface>>
  +void insertar(Pedido p)
  +Pedido extraerMin()
  +boolean estaVacia()
  +int tamano()
  +int getComparaciones()
}

class ColaPrioridadPedidos {
  - Pedido[] datos
  - int numElementos
  - int comparaciones
  - int capacidad

  +ColaPrioridadPedidos(int capacidad)
  +void insertar(Pedido p)
  +Pedido extraerMin()
  +boolean estaVacia()
  +int tamano()
  +int getComparaciones()
}

class EstadisticasJornada {
  - int pedidosAtendidos
  - int pedidosPendientes
  - int tiempoTotalEspera
  - double tiempoMedioEspera
  - int comparacionesTotales

  +EstadisticasJornada()
  +void setPedidosAtendidos(int valor)
  +void setPedidosPendientes(int valor)
  +void setTiempoTotalEspera(int valor)
  +void setComparacionesTotales(int valor)
  +void calcularMedias()
  +int getPedidosAtendidos()
  +int getPedidosPendientes()
  +int getTiempoTotalEspera()
  +double getTiempoMedioEspera()
  +int getComparacionesTotales()
  +String toString()
}

%% ================= RELACIONES / FLUJO =================

Simulation --> CocinaRCCCF : crea / simular()

CocinaRCCCF *-- Cocinero : tiene 1
CocinaRCCCF *-- IPriorityQueuePedidos : usa cola
CocinaRCCCF *-- EstadisticasJornada : genera resumen

IPriorityQueuePedidos <|.. ColaPrioridadPedidos : implementa

CocinaRCCCF --> Pedido : crea
CocinaRCCCF --> TipoPlato : usa (tiempo aleatorio)

Cocinero --> IPriorityQueuePedidos : extraerMin()
Cocinero --> Pedido : procesa

ColaPrioridadPedidos --> Pedido : almacena / ordena

Pedido --> TipoPlato : tipo

CocinaRCCCF --> EstadisticasJornada : rellena datos
