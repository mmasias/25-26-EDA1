# Ser vs. Actuar como

> ***Cuando un Array se hace pasar por Lista***

## ¿Por qué?

Pregunta rápida: ¿Alguna vez ha usado `ArrayList` en Java, `list` en Python, o `vector` en C++?

Ahora la pregunta incómoda: **¿Se sabe cómo funcionan internamente?**

Spoiler: Son arrays. Arrays normales y corrientes.

Pero un momento... Esas estructuras se usan como listas dinámicas: se agregan elementos sin límite, se eliminan del medio, se insertan donde se quiera. ¿Cómo puede un array RÍGIDO (tamaño fijo) actuar como una lista FLEXIBLE (tamaño variable)?

El problema conceptual es profundo:

1. **Se confunde la interfaz con la limitación natural**: Se piensa que "array = rígido" y "lista = flexible" son características inherentes, no decisiones de diseño

2. **Se ignora que toda flexibilidad tiene un precio**: El `ArrayList.add()` parece mágico, pero alguien está pagando el costo de redimensionar, copiar, mover elementos

3. **Se asume que las limitaciones físicas dictan el comportamiento**: Si un array es rígido "por naturaleza", ¿cómo puede comportarse como algo flexible?

El escenario problemático: **¿Puede una estructura rígida simular flexibilidad? ¿O estamos siendo engañados por una ilusión bien diseñada?**

## ¿Qué?

Propuesta radical: **La rigidez y flexibilidad no son propiedades intrínsecas, son decisiones de diseño**.

Un array no es "rígido por naturaleza". Lo que es rígido es **un bloque de memoria contigua de tamaño fijo**. Pero se puede construir SOBRE esa rigidez para crear la ILUSIÓN de flexibilidad.

Una lista no es "flexible por esencia". Lo que es flexible es **un protocolo de acceso que permite crecer/decrecer dinámicamente**. Este protocolo puede implementarse sobre cualquier sustrato, incluso uno rígido.

Este proyecto demuestra DOS estrategias opuestas para simular flexibilidad:

### Estrategia 1: **Redimensionamiento Dinámico** (l000)

Cuando el array se llena, se crea uno más grande y se copia todo. Esconde la rigidez mediante expansión periódica.

### Estrategia 2: **Gestión de Espacios Libres** (l001)

Se usa un array como si fuera un "espacio de trabajo" donde los elementos no están necesariamente contiguos. Recicla posiciones eliminadas mediante una "free list".

**La pregunta filosófica**: Si estas implementaciones exponen una interfaz de lista perfecta, ¿SON listas? ¿O siguen siendo arrays que "actúan como" listas?

## ¿Para qué?

Esta perspectiva resuelve los problemas planteados:

### 1. Rompe la asociación rígida entre estructura física y comportamiento

Ya no se piensa "arrays son rígidos, listas son flexibles". Ahora se entiende:

- Un array puede ACTUAR flexiblemente si redimensiona
- Una lista puede SER rígida si se prohíbe agregar más allá de su capacidad inicial

**Beneficio**: Se libera la creatividad para diseñar estructuras con cualquier combinación de propiedades físicas y comportamentales.

### 2. Revela las estrategias de simulación que ya se usan a diario

Cuando se escribe `ArrayList.add()` en Java, este proyecto muestra QUÉ está sucediendo internamente. **Beneficio**: Se aprende a predecir cuándo operaciones "simples" son realmente costosas (ej: agregar al ArrayList lleno causa O(n) por copia completa).

### 3. Expone los trade-offs entre las estrategias

- **l000** (redimensionar) es simple pero desperdicia memoria en cada redimensión
- **l001** (free list) es eficiente pero conceptualmente complejo

**Beneficio**: Se entiende por qué `ArrayList` crece con factor 1.5x o 2x (equilibrio entre memoria desperdiciada y frecuencia de redimensionamiento).

### 4. Demuestra que "ser" y "actuar como" son indistinguibles desde fuera

Un cliente que usa esta `Lista` no puede saber si internamente se usan arrays, nodos enlazados, o magia. **Beneficio**: Se comprende el valor de la encapsulación y por qué cambiar implementaciones sin romper contratos es posible.

### 5. Plantea la pregunta incómoda

Si `ArrayList` en Java ES un array internamente pero ACTÚA como lista, ¿qué es realmente? ¿Importa la respuesta?

**El objetivo pedagógico**: Destruir la ilusión de que las estructuras de datos tienen "esencias" inmutables. Todo es ingeniería de abstracciones.

## ¿Cómo?

### Estrategia 1: Redimensionamiento Dinámico (l000)

La técnica más común en lenguajes modernos: esconde la rigidez mediante expansión periódica.

```java
// listaConArray/src/l000/Lista.java
public class Lista {
    private int[] elementos;
    private int tamaño;  // Elementos actuales
    // capacidad = elementos.length (implícito)

    public Lista() {
        elementos = new int[10];  // Capacidad inicial arbitraria
        tamaño = 0;
    }

    public void agregar(int valor) {
        if (tamaño == elementos.length) {
            redimensionar();  //  Aquí está el COSTO OCULTO
        }
        elementos[tamaño] = valor;
        tamaño++;
    }

    private void redimensionar() {
        int nuevaCapacidad = elementos.length * 2;  // Crece exponencialmente
        int[] nuevoArray = new int[nuevaCapacidad];

        //  Copia completa O(n)
        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = elementos[i];
        }

        elementos = nuevoArray;  // Abandona el array viejo
        // Garbage collector se encarga de la memoria vieja
    }

    public void eliminar(int indice) {
        assert indice >= 0 && indice < tamaño;

        //  Desplazamiento O(n) para mantener contigüidad
        for (int i = indice; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tamaño--;
    }

    public int obtener(int indice) {
        assert indice >= 0 && indice < tamaño;
        return elementos[indice];  //  O(1) - Aquí el array brilla
    }
}
```

#### Análisis del Engaño (l000)

**Lo que el cliente ve:**

```java
Lista lista = new Lista();
lista.agregar(10);  // "Puedo agregar infinitamente"
lista.agregar(20);
lista.agregar(30);
// ... agregar 1 millón de elementos
```

**La realidad interna:**

```
Capacidad inicial: 10
Agregar elemento 11 → Redimensiona a 20 → Copia 10 elementos
Agregar elemento 21 → Redimensiona a 40 → Copia 20 elementos
Agregar elemento 41 → Redimensiona a 80 → Copia 40 elementos
...

Total de copias para llegar a 1M elementos: ≈ 2 millones de operaciones
```

**La paradoja**: Cada `agregar()` parece O(1), pero periódicamente es O(n). El costo se **amortiza** - es O(1) en promedio pero O(n) en el peor caso.

#### Trade-offs de l000

| Aspecto | Ventaja | Desventaja |
||||
| **Simplicidad** |  Código simple y directo | - |
| **Acceso** |  O(1) como array nativo | - |
| **Agregar (amortizado)** |  O(1) en promedio |  O(n) ocasionalmente |
| **Memoria** |  Desperdicia hasta 50% | Después de redimensionar, mitad del espacio vacío |
| **Eliminar** |  O(n) siempre | Debe desplazar elementos |

### Estrategia 2: Gestión de Espacios Libres (l001)

Enfoque más sofisticado: trata el array como "memoria" donde los elementos no están necesariamente contiguos.

```java
// listaConArray/src/l001/Lista.java
public class Lista {
    private int[][] nodos;  // [data, nextIndex] - Simula nodos
    private int cabeza;     // Índice del primer elemento
    private int libres;     // Índice de la primera posición libre
    private int tamaño;
    private int siguienteNuevo;  // Próxima posición nunca usada

    private static final int DATO = 0;
    private static final int SIGUIENTE = 1;
    private static final int FIN = -1;

    public Lista() {
        nodos = new int[10][2];
        cabeza = FIN;
        libres = FIN;
        tamaño = 0;
        siguienteNuevo = 0;
    }

    public void agregar(int valor) {
        int nuevoIndice;

        // ¿Hay posiciones recicladas disponibles?
        if (libres != FIN) {
            nuevoIndice = libres;
            libres = nodos[libres][SIGUIENTE];  // Pop de la free list
        } else {
            // Usar nueva posición
            if (siguienteNuevo == nodos.length) {
                redimensionar();  // Solo cuando REALMENTE no hay espacio
            }
            nuevoIndice = siguienteNuevo;
            siguienteNuevo++;
        }

        // Insertar al inicio (más simple)
        nodos[nuevoIndice][DATO] = valor;
        nodos[nuevoIndice][SIGUIENTE] = cabeza;
        cabeza = nuevoIndice;
        tamaño++;
    }

    public void eliminar(int posicion) {
        assert posicion >= 0 && posicion < tamaño;

        // Navegar hasta el nodo anterior
        if (posicion == 0) {
            int eliminado = cabeza;
            cabeza = nodos[cabeza][SIGUIENTE];

            // Agregar a la free list
            nodos[eliminado][SIGUIENTE] = libres;
            libres = eliminado;
        } else {
            // Recorrer hasta posición-1
            int actual = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                actual = nodos[actual][SIGUIENTE];
            }

            int eliminado = nodos[actual][SIGUIENTE];
            nodos[actual][SIGUIENTE] = nodos[eliminado][SIGUIENTE];

            // Reciclar el espacio
            nodos[eliminado][SIGUIENTE] = libres;
            libres = eliminado;
        }
        tamaño--;
    }

    public int obtener(int posicion) {
        assert posicion >= 0 && posicion < tamaño;

        int actual = cabeza;
        for (int i = 0; i < posicion; i++) {  //  O(n)
            actual = nodos[actual][SIGUIENTE];
        }
        return nodos[actual][DATO];
    }
}
```

#### Análisis del engaño (l001)

**Visualización de la memoria:**

```
Array físico (nodos):
Índice: [0]    [1]    [2]    [3]    [4]    [5]    [6]
Dato:   [50]   [--]   [30]   [10]   [--]   [70]   [20]
Next:   [6]    [4]    [5]    [0]    [1]    [-1]   [2]

cabeza = 3  (apunta a 10)
libres = 1  (posiciones reciclables: 1 → 4)
tamaño = 5

Orden lógico: 10 → 50 → 20 → 30 → 70
Orden físico: [3] → [0] → [6] → [2] → [5]
```

**El truco**: Los elementos están DISPERSOS en el array, pero enlazados lógicamente mediante índices. Es una **lista enlazada SIMULADA sobre un array**.

#### Compromisos de l001

| Aspecto | Ventaja | Desventaja |
||||
| **Memoria** |  Recicla espacios eliminados |  Overhead de almacenar índices |
| **Agregar** |  O(1) siempre (al inicio) | - |
| **Eliminar** |  No desperdicia espacio |  O(n) para acceder + modificar |
| **Acceso** |  O(n) como lista enlazada | Perdemos el beneficio del array |
| **Complejidad** |  Conceptualmente difícil | Requiere entender free lists |



### ¿Cuál es mejor?

| Operación | Array Nativo | l000 (Redimensionar) | l001 (Free List) | Lista Enlazada |
|--|--|||-|
| `agregar` | N/A (fijo) | O(1) amortizado | O(1) | O(1) |
| `obtener(i)` | O(1) | O(1) | O(n) | O(n) |
| `eliminar(i)` | N/A | O(n) | O(n) | O(n) |
| Memoria desperdiciada | 0% | ~50% | Mínimo | Overhead punteros |

**Revelación**: l000 sacrifica memoria por velocidad de acceso. l001 sacrifica acceso rápido por eficiencia de memoria. **No hay almuerzo gratis**.

## ¿Cuándo?

### Cuándo usar cada estrategia

#### Usar l000 (Redimensionamiento) cuando:

- Se necesita acceso rápido por índice (O(1))
- Se agrega principalmente al final
- Raramente se elimina del medio
- Hay memoria disponible
- **Ejemplo real**: `ArrayList` en Java, `vector` en C++, `list` en Python

#### Usar l001 (Free List) cuando:

- Se elimina frecuentemente y se quiere reciclar espacio
- El acceso por índice no es crítico
- La memoria es limitada
- Se necesita predecir el uso de memoria máximo
- **Ejemplo real**: Administradores de memoria, pools de objetos, caches con reemplazo

#### No usar estas simulaciones cuando:

- Se puede usar lista enlazada nativa (y no se necesita acceso O(1))
- Se puede usar array nativo (y el tamaño es conocido y fijo)
- El rendimiento es crítico y las constantes importan

### Cuándo importa la diferencia entre "ser" y "actuar como"

**Importa en:**

1. **Análisis de complejidad**: El código puede ser O(n²) sin que se note
2. **Debugging de memoria**: ¿Por qué la lista de 100 elementos usa 1MB?
3. **Optimización**: Saber CUÁNDO redimensiona permite pre-alocar

**No importa en:**

1. **Prototipado rápido**: Funciona = suficiente
2. **Aprendizaje de interfaces**: Solo importa el contrato
3. **Código de negocio**: La lógica domina sobre la estructura

## ¿Alternativas?

### 1. **Lista Enlazada Nativa**

Usa nodos con punteros reales. Flexibilidad genuina, no simulada. Pero se pierde acceso O(1) y localidad de caché.

### 2. **Hybrid: Unrolled List**

Array de nodos pequeños - cada nodo contiene un mini-array. Equilibrio entre acceso rápido y flexibilidad. Usado en bases de datos.

### 3. **Chunked Array**

Array de arrays de tamaño fijo. Al crecer, se agregan chunks sin copiar todo. Menos desperdicio que duplicar tamaño, pero acceso más complejo.

## ¿Y ahora qué?

### Preguntas para destruir certezas

1. **Pregunta existencial**: `ArrayList` en Java ES un array internamente. Pero ACTÚA como lista. Entonces, ¿qué ES? ¿Array o lista?

2. **Pregunta práctica**: Si el código depende de que `ArrayList.add()` sea O(1), se está ASUMIENDO algo sobre la implementación que la interfaz no garantiza. ¿Es esto un bug esperando a suceder?

3. **Pregunta de diseño**: Si se pudiera diseñar una estructura desde cero sin restricciones de memoria/CPU, ¿seguiría siendo necesario distinguir entre "array" y "lista"?

4. **Pregunta límite**: En este proyecto, un array actúa como lista. En el otro proyecto, una lista actúa como array. ¿Existe alguna diferencia REAL entre estas estructuras más allá de las convenciones?

### Experimentos mentales devastadores

1. **El dilema del redimensionamiento**: `ArrayList` crece 1.5x en Java. ¿Por qué no 2x? ¿O 1.1x? Investigar el trade-off entre memoria desperdiciada y frecuencia de copias.

2. **Medir el costo real**: Agregar 1 millón de elementos a l000 y contar cuántas operaciones de copia realmente ocurren. ¿Confirma el análisis teórico de complejidad amortizada?

3. **Fragmentación de l001**: En l001, después de muchas operaciones de agregar/eliminar, ¿qué tan "desordenado" queda el array interno? ¿Importa para el rendimiento?

4. **Comparar con el original**: Ir a `arrayConLista/` y comparar ambas simulaciones. ¿Son simétricas? ¿Qué revela esta simetría (o asimetría) sobre la naturaleza de las estructuras?

### El experimento final

Crear una interfaz `EstructuraIndexable`:

```java
interface EstructuraIndexable {
    void agregar(int valor);
    int obtener(int indice);
    void eliminar(int indice);
    int tamaño();
}
```

Implementar con:

- Array nativo (con tamaño fijo, `agregar` falla si lleno)
- Lista enlazada
- l000 (array con redimensionamiento)
- l001 (array con free list)

Escribir un único cliente que use solo la interfaz. **Demostrar que desde fuera, todas SON la misma cosa.**

### La verdad final

Este proyecto revela algo inquietante: **La distinción entre "array" y "lista" es arbitraria**.

Lo único real es:

- **Contratos de interfaz** (qué operaciones se prometen)
- **Garantías de complejidad** (qué rendimiento se promete)
- **Trade-offs de implementación** (qué costos se esconden)

Un `ArrayList` no es "un array que actúa como lista". Es **una estructura de datos que satisface cierto contrato usando cierta estrategia de implementación**.

Preguntar "¿pero QUÉ ES realmente?" es como preguntar "¿qué es realmente el dinero?" No es el papel ni el metal - es el consenso sobre su valor. Las estructuras de datos son consensos sobre contratos.

**Todo es simulación. Siempre lo fue.**

Ahora ir a `arrayConLista/serVsActuarComo.md` y descubrir la perspectiva opuesta. Cuando se regrese, nada será igual.
