@startuml Ludoteca_Diagrama_Clases
title Ludoteca - Diagrama de Clases

skinparam classAttributeIconSize 0

class Universo {
  +main(args: String[]): void
}

class Mundo {
  -ludoteca: Ludoteca
  -scanner: java.util.Scanner
  -mensaje: Mensaje
  +ejecutarSimulacion(): void
  -- Menú y acciones --
  -mostrarMenu(): void
  -procesarOpcion(opcion: int): void
  -simularLlegada(): void
  -intentarInicioJuego(): void
  -presentacionGeneral(): void
  -presentacionPorEdad(): void
  -presentacionPorInicial(): void
  -primerosCinco(): void
  -ultimosCinco(): void
  -conteoAsistencia(): void
  -edadPromedio(): void
  -juegoRana(): void
  -separarPorEdad(): void
  -alarmaIncendios(): void
}

class Ludoteca {
  -lydia: Monitor
  -aisha: Monitor
  -dalsy: Monitor
  +getLydia(): Monitor
  +getAisha(): Monitor
  +getDalsy(): Monitor
  +mostrarEstado(): void
  +totalNiños(): int
}

class Monitor {
  {static} CAPACIDAD_MAXIMA: int
  {static} CANTIDAD_PRESENTACION_LISTAS: int
  -nombre: String
  -niños: Niño[]
  -cantidad: int
  +getNombre(): String
  +tieneNiños(): boolean
  +getCantidad(): int
  +recibirNiño(nino: Niño): void
  +transferirNiños(destino: Monitor): void
  +mostrarNiños(): void
  +getNiñoEn(indice: int): Niño
  +removerEn(indice: int): Niño
  +edadPromedio(): double
  +contarMayoresDe(edadMinima: int): int
  +mostrarPrimerosCinco(): void
  +mostrarUltimosCinco(): void
}

class Niño {
  -nombre: String
  -edad: int
  -pizarrin: Pizarra
  +getNombre(): String
  +getEdad(): int
  +presentarse(): void
  +presentarseNombre(): void
  +recibirPizarrin(pizarra: Pizarra): void
  +limpiarPizarrin(): void
}

class Pizarra {
  -mensaje: String
  +escribirMensaje(mensaje: String): void
  +leerMensaje(): String
  +limpiar(): void
}

class Mensaje {
  +mensaje(texto: String): void
  +mensajeLn(texto: String): void
}

Universo --> Mundo : crea
Mundo --> Ludoteca : usa
Mundo --> Mensaje : usa
Ludoteca *-- Monitor : lydia/aisha/dalsy
Monitor *-- "0..50" Niño
Niño o-- "0..1" Pizarra

note right of Ludoteca
  Lydia: recibe niños inicialmente
  Aisha: gestiona la cola principal
  Dalsy: se encarga de menores de 5 años
end note

note bottom of Mundo
  Reglas (constantes):
  - Mín. niños para iniciar juego: 5
  - Edad mínima juego de la rana: 5
  - Mayoría simple: > total/2
end note

@enduml