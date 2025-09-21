# Diagrama de Workflow - Ludoteca

```mermaid
sequenceDiagram
    participant Main
    participant Ludoteca
    participant Tiempo
    participant Recepcionista as Lydia
    participant Animadora as Aisha
    participant Juego
    participant Ninho
    participant Pizarra

    Main->>Ludoteca: abrir()
    Ludoteca->>Tiempo: new Tiempo()
    Ludoteca->>Recepcionista: new Recepcionista()
    Ludoteca->>Animadora: new Animadora()
    Ludoteca->>Pizarra: new Pizarra()
    Ludoteca->>Juego: new Juego()

    loop Mientras está abierto (2 horas)
        Ludoteca->>Tiempo: imprimir()
        Ludoteca->>Recepcionista: recibeNiño(tiempo)
        Ludoteca->>Recepcionista: imprimirLista()
        Ludoteca->>Animadora: imprimirLista()

        alt La fila de Aisha no está completa
            Ludoteca->>Animadora: pideNinho(Lydia)
            Animadora->>Recepcionista: tieneEsperando()?
            Recepcionista-->>Animadora: boolean
            Animadora->>Recepcionista: darNinho()
            Recepcionista-->>Animadora: Ninho
            Animadora->>Fila: agregar(Ninho)
        else El juego no ha comenzado
            Ludoteca->>Juego: inicia()
            Ludoteca->>Animadora: sientaNinhos()
            Animadora-->>Ludoteca: Ninho[]
            Ludoteca->>Animadora: limpia(pizarra)
            Animadora->>Pizarra: limpiar()
            Ludoteca->>Animadora: pideLimpiarPizarrines()
        else El juego está en curso
            alt Es el primer turno
                Ludoteca->>Animadora: escribePalabra()
                Ludoteca->>Animadora: muestraPizarrin(primerNinho)
                Animadora->>Ninho: recibeMensaje(palabra)
            else Es un turno intermedio
                Ludoteca->>Ninho: muestraPizarrin(siguienteNinho)
                Ninho->>Ninho: recibeMensaje(mensajeDistorsionado)
            else Es el último turno
                Ludoteca->>Ninho: escribe(pizarra)
                Ninho->>Pizarra: setMensaje(mensaje)
                Ludoteca->>Pizarra: imprimir()
                Ludoteca->>Juego: termina()
                Ludoteca->>Animadora: getFila().vaciar()
                loop Mientras Lydia tenga esperando y fila no completa
                    Ludoteca->>Animadora: pideNinho(Lydia)
                    Animadora->>Recepcionista: darNinho()
                    Recepcionista-->>Animadora: Ninho
                    Animadora->>Fila: agregar(Ninho)
                end
            end
        end
        Ludoteca->>Tiempo: pasarMinuto()
        Note over Ludoteca: =======
    end