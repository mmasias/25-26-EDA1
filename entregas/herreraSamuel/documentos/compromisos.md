# Compromisos y Costes del Diseño ⚖️

Para conseguir un sistema rápido, hemos tenido que aceptar ciertos sacrificios (Trade-offs) en el diseño de las estructuras.

## 1. Redundancia de Datos (Memoria vs Velocidad)
Hemos decidido guardar la información por duplicado (Bidireccionalidad):
1.  La Asignatura tiene una lista de sus alumnos.
2.  El Alumno tiene una lista de sus asignaturas (`misAsignaturas`).

**¿Cuál es el coste?**
Consumimos más memoria RAM, ya que guardamos referencias dobles.

**¿Por qué vale la pena?**
Ganamos una velocidad inmensa al gestionar bajas. Si un alumno abandona la universidad (Exalumno), solo tenemos que leer su lista `misAsignaturas` e ir directamente a esos nodos del árbol para borrarlo.
* *Sin esta redundancia:* Tendríamos que recorrer TODO el árbol buscando al alumno en cada asignatura, lo cual sería inaceptablemente lento.

## 2. Complejidad en la Suscripción
Al tener datos redundantes, cuando alguien se apunta a una clase, hay que escribir en dos sitios: en el nodo del árbol y en la lista del alumno.

**¿Cuál es el coste?**
El código de "suscribir" es un poco más complejo y debemos asegurar que se hagan ambos pasos. Si el programa falla a la mitad, podríamos tener datos inconsistentes (que el alumno crea que está apuntado pero la asignatura no lo sepa).

## 3. Riesgo de Desbalanceo (BST)
Usamos un Árbol Binario estándar. Si insertamos las asignaturas en orden alfabético estricto (A, B, C...), el árbol podría quedar como una línea recta.

**¿Cuál es el coste?**
En el peor caso, la búsqueda dejaría de ser rápida ($O(\log n)$) y pasaría a ser lenta ($O(n)$). Asumimos este riesgo porque la implementación de un árbol AVL (auto-balanceado) es demasiado compleja para esta fase del proyecto.