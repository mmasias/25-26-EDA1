# Examen parcial


## Estructura general

| Clase                      | Función principal                                          | Implementa / Usa               |
| -------------------------- | ---------------------------------------------------------- | ------------------------------ |
| `Nodo`                     | Nodo básico (`dato`, `sig`)                                | Base de `ArraySimulado`        |
| `ArraySimulado`            | Simula un **array fijo** sobre una **lista enlazada**      | Usa `Nodo`                     |
| `ListaUsandoArraySimulado` | Implementa una **lista dinámica** sobre el `ArraySimulado` | Usa `ArraySimulado`            |
| `Main`                     | Ejecuta y demuestra la simulación                          | Usa `ListaUsandoArraySimulado` |

---

##  Clases y métodos clave

### `Nodo`

* Guarda un entero y un puntero al siguiente.

### `ArraySimulado`

* **`length()`** → tamaño fijo del “array”.
* **`get(i)` / `set(i, v)`** → accede/modifica el valor en la posición *i* recorriendo la lista.
* Implementa el **comportamiento de un array** pero internamente usa una **lista enlazada simple**.

### `ListaUsandoArraySimulado`

* **`size()` / `isEmpty()`** → control del número real de elementos.
* **`get` / `set`** → acceden mediante el `ArraySimulado`.
* **`add(v)`** → añade al final (usa `ensureCapacity` para crecer).
* **`add(index, v)` / `remove(index)`** → hacen **corrimientos** manuales con bucles.
* **`ensureCapacity`** → duplica la capacidad creando un nuevo `ArraySimulado` y copiando valores.

### `Main`

* Prueba todas las operaciones: inserciones, eliminaciones, modificaciones y crecimiento automático.

---

##  Comparativa con los códigos base

| Concepto              | “Array usando lista”             | “Lista usando array”             | Proyecto actual                            |
| --------------------- | -------------------------------- | -------------------------------- | ------------------------------------------ |
| Objetivo              | Simular un array sobre una lista | Simular una lista sobre un array | Unifica ambos enfoques                     |
| Estructura interna    | Lista enlazada                   | Array real (`int[]`)             | Lista enlazada (dentro del array simulado) |
| Tamaño                | Fijo                             | Dinámico                         | Dinámico, sobre base fija simulada         |
| Inserción/eliminación | No permitida                     | Permitida con corrimientos       | Permitida con corrimientos manuales        |

---

##  Conclusiones

* **`ArraySimulado`** *actúa como* un array aunque sea una lista.
* **`ListaUsandoArraySimulado`** *actúa como* una lista dinámica usando ese array simulado.
* Se demuestra cómo una estructura **rígida** puede fingir ser **flexible**, y viceversa.
* Ejemplo clásico de **abstracción y encapsulamiento** en diseño de estructuras.

