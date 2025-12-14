# Simulación y estructuras de datos

## ¿Por qué?

En la enseñanza y práctica convencional de estructuras de datos, existe una confusión fundamental que limita la comprensión profunda. Los estudiantes y desarrolladores suelen memorizar definiciones como "un array es memoria contigua" o "una lista son nodos enlazados", sin cuestionar qué significa realmente "ser" una estructura de datos.

Esta aproximación genera varios problemas:

Los principiantes asocian cada estructura con una implementación específica, sin entender que pueden existir múltiples implementaciones para el mismo comportamiento. Cuando se enfrentan a sistemas donde ciertas estructuras no están disponibles nativamente, carecen de las herramientas conceptuales para simularlas. Existe una desconexión entre la teoría abstracta y las implementaciones concretas, donde los mismos conceptos se materializan de formas diferentes según el lenguaje o contexto.

Pero el problema más profundo es la incapacidad para reconocer que todas las estructuras de datos son, en esencia, contratos de comportamiento más que entidades físicas inmutables.

## ¿Qué?

La simulación de estructuras de datos propone un cambio de perspectiva: en lugar de definir las estructuras por su implementación interna, las definimos por su comportamiento externo y las reglas que gobiernan su uso.

Este enfoque considera que cualquier estructura de datos es esencialmente un conjunto de operaciones con garantías específicas sobre su comportamiento. Un array no "es" memoria contigua, sino cualquier implementación que ofrezca acceso indexado en tiempo constante, tamaño fijo y homogeneidad de tipos. Una lista no "son" nodos enlazados, sino cualquier implementación que permita inserción y eliminación eficiente en extremos, crecimiento dinámico y recorrido secuencial.

La simulación consciente y explícita consiste en tomar una estructura disponible y restringirla o adaptarla para que cumpla el contrato de comportamiento de otra estructura diferente.

## ¿Para qué?

Este enfoque resuelve directamente los problemas identificados:

Permite a los desarrolladores crear las estructuras que necesitan incluso cuando el lenguaje o entorno no las provee nativamente. Por ejemplo, simular un array en un lenguaje que solo tiene listas, o viceversa.

Facilita la comprensión conceptual al separar claramente la interfaz de la implementación. Los estudiantes entienden que lo que define a una estructura son sus operaciones y garantías, no su representación en memoria.

Desarrolla el pensamiento crítico sobre costos computacionales. Al simular un array con una lista, se hace evidente que aunque la interfaz es idéntica, el rendimiento puede ser radicalmente diferente.

Prepara para situaciones reales donde las restricciones del sistema obligan a implementaciones no convencionales, como en sistemas embebidos, distribuidos o con limitaciones de memoria.

 Enriquece el diseño de software al hacer visible el principio de sustitución de Liskov en estructuras de datos: si dos implementaciones cumplen el mismo contrato, deberían ser intercambiables.

## ¿Cómo?

La implementación sigue un patrón consistente: identificar el contrato de la estructura objetivo y implementarlo usando la estructura disponible, imponiendo las restricciones necesarias.

Para simular un array usando una lista:

```java
public class ArraySimulado {
    private Lista lista;
    private final int capacidad;
    
    public ArraySimulado(int capacidad) {
        assert capacidad > 0 : "La capacidad debe ser positiva";
        this.capacidad = capacidad;
        this.lista = new Lista();
        
        // Imponer tamaño fijo mediante pre-llenado
        for (int i = 0; i < capacidad; i++) {
            lista.agregar(0);
        }
    }
    
    public int obtener(int indice) {
        assert indice >= 0 && indice < capacidad : "Índice fuera de rango";
        return lista.obtener(indice);
    }
    
    public void establecer(int indice, int valor) {
        assert indice >= 0 && indice < capacidad : "Índice fuera de rango";
        lista.modificar(indice, valor);
    }
    
    public int longitud() {
        return capacidad;
    }
}
```

Para simular una lista usando un array:

```java
public class ListaSimulada {
    private int[] elementos;
    private int tamaño;
    
    public ListaSimulada() {
        this.elementos = new int[10];
        this.tamaño = 0;
    }
    
    public void agregar(int valor) {
        // Permitir crecimiento dinámico controlado
        if (tamaño == elementos.length) {
            int[] nuevo = new int[elementos.length * 2];
            System.arraycopy(elementos, 0, nuevo, 0, elementos.length);
            elementos = nuevo;
        }
        elementos[tamaño++] = valor;
    }
    
    public void eliminar(int indice) {
        assert indice >= 0 && indice < tamaño : "Índice fuera de rango";
        
        // Simular re-enlazado mediante desplazamiento
        for (int i = indice; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tamaño--;
    }
    
    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño : "Índice fuera de rango";
        return elementos[indice];
    }
}
```

El caso más revelador es la simulación circular (uroboro), donde se demuestra que las capas de abstracción pueden ser múltiples mientras se mantenga el contrato:

```java
// Array que usa Lista que internamente usa array
public class ArrayUroboro {
    private ListaSimulada lista;
    private final int capacidad;
    
    public ArrayUroboro(int capacidad) {
        assert capacidad > 0 : "La capacidad debe ser positiva";
        this.capacidad = capacidad;
        this.lista = new ListaSimulada();
        
        for (int i = 0; i < capacidad; i++) {
            lista.agregar(0);
        }
    }
    
    public int obtener(int indice) {
        assert indice >= 0 && indice < capacidad : "Índice fuera de rango";
        return lista.obtener(indice); // O(n) oculto
    }
    
    // Misma interfaz que ArraySimulado
    // Mismo comportamiento observable
    // Implementación radicalmente diferente
}
```

Las mejores prácticas incluyen: documentar claramente las garantías de rendimiento, usar aserciones para hacer explícito el contrato, y proporcionar métodos de inspección que revelen el costo real de las operaciones.

La implementación específica varía según el lenguaje, pero el patrón conceptual permanece: definir primero el comportamiento esperado, luego implementarlo con las estructuras disponibles, y finalmente verificar que se cumplan todas las garantías del contrato original.