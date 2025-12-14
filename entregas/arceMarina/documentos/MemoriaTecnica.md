# Proyecto Iris - Memoria Técnica Resumida

## 1. Justificación de Estructuras 
El diseño combina cuatro estructuras fundamentales de EDA. Cada una se ha elegido para resolver un problema específico de eficiencia:

| Operación | Estructura Elegida | ¿Por qué esta estructura? |
| :--- | :--- | :--- |
| **Buscar Asignatura** | **Árbol Binario** | Necesitamos encontrar un código (ej. "MAT101") rápidamente. El Árbol permite descartar mitades del catálogo en la búsqueda, siendo mucho más rápido que leer una lista entera secuencialmente. |
| **Gestionar Alumnos** | **Lista** | El número de alumnos por clase es variable e impredecible. La Lista Enlazada crece dinámicamente sin desperdiciar memoria (como haría un Array fijo) y es sencilla de recorrer para notificar. |
| **Recibir Mensajes** | **Cola** | La mensajería es temporal. La Cola garantiza el orden estricto de llegada (FIFO), asegurando que los mensajes se procesen tal cual fueron enviados. |
| **Generar Resumen** | **Pila** | Para la vista del usuario, queremos mostrar lo más reciente arriba. La Pila invierte el orden natural de la Cola (LIFO), dejando el último mensaje listo para leer primero. |

## 2. Compromisos (Trade-offs)
Para mantener la simplicidad y eficiencia del diseño, hemos aceptado estos costes:
* **Velocidad de Acceso al Alumno:** Al usar Listas en lugar de Arrays, buscar a un alumno concreto es lento (secuencial). **Motivo:** Priorizamos la flexibilidad de memoria y la inserción rápida sobre la búsqueda individual.
* **Redundancia de Datos:** Si un mensaje va a 100 alumnos, se replica en 100 colas. **Motivo:** Permite que cada alumno gestione su lectura de forma independiente.

## 3. Manejo de Casos Límite
Así responde el sistema ante situaciones excepcionales:

* **Asignaturas sin alumnos:** El sistema detecta que la Lista está vacía (`null`) y detiene el envío inmediatamente. Coste computacional nulo.
* **Alumnos sin asignaturas:** Existen en memoria pero no están en ninguna lista. Su Cola permanece vacía y el resumen indica "Sin novedades".
* **Exalumnos:** Se recorren las listas de las asignaturas y se elimina el nodo correspondiente (ajustando punteros) para detener los envíos.
* **Mensajes sin Asignatura (Huérfanos):** La búsqueda en el Árbol devuelve `null`. El mensaje se descarta o se envía a un log de errores, evitando fallos.
* **Mensajes no enviados:** Permanecen seguros en la Cola del alumno hasta que se confirme la conexión. No se pierde información.