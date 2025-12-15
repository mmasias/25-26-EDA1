# Compromisos y Costes Estructurales

Al elegir un diseño basado en **Árboles** y **Relaciones Bidireccionales** (donde el alumno conoce sus asignaturas y viceversa), asumimos los siguientes compromisos en el funcionamiento interno de la estructura:

## 1. Sacrificamos: Simplicidad en la Consistencia de Datos (Sincronización)
* **El Problema:** Al tener la relación guardada en dos sitios (Lista del Alumno y Lista de la Asignatura), creamos un riesgo de **desincronización**.
* **La Estructura Afectada:** Las Listas Enlazadas cruzadas.
* **El Coste:**
    * Cada vez que se hace una modificación (alta o baja), estamos obligados a actualizar dos estructuras distintas perfectamente.
    * **Riesgo:** Si el sistema falla justo a la mitad (añade al alumno a la asignatura pero no la asignatura al alumno), la estructura queda "corrupta" o inconsistente. Esto nos obliga a programar validaciones dobles que no serían necesarias en un sistema más simple.

## 2. Sacrificamos: Acceso Directo a los Elementos (Acceso Aleatorio)
* **El Problema:** Al usar Listas Enlazadas para los suscriptores, perdemos la capacidad de ir directamente a una posición (ej. "Dame el suscriptor número 50").
* **La Estructura Afectada:** La Lista de Suscriptores.
* **El Coste:**
    * Para llegar al último alumno de una lista, la estructura nos obliga a recorrer obligatoriamente a todos los anteriores, uno por uno (navegación secuencial). No podemos "saltar" posiciones como haríamos en un Array.

## 3. Sacrificamos: Facilidad de Reordenamiento
* **El Problema:** Los árboles BST organizan los datos según llegan. Si el árbol se desbalancea (ej. llegan IDs en orden: 1, 2, 3, 4...), la estructura se deforma convirtiéndose casi en una línea recta.
* **La Estructura Afectada:** Los Árboles de Alumnos y Asignaturas.
* **El Coste:**
    * A diferencia de una estructura autogestionada, nuestro árbol básico depende del orden de inserción. Si los datos entran muy ordenados, la estructura pierde su eficiencia de forma y se vuelve lenta estructuralmente, requiriendo algoritmos complejos para "re-balancearlo" si quisiéramos corregirlo.

## 4. Sacrificamos: Robustez ante fallos de Punteros
* **El Problema:** Todo el sistema se basa en cadenas de punteros (referencias de memoria).
* **La Estructura Afectada:** Todo el sistema (Nodos).
* **El Coste:**
    * En un Array, si se borra un dato, queda un hueco pero el resto sigue ahí.
    * En nuestra estructura de Listas y Árboles, si un puntero se rompe o se asigna mal (apunta a `null` indebidamente), perdemos el acceso a **toda la cadena** de datos que colgaba de él. La estructura es más frágil a errores de programación.