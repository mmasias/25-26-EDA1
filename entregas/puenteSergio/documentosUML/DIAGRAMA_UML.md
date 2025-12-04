classDiagram
    %% Definición de clases y sus miembros
    class SimulacionRCCCF {
        -int DURACION_SIMULACION$
        -double PROBABILIDAD_LLEGADA$
        -Random aleatorio$
        +main(String[] args)$
        -generarPedidoAleatorio(double tiempoLlegada)$ Pedido
        -imprimirResumen(Restaurante restaurant)$
    }

    class Restaurante {
        -ArbolPedidos cola
        -Pedido pedidoActual
        -int pedidosCompletados
        -int tiempoTotalEspera
        -long contadorComparaciones
        +Restaurante()
        +anadirPedido(Pedido pedido)
        +tic(double tiempoActual)
        +getPedidoActual() Pedido
        +getTamanoCola() int
        +getContadorComparaciones() long
        +getPedidosCompletados() int
        +getContadorPedidosPendientes() int
    }

    class ArbolPedidos {
        -Nodo raiz
        -int tamano
        +ArbolPedidos()
        +insertar(Pedido pedido)
        -insertarRec(Nodo nodo, Pedido pedido) Nodo
        +eliminarMinimo() Pedido
        +estaVacio() boolean
        +getTamano() int
    }

    class Nodo {
        -Pedido pedido
        -Nodo izquierdo
        -Nodo derecho
        +Nodo(Pedido pedido)
        +getPedido() Pedido
        +getIzquierdo() Nodo
        +setIzquierdo(Nodo izquierdo)
        +getDerecho() Nodo
        +setDerecho(Nodo derecho)
    }

    class Pedido {
        -int contadorId$
        -int id
        -String tipo
        -int tiempoTotalPreparacion
        -int tiempoRestante
        -double tiempoLlegada
        +Pedido(String tipo, int tiempoPrep, double tiempoLlegada)
        +disminuirTiempoRestante()
        +estaTerminado() boolean
        +compareTo(Pedido otro) int
        +aCadenaEstado() String
        +toString() String
    }

    %% --- Relaciones entre clases ---

    SimulacionRCCCF ..> Restaurante : «usa»
    SimulacionRCCCF ..> Pedido : «crea»

    Restaurante *-- ArbolPedidos : «contiene»
    Restaurante o-- Pedido : «gestiona»

    ArbolPedidos *-- Nodo : «compuesto por»
    
    Nodo o-- Nodo : «izquierdo/derecho»
    Nodo *-- Pedido : «contiene»
