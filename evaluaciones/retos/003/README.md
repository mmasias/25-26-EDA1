# Simulación

<div align=center>

|||
|-|-|
En programación, las estructuras de datos no solo se utilizan: también pueden **simularse**.  |Simular una estructura consiste en **reproducir su comportamiento esencial** empleando otra que no posee exactamente las mismas propiedades.

</div>

Este ejercicio propone dos direcciones de simulación opuestas:

1. **Simular un array utilizando una lista.**  
2. **Simular una lista utilizando un array.**

En ambos casos se busca razonar sobre **qué define realmente a cada estructura**, **qué reglas habría que imponer o relajar**, y **qué se gana o se pierde** al hacerlo.

## Simular un array utilizando una lista

### Contexto

Imagine que el lenguaje de programación **no dispone de arrays**, pero sí permite el uso de **listas**.  
Una lista es una estructura dinámica que puede crecer o reducirse, mientras que un array es una estructura de tamaño fijo y posiciones contiguas.

El objetivo es **simular el comportamiento de un array utilizando una lista**, es decir, usar una estructura flexible para comportarse como una rígida.

### Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento esperado)|Lista (comportamiento real)|¿Qué habría que imponer o restringir?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable||
|**Tipo de dato**|Homogéneo|Posiblemente heterogéneo||
|**Inserción y eliminación**|No permitidas|Permitidas||
|**Acceso**|Por índice directo|Por índice o recorrido||
|**Posiciones en memoria**|Contiguas|No necesariamente contiguas||

</div>

### Parte 2: Cuestiones para el análisis

1. Si una lista puede crecer y reducirse, ¿cómo podría fijarse un tamaño constante?  
2. ¿Qué mecanismos conceptuales permitirían “bloquear” la inserción o eliminación de elementos?  
3. ¿Cómo se garantizaría la homogeneidad de tipos dentro de la lista?  
4. ¿Qué implicaciones tendría acceder siempre por índice, incluso si la lista internamente no está organizada de forma contigua?  
5. ¿Qué se pierde y qué se gana al imponer a una lista el comportamiento de un array?

### Parte 3: Reflexión

1. ¿Hasta qué punto una estructura flexible puede comportarse de manera disciplinada como una estructura rígida?  
2. ¿Qué dice este ejercicio sobre la relación entre **naturaleza de una estructura** y **modo de uso**?  
3. ¿Cuándo podría ser útil una simulación de este tipo en un contexto real de programación o diseño de sistemas?

## Simular una lista utilizando un array

### Contexto

Imagine que el lenguaje de programación **solo ofrece arrays**, es decir:  

- Estructuras de **tamaño fijo**.  
- Elementos del **mismo tipo**.  
- Acceso a través de **índices numéricos**.

El objetivo es **simular el comportamiento de una lista** utilizando un array: usar una estructura rígida para representar algo flexible.

### Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento real)|Lista (comportamiento esperado)|¿Qué habría que simular o emular?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable||
|**Inserción**|No permitida|Permitida en cualquier posición||
|**Eliminación**|No permitida|Permitida en cualquier posición||
|**Espacio libre**|No considerado|Puede existir implícitamente||
|**Acceso**|Directo por índice|Directo o secuencial||

</div>

### Parte 2: Cuestiones para el análisis

1. ¿Cómo podría representarse el número real de elementos de la lista dentro del array?  
2. ¿Qué debería ocurrir cuando el array se llena por completo?  
3. ¿Cómo se simularía la inserción de un elemento en una posición intermedia?  
4. ¿Qué operaciones serían necesarias para eliminar un elemento sin dejar “huecos” lógicos?  
5. ¿Qué mecanismos permitirían ocultar estos detalles al usuario, de modo que la estructura parezca verdaderamente dinámica?

### Parte 3: Reflexión

1. ¿Qué tipo de “flexibilidad simulada” puede lograrse con una estructura fija?  
2. ¿Qué costos en tiempo o memoria implica mantener la ilusión de dinamismo?  
3. ¿Qué enseña este ejercicio sobre la diferencia entre **abstracción conceptual** y **limitación técnica**?  
4. ¿Existen ejemplos en ingeniería donde se utilicen mecanismos similares para ofrecer una interfaz más flexible sobre una base rígida?

## Conclusión

<div align=center>

|||
|-|-|
Ambas simulaciones —una lista que finge ser un array y un array que finge ser una lista— revelan la tensión entre **estructura y comportamiento**.  |El análisis permite observar que lo esencial en una estructura de datos no es solo su implementación, sino **las reglas de uso y las expectativas de quien la emplea**.

</div>

Se recomienda discutir en grupo las siguientes cuestiones:

- ¿Qué diferencia hay entre “ser” una estructura y “actuar como” una estructura?  
- ¿Qué papel juega la abstracción en la evolución de los tipos de datos?  
- ¿Qué enseñanzas de diseño pueden extraerse de estas simulaciones para la ingeniería de software?