classDiagram
    direction TB

    %% ========================
    %% CLASE Pedido
    %% ========================
    class Pedido {
        - String tipo
        - int tiempoPreparacion
        - int tiempoRestante
        - int tiempoLlegada
        + Pedido(String tipo, int tiempoPreparacion, int tiempoLlegada)
        + getTiempoRestante() int
        + reducirTiempo()
        + getTipo() String
        + getTiempoPreparacion() int
        + getTiempoLlegada() int
    }

    %% ========================
    %% CLASE ListaEnlazada
    %% ========================
    class ListaEnlazada {
        - Nodo cabeza
        - int tamaño
        + ListaEnlazada()
        + agregar(Pedido)
        + obtener(int) Pedido
        + eliminar(int)
        + size() int
        + estaVacia() boolean
        + indiceMinPreparacion() int
    }

    %% ========================
    %% CLASE Nodo
    %% ========================
    class Nodo {
        - Pedido dato
        - Nodo siguiente
        + Nodo(Pedido dato)
    }

    %% ========================
    %% CLASE Pila (para registrar historial, opcional)
    %% ========================
    class Pila {
        - Pedido[] elementos
        - int tope
        + Pila(int capacidad)
        + apilar(Pedido)
        + desapilar() Pedido
        + cima() Pedido
        + estaVacia() boolean
    }

    %% ========================
    %% CLASE PedidoGenerator
    %% ========================
    class PedidoGenerator {
        + static Pedido generarPedido(int minutoActual)
        + static String tipoAleatorio()
        + static int tiempoPreparacionPara(String tipo)
    }

    %% ========================
    %% CLASE Simulation
    %% ========================
    class Simulation {
        - ListaEnlazada colaPedidos
        - Pedido pedidoActual
        - int comparaciones
        - int tiempoEsperaTotal
        - int pedidosAtendidos
        + Simulation()
        + iniciar()
        + llegarPedido(int minutoActual)
        + seleccionarMinimo()
        + procesarMinuto()
        + resumenFinal()
    }

    %% RELACIONES
    ListaEnlazada --> Nodo
    ListaEnlazada --> Pedido
    Pila --> Pedido
    Simulation --> ListaEnlazada
    Simulation --> Pedido
    Simulation --> Pila
    PedidoGenerator --> Pedido
