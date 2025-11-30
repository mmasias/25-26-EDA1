# Diagrama de Clases - Ludoteca

```mermaid
classDiagram
    class Ludoteca {
        +TIEMPO_ABIERTO_HORAS: int
        +abrir()
        -estaAbierto(Tiempo): boolean
    }
    class Main {
        +main(String[])
    }
    class Tiempo {
        -minutosTotales: int
        +horas(): int
        +pasarMinuto()
        +minutos(): int
        +imprimir()
    }
    class Tutor {
        #nombre: String
        +Tutor(String)
        +imprimirLista()
    }
    class Recepcionista {
        -esperando: ColaNinho
        -random: Random
        +recibeNiño(Tiempo)
        +tieneEsperando(): boolean
        +darNinho(): Ninho
        +imprimirLista()
    }
    class Animadora {
        -fila: Fila
        -palabraInicial: char[]
        -random: Random
        +getFila(): Fila
        +pideNinho(Recepcionista)
        +limpia(Pizarra)
        +pideLimpiarPizarrines()
        +muestraPizarrin(Ninho)
        +escribePalabra()
        +sientaNinhos(): Ninho[]
        +imprimirLista()
    }
    class Fila {
        -cola: ColaNinho
        -size: int
        +agregar(Ninho)
        +estaCompleto(): boolean
        +toArray(): Ninho[]
        +imprimirLista()
        +vaciar()
    }
    class ColaNinho {
        -frente: Nodo
        -ultimo: Nodo
        +enqueue(Ninho)
        +dequeue(): Ninho
        +peek(): Ninho
        +estaVacia(): boolean
        +imprimir()
    }
    class Juego {
        -iniciado: boolean
        -posicion: int
        +estaIniciado(): boolean
        +inicia()
        +termina()
        +getPosicion(): int
        +siguiente()
    }
    class Ninho {
        -pizarrin: char[]
        -random: Random
        +recibeMensaje(char[])
        +getMensaje(): String
        +muestraPizarrin(Ninho)
        +escribe(Pizarra)
        +limpiarPizarrin()
    }
    class Pizarra {
        -mensaje: char[]
        +setMensaje(char[])
        +limpiar()
        +imprimir()
    }
    Main ..> Ludoteca : calls
    Ludoteca ..> Tiempo : uses
    Ludoteca ..> Recepcionista : creates
    Ludoteca ..> Animadora : creates
    Ludoteca ..> Pizarra : creates
    Ludoteca ..> Juego : uses
    Ludoteca ..> Ninho : creates array
    Tutor <|-- Recepcionista : inherits
    Tutor <|-- Animadora : inherits
    Recepcionista *-- ColaNinho : has
    Recepcionista ..> Tiempo : uses
    Recepcionista ..> Ninho : creates
    Animadora *-- Fila : has
    Animadora ..> Recepcionista : uses
    Animadora ..> Pizarra : uses
    Animadora ..> Ninho : uses
    Fila *-- ColaNinho : has
    Fila ..> Ninho : uses
    ColaNinho *-- Nodo : has
    Ninho ..> Pizarra : uses
    Ninho ..> Ninho : uses