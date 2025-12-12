Reto 006 – Iris (Razonamiento basado en el UML final)
====================================================

**1) Diagramas**

El sistema Iris se representa mediante un conjunto de clases que modelan **estructuras de datos** y sus relaciones:

- `Iris` es el componente principal que coordina el flujo.
- `ArbolAsignaturas` organiza el conjunto de asignaturas.
- Cada `Asignatura` mantiene:
  - `ColaMensajes` para almacenar mensajes recibidos (`Mensaje`).
  - `ListaAlumnos` para gestionar suscriptores (`Alumno`).
  - `PilaResumenes` para guardar resúmenes generados (`Resumen`).

El UML final (solo con flechas) refleja **asociaciones** entre estas estructuras, dejando la semántica de “contener/gestionar” explicada en este razonamiento.



**2) Justificación (por operación)**

**Operación: localizar una asignatura para enrutar un mensaje**  
- **Estructura que lo soporta:** `ArbolAsignaturas` (con `NodoAsignatura`)  
- **Por qué:** permite organizar las asignaturas por una clave (p.ej., código) y localizar la asignatura adecuada sin recorrerlas todas una a una. Es una estructura escalable cuando el número de asignaturas crece.

**Operación: registrar/insertar una asignatura nueva en el sistema**  
- **Estructura que lo soporta:** `ArbolAsignaturas`  
- **Por qué:** el árbol admite inserciones manteniendo una estructura global ordenada/organizada, útil para mantener el catálogo de asignaturas.

**Operación: recibir mensajes de una asignatura**  
- **Estructura que lo soporta:** `ColaMensajes`  
- **Por qué:** los mensajes llegan en orden temporal y deben procesarse en ese mismo orden. Una cola FIFO preserva el orden de llegada de forma natural.

**Operación: procesar mensajes para generar un resumen**  
- **Estructuras que lo soportan:** `ColaMensajes` + `PilaResumenes`  
- **Por qué:**  
  - La cola permite extraer mensajes en el mismo orden en el que llegaron (coherencia temporal del resumen).  
  - La pila almacena los resúmenes generados priorizando el último (lo más reciente suele ser lo más consultado).

**Operación: suscribir o dar de baja alumnos de una asignatura**  
- **Estructura que lo soporta:** `ListaAlumnos`  
- **Por qué:** permite gestionar altas/bajas y mantener un conjunto iterable de alumnos suscritos. El caso de uso clave es poder recorrerlos al enviar notificaciones.

**Operación: enviar el resumen a los alumnos suscritos**  
- **Estructura que lo soporta:** `ListaAlumnos`  
- **Por qué:** la operación principal es iterar sobre todos los alumnos registrados en esa asignatura para enviar el resumen.

**Operación: consultar el último resumen generado**  
- **Estructura que lo soporta:** `PilaResumenes`  
- **Por qué:** al usar LIFO, el resumen más reciente queda accesible inmediatamente (es el primero que se consulta en la mayoría de casos).



**3) Compromisos (trade-offs)**

- **Árbol de asignaturas (sin equilibrado explícito):**  
  Puede degradarse si la inserción crea un árbol muy desbalanceado. Se acepta este coste por simplicidad y por la ventaja de estructurar el catálogo.

- **Lista de alumnos:**  
  No está optimizada para búsquedas rápidas de un alumno concreto si se implementa como lista enlazada. Se acepta porque la operación principal es recorrer la lista completa para notificar.

- **Cola de mensajes:**  
  No permite acceso directo a un mensaje arbitrario sin desencolar/recorrer. Se acepta porque Iris trabaja con procesamiento secuencial y ordenado por llegada.

- **Pila de resúmenes:**  
  Acceder a resúmenes antiguos concretos no es inmediato. Se acepta porque se prioriza el acceso al resumen más reciente.



**4) Casos límite**

**Asignaturas sin alumnos suscritos**  
La `ListaAlumnos` de esa asignatura está vacía.  
El sistema puede generar el `Resumen` y guardarlo en `PilaResumenes`, pero al no haber destinatarios, no se envía a nadie.

**Alumnos sin asignatura inscrita**  
El `Alumno` existe como entidad, pero no aparece en ninguna `ListaAlumnos`.  
Por tanto, no recibe resúmenes ni notificaciones.

**Exalumnos**  
Se eliminan de la `ListaAlumnos` (o se evita incluirlos en dicha lista cuando cambian de estado).  
Al no estar en la lista de suscriptores, dejan de recibir mensajes.

**Mensajes que no provienen de una asignatura**  
Si el mensaje no incluye un identificador válido de asignatura o no se encuentra en `ArbolAsignaturas`, no puede enrutarse a ninguna `Asignatura`.  
Se descarta o se gestiona como mensaje “genérico” según la política del sistema.

**Mensajes no enviados**  
Si falla el envío a un alumno (por ejemplo, error en el canal de salida), el `Resumen` sigue almacenado en `PilaResumenes`.  
Esto permite reintentos posteriores o auditoría, evitando pérdida de información.

