# Proyecto: Implementación de una Matriz con Listas Enlazadas

Este proyecto muestra cómo construir una **matriz** sin usar arrays bidimensionales directamente, sino **listas enlazadas** creadas con **arrays**.  
Cada fila está formada por nodos, y la matriz se compone de un conjunto de filas.

---

## Estructura del proyecto

El proyecto contiene las siguientes clases:

- **Nodo.java** → representa un nodo individual que almacena un dato y una referencia al siguiente.
- **Fila.java** → construye una fila como una lista enlazada de nodos.
- **ListaDeFilas.java** → mantiene todas las filas de la matriz en un array.
- **Matriz.java** → gestiona las operaciones principales sobre la matriz (añadir, obtener e imprimir valores).
- **EjemploMatriz.java** → ejecuta pruebas de todos los métodos para comprobar su funcionamiento.

---

## Pasos realizados

1. **Creación del nodo base**  
   Se definió la clase `Nodo` con un valor entero y un puntero al siguiente nodo.

2. **Construcción de una fila enlazada**  
   En `Fila`, se implementó una lista enlazada de nodos que permite recorrer, asignar e imprimir valores de cada columna.

3. **Agrupación de filas**  
   En `ListaDeFilas`, se generó un conjunto de objetos `Fila`, uno por cada fila de la matriz.

4. **Creación de la matriz completa**  
   En `Matriz`, se gestionan todas las filas y se añaden métodos para:
   - `setValor(fila, columna, valor)`
   - `getValor(fila, columna)`
   - `getFila(fila)`
   - `imprimir()`

5. **Prueba de funcionamiento**  
   En `EjemploMatriz`, se crean ejemplos para probar todos los métodos:
   - Inicializar la matriz  
   - Añadir y leer valores  
   - Imprimir el contenido  
   - Comprobar accesos fuera de rango  
   - Copiar una fila y verificar que no afecta a la matriz original