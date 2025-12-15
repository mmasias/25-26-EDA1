# Reto 006 – pyIris

El sistema pyIris permite gestionar mensajes asociados a asignaturas y enviarlos a los alumnos que estén suscritos.  
Los mensajes se agrupan y se envían en forma de resumen, en lugar de enviarse uno a uno.


## 1. Diagramas UML

El diseño se basa en un diagrama UML donde:

- `Iris` es la clase principal que coordina todo.
- Cada asignatura tiene un `CanalAsignatura`.
- Los alumnos pueden suscribirse o desuscribirse de asignaturas.
- Los mensajes se agrupan por emisor.
- Los errores y envíos fallidos se gestionan mediante colas.


## 2. Justificación de las estructuras

### Acceso a asignaturas
Se utiliza un **mapa** que asocia el código de la asignatura con su canal.  
Esto permite acceder directamente a una asignatura sin recorrer todas.

---

### Suscripción de alumnos
Cada asignatura guarda un **conjunto de alumnos suscritos**, y cada alumno guarda un **conjunto de asignaturas**.

Esto:
- evita duplicados,
- facilita las altas y bajas,
- y simplifica las comprobaciones.

---

### Gestión de mensajes
Los mensajes se guardan dentro de su asignatura y se agrupan por emisor.  
Cada emisor tiene una **cola de mensajes**, manteniendo el orden de llegada.

Esto facilita la creación posterior del resumen.

---

### Creación de resúmenes
Para crear un resumen:
- se recorren los emisores,
- se vacían sus colas,
- y se genera una lista con la información resumida.

El coste depende del número de mensajes, lo cual es razonable.

---

### Envío de resúmenes
El resumen se envía a todos los alumnos suscritos.

Si un envío falla, se guarda en una **cola de pendientes** para reintentar más tarde.

---

### Gestión de errores
Los problemas del sistema (mensajes inválidos, errores de envío, etc.) se guardan en una **cola de incidencias** para poder revisarlos.


## 3. Compromisos del diseño

- Se guarda la relación alumno–asignatura en ambos sentidos.
  - Coste: algo más de memoria.
  - Ventaja: operaciones más simples.

- El resumen se construye recorriendo mensajes.
  - Es sencillo y fácil de entender.
  - No se optimiza más para evitar complejidad innecesaria.

- No se mantiene un orden global exacto entre todos los emisores.
  - No es necesario para el funcionamiento del sistema.


## 4. Casos límite

### Asignaturas sin alumnos suscritos
La asignatura puede recibir mensajes, pero no se envía ningún resumen.

---

### Alumnos sin asignaturas
Un alumno puede existir sin estar suscrito a ninguna asignatura y no causa problemas.

---

### Exalumnos
Los exalumnos:
- no pueden suscribirse,
- no reciben mensajes,
- y se eliminan de las suscripciones si aparecen.

---

### Mensajes sin asignatura válida
Si un mensaje no tiene asignatura correcta:
- no se procesa,
- y se registra una incidencia.

---

### Mensajes no enviados
Si un resumen no se puede enviar:
- se reintenta varias veces,
- y si falla definitivamente, se registra como incidencia.