# Ley de Demeter - Correcciones aplicadas

> **Rama:** `reto-005-demeter`  
> **Fecha:** Diciembre 2025  
> **Concepto:** Eliminación de violaciones de la Ley de Demeter mediante métodos delegados

## ¿Qué es la Ley de Demeter?

La **Ley de Demeter** (también conocida como "Principio de mínimo conocimiento") establece que un objeto debe **hablar solo con sus amigos cercanos**, no con extraños.

### Regla simple:

Un método solo debería llamar métodos de:
1. El mismo objeto (`this`)
2. Objetos pasados como parámetros
3. Objetos creados localmente
4. Atributos de instancia

### Lo que NO debe hacer:

**Evitar cadenas de llamadas** tipo: `a.getB().getC().doSomething()`

Esto se conoce como **"intimidad inapropiada"** - el código conoce demasiado sobre la estructura interna de otros objetos.

## Violaciones encontradas en el código original

### 1. En `ArbolPedidos.insertar()` - Línea 23

```java
if (pedido.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
```

**Problema:** `actual.getPedido().getTiempoPreparacion()` - cadena de dos llamadas.

**¿Por qué es malo?** ArbolPedidos conoce que:
1. `Nodo` tiene un `Pedido`
2. `Pedido` tiene el método `getTiempoPreparacion()`

Es decir, conoce **2 niveles de profundidad** de la estructura interna.

### 2. En `ArbolPedidos.incrementarEspera()` - Línea 85

```java
actual.getPedido().incrementarEspera();
```

**Problema:** ArbolPedidos debe saber que Nodo contiene Pedido y que Pedido tiene `incrementarEspera()`.

### 3. En `ArbolPedidos.tiempoTotalEspera()` - Línea 113

```java
total += actual.getPedido().getTiempoEspera();
```

**Problema:** Misma cadena - conocimiento excesivo de la estructura interna.

### 4. En `ArbolPedidos.extraerMinimo()` - Líneas 57-58

```java
min = padre.getIzquierdo().getPedido();
padre.setIzquierdo(padre.getIzquierdo().getDerecho());
```

**Problemas:**
- Cadena doble: `padre.getIzquierdo().getPedido()`
- Cadena triple: `padre.getIzquierdo().getDerecho()`
- Duplicación: se llama `padre.getIzquierdo()` dos veces

### 5. En `ArbolPedidos.extraerMinimo()` - Línea 53

```java
while (padre.getIzquierdo().getIzquierdo() != null) {
```

**Problema:** Navegación profunda en la estructura del árbol.

### 6. En `ArbolPedidos.printTree()` - Líneas 137-138, 173-174

```java
Pedido pedidoRaiz = raiz.getPedido();
System.out.println(pedidoRaiz.getTipo() + " (" + pedidoRaiz.getTiempoPreparacion() + " min)");
```

**Problema:** Aunque usa variable intermedia, sigue accediendo a métodos de `Pedido` desde `ArbolPedidos`.

## Solución: Métodos delegados en Nodo

### Métodos agregados en `Nodo.java`:

```java
public String getTipo() {
    return pedido.getTipo();
}

public int getTiempoPreparacion() {
    return pedido.getTiempoPreparacion();
}

public void incrementarEspera() {
    pedido.incrementarEspera();
}

public int getTiempoEspera() {
    return pedido.getTiempoEspera();
}
```

## Código corregido

### 1. `insertar()` - Ahora línea 23

```java
if (pedido.getTiempoPreparacion() < actual.getTiempoPreparacion()) {
```

### 2. `incrementarEspera()` - Ahora línea 91

```java
actual.incrementarEspera();
```

### 3. `tiempoTotalEspera()` - Ahora línea 119

```java
total += actual.getTiempoEspera();
```

### 4. `extraerMinimo()` - Ahora líneas 52-60

```java
Nodo padre = raiz;
Nodo hijoIzquierdo = padre.getIzquierdo();
while (hijoIzquierdo.getIzquierdo() != null) {
    comparaciones++;
    padre = hijoIzquierdo;
    hijoIzquierdo = padre.getIzquierdo();
}
min = hijoIzquierdo.getPedido();
padre.setIzquierdo(hijoIzquierdo.getDerecho());
```

### 5. `printTree()` - Ahora líneas 137, 172

```java
System.out.println(raiz.getTipo() + " (" + raiz.getTiempoPreparacion() + " min)");
System.out.println(prefix + connector + side + nodo.getTipo() + " (" + nodo.getTiempoPreparacion() + " min)");
```

## Comparación: antes y después

<div align=center>

|Método|Violaciones antes|Violaciones después|
|-|-|-|
|`insertar()`|1 cadena|0|
|`extraerMinimo()`|3 cadenas|0|
|`incrementarEspera()`|1 cadena|0|
|`tiempoTotalEspera()`|1 cadena|0|
|`printTree()`|4 cadenas|0|
|**TOTAL**|**10 violaciones**|**0 violaciones**|

</div>

## Vista pública actualizada

### Nodo.java

<div align=center>

|Métodos agregados
|-
public Nodo(Pedido pedido)
public Pedido getPedido()
public String getTipo() - ***NUEVO***
public int getTiempoPreparacion() - ***NUEVO***
public void incrementarEspera() - ***NUEVO***
public int getTiempoEspera() - ***NUEVO***
public Nodo getIzquierdo()
public void setIzquierdo(Nodo izquierdo)
public Nodo getDerecho()
public void setDerecho(Nodo derecho)

</div>

## Referencias

- **Ley de Demeter:** Lieberherr, K. (1987) - Northeastern University
- **Principio de mínimo conocimiento:** Design Patterns (Gang of Four)
- **Tell, Don't Ask:** Pragmatic Programmer

## Relación con Diseño por Contrato

Este refactoring de Ley de Demeter se aplica además de las mejoras de diseño por contrato (assertions) que ya están en la rama `reto-005`.

- Diseño por Contrato: Precondiciones, postcondiciones (assertions)
- Ley de Demeter: Estructura de dependencias, encapsulación
