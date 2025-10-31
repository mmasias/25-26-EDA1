# Cambios realizados y justificación

## 1. División de la clase `Ejemplo`
La clase `Ejemplo` del esquema original se dividió en dos:
- `EjemploUnidimensional`: prueba la clase `ListaComoArray`.
- `EjemploBidimensional`: prueba la clase `ListaDeFilas`.

**Motivo:** separar claramente los ejemplos para cada tipo de estructura y facilitar su ejecución individual.


## 2. Tamaño fijo y tipo de dato
En las clases `ListaComoArray` y `Fila` se crea desde el constructor una lista enlazada con tamaño fijo según la capacidad o número de columnas.  
El tipo de dato se mantuvo como `int` para garantizar homogeneidad.

**Motivo:** cumplir con la idea de que un array tiene tamaño fijo y solo admite un tipo de dato.


## 3. Contador de elementos
Se añadió en `ListaComoArray` un campo `contadorElementosUsados` que comienza en 0 y aumenta cuando se utiliza por primera vez una posición.  
Si el contador alcanza el límite de la capacidad, no se permite usar más posiciones.

**Motivo:** reflejar la condición de que cuando se llena el array, no pueden añadirse más elementos.


## 4. Acceso directo sin recorrer toda la lista
Se incluyó una estructura auxiliar interna `NodoIndice` con los campos `indice` y `referenciaNodo`.  
Esto permite guardar las posiciones ya visitadas para acceder a ellas directamente sin recorrer la lista cada vez.

**Motivo:** imitar un acceso más rápido a posiciones ya usadas, tal como sugiere el enunciado.


## 5. Enlace de filas sin modificar la clase `Fila`
El diagrama no muestra un enlace entre filas, por lo que se creó una clase interna `NodoFila` dentro de `ListaDeFilas` con los atributos `Fila fila` y `NodoFila siguiente`.

**Motivo:** enlazar las filas de la matriz sin modificar la estructura de la clase `Fila`.


## 6. Registro de celdas usadas
En `ListaDeFilas` se añadió una estructura auxiliar `NodoCeldaUsada` y un contador `contadorCeldasUsadas` para llevar el control de las posiciones realmente utilizadas.

**Motivo:** mantener coherencia con la versión unidimensional y controlar cuántas celdas han sido modificadas.


## 7. Nombres y manejo de errores
Se reemplazaron nombres de variables cortos por nombres descriptivos (por ejemplo, `nodoActual`, `posicionActual`, `contadorElementosUsados`).  
Los errores se muestran con `System.out.println()` en lugar de lanzar excepciones.

**Motivo:** cumplir con el requisito de utilizar nombres claros y evitar código avanzado.


## 8. Métodos de impresión y clases de prueba
- `imprimir()` en `ListaComoArray` muestra los valores en formato de lista.
- `imprimirTodo()` e `imprimirFila()` en las estructuras bidimensionales muestran la matriz completa.  
Las clases de ejemplo ejecutan llamadas a `set`, `get` y los métodos de impresión.

**Motivo:** facilitar la comprobación visual del funcionamiento del programa.


## 9. Elementos respetados del esquema original

| Elemento | Implementación | Estado |
|-----------|----------------|--------|
| Tamaño fijo | `capacidad`, `filas`, `columnas` | Cumplido |
| Tipo homogéneo | Uso de `int` | Cumplido |
| Solo `get` y `set` | Sin inserción ni eliminación | Cumplido |
| Control de límite | Comprobación de capacidad o número de celdas | Cumplido |
| Acceso por posición o por fila y columna | Métodos `get` y `set` | Cumplido |


## 10. Elementos imposibles de mantener sin cambios
- El acceso directo O(1) no puede lograrse en una lista enlazada. Se añadió la clase auxiliar `NodoIndice` para almacenar referencias.
- La clase `Fila` no puede enlazarse directamente con otras filas sin añadir un campo `siguiente`. Para mantener la integridad del esquema, se introdujo `NodoFila` como enlace externo.


## 11. Resumen final
Los cambios realizados permiten mantener el comportamiento de un array mediante listas enlazadas, con tamaño fijo, tipo homogéneo y operaciones limitadas a `get` y `set`.  
Las modificaciones adicionales (`NodoIndice`, `NodoFila`, `NodoCeldaUsada`) se introdujeron solo para que el código fuese funcional sin alterar la estructura original de los diagramas.
