# Manejo de Casos Límite y Situaciones Especiales

El diseño propuesto, basado en Árboles (BST) y Listas Enlazadas Bidireccionales, permite gestionar las excepciones y casos borde de forma nativa sin necesidad de "parches" externos. A continuación se detalla el comportamiento del sistema ante estas situaciones.

## 1. Asignaturas sin alumnos suscritos
**Escenario:** Llega una notificación para una asignatura (ej. "Electiva Avanzada") que existe en el catálogo pero nadie se ha inscrito aún.
* **Manejo:**
    1.  El sistema busca el `NodoAsignatura` en el árbol.
    2.  Accede a su lista de `suscriptores`.
    3.  La lista apunta a `null` (o su tamaño es 0).
    4.  El bucle de iteración `while(nodo != null)` no se ejecuta ni una sola vez.
* **Resultado:** El proceso termina instantáneamente. No se generan mensajes vacíos ni errores.

## 2. Alumnos sin asignatura inscrita
**Escenario:** Un alumno nuevo se ha registrado en el sistema pero aún no ha realizado la matrícula de ninguna materia.
* **Manejo:**
    1.  El `NodoAlumno` existe en el Árbol de Alumnos.
    2.  Su lista `misAsignaturas` está vacía.
    3.  Ningún `NodoAsignatura` tiene una referencia apuntando a este alumno.
* **Resultado:** El alumno está "durmiente". Al recorrer las listas de las asignaturas para enviar mensajes, el sistema nunca pasará por este alumno. No consume recursos de procesamiento de envíos.

## 3. Exalumnos (Gestión de Bajas)
**Escenario:** Un alumno abandona la universidad y debe ser eliminado para no recibir mensajes.
* **Manejo (Aprovechando la Bidireccionalidad):**
    * *En lugar de dejar campos vacíos (que fragmentan la memoria), eliminamos el nodo completamente.*
    1.  Buscamos al alumno en el Árbol de Alumnos.
    2.  Recorremos su lista `misAsignaturas`.
    3.  Para cada asignatura en esa lista, vamos al `NodoAsignatura` y borramos la referencia a ese alumno.
    4.  Finalmente, eliminamos el `NodoAlumno` del árbol principal.
* **Resultado:** Limpieza total. No quedan "usuarios fantasma" ni referencias rotas en el sistema.

## 4. Mensajes que no provienen de una asignatura (Avisos Generales)
**Escenario:** El rectorado envía un aviso de "Cierre por Festivo" que no pertenece a una materia específica, pero debe llegar a todos.
* **Manejo (Patrón de Asignatura Ficticia):**
    1.  El sistema incluye un `NodoAsignatura` especial reservado con código `GLOBAL` (o `0000`).
    2.  **Suscripción Automática:** Al registrarse un nuevo alumno, el sistema lo inserta automáticamente en la lista de suscriptores de `GLOBAL`.
    3.  Cuando llega un mensaje sin código de asignatura, se enruta a la cola como si fuera para `GLOBAL`.
* **Resultado:** Se reutiliza la misma lógica de envío que para una asignatura normal, sin programar casos especiales `if/else` para mensajes masivos.

## 5. Mensajes no enviados (Fallos de Red/WhatsApp)
**Escenario:** El sistema intenta procesar la `ColaWhatsapp`, pero la API falla o no hay internet.
* **Manejo (Estrategia de Reintento):**
    1.  El elemento en la cola tiene un contador interno de `intentos`.
    2.  Si el envío falla, no se descarta el mensaje.
    3.  Se incrementa el contador y se vuelve a insertar al final de la `ColaWhatsapp` (Re-encolar).
    4.  **Límite:**