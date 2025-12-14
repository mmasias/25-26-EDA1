# Uroboro de Lista usando Array

## Contexto

En el ejercicio **listaConArray**, se implementó una lista usando un array primitivo del lenguaje.

En el ejercicio **arrayConLista**, se implementó un array usando una lista enlazada con nodos.

**El objetivo ahora es cerrar el círculo**: implementar una Lista usando el Array de `arrayConLista.a000`.

## La paradoja

```java
// Una Lista
Lista miLista = new Lista();
miLista.agregar(100);

// Internamente:
// 1. Lista llama a Array.set(...)
// 2. Array (de arrayConLista) llama a Lista.modificar(...)
// 3. Esa Lista recorre nodos enlazados
// 4. Los nodos están en un array

Lista → Array → Lista (enlazada) → Array (nodos)
```

**¿Dónde está el array "real"?** ¿Y la lista "real"?

## Propuesta de interfaz

### Clase `Lista` (package `uroboros.lista`)

<div align=center>

|Vista pública|
|-|
|public Lista()|
|public void agregar(int valor)|
|public int obtener(int indice)|
|public void modificar(int indice, int nuevoValor)|
|public void eliminar(int indice)|
|public int tamaño()|
|public void mostrarEstructura()|

</div>

### Implementación interna

```java
import arrayConLista.a000.Array;

public class Lista {
    private Array elementos;  // ← Array que usa Lista enlazada
    private int tamaño;

    public Lista() {
        elementos = new Array(10);  // ← Capacidad inicial
        tamaño = 0;
    }
}
```

## Comparación con l000

La **lógica** es idéntica a `listaConArray.l000.Lista`:

- Misma gestión de tamaño
- Mismo comportamiento en agregar/eliminar
- Misma redimensionamiento al llenarse

La **diferencia** está en una línea:

| l000 | uroboro.lista |
|-|-|
| `private int[] elementos;` | `private Array elementos;` |
| `elementos[i]` | `elementos.get(i)` |
| `elementos[i] = valor` | `elementos.set(i, valor)` |

## Reflexiones

### Antes de implementar

1. ¿Es esperable que cambiar la implementación sea tan simple?
2. ¿Qué revela esto sobre la importancia de las interfaces bien diseñadas?

### Después de implementar

1. ¿Funciona igual que l000?
2. ¿Es igual de eficiente?
3. ¿Cuántas "capas" atraviesa un dato al ejecutar `agregar(100)`?
4. Si se midiera el tiempo, ¿cuál sería más lento?

## El test del uroboro

Si la implementación es correcta:

```java
// Estas dos listas deberían comportarse IDÉNTICAMENTE:
listaConArray.l000.Lista lista1 = new listaConArray.l000.Lista();
uroboros.lista.Lista lista2 = new uroboros.lista.Lista();

lista1.agregar(100);
lista2.agregar(100);

assert lista1.obtener(0) == lista2.obtener(0);  // true
assert lista1.tamaño() == lista2.tamaño();      // true
```

Pero por dentro:

- `lista1` usa un `int[]` nativo de Java
- `lista2` usa un `Array` que usa una `Lista` que usa `Nodos` que usan un `int[][]`

**Misma interfaz, complejidad radicalmente diferente.**

## Pregunta final

¿En qué punto una abstracción deja de ser "elegante" y se convierte en "sobre-ingeniería"?
