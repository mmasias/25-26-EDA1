# Propuesta

## Clase `Array`

<div align=center>

|Vista pública|
|-|
|public Array(int capacidad)|
|public int get(int indice)|
|public void set(int indice, int valor)|
|public int longitud()|

</div>

### Un cliente debería poder hacer esto:

```java
Array array = new Array(5);
array.set(0, 100);
array.set(1, 200);
int valor = array.get(0);
int tamaño = array.longitud();
```
