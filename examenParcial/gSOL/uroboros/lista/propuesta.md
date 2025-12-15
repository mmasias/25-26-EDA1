# Uroboro de Lista usando Array

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

