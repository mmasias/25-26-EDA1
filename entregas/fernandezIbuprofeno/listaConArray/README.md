# Simular una lista utilizando un array

## Contexto

Imagine que el lenguaje de programación **solo ofrece arrays**, es decir:  

- Estructuras de **tamaño fijo**.  
- Elementos del **mismo tipo**.  
- Acceso a través de **índices numéricos**.

El objetivo es **simular el comportamiento de una lista** utilizando un array: usar una estructura rígida para representar algo flexible.

## Parte 1: Identificación de diferencias

<div align=center>

|Característica|Array (comportamiento real)|Lista (comportamiento esperado)|¿Qué habría que simular o emular?|
|-|:-:|:-:|-|
|**Tamaño**|Fijo|Variable||
|**Inserción**|No permitida|Permitida en cualquier posición||
|**Eliminación**|No permitida|Permitida en cualquier posición||
|**Espacio libre**|No considerado|Puede existir implícitamente||
|**Acceso**|Directo por índice|Directo o secuencial||

</div>

## Parte 2: Cuestiones para el análisis

1. ¿Cómo podría representarse el número real de elementos de la lista dentro del array?  
2. ¿Qué debería ocurrir cuando el array se llena por completo?  
3. ¿Cómo se simularía la inserción de un elemento en una posición intermedia?  
4. ¿Qué operaciones serían necesarias para eliminar un elemento sin dejar “huecos” lógicos?  
5. ¿Qué mecanismos permitirían ocultar estos detalles al usuario, de modo que la estructura parezca verdaderamente dinámica?

## Parte 3: Reflexión

1. ¿Qué tipo de “flexibilidad simulada” puede lograrse con una estructura fija?  
2. ¿Qué costos en tiempo o memoria implica mantener la ilusión de dinamismo?  
3. ¿Qué enseña este ejercicio sobre la diferencia entre **abstracción conceptual** y **limitación técnica**?  
4. ¿Existen ejemplos en ingeniería donde se utilicen mecanismos similares para ofrecer una interfaz más flexible sobre una base rígida?
