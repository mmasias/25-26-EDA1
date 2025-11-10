
# Proyecto: Array de Listas de Enteros en Java

## Descripción

Este proyecto implementa una estructura de datos en Java que combina **arrays y listas** de manera personalizada.  
Se parte de la base de un código que trabajaba con listas de enteros usando arrays internos, y se extiende para manejar un **array que contiene múltiples listas**, cada una de las cuales utiliza internamente su propio array dinámico de enteros.

En otras palabras, tenemos un **array de listas**, donde cada lista mantiene su **propio array de enteros**, y todas las operaciones se realizan usando únicamente arrays (`int[]` y `ListaEnteros[]`), sin usar `ArrayList`.

---

## Estructura del proyecto

- **Clase `ListaEnteros`**  
  Representa una lista de enteros con funcionalidades básicas:  
  - Insertar un elemento en cualquier posición.  
  - Eliminar un elemento por posición.  
  - Obtener un elemento por posición.  
  - Mostrar todos los elementos.  
  Internamente utiliza un **array dinámico (`int[]`)** que se expande automáticamente cuando se llena.

- **Clase `ArrayDeListas`**  
  Representa un **array de listas** (`ListaEnteros[]`) con funcionalidades:  
  - Insertar una lista en cualquier posición.  
  - Eliminar una lista por posición.  
  - Obtener una lista por posición.  
  - Mostrar todas las listas con sus elementos.  
  Al igual que `ListaEnteros`, el array se expande automáticamente cuando se llena.

- **Clase `Principal`**  
  Proporciona un menú interactivo para el usuario mediante consola, que permite:  
  1. Crear nuevas listas con varios elementos.  
  2. Insertar elementos en listas existentes.  
  3. Eliminar listas.  
  4. Mostrar todas las listas y sus elementos.  
  0. Salir del programa.

