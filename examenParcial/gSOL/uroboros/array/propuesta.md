# Array usando Lista

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
    private Lista lista;
    private int capacidad;

    public Array(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Lista();

        for (int i = 0; i < capacidad; i++) {
            lista.agregar(0);
        }
    }
}
```
