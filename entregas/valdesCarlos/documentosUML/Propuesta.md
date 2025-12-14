# Propuesta de Diseño de Estructuras de Datos - Proyecto Iris

## 1. Introducción
Para dar solución al reto de **Iris**, se requiere un sistema capaz de redirigir mensajes de forma masiva y eficiente. Dado que el número de alumnos y asignaturas es dinámico y no conocemos el tamaño final, se descartan los arrays estáticos.

La propuesta se basa en el uso de **Árboles de Búsqueda Binaria** para el almacenamiento y búsqueda rápida, combinados con una **Cola** para gestionar el flujo de mensajes.

## 2. Diagrama de Estructuras
[Ver Diagrama UML](Diagrama.puml)

## 3. Estructuras Elegidas y Justificación

### A. Árbol de Asignaturas (`arbolAsignaturas`)
* **Tipo:** Árbol Binario de Búsqueda.
* **Función:** Almacena todas las asignaturas disponibles ordenadas por su código (ej. "IYA022").
* **Justificación:** Cuando llega un mensaje, necesitamos encontrar su asignatura rápidamente. En una lista tendríamos que recorrer todos los elementos, mientras que en un árbol balanceado la búsqueda es mucho más rápida, reduciendo el tiempo de proceso.

### B. Árbol de Usuarios (`arbolUsuarios`)
* **Tipo:** Árbol Binario de Búsqueda.
* **Función:** Mantiene el registro central de todos los alumnos (nodos) ordenados por su ID o email.
* **Justificación:** Permite verificar si un alumno existe o gestionar sus datos (altas/bajas) sin tener que redimensionar memoria, como ocurriría con un Array.

### C. Suscriptores por Asignatura (`suscriptores`)
* **Tipo:** Árbol Binario de Búsqueda (dentro de cada Asignatura).
* **Función:** Cada asignatura tiene su propio "mini-árbol" con los IDs de los alumnos apuntados a ella.
* **Justificación:** Se elige un árbol en lugar de una lista para permitir que un alumno se **desapunte (dar de baja)** de forma instantánea sin tener que recorrer toda la lista de la clase buscando su nombre.

### D. Cola de Mensajes (`colaMensajes`)
* **Tipo:** Cola FIFO (First In, First Out).
* **Función:** Buffer temporal donde se guardan los mensajes tal cual llegan.
* **Justificación:** Es necesaria para **desacoplar** la recepción del envío. Si llegan 100 correos en un segundo, el sistema no se bloquea; los encola y los procesa uno a uno ordenadamente.

---

## 4. Compromisos (Lo que sacrificamos)

Al elegir esta arquitectura basada en punteros y árboles, aceptamos los siguientes costes:

1.  **Complejidad:** Programar un Árbol Binario (especialmente el borrado de nodos) es más difícil y propenso a errores que usar una lista simple.
2.  **Memoria:** Cada dato necesita espacio extra para guardar las direcciones de sus nodos hijos (izquierda y derecha), ocupando más memoria RAM que un array compacto.
3.  **Mantenimiento (Balanceo):** Si los datos entran ya ordenados (A, B, C...), el árbol se deforma y parece una línea, volviéndose lento. Asumimos que los datos llegan desordenados o que implementaremos un mecanismo de balanceo futuro.

---

## 5. Análisis de Casos Límite

* **Asignatura sin alumnos:** El sistema busca en el `arbolAsignaturas`, encuentra el nodo, pero su árbol interno de `suscriptores` está vacío (puntero a null). El proceso se detiene limpiamente sin enviar nada.
* **Alumno sin asignaturas:** El alumno existe en el `arbolUsuarios` (puede hacer login), pero su ID no aparece en ningún árbol de suscriptores de ninguna asignatura. El sistema simplemente nunca le notificará.
* **Exalumnos:** Para no "romper" el árbol borrando nodos históricos, añadimos una bandera `activo = false` al usuario. Al recorrer el árbol para enviar mensajes, si el nodo está inactivo, lo saltamos.
* **Mensaje de asignatura desconocida:** La búsqueda en el `arbolAsignaturas` llegará al final de una rama (hoja) sin encontrar el código y devolverá `null`. El sistema detecta esto, descarta el mensaje y registra el error en un log.
* **Mensaje no enviado:** Si falla el envío (error de red), el mensaje no se elimina. Se vuelve a insertar al final de la `colaMensajes` para intentarlo de nuevo más tarde, garantizando que no se pierda.