# Propuesta para el sistema RCCCF

El RCCCF requiere un sistema que administre pedidos que llegan de manera aleatoria y deben ser tratados conforme a su tiempo de preparación, no al orden en el que llegan.  
Así que la estructura de datos principal tiene que posibilitar de manera eficaz la inserción de pedidos y la obtención del que tarda menos tiempo prepararse.

## Comparación de estructuras de datos

**Cola FIFO**  
No sirve porque atiende en orden de llegada y aquí no queremos atender por orden de llegada, sino según el tiempo de preparación.

**Pila (LIFO)**  
Tampoco sirve porque atiende el último que entra primero, y en este sistema necesitamos otro criterio distinto.

**Lista**  
Es la opción que mejor encaja dentro de lo visto en clase, porque nos permite insertar los pedidos donde queramos.  
De esta forma, en el código podemos decidir en qué posición colocar cada pedido según su tiempo de preparación y así controlar cuál será el siguiente pedido que se atienda.

---

Entonces, la lista es la estructura adecuada porque nos da la flexibilidad necesaria para organizar los pedidos según lo que necesita el sistema.

---

# Diagrama UML del sistema

## Diagrama Sistema de Gestión de Pedidos del RCCCF

```plantuml
    @startuml diagramaReto005_v2
    title Diagrama Sistema de Gestión de Pedidos del RCCCF (con Lista)

    class Simulacion {
        + main(args : String[]) : void
    }

    class Restaurante {
        - HORA_APERTURA : double
        - HORA_CIERRE : double
        - MINUTO : double
        - PROBABILIDAD_LLEGADA : double
        - cocinero : Cocinero
        - generadorPedidos : GeneradorPedidos
        - listaPedidos : ListaPedidos
        - minutoActual : int
        - pedidosAtendidos : int
        - tiempoEsperaTotal : long
        + Restaurante()
        + ejecutar() : void
        - validarLlegada() : void
        - recibirPedido(pedido : Pedido) : void
        - seleccionarPedido() : Pedido
        - procesarCocina() : Pedido
        - mostrarResumen() : void
    }

    class Cocinero {
        - pedidoActual : Pedido
        + asignarPedido(pedido : Pedido, minutoActual : int) : void
        + estaLibre() : boolean
        + cocinarDuranteUnMinuto() : Pedido
        + getPedidoActual() : Pedido
    }

    class GeneradorPedidos {
        - probabilidadLlegada : double
        - generadorAleatorio : Random
        + GeneradorPedidos(probabilidadLlegada : double)
        + generarPedido(minutoActual : int) : Pedido
    }

    class ListaPedidos {
        - cabeza : NodoPedido
        - tamaño : int
        + ListaPedidos()
        + insertarOrdenado(pedido : Pedido) : void
        + extraerPrimero() : Pedido
        + estaVacia() : boolean
        + tamaño() : int
    }

    class NodoPedido {
        - dato : Pedido
        - siguiente : NodoPedido
        + NodoPedido(dato : Pedido)
        + getDato() : Pedido
        + getSiguiente() : NodoPedido
        + setSiguiente(siguiente : NodoPedido) : void
    }

    class Pedido {
        - siguienteId : int <<static>>
        - comparaciones : long <<static>>
        - identificador : int
        - tipoPlato : TipoPlato
        - tiempoTotalPreparacion : int
        - tiempoRestantePreparacion : int
        - minutoLlegada : int
        - minutoInicioPreparacion : Integer
        + Pedido(tipoPlato : TipoPlato, tiempoTotalPreparacion : int, minutoLlegada : int)
        + getTipoPlato() : TipoPlato
        + getTiempoTotalPreparacion() : int
        + getTiempoRestantePreparacion() : int
        + reducirTiempoRestante() : void
        + calcularTiempoEspera() : int
        + setMinutoInicioPreparacion(minuto : int) : void
        + getComparaciones() : long <<static>>
        + compareTo(otro : Pedido) : int
        + toString() : String
    }

    class TipoPlato {
        - nombre : String
        - tiempoMinimo : int
        - tiempoMaximo : int
        + TipoPlato(nombre : String, tiempoMinimo : int, tiempoMaximo : int)
        + getNombre() : String
        + obtenerTiempoAleatorio(r : Random) : int
        + obtenerAleatorio(r : Random) : TipoPlato <<static>>
        {static} TIPOS : TipoPlato[]
    }

    Simulacion --> Restaurante
    Restaurante --> Cocinero : tiene
    Restaurante --> GeneradorPedidos : usa
    Restaurante --> ListaPedidos : gestiona
    GeneradorPedidos --> Pedido : crea
    GeneradorPedidos --> TipoPlato : usa
    ListaPedidos --> NodoPedido : encadena
    NodoPedido --> Pedido : contiene
    Cocinero --> Pedido : cocina
    Pedido --> TipoPlato : usa

    @enduml
```

![Diagrama UML RCCCF](../documentosUML/diagramaReto005.svg)

