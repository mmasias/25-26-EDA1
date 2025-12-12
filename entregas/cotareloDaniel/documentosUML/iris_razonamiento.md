# Reto 006 – Iris

### Diagramas
El sistema Iris se organiza mediante un árbol de asignaturas.  
Cada asignatura gestiona de forma independiente sus mensajes, alumnos y mensajes resumidos mediante estructuras de datos específicas.

El diagrama UML adjunto muestra:
- Iris como elemento principal.
- Un árbol de asignaturas.
- Cada asignatura con una cola de mensajes, una lista enlazada de alumnos y una pila de mensajes resumidos.


### Justificación

- **Búsqueda de asignaturas**  
Se utiliza un árbol para organizar y localizar las asignaturas sin recorrerlas todas una a una.

- **Almacenamiento de mensajes**  
Los mensajes recibidos se almacenan en una cola para mantener el orden de llegada (FIFO).

- **Gestión de alumnos**  
Los alumnos suscritos se gestionan mediante una lista enlazada, permitiendo altas y bajas dinámicas y el recorrido completo para el envío de mensajes.

- **Almacenamiento de mensajes resumidos**  
Un mensaje resumido es el resultado de procesar y simplificar un correo académico.  
Estos mensajes resumidos se almacenan en una pila, priorizando el acceso al último generado.


### Compromisos

- El árbol puede degradarse si no está equilibrado, pero se acepta por simplicidad.
- La lista enlazada no permite búsquedas rápidas, pero el uso principal es el recorrido.
- La cola no permite acceso directo a mensajes concretos, lo cual no es necesario.
- La pila dificulta el acceso a mensajes resumidos antiguos, priorizando el más reciente.


### Casos límite

- **Asignaturas sin alumnos suscritos**  
Se generan los mensajes resumidos, pero no se envían a ningún destinatario.

- **Alumnos sin asignatura inscrita**  
No reciben mensajes al no aparecer en ninguna lista.

- **Exalumnos**  
Se eliminan de las listas y dejan de recibir mensajes.

- **Mensajes sin asignatura**  
Se asocian a una categoría genérica dentro del árbol.

- **Mensajes no enviados**  
El mensaje resumido se mantiene almacenado y puede reenviarse.