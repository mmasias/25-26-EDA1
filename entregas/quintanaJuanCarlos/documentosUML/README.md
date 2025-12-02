```mermaid
classDiagram
direction LR

%% ===================== CLASES =====================

class Simulation {
  +main(String[] args) void
}

class CocinaRCCCF {
  - int tiempoActual
  - int duracion
  - double probLlegada
  - ArbolPedidos arbol
  - Cocinero cocinero
  - EstadisticasJornada estadisticas

  +CocinaRCCCF(int duracion, double probLlegada)
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
  +void procesarMinuto(ArbolPedidos arbol, int tiempoActual)
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

class ArbolPedidos {
  - NodoPedido raiz
  - int numElementos

  +ArbolPedidos()
  +void insertar(Pedido p)
  +Pedido extraerMin()
  +boolean estaVacia()
  +int tamano()
}

class NodoPedido {
  - Pedido pedido
  - NodoPedido izq
  - NodoPedido der

  +NodoPedido(Pedido pedido)
  +Pedido getPedido()
  +NodoPedido getIzq()
  +NodoPedido getDer()
  +void setIzq(NodoPedido n)
  +void setDer(NodoPedido n)
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

%% ===================== RELACIONES / FLUJOS =================

Simulation --> CocinaRCCCF : crea / simular()

CocinaRCCCF *-- Cocinero : tiene
CocinaRCCCF *-- ArbolPedidos : gestiona pedidos
CocinaRCCCF *-- EstadisticasJornada : acumula datos

CocinaRCCCF --> Pedido : crea
CocinaRCCCF --> TipoPlato : usa (tiempos aleatorios)

Cocinero --> ArbolPedidos : extraerMin()/insertar()
Cocinero --> Pedido : procesa

ArbolPedidos *-- NodoPedido : nodos del árbol
NodoPedido --> Pedido : almacena

Pedido --> TipoPlato : tipo

CocinaRCCCF --> EstadisticasJornada : rellena resumen
