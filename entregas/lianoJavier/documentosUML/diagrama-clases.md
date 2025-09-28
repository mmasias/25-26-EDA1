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
    +recibir(niño: Nino["Niño"])
    +recibir(monitor: Monitor)
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
    +Monitor(nombre: String)
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
Ludoteca o-- Nino
Ludoteca o-- Monitor