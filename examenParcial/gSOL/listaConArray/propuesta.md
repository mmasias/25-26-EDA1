# Propuesta

## Clase `Lista`

<div align=center>

|Vista pública
|-
|public Lista()
|public void agregar(int valor)
|public int obtener(int posicion)
|public int tamaño()
|public void eliminar(int posicion)

</div>

### Un cliente debería poder hacer esto:

```java
Lista lista = new Lista();
lista.agregar(100);
int valor = lista.obtener(0);
int cantidad = lista.tamaño();
lista.eliminar(0);
```