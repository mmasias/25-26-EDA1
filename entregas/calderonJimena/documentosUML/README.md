@startuml
  class Simulation {
    + static void main(String[] args)
  }

  class Restaurant {
    - static final double HORA_APERTURA = 9.0
    - static final double HORA_CIERRE = 21.0
    - static final double UN_MINUTO_EN_HORAS = 1.0 / 60.0
    - static final double PROBABILIDAD_LLEGADA_PEDIDO = 0.4
    - static final int TOTAL_MINUTOS_JORNADA = 720
    - static final String[] TIPOS_PLATOS_DISPONIBLES
    - static final int[][] RANGOS_TIEMPO_PREPARACION

    - ArbolBinario arbolPedidosPendientes
    - Pedido pedidoEnProcesamiento
    - int cantidadPedidosAtendidos
    - int acumuladorTiempoEspera
    - int contadorMinutoSimulacion
    - Random generadorNumerosAleatorios
    - Scanner lectorEntradaUsuario

    + void run()
    - void validarLlegadaNuevoPedido()
    - void procesarColaDeCocina()
    - void mostrarEstadoActualDelSistema()
    - void mostrarResumenFinalDeLaJornada()
  }

  class ArbolBinario {
    - Nodo raiz
    - int totalComparaciones
    - int cantidadPedidos

    + void insertar(Pedido nuevoPedido)
    + Pedido extraerMinimo()
    + boolean estaVacio()
    + int obtenerCantidadPedidos()
    + int obtenerTotalComparaciones()
    - Nodo insertarDesdeRaiz(Nodo nodoActual, Pedido nuevoPedido)
  }

  class Nodo {
    + Pedido pedido
    + Nodo hijoIzquierdo
    + Nodo hijoDerecho
    + Nodo(Pedido pedido)
  }

  class Pedido {
    + String tipoPlato
    + int tiempoTotalPreparacion
    + int tiempoRestantePreparacion
    + int minutoLlegada
    + Pedido(String tipoPlato, int tiempoTotalPreparacion, int minutoLlegada)
    + int calcularTiempoEspera(int minutoActual)
  }


' Relaciones
Simulation --> Restaurant : crea
Restaurant --> ArbolBinario : contiene
Restaurant -->  Pedido : procesa actual
ArbolBinario --> Nodo : raíz
Nodo -->Nodo : hijoIzquierdo
Nodo --> Nodo : hijoDerecho
Nodo --> Pedido : referencia a
ArbolBinario ..> Pedido : inserta y extrae


@enduml