# Documentación

## Arquitectura y Diseño

### Diagrama de Clases (Vista Pública)
El sistema se ha simplificado para eliminar abstracciones innecesarias y centrarse en objetos concretos.

* **Cliente** $\rightarrow$ **Menu** $\rightarrow$ **Arreglo/Lista** $\rightarrow$ **Nodo**

### Patrones Utilizados
1.  **Composición sobre Herencia:** La clase `Arreglo` **tiene una** `Lista` interna para almacenar datos, en lugar de heredar de ella. Esto permite restringir comportamientos (como impedir agregar elementos más allá de la capacidad definida) encapsulando la lógica dinámica.
2. **Programación defensiva:** Se evita el uso de programación denfensiva 
---

## 3. Refactorización

### A. Estructura
* **Antes:** Existían `ListaAbstracta.java` y `NodoAbstracto.java` que definían métodos abstractos y atributos protegidos. `Lista` y `Nodo` heredaban de ellos.
* **Ahora:** Se eliminaron las clases abstractas. `Lista` y `Nodo` son clases independientes y autosuficientes.
    * *Motivo:* Reducir la complejidad innecesaria.

### B. Control de Flujo
* **Antes:** Los bucles `while` o `for` utilizaban la sentencia `break` para detener la búsqueda una vez encontrado un elemento.
* **Ahora:** Se utilizan variables de control booleanas.

### C. Validación de Datos 
* **Antes:** Se utilizaba **Programación Defensiva**. El código verificaba con `if` si el índice era válido y, si no lo era, imprimía un error en consola (`System.out.println`) o retornaba `null`.
* **Ahora:** Se utiliza **Programación por Contrato** mediante `assert`.
   
### D. Optimización del Nodo
* **Antes:** La clase `Nodo` tenía un atributo `int posicion` que debía actualizarse manualmente cada vez que la lista cambiaba.
* **Ahora:** Se eliminó el atributo `posicion`. La posición es un concepto lógico calculado en tiempo de ejecución por la clase `Lista`.
---

## 4. Descripción de Clases

### `Nodo.java`
Clase que almacena el dato y la referencia al siguiente nodo.
* `Object dato`: El valor almacenado.
* `Nodo siguiente`: Puntero al siguiente nodo.

### `Lista.java`
* **Gestión dinámica:** Crece y decrece según necesidad.
* **Métodos principales:**
    * `insertar(valor, posicion)`: Recorre la lista usando un contador y una bandera booleana para encontrar el punto de inserción.
    * `eliminar(indice)`: Desvincula un nodo reajustando los punteros.
    * `obtener(indice)`: Recupera el dato en la posición $n$.

### `Arreglo.java`
* **Constructor:** Recibe una `capacidad`. Inicializa internamente una `Lista` y la rellena inmediatamente con ceros (`0`) hasta alcanzar dicha capacidad.

### `Menu.java`
* Valida que las entradas sean números enteros antes de consumirlas para evitar excepciones de tipo.

---
