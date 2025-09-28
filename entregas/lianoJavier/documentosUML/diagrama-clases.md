# Diagrama de Clases

```mermaid
classDiagram
class Mundo {
    -VERSION: String
    -ludoteca: Ludoteca
    -tiempo: Tiempo
    +main(args: String[])
    +simular()
    -nombrar(): String
    -llegaNiño(tiempo: Tiempo): boolean
    -presentacion()
}
class Ludoteca {
    -lydia: Recepcionista
    -aisha: Directriz
    +Ludoteca()
    +recibir(niño: Nino)
    +actualizar()
    +imprimirEstado()
}
class Tiempo {
    +Tiempo(horas: int, minutos: int)
    +terminado(): boolean
    +siguiente()
    +imprimir()
    +getMinutos(): int
}
class Nino["Niño"] {
    +Niño(nombre: String)
    +getNombre(): String
}
class Monitor {
    <<abstract>>
    +Monitor(nombre: String)
    abstract imprimirEstado()
}
class Recepcionista {
    +Recepcionista(nombre: String)
    +recibir(niño: Nino)
    protected imprimirEstado()
}
class Directriz {
    +Directriz(nombre: String)
    +getCola(): ColaNiños
    +pideNiño(lydia: Recepcionista)
    +estaJugando(): boolean
    +juega()
    +siguienteRonda()
    protected imprimirEstado()
}
class ColaNiños {
    +estaCompleta(): boolean
}
class Console {
    +separador(s: String)
    +imprimir(s: String)
    +espacio(): String
    +waitUser()
    +clear()
}
Mundo *-- Ludoteca
Mundo *-- Tiempo
Ludoteca *-- Recepcionista
Ludoteca *-- Directriz
Ludoteca o-- Nino
Directriz *-- ColaNiños
Recepcionista o-- Nino
Directriz --> Recepcionista
Monitor <|-- Recepcionista
Monitor <|-- Directriz