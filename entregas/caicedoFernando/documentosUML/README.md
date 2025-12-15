```mermaid
@startuml pyIris - Estructura 

title Estructura pyIris

actor "Emisor (Campus Virtual)" as EMISOR
actor "Cliente (Estudiante)" as CLIENTE

package "Sistema pyIris" {

  component "Recepción y Procesamiento" as PROCESO {
    
    queue "Cola de Mensajes Entrantes" as Q_MENSAJES

    database "Árbol de Acumulación " as TREE_ACUMULACION
    note right of TREE_ACUMULACION
      -- Mapeo por Asignatura --
      * Clave: Asignatura ID
      * Valor: Cola de Mensajes
    end note
  }

  component "Motor de Resumen y Envío" as MOTOR {
    
    [Lógica de Resumen] as LOGICA_RESUMEN
    [Proceso de Envío] as PROCESO_ENVIO

  }

  component "Gestión de Suscripciones" as SUSCRIPCION {
    
    database "Árbol de Suscripciones (BST)" as TREE_SUSCRIPCIONES
    note right of TREE_SUSCRIPCIONES
        -- Mapeo por Asignatura --
        * Clave: Asignatura ID
        * Valor: Lista de Clientes
    end note
    
    database "Lista de Clientes" as L_CLIENTE_DATA
    note right of L_CLIENTE_DATA
        -- Búsqueda O(n) --
        * Contiene: ID y Endpoint
    end note
  }

}

' Flujos de Datos
EMISOR -> Q_MENSAJES : Envía Mensaje
Q_MENSAJES -> PROCESO : DEQUEUE 

PROCESO -> TREE_ACUMULACION : Búsqueda 

TREE_ACUMULACION -> MOTOR : Recorrido y Resumen
MOTOR -> LOGICA_RESUMEN : Inicia ciclo

LOGICA_RESUMEN -> TREE_SUSCRIPCIONES : Consulta Suscriptores 

TREE_SUSCRIPCIONES -> L_CLIENTE_DATA : Obtiene IDs de Clientes
L_CLIENTE_DATA -> PROCESO_ENVIO : Búsqueda de Endpoint 

PROCESO_ENVIO -> CLIENTE : Envía Resumen Final

@enduml

```