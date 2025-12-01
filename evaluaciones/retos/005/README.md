# Reto RCCCF

> ***Nota pedagógica***
> 
> *Este ejercicio demuestra un caso donde una aproximación usando matrices o listas tradicionales **podrían no resultar la estructura apropiada** por los requisitos*
> 
> *Compare la solución con diferentes estructuras y observe cómo el número de comparaciones impacta en la eficiencia del sistema.*

Ante el éxito del [CCCF](https://github.com/puntoReflex/pyCCCF/blob/main/enunciado.md), la empresa decidió abrir un restaurante en el local contiguo. El RCCCF opera con un modelo de cocina optimizada donde los pedidos se preparan según su tiempo de elaboración, no según su orden de llegada.

Un estudio previo determinó que la probabilidad de llegada de un pedido nuevo se estima en un 40% por minuto.

A diferencia de las cajas del supermercado donde los clientes se atienden en orden de llegada (FIFO), en la cocina esto no tiene sentido: **¿un café debe esperar detrás de una ensalada?**.

El cocinero -cuando ha terminado de atender un pedido- selecciona el **pedido pendiente con menor tiempo de preparación** para optimizar el número de pedidos completados durante la jornada.

## Tipos de platos

El restaurante ofrece 5 tipos de platos, cada uno con un rango de tiempo de preparación:

<div align=center>

|Plato|Tiempo de preparación|
|-|-|
|Bebida|1-2 minutos|
|Café|2-3 minutos|
|Colacao|2-4 minutos|
|Bocadillo|3-5 minutos|
|Ensalada|5-8 minutos|

</div>

Cuando llega un pedido, el cliente puede pedir entre uno cualquiera de estos tipos de plato (asuma una distribución aleatoria con la misma probabilidad de pedir cada uno). El tiempo específico de preparación se genera aleatoriamente dentro del rango correspondiente.

## Funcionamiento de la cocina

- El restaurante opera durante la misma jornada del CCCF
- Hay **un cocinero** que procesa los pedidos
- Cada minuto puede llegar un nuevo pedido (40% de probabilidad)
- El cocinero siempre selecciona el pedido pendiente con **menor tiempo de preparación**
- Cuando termina un pedido, inmediatamente toma el siguiente de menor tiempo
- La preparación se procesa minuto a minuto (cada minuto resta 1 al tiempo restante del pedido en proceso)

## Reto

Desarrolle un programa que modele y simule el sistema de la cocina del RCCCF, teniendo en cuenta las condiciones indicadas.

El programa debe ir mostrando los siguientes datos conforme avance el tiempo:

- Llegada de pedidos (tipo y tiempo de preparación)
- Número de pedidos en cola de espera
- Pedido que está procesando el cocinero (tipo y tiempo restante)

Al finalizar la jornada, el programa debe presentar un resumen con:

- Número de pedidos atendidos durante la jornada
- Número de pedidos que quedaron pendientes al cierre
- Tiempo total de espera acumulado de todos los pedidos
- Tiempo medio de espera por pedido
- Número de comparaciones realizadas para encontrar el pedido de menor tiempo (métrica de eficiencia del algoritmo)

## Reflexión

Este problema requiere **extraer repetidamente el elemento mínimo** de una colección que cambia dinámicamente (llegan pedidos nuevos, se completan pedidos).

Piense en diferentes formas de resolver este problema y compare su eficiencia:

- ¿Qué estructura de datos utilizaría? ¿Por qué?
- Proponga un diagrama que arquitecte su propuesta
- Implemente su propuesta

## Sugerencia de presentación

```console
manuel@manuel-OptiPlex-7020:~/rcccf$ java Simulation
========================================
[1.0]
Llega pedido: Café (2 min)
COLA: 0 pedidos
Cocinero: [Café - 2 min restantes]
========================================
[2.0]
Llega pedido: Cocido (23 min)
COLA: 1 pedidos
Cocinero: [Café - 1 min restantes]
========================================
[3.0]
COLA: 1 pedidos
Cocinero: [Cocido - 23 min restantes]
========================================
[4.0]
Llega pedido: Bocadillo (4 min)
COLA: 1 pedidos
Cocinero: [Bocadillo - 4 min restantes]
----------------------------------------
[120.0]
COLA: 3 pedidos
Cocinero: [Pasta - 2 min restantes]
========================================

RESUMEN DE LA JORNADA
========================================
Pedidos atendidos        : 16
Pedidos pendientes       : 32
Tiempo total de espera   : 485 minutos
Tiempo medio de espera   : 10.1 minutos
Comparaciones totales    : 624
========================================
```
