@startuml
  class Simulation {
    + static void main(String[] args)
  }

  class Restaurant {
    - final double OPENING_TIME = 9.0
    - final double CLOSING_TIME = 21.0
    - final double MINUTE = 1.0 / 60.0
    - final double PROBABILITY_ARRIVAL = 0.4
    - final int TOTAL_MINUTES = 720
    - static final String[] PLATOS
    - static final int[][] RANGOS
    - MinHeap cola
    - Pedido pedidoEnProceso
    - int pedidosAtendidos
    - int tiempoTotalEspera
    - Random random
    - Scanner scanner
    + void run()
    - void validateArrival(int minuto)
    - void processQueue(int minuto)
    - void showSummary(int minuto)
    - void imprimirResumenFinal()
  }

  class MinHeap {
    - ListaPedidos heap
    - int comparaciones
    + void insertar(Pedido p)
    + Pedido extraerMin()
    + boolean estaVacia()
    + int size()
    + int getComparaciones()
    - void flotar(int i)
    - void hundir(int i)
    - void intercambiar(int i, int j)
  }

  class ListaPedidos {
    - Pedido[] datos
    - int tamaño
    - int capacidad
    + void agregar(Pedido p)
    + Pedido obtener(int i)
    + void establecer(int i, Pedido p)
    + Pedido eliminarUltimo()
    + boolean estaVacia()
    + int tamaño()
    - void redimensionar()
  }

  class Pedido {
    - String tipo
    - int tiempoPreparacion
    - int tiempoRestante
    - int minutoLlegada
    + String getTipo()
    + int getTiempoPreparacion()
    + int getTiempoRestante()
    + void decrementarTiempo()
    + int getMinutoLlegada()
    + int getTiempoEspera(int minutoActual)
  }


' Relaciones
Simulation --> Restaurant : crea
Restaurant --> MinHeap : contiene
Restaurant --> "1" Pedido : procesa actual
MinHeap --> ListaPedidos : usa para almacenamiento interno
MinHeap ..> Pedido : opera con
Restaurant ..> Pedido : crea y usa
ListaPedidos ..> Pedido : almacena

@enduml