#  Explicación de la clase `ListaPorBloquesEnteros`

##  Concepto general
La clase **`ListaPorBloquesEnteros`** representa una lista dinámica de enteros,  
implementada **a partir de arreglos organizados en bloques**.  
En lugar de usar un único array que crece y se copia constantemente,  
la información se distribuye en **múltiples contenedores más pequeños** llamados **bloques**.

Cada bloque tiene un **tamaño fijo** (`capacidadBloque`)  
y almacena un subconjunto de los elementos de la lista.  
Cuando un bloque se llena, se **crea uno nuevo** para seguir almacenando datos.  
Esto permite manejar grandes volúmenes de información de manera más eficiente.

---

##  Estructura interna
- **Bloques:** son pequeños arreglos de enteros de tamaño fijo.  
- **Lista principal:** contiene todos los bloques y gestiona la lógica de inserción, eliminación y acceso.  
- **Capacidad lógica:** cantidad real de elementos en la lista.  
- **Capacidad física:** número de bloques × tamaño de cada bloque.

Cada vez que se agregan o eliminan elementos,  
la clase se encarga de mantener los bloques equilibrados,  
agregando nuevos cuando crece y eliminando los vacíos cuando se reduce.

---

## Funcionamiento general por método

### `public ListaPorBloquesEnteros(int capacidadBloque)`
Constructor que inicializa la lista vacía con la **capacidad de cada bloque**.  
Crea el primer bloque vacío y prepara la estructura para almacenar datos.

---

### `public void agregarAlFinal(int elemento)`
Agrega un elemento al **final de la lista**:
1. Busca el último bloque disponible.  
2. Si ese bloque está lleno, **crea un nuevo bloque**.  
3. Inserta el elemento dentro del último bloque.

*Si la lista crece, se van añadiendo bloques automáticamente.*

---

### `public void eliminarEn(int posicion)`
Elimina un elemento en una **posición específica**:
1. Localiza el bloque correspondiente y la posición exacta dentro de él.  
2. Desplaza los elementos del mismo bloque hacia la izquierda.  
3. Si el bloque queda vacío, puede eliminarse.  
4. En caso necesario, mueve el primer elemento del siguiente bloque al anterior para **mantener la continuidad**.

---

### `public int obtener(int posicion)`
Devuelve el elemento almacenado en una posición determinada.  
Para lograrlo, se calcula en qué bloque se encuentra el índice solicitado  
y se obtiene el valor directamente del bloque correspondiente.

---

### `public void reemplazar(int posicion, int elemento)`
Sustituye el elemento existente en la posición indicada por un nuevo valor,  
sin alterar la estructura de bloques.

---

### `public int totalElementos()`
Devuelve el **número total de elementos** que contiene la lista,  
sumando los elementos de todos los bloques.

---

### `public boolean estaVacia()`
Indica si la lista no contiene elementos.  
Devuelve `true` si no hay ningún dato almacenado.

---

### `public void limpiar()`
Elimina todos los elementos de la lista,  
reiniciando la estructura a su estado inicial con un solo bloque vacío.

---

### `public int[] aArray()`
Convierte la lista completa en un **arreglo continuo de enteros**,  
uniendo todos los bloques en un solo array de salida.

---

### `public void mostrarEstructura()`
Muestra en consola la organización interna de la lista,  
imprimiendo la cantidad de bloques, su tamaño y los elementos que contiene cada uno.  
Es útil para depurar o visualizar cómo están distribuidos los datos.

---

##  Mecanismo interno: clase “Bloque”
Cada bloque funciona como un **contenedor de tamaño fijo**.  
Dentro de cada bloque, las operaciones de inserción, obtención y eliminación  
se realizan de manera directa y rápida.

**Inserción:**  
Cuando se agrega un elemento, se coloca al final del bloque.  
Si el bloque está lleno, el sistema crea un nuevo bloque y continúa allí.

**Eliminación:**  
Cuando se elimina un elemento:
- Los elementos dentro del bloque se desplazan hacia la izquierda.  
- Si un bloque queda vacío, se puede eliminar o reservar para reutilizar.  
- Si un bloque se llena o vacía, el sistema ajusta dinámicamente la estructura.

**Acceso:**  
El acceso a un elemento se realiza calculando:
1. En qué bloque se encuentra.  
2. Su posición interna dentro del bloque.

Esto permite mantener un equilibrio entre **velocidad de acceso** y **eficiencia en memoria**.

---


---

