```mermaid
classDiagram
    class Simulacion {
        +iniciar()
        -cocinero : Cocinero
    }

    class Pedido {
        +tipo
        +tiempoPreparacion
        +tiempoRestante
        +reducirTiempo()
    }

    class Cocinero {
        -actual : Pedido
        -arbol : ArbolPedidos
        +agregarPedido(Pedido)
        +procesarMinuto()
        +getPedidoActual()
    }

    class ArbolPedidos {
        -raiz : Nodo
        +insertar(Pedido)
        +consultarMinimo()
        +extraerMinimo()
    }

    class Nodo {
        +pedido : Pedido
        +left : Nodo
        +right : Nodo
    }

    Simulacion --> Pedido : crea
    Simulacion --> Cocinero : usa
    Cocinero --> Pedido : tiene actual
    Cocinero --> ArbolPedidos : inserta/extrae
    ArbolPedidos --> Nodo : compuesto por
    Nodo --> Pedido : contiene
