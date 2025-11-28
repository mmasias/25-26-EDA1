```mermaid
classDiagram
    class Simulacion {
        +iniciar()
    }

    class Pedido {
        +tipo
        +tiempoPreparacion
    }

    class Cocinero {
        +agregarPedido(Pedido)
        +getPedidoActual()
    }

    class ArbolPedidos {
        +insertar(Pedido)
        +extraerMinimo()
    }

    Simulacion --> Pedido : crea pedido
    Simulacion --> Cocinero : entrega pedido
    Cocinero --> ArbolPedidos : inserta pedido (ordenado por menor tiempo de preparación)
    ArbolPedidos --> Pedido : mantiene orden por tiempo
