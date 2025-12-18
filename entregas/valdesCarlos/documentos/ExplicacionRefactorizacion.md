# Refactorización del Examen Parcial (Array --> Lista)

En el examen parcial implementamos una lista con un array con la utilización de una clase `Nodo`. Al refactorizar este ejercicio hemos eliminado esa clase ya que estaba mal implementado. La nueva versión utiliza un almacenamiento contiguo (un array interno de enteros) para gestionar los datos de forma directa y rápida.

---

## Explicación del Funcionamiento (Paso a paso)

A continuación, explico la lógica que sigue el proyecto para cumplir con los requisitos:

### 1. Clase `Lista`
Esta es la parte que "trabaja" por debajo. Al haber eliminado los nodos, esta clase ahora gestiona un espacio de memoria simple para guardar números enteros.
* Su función es almacenar los datos reales.
* Permite acceder directamente a una posición concreta sin tener que recorrer las anteriores (acceso directo).

### 2. Clase `Array`
Esta es la clase que usa el "Cliente". Actúa como un intermediario o controlador.
* Su función principal es **poner límites**. Aunque la lista interna podría crecer, esta clase se asegura de que el tamaño se mantenga fijo, tal y como se comporta un array real.
* Protege los datos: verifica que nadie intente leer o escribir fuera de las posiciones permitidas.

### 3. Array (Constructor)
Cuando el programa empieza y se pide crear un Array (por ejemplo, de tamaño 4), ocurren estos pasos lógicos:
1.  Se define la capacidad máxima (4).
2.  Se crea la lista interna vacía.
3.  Automáticamente, se rellena esa lista con **ceros** hasta completar el tamaño deseado.
* **Resultado:** El usuario recibe una estructura lista para usarse, rellena de ceros, simulando un array vacío de Java.

### 4. Operaciones de Guardado y Lectura (set y get)
Cuando queremos manipular datos, el proceso es el siguiente:
1.  **Validación:** El programa comprueba primero si la posición que pedimos es válida (que no sea negativa ni mayor que el tamaño del array).
2.  **Delegación:** Si la posición es correcta, la clase `Array` le da la orden a la lista interna.
3.  **Acceso:** La lista va directamente a la memoria y guarda o recupera el dato al instante.
