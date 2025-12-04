### UML del proyecto RCCCF (Mermaid)

```mermaid
classDiagram
    class Plato {
        - String nombre
        - int tiempoPrep
        + Plato(String nombre, int tiempoPrep)
        + String getNombre()
        + int getTiempoPrep()
        + String toString()
    }

    class Pedido {
        - Plato plato
        - int tiempoRestante
        - int momentoLlegada
        + Pedido(Plato plato, int minuto)
        + String getNombre()
        + int getTiempoRestante()
        + void reducirTiempo()
        + int getMomentoLlegada()
        + String toString()
    }

    class NodoPedido {
        - Pedido pedido
        - NodoPedido izq
        - NodoPedido der
        + NodoPedido(Pedido pedido)
    }

    class ArbolPedidos {
        - NodoPedido raiz
        - int comparaciones
        + void insertar(Pedido p)
        + Pedido extraerMinimo()
        + boolean estaVacio()
        + int getComparaciones()
    }

    class Registro {
        - List~Pedido~ hechos
        + void registrar(Pedido p)
        + int totalAtendidos()
        + void mostrarResumen(int pendientes, int comps, int tiempoTotalEspera)
    }

    class Cocinero {
        - ArbolPedidos arbol
        - Registro registro
        - Pedido actual
        - int tiempoEsperaTotal
        + void nuevoPedido(Pedido p)
        + void procesarMinuto(int minuto)
        + void resumenFinal()
        + int pedidosPendientes()
    }

    class Main {
        + static void main(String[] args)
        + static Plato generarPlatoAleatorio(Random r)
    }

    Plato "1" --> "1" Pedido : contiene
    Pedido "1" --> "1" NodoPedido : envuelve
    NodoPedido "1" --> "1..*" ArbolPedidos : contiene
    ArbolPedidos "1" --> "1" Cocinero : usa
    Registro "1" --> "1" Cocinero : registra
    Cocinero "1" --> "1" Main : instancia
