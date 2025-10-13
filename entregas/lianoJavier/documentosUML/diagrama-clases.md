# Diagrama de Clases

```mermaid
classDiagram
class Mundo {
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
    +recibir(niño: Niño)
    +actualizar()
    +imprimirEstado()
}
class Tiempo {
    -minutos: int
    +Tiempo(horas: int, minutos: int)
    +terminado(): boolean
    +siguiente()
    +imprimir()
    +getMinutos(): int
}
class Nino["Niño"] {
    -nombre: String
    -pizarrin: Pizarra
    +Niño(nombre: String)
    +getNombre(): String
    +coger(pizarrin: Pizarra)
    +enseñarPizarrin(niño: Niño)
    +escribirEnPizarrin(palabra: String)
    +leer(pizarrin: Pizarra): String
    +escribirEnPizarra()
}
class Monitor {
    <<abstract>>
    -nombre: String
    -colaNiños: Cola
    +Monitor(nombre: String)
    +abstract imprimirEstado()
    +pideNiño(otroMonitor: Monitor)
    +getCola(): Cola
    +hayNiños(): boolean
    +getCantidad(): int
}
class Recepcionista {
    +Recepcionista(nombre: String)
    +recibir(niño: Niño)
    +imprimirEstado()
    +pideNiño(otroMonitor: Monitor)
}
class Directriz {
    -partida: Partida
    -pizarrin: Pizarra
    +Directriz(nombre: String)
    +estaJugando(): boolean
    +juegoTerminado(): boolean
    +reset()
    +juega()
    +siguienteRonda()
    +imprimirEstado()
    +inventarPalabra(): String
    +enseñarPizarrin(niño: Niño)
    +escribirEnPizarrin(palabraSecreta: String)
    +pideNiño(otroMonitor: Monitor)
    +estaCompleta(): boolean
}
class Cola {
    -niños: Niño[]
    +Cola()
    +poner(niño: Niño
    +sacar(): Niño
    +hayNiños(): boolean
    +toArray(): Niño[]
    +getPosicion(posicion: int): Niño
    +getCantidad(): int
}
class Partida {
    -directriz: Directriz
    -jugadores: Cola
    -posicion: int
    -jugando: boolean
    +Partida(directriz: Directriz, cola: Cola)
    +iniciar()
    +siguienteRonda()
    +isTerminada(): boolean
    +reset()
    +getMaximoJugadores(): int
}
class Pizarra {
    -palabraSecreta: String
    +getTexto(): String
    +escribir(palabra: String)
}
class Console {
    +separador(divisor: String)
    +imprimir(string: String)
    +imprimirLinea()
    +espacio(): String
    +waitUser()
    +clear()
}
Mundo *-- Ludoteca
Mundo *-- Tiempo
Ludoteca *-- Recepcionista
Ludoteca *-- Directriz
Ludoteca o-- Nino
Directriz *-- Partida
Directriz *-- Pizarra
Directriz *-- Cola
Recepcionista *-- Cola
Monitor <|-- Recepcionista
Monitor <|-- Directriz
Nino o-- Pizarra
Partida *-- Directriz
Partida *-- Cola