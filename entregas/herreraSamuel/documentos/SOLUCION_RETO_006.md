# Reto 006: Estructuras de Datos para Iris 🪻

## 1. Estructuras Planteadas

Para modelar la solución de Iris hemos seleccionado tres estructuras fundamentales que equilibran la velocidad de búsqueda con la flexibilidad de gestión de usuarios.

### A. Árbol Binario de Búsqueda (BST) - El Catálogo
Utilizamos un **Árbol (BST)** para gestionar las asignaturas.
* **¿Por qué?** El sistema recibirá códigos como "MAT101" o "HIS200". Necesitamos encontrar la asignatura correspondiente muy rápido. Un árbol nos permite descartar la mitad de las asignaturas en cada comparación, siendo mucho más eficiente que recorrer una lista eterna de materias.
* **Ordenación:** El árbol se ordena alfabéticamente por el código de la asignatura.

### B. Listas Enlazadas - Los Usuarios
Usaremos listas en dos lugares clave:
1.  **En la Asignatura:** Cada nodo del árbol tendrá una lista de `Suscriptores`. Usamos una lista porque el número de alumnos cambia constantemente (se apuntan y desapuntan) y la lista maneja bien esa memoria dinámica.
2.  **En el Estudiante (Bidireccionalidad):** Cada estudiante tendrá una lista interna llamada `misAsignaturas`. Esto es vital para saber rápidamente en qué está matriculado un alumno sin tener que preguntar a todo el sistema.

### C. Cola (Queue) - El Buffer
Usamos una **Cola (FIFO)** para la entrada de mensajes.
* **¿Por qué?** Si el Campus Virtual envía 50 correos en un segundo, Iris no puede procesarlos todos a la vez. La cola los mantiene en orden de llegada y asegura que ninguno se pierda mientras el sistema los procesa uno a uno.