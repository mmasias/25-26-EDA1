# Carpeta de entregas
La propuesta es la siguiente:

@startuml
left to right direction

class Pedido {
  - String tipo
  - int tiempoPreparacion
  - int tiempoRestante
  - int tiempoLlegada
  + Pedido(String, int, int)
  + int calcularTiempoEspera(int minutoInicio)
  + boolean estaTerminado()
  + getters()
}

class ColaPedidos {
  - List<Pedido> pendientes
  + void agregar(Pedido p)
  + Pedido extraerMinimoPorTiempoPreparacion(int minutoActual, SimulacionContexto ctx)
  + boolean estaVacia()
  + int size()
}

class Cocinero {
  - Pedido pedidoEnProceso
  + void avanzarMinuto()
  + Pedido finalizarSiTerminado()
  + void asignarPedido(Pedido p)
  + boolean estaLibre()
  + Pedido getPedidoEnProceso()
}

class SimulacionContexto {
  - int duracionJornada = 120
  - double probabilidadLlegada = 0.4
  - int pedidosAtendidos = 0
  - int comparacionesTotales = 0
  - int tiempoTotalEspera = 0
  - Random generador
}

class SimuladorRCCCF {
  - List<Cocinero> cocineros
  - ColaPedidos cola
  - SimulacionContexto contexto
  + void inicializar()
  + void simularMinuto(int minuto)
  + void ejecutar()
  + void mostrarResumen()
}

' Relaciones
SimuladorRCCCF --> "1" SimulacionContexto : usa
SimuladorRCCCF --> "1" ColaPedidos : gestiona
SimuladorRCCCF --> "1..*" Cocinero : coordina
ColaPedidos --> "0..*" Pedido : contiene
Cocinero --> "0..1" Pedido : procesa

@enduml
