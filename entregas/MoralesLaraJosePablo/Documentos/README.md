# Diagramas UML - Sistema de Gestión de Ludoteca

Código fuente de plantUML aquí


## ️ Diagrama de Clases

Este diagrama muestra la estructura completa del sistema con todas las clases, atributos, métodos y relaciones.

```plantuml

@startuml
' Diagrama de clases de la ludoteca

class Console {
  - scanner : Scanner
  + Console()
  + readString(prompt : String) : String
  + readInt(prompt : String) : int
  + readChar(prompt : String) : char
  + println(mensaje : String) : void
  + print(mensaje : String) : void
}

class Niño {
  - nombre : String
  - edad : int
  + Niño(nombre : String, edad : int)
  + getNombre() : String
  + getEdad() : int
  + presentarse() : void
  + presentarseSinEdad() : void
  + toString() : String
}

class Monitora {
  - MAX_NIÑOS : int <<static, final>>
  - nombre : String
  - cola : Niño[]
  - inicio : int
  - fin : int
  - tamaño : int
  + Monitora(nombre : String)
  + getNombre() : String
  + agregarNiño(niño : Niño) : void
  + sacarNiño() : Niño
  + obtenerNiño(indice : int) : Niño
  + getCantidadNiños() : int
  + estaVacia() : boolean
  + transferirTodosA(destino : Monitora) : void
  + presentarse() : void
  + calcularEdadPromedio() : double
  + contarNiñosPorEdad(edadMinima : int) : int
  + mostrarNiños() : void
}

class Ludoteca {
  - MIN_NIÑOS_JUEGO : int <<static, final>>
  - EDAD_JUEGO_RANA : int <<static, final>>
  - MAX_NIÑOS : int <<static, final>>
  - lydia : Monitora
  - aisha : Monitora
  - dalsy : Monitora
  - backupAisha : Niño[]
  - numBackupAisha : int
  - hayBackup : boolean
  + Ludoteca()
  + simularLlegadaNiño(nombre : String, edad : int) : void
  + registrarNiñoSilencioso(nombre : String, edad : int) : void
  + simularInicioJuego() : void
  + presentacionesGenerales() : void
  + presentacionesPorEdad(edadMinima : int) : void
  + presentacionesPorInicial(letra : char) : void
  + presentacionesPrimerosCinco() : void
  + presentacionesUltimosCinco() : void
  + mostrarConteoAsistencia() : void
  + mostrarEdadPromedio() : void
  + verificarJuegoRana() : void
  + separarParaJuego() : void
  + activarAlarmaIncendios() : void
  + mostrarEstado() : void
  - guardarBackupAisha() : void
  - formatearDecimal(valor : double) : String
  + aishaTieneNiños() : boolean
}

class Mundo {
  - ludoteca : Ludoteca
  - console : Console
  - random : Random
  - nombres : String[]
  + Mundo()
  + ejecutarSimulacion() : void
  - mostrarMenu() : void
  - procesarOpcion(opcion : int) : void
  - opcion3() : void
  - transferirDesdeLydia() : boolean
  - asegurarNiñosAisha() : void
  - opcion1() : void
  - limpiarPantalla() : void
  - ludotecaContarNiñosAisha() : int
  - ludotecaContarNiñosLydia() : int
  - ludotecaContar(monitora : String) : int
  - ludotecaObtenerNiñoAisha(index : int) : Niño
  + main(args : String[]) : void
}

' Relaciones
Ludoteca "1" o-- "1" Monitora : lydia
Ludoteca "1" o-- "1" Monitora : aisha
Ludoteca "1" o-- "1" Monitora : dalsy

Monitora "1" *-- "*" Niño : cola
Ludoteca "1" *-- "*" Niño : backupAisha

Mundo "1" o-- "1" Ludoteca
Mundo "1" o-- "1" Console

@enduml

```

---

## Diagrama de Actividades

Este diagrama representa el flujo de ejecución del programa, mostrando las diferentes opciones del menú y sus acciones.

```plantuml
@startuml
start

:Inicializar Ludoteca;
:Inicializar Console;

repeat
  :Mostrar menú;
  :Leer opción del usuario;
  
  if (opción == 0?) then (sí)
    :Mensaje de despedida;
    stop
  else (no)
    if (opción == 1?) then (sí)
      :Generar cantidad aleatoria de niños;
      repeat
        :Generar nombre y edad aleatorios;
        :Simular llegada del niño;
        :Agregar a cola de Lydia;
      repeat while (¿Hay más niños?)
      
    elseif (opción == 2?) then (sí)
      if (Lydia tiene >= 5 niños?) then (sí)
        :Transferir todos los niños\nde Lydia a Aisha;
      else (no)
        :Mostrar mensaje\ninsuficientes niños;
      endif
      
    elseif (opción == 3?) then (sí)
      if (Aisha no tiene niños?) then (sí)
        :Generar o transferir\nniños a Aisha;
      endif
      :Aisha se presenta;
      :Cada niño se presenta;
      
    elseif (opción == 10?) then (sí)
      :Contar niños >= 5 años;
      if (Mayores > mitad del total?) then (sí)
        :Mensaje: pueden jugar;
      else (no)
        :Mensaje: no pueden jugar;
      endif
      
    elseif (opción == 11?) then (sí)
      :Guardar backup de Aisha;
      while (Aisha no está vacía?)
        :Sacar niño de Aisha;
        if (edad < 5?) then (sí)
          :Agregar a cola de Dalsy;
        else (no)
          :Devolver a cola de Aisha;
        endif
      endwhile
      
    elseif (opción == 12?) then (sí)
      :¡ALARMA DE INCENDIOS!;
      :Transferir todos los niños\nde Dalsy a Lydia;
      :Transferir todos los niños\nde Aisha a Lydia;
      :Borrar backup;
      
    else (otras opciones)
      :Procesar opción\ncorrespondiente;
    endif
    
    :Presionar ENTER para continuar;
  endif

repeat while (continuar?)

stop

@enduml
```

---

## Diagrama de Estados

Este diagrama muestra los diferentes estados por los que puede pasar un niño en el sistema.

```plantuml
@startuml
[*] --> Llegada

state "Llegada a Ludoteca" as Llegada
state "Cola de Lydia" as ColaLydia
state "Cola de Aisha" as ColaAisha
state "Cola de Dalsy" as ColaDalsy
state "En Juego" as EnJuego
state "Evacuación" as Evacuacion

Llegada --> ColaLydia : Niño llega\n(simularLlegadaNiño)

ColaLydia --> ColaAisha : ≥5 niños en Lydia\n(simularInicioJuego)
ColaLydia --> Evacuacion : Alarma incendios

state ColaAisha {
  [*] --> Esperando
  Esperando --> Presentandose : Presentaciones
  Presentandose --> Esperando
  Esperando --> VerificandoEdad : Verificar juego rana
  VerificandoEdad --> Esperando : No cumplen condición
  VerificandoEdad --> ListoParaJugar : >50% tiene ≥5 años
}

ColaAisha --> EnJuego : Separación por edad\n(separarParaJuego)
ColaAisha --> ColaDalsy : Niño < 5 años\n(separarParaJuego)
ColaAisha --> Evacuacion : Alarma incendios

EnJuego --> ColaAisha : Juego termina\n(restaurar backup)

ColaDalsy --> Evacuacion : Alarma incendios

state Evacuacion {
  [*] --> TransfiriendoALydia
  TransfiriendoALydia --> ListoParaEvacuar
  ListoParaEvacuar --> [*]
}

Evacuacion --> [*] : Evacuar en orden

@enduml
```

---

## Diagrama de Secuencia

Este diagrama detalla las interacciones entre objetos para los escenarios más importantes.

```plantuml
@startuml
actor Usuario
participant "Mundo" as Mundo
participant "Ludoteca" as Ludo
participant "Aisha\n:Monitora" as Aisha
participant "Dalsy\n:Monitora" as Dalsy
participant "Niño" as Nino

== Verificación de condiciones para juego ==
Usuario -> Mundo: seleccionar opción 10
Mundo -> Ludo: verificarJuegoRana()
Ludo -> Aisha: getCantidadNiños()
Aisha --> Ludo: totalNiños
Ludo -> Aisha: contarNiñosPorEdad(5)
loop para cada niño
  Aisha -> Nino: getEdad()
  Nino --> Aisha: edad
end
Aisha --> Ludo: niñosMayores
alt niñosMayores > totalNiños/2
  Ludo --> Usuario: "¡Pueden jugar!"
else
  Ludo --> Usuario: "No pueden jugar todavía"
end

== Separación de niños para el juego ==
Usuario -> Mundo: seleccionar opción 11
Mundo -> Ludo: separarParaJuego()
Ludo -> Ludo: guardarBackupAisha()
note right: Guarda posiciones originales

loop mientras Aisha no esté vacía
  Ludo -> Aisha: sacarNiño()
  Aisha --> Ludo: niño
  Ludo -> Nino: getEdad()
  Nino --> Ludo: edad
  
  alt edad < 5
    Ludo -> Dalsy: agregarNiño(niño)
    note right: Niño va a Dalsy
  else edad >= 5
    Ludo -> Aisha: agregarNiño(niño)
    note right: Niño regresa a Aisha\npara jugar
  end
end

Ludo --> Usuario: "Separación completada"

== Alarma de incendios ==
Usuario -> Mundo: seleccionar opción 12
Mundo -> Ludo: activarAlarmaIncendios()
note over Ludo: ¡PROTOCOLO DE EMERGENCIA!

Ludo -> Dalsy: transferirTodosA(lydia)
loop mientras Dalsy no esté vacía
  Dalsy -> Dalsy: sacarNiño()
  participant "Lydia\n:Monitora" as Lydia
  Dalsy -> Lydia: agregarNiño(niño)
end

Ludo -> Aisha: transferirTodosA(lydia)
loop mientras Aisha no esté vacía
  Aisha -> Aisha: sacarNiño()
  Aisha -> Lydia: agregarNiño(niño)
end

Ludo -> Ludo: hayBackup = false
Ludo --> Usuario: "Todos en Lydia\nlistos para evacuar"

@enduml
```

---