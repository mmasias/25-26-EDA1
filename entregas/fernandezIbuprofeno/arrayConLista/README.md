# Simular un array utilizando una lista

## Contexto

Imagine que el lenguaje de programación **no dispone de arrays**, pero sí permite el uso de **listas**.  
Una lista es una estructura dinámica que puede crecer o reducirse, mientras que un array es una estructura de tamaño fijo y posiciones contiguas.

El objetivo es **simular el comportamiento de un array utilizando una lista**, es decir, usar una estructura flexible para comportarse como una rígida.

## Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento esperado)|Lista (comportamiento real)|¿Qué habría que imponer o restringir?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable||
|**Tipo de dato**|Homogéneo|Posiblemente heterogéneo||
|**Inserción y eliminación**|No permitidas|Permitidas||
|**Acceso**|Por índice directo|Por índice o recorrido||
|**Posiciones en memoria**|Contiguas|No necesariamente contiguas||

</div>

## Parte 2: Cuestiones para el análisis

1. Si una lista puede crecer y reducirse, ¿cómo podría fijarse un tamaño constante?  
2. ¿Qué mecanismos conceptuales permitirían “bloquear” la inserción o eliminación de elementos?  
3. ¿Cómo se garantizaría la homogeneidad de tipos dentro de la lista?  
4. ¿Qué implicaciones tendría acceder siempre por índice, incluso si la lista internamente no está organizada de forma contigua?  
5. ¿Qué se pierde y qué se gana al imponer a una lista el comportamiento de un array?

## Parte 3: Reflexión

1. ¿Hasta qué punto una estructura flexible puede comportarse de manera disciplinada como una estructura rígida?  
2. ¿Qué dice este ejercicio sobre la relación entre **naturaleza de una estructura** y **modo de uso**?  
3. ¿Cuándo podría ser útil una simulación de este tipo en un contexto real de programación o diseño de sistemas?
