# Refactorización del ejercicio del parcial

## Introducción

En el examen parcial se nos pedía simular el comportamiento de una **lista** utilizando internamente un **array**.  
A partir del código trabajado durante el parcial, he realizado una refactorización con el objetivo de corregir diseño y ajustar la solución a los principios vistos en clase, como la modularidad, la correcta distribución de responsabilidades y una defensiva adecuada.


## Corrección en el desarrollo del ejercicio

En la solución final, la estructura se comporta externamente como una lista: permite añadir elementos, eliminar posiciones intermedias y acceder por índice. Internamente, la lista se apoya en un array, gestionando de forma explícita el número de elementos utilizados y la capacidad disponible.

Para evitar vicios de diseño, se han tenido en cuenta los siguientes aspectos:

- El tamaño lógico de la lista es independiente del tamaño del array interno.
- Cuando el array se llena, se redimensiona para permitir el crecimiento de la lista.
- Al eliminar elementos, se desplazan los valores para evitar huecos.
- Los accesos fuera de rango se controlan mediante una defensiva clara.

De este modo, la estructura mantiene el comportamiento propio de una lista sin exponer los detalles internos del array.


## Vista pública de las clases

### ListaConArray

Es la clase principal del ejercicio. Representa una lista dinámica cuya implementación interna se basa en un array.

**Vista pública:**
- `ListaConArray(int capacidadInicial)`
- `void add(int valor)`
- `void remove(int posicion)`
- `int get(int posicion)`
- `int size()`
- `void imprimir()`


### EjemploUnidimensional

Clase de prueba que contiene el punto de entrada del programa. Su única función es crear una instancia de la lista y comprobar su funcionamiento, manteniendo un `main` sencillo y sin lógica innecesaria.

**Vista pública:**
- `main(String[] args)`


## Relación entre las clases

- `EjemploUnidimensional` utiliza la clase `ListaConArray` para probar su comportamiento.
- `ListaConArray` no depende de ninguna otra estructura externa y gestiona internamente su array y el número de elementos almacenados.

Las responsabilidades están claramente separadas: la lista se gestiona a sí misma y la clase de ejemplo se limita a usarla.