# RETO-007: Simulación ARRAY → LISTA → ARRAY

El objetivo final es lograr la siguiente simulación:

```
ARRAY (cliente)
   ↓
LISTA (estructura intermedia)
   ↓
ARRAY (implementación interna)
```

---

## 1. Situación inicial (código original)

### Estructura original

- `Cliente` utiliza una clase `Array`
- `Array` **simula un array** usando una `Lista`
- `Lista` está implementada mediante Nodos enlazados (`Nodo`)

### Problema detectado

Aunque el `Array` no utiliza un array real, la `Lista` sí utiliza nodos enlazados, lo cual incumple el reto, ya que la lista también debía estar simulada con arrays.

---

## 2. Objetivo del cambio

Eliminar completamente el uso de nodos enlazados y referencias reales, sustituyéndolos por arrays

---

## 3. Eliminación de la clase `Nodo`

### Antes

```java
public class Nodo {
    private int dato;
    private Nodo siguiente;
}
```

### Después

- La clase `Nodo` se elimina por completo.
- Su funcionalidad se simula mediante arrays de enteros.

---

## 4. Nueva estructura interna de la clase `Lista`

### Antes

```java
private Nodo cabeza;
private int tamaño;
```

### Después

```java
private int[] datos;        // almacena los valores
private int[] siguiente;    // simula los punteros
private int cabeza;         // índice del primer elemento
private int libre;          // índice del primer espacio libre
private int tamaño;
```

---

## 5. Constructor de `Lista`

### Cambio realizado

El constructor ahora recibe una capacidad máxima e inicializa un pool de nodos dentro de arrays.

### Implementación

```java
public Lista(int capacidad) {
    datos = new int[capacidad];
    siguiente = new int[capacidad];

    for (int i = 0; i < capacidad - 1; i++) {
        siguiente[i] = i + 1;
    }
    siguiente[capacidad - 1] = -1;

    cabeza = -1;
    libre = 0;
    tamaño = 0;
}
```

El array `siguiente` cumple la función de los enlaces entre nodos.

---

## 6. Método `agregar(int valor)`

### Antes

- Se creaba un nuevo `Nodo`
- Se recorría la lista mediante referencias

### Después

- Se toma una posición libre del array
- Se enlaza mediante índices

```java
public void agregar(int valor) {
    assert libre != -1 : "Mal: lista llena";

    int nuevo = libre;
    libre = siguiente[libre];

    datos[nuevo] = valor;
    siguiente[nuevo] = -1;

    if (cabeza == -1) {
        cabeza = nuevo;
    } else {
        int actual = cabeza;
        while (siguiente[actual] != -1) {
            actual = siguiente[actual];
        }
        siguiente[actual] = nuevo;
    }

    tamaño++;
}
```

---

## 7. Método `obtener(int indice)`

### Cambio conceptual

- Antes: recorrido con nodos
- Ahora: recorrido con índices

```java
public int obtener(int indice) {
    assert indice >= 0 && indice < tamaño;

    int actual = cabeza;
    for (int i = 0; i < indice; i++) {
        actual = siguiente[actual];
    }
    return datos[actual];
}
```

---

## 8. Método `modificar(int indice, int nuevoValor)`

```java
public void modificar(int indice, int nuevoValor) {
    assert indice >= 0 && indice < tamaño;

    int actual = cabeza;
    for (int i = 0; i < indice; i++) {
        actual = siguiente[actual];
    }
    datos[actual] = nuevoValor;
}
```

---

## 9. Cambios en la clase `Array`

### Cambio necesario

El constructor de `Lista` ahora requiere una capacidad, por lo que el constructor de `Array` se adapta mínimamente.

```java
this.miLista = new Lista(capacidad);
```

No se modifica **ningún otro método** de `Array`.

---

## 11. Resultado final

✔️ El cliente cree usar un array ✔️ El array está simulado con una lista ✔️ La lista está simulada con arrays ✔️ No existen nodos ni referencias reales

```
Cliente → Array → Lista → Arrays
```

---


