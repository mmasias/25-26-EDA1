# Propuesta - Vista publica

- `Cliente`: capa de interacción con el usuario (consola).
- `CustomArray`: fachada que expone operaciones de arreglo.
- `ListaEnteros`: estructura de datos interna que almacena los enteros.

---

## 1. `Cliente`

**Propósito**: Aplicación ejecutable con menú interactivo.

### Método público
- `main(String[] args)`:  
  Crea un `CustomArray` con tamaño definido por el usuario y permite:
  - Mostrar todo el arreglo o un elemento específico.
  - Modificar o eliminar elementos por posición.
  - Salir del programa.


---

## 2. `CustomArray`

**Propósito**: Abstracción de un arreglo personalizado de enteros con operaciones básicas (CRUD por índice).

### Constructor
- `CustomArray(int tamaño)`:  
  Inicializa un arreglo del tamaño dado, lleno de ceros.

### Métodos públicos
- `void mostrarArray()` → Muestra todo el contenido: `[a, b, c, ...]`
- `void mostrarElemento(int posicion)` → Muestra el valor en la posición dada.
- `void modificarElemento(int posicion, int nuevoValor)` → Reemplaza el valor en la posición.
- `void eliminarElemento(int posicion)` → Elimina el elemento y reduce el tamaño.
- `int getTamaño()` → Devuelve la cantidad actual de elementos.


---

## 3. `ListaEnteros`

**Propósito**: Lista dinámica interna que gestiona el almacenamiento real de los datos.

### Constructor
- `ListaEnteros()` → Crea una lista vacía con capacidad inicial mínima.

### Métodos públicos
- `int getCantidadElementos()` → Número de elementos actuales.
- `void insertar(int valor, int posicion)` → Inserta en la posición (desplaza a la derecha).
- `void eliminar(int posicion)` → Elimina en la posición (desplaza a la izquierda).
- `int obtener(int posicion)` → Retorna el valor; si es inválido, devuelve `-1`.
- `void mostrar()` → Imprime el contenido en formato `[a, b, c, ...]`.
- `void mostrarPosicionesValidas()` → Muestra posiciones válidas para inserción (depuración).
---