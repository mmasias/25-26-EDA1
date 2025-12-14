# Uroboro de Array usando Lista

## Contexto

En el ejercicio **arrayConLista**, se implementó un array usando una lista enlazada con nodos.

En el ejercicio **listaConArray**, se implementó una lista usando un array de nodos con gestión de espacios libres.

**El objetivo ahora es completar el círculo**: implementar un Array usando la Lista de `listaConArray.l001`.

## La paradoja inversa

```java
// Un Array
Array miArray = new Array(5);
miArray.set(2, 100);

// Internamente:
// 1. Array llama a Lista.modificar(...)
// 2. Lista (de listaConArray) recorre su estructura de nodos
// 3. Los nodos están en un array int[][]
// 4. El array gestiona espacios libres con índices

Array → Lista (con array de nodos) → int[][]
```

**¿Es esto un array o una lista?** Depende de qué lado se mire.

## Propuesta de interfaz

### Clase `Array` (package `uroboros.array`)

<div align=center>

|Vista pública|
|-|
|public Array(int capacidad)|
|public int get(int indice)|
|public void set(int indice, int valor)|
|public int longitud()|

</div>

### Implementación interna

```java
import listaConArray.l001.Lista;

public class Array {
    private Lista lista;      // ← Lista que usa array de nodos
    private int capacidad;

    public Array(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Lista();

        // Simular tamaño fijo
        for (int i = 0; i < capacidad; i++) {
            lista.agregar(0);
        }
    }
}
```

## Comparación con arrayConLista.a000

La **interfaz** es idéntica a `arrayConLista.a000.Array`:
- Mismo tamaño fijo
- Mismas operaciones get/set
- Mismo comportamiento de "array rígido"

La **diferencia** está en qué Lista usa:

| a000.Array | uroboros.array.Array |
|-|-|
| Usa `a000.Lista` (enlazada con nodos) | Usa `l001.Lista` (array de nodos) |
| `Lista` usa `Nodo` (objetos) | `Lista` usa `int[][]` (array primitivo) |

## La cadena completa

Al ejecutar `array.set(2, 999)`:

```
1. Array.set(2, 999)
   ↓
2. lista.modificar(2, 999)
   ↓
3. Recorre la lista enlazada hasta posición 2
   ↓
4. Accede a nodos[indice][0] = 999
   ↓
5. Modifica un array int[][]
```

**Paradoja**: Un "array" implementado con una "lista" implementada con un array.

## Reflexiones

### Antes de implementar

1. ¿Qué diferencia fundamental hay entre usar `a000.Lista` y `l001.Lista`?
2. ¿Importa cuál se utilice si la interfaz es la misma?

### Después de implementar

1. ¿Funciona igual que `arrayConLista.a000.Array`?
2. Si l001 gestiona espacios libres, ¿se aprovecha esa característica en este contexto?
3. ¿Qué estructura resulta más "ineficiente": uroboros.lista o uroboros.array?

## El dilema de la lista enlazada en array

`l001.Lista` fue diseñada para:
- Insertar al final eficientemente
- Reutilizar espacios eliminados
- Mantener un puntero de cabeza y cola

Pero `Array`:
- Solo usa `modificar()` y `obtener()`
- Nunca elimina elementos
- No aprovecha la free-list

**¿Es como usar un Ferrari para ir al supermercado?**

## Comparación de complejidad

| Operación | Array real (Java) | uroboros.array |
|-|-|-|
| `get(i)` | O(1) - acceso directo | O(n) - recorre lista |
| `set(i, v)` | O(1) - acceso directo | O(n) - recorre lista + O(n) busca nodo |

**El uroboro es más lento** que un array real.

Pero desde fuera, **se comporta exactamente igual**.

## Pregunta final

Si dos estructuras tienen el mismo comportamiento observable pero distinta eficiencia:

1. ¿Son la misma estructura?
2. ¿En qué momento la diferencia de rendimiento "rompe" la equivalencia?
3. ¿Puede ser O(n) "suficientemente bueno" si nadie lo nota?
