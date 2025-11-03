# Simulación de Lista Dinámica con Array en Java

## Diferencias entre la idea inicial y la implementación

### 1. Tamaño y expansión (Parte 1 y 2)
**Idea inicial:**
- Mantener un tamaño lógico independiente del tamaño físico del array.
- Permitir expansión automática cuando se alcanza la capacidad máxima.
- Controlar el acceso solo dentro del rango válido según el tamaño lógico.

**Implementación:**
- Se creó la variable `cantidadElementos` para el tamaño lógico y un array `elementos` para el tamaño físico.
- La expansión se realiza mediante el método `expandir()`, que crea un nuevo array de tamaño `elementos.length + 1` y copia los elementos existentes.
- El método `obtener(int posicion)` controla el acceso fuera del rango de elementos usados, devolviendo -1 y mostrando un mensaje de error.

**Diferencia clave:**  
- La implementación es más simple: el tamaño inicial se fija en 1 y se expande de uno en uno, mientras que la idea inicial podía contemplar estrategias más complejas de expansión (como duplicar tamaño).

---

### 2. Inserción y eliminación (Parte 1 y 2)
**Idea inicial:**
- Para insertar, mover los elementos a la derecha para abrir un hueco.
- Para eliminar, mover los elementos a la izquierda para cerrar el hueco.
- Todo esto debería ser transparente para el usuario.

**Implementación:**
- `insertar(int valor, int posicion)` mueve los elementos de derecha a izquierda para crear espacio en la posición deseada.
- `eliminar(int posicion)` desplaza los elementos a la izquierda y limpia la última posición (`elementos[cantidadElementos - 1] = 0`) para marcarla como libre.
- `mostrarPosicionesValidas()` indica al usuario las posiciones donde puede insertar.

**Diferencia clave:**  
- Se añadió la función de mostrar posiciones válidas para mejorar la interacción con el usuario, algo que no estaba explícito en la descripción inicial.

---

### 3. Espacio libre y control de acceso (Parte 1 y 3)
**Idea inicial:**
- Manejar espacios libres dentro del array.
- Evitar que el usuario acceda fuera del rango válido.
- Simular un array flexible incluso sobre un array estático.

**Implementación:**
- No se manejan explícitamente espacios vacíos internos; se asume que las posiciones fuera de `cantidadElementos` no se usan.
- Se asegura que todas las operaciones respeten el rango válido (`0 <= posicion < cantidadElementos` para obtener y eliminar; `0 <= posicion <= cantidadElementos` para insertar).
- La expansión permite simular flexibilidad de un array estático.

**Diferencia clave:**  
- El concepto de “marcar espacios libres” se simplifica: se usa un valor `0` para limpiar la posición eliminada, pero internamente el array se considera ocupado hasta `cantidadElementos`.

---

### 4. Interfaz y experiencia de usuario
**Idea inicial:**
- Operaciones de inserción y eliminación deben ser automáticas y transparentes.
- Se planteaba la idea de controlar el tamaño lógico vs físico de manera interna.

**Implementación:**
- Se creó un menú interactivo en `Principal.java` con opciones para insertar, eliminar, obtener y mostrar elementos.
- La interfaz permite ver posiciones válidas antes de insertar, respetando el control del tamaño lógico.
- Mensajes de error claros cuando se intenta acceder fuera del rango.

**Diferencia clave:**  
- La implementación concreta incluye interacción por consola, que no estaba detallada en la descripción inicial.

