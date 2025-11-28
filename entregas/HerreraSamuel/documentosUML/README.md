@startuml
title Relaciones de Clases 

' Oculta todos los atributos y métodos
hide emptyMembers

class SimulacionCocina
class Vista
class Cocinero
class ColaDePrioridad
class Pedido

SimulacionCocina ..> Vista : usa
SimulacionCocina ..> Cocinero : usa
SimulacionCocina ..> ColaDePrioridad : usa
SimulacionCocina ..> Pedido : crea

ColaDePrioridad "1" -- "0..*" Pedido : contiene
Cocinero "1" -- "0..1" Pedido : prepara
@enduml

