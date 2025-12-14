# Diseño de pyIris: ¡El Redirector Turbo!

## 1. La Filosofía: ¡No Me Bloques! 

Mi idea principal fue: el que envía el mensaje (el profesor) no tiene por qué esperar. El sistema tiene que ser un "Bus de Eventos": recibe, guarda en una cola, dice "¡Listo!", y luego se encarga tranquilamente de procesar, resumir y enviar los correos.

Lo clave es el **Desacoplamiento** y la **Velocidad de Búsqueda**.

---

## 2. ¿Por Qué Elegí Estas Piezas (Estructuras de Datos)?

Aquí explico por qué cada clase tiene la estructura que tiene. Todo es por eficiencia (Big O).

### A. `BuzonEntrada` (La Cola)
* **¿Qué es?** Un `Queue` (Cola FIFO).
* **Motivo:** Cuando llega un mensaje, la operación de **"encolar"** es **O(1)** (tiempo constante, ¡instantáneo!). Si de repente llegan mil mensajes a la vez (por ejemplo, al inicio de un semestre), la cola los amortigua. El controlador los saca a su propio ritmo sin que el sistema colapse.

### B. `DirectorioTemas` (El Mapa/Diccionario)
* **¿Qué es?** Un `HashMap<String, Tema>` (Diccionario).
* **Motivo:** ¡La estrella del diseño!
    * Si busco una categoría por su ID, con un diccionario lo encuentro en **O(1)**.
    * Si hubiera usado un **Árbol**, la búsqueda sería **O(log n)** o incluso **O(n)** si está desbalanceado. Como la búsqueda es la operación más frecuente, necesitaba la velocidad máxima. **Sacrifico la jerarquía visual por la velocidad**.

### C. `Tema` (El Contenedor Inteligente)

El `Tema` (que es la asignatura) tiene que hacer dos cosas: acumular mensajes y saber a quién avisar.

1.  **Buffer de Mensajes (`LinkedList`)**:
    * **¿Qué es?** Una Lista Enlazada.
    * **Motivo:** Insertar mensajes es **O(1)**. Luego, para el resumen, solo tengo que recorrer la lista de inicio a fin. Simple y rápido, ya que no necesito acceder a un mensaje específico por su índice.

2.  **Registro de Suscriptores (`HashSet`)**:
    * **¿Qué es?** Un Conjunto.
    * **Motivo:**
        * **No Duplicados:** Si un alumno hace clic diez veces en "Suscribir", el `Set` solo guarda una referencia. ¡Adiós a los correos duplicados!
        * **Altas/Bajas Rápidas:** Añadir o eliminar un alumno de la lista es **O(1)**. Imagina que el sistema tiene 10.000 alumnos; eliminarlos de un Array sería lentísimo.

---

## 3. Las Conexiones entre las Clases

Así se relacionan las piezas en el diagrama:

| Relación | Tipo | Explicación "De Alumno" |
| :--- | :--- | :--- |
| `Controller` $\diamond$-- `Buzon` | **Composición** | El buzón le **pertenece** al controlador; es parte de su motor. Si el controlador muere, el buzón también. |
| `Controller` --> `Directorio` | **Asociación de Uso** | El controlador le **pregunta** al Directorio: "¿Dónde está la clase con el ID 'MATH101'?" |
| `Directorio` o-- `Tema` | **Agregación** | El Directorio es el **índice** de todos los temas. Los contiene, pero los temas tienen vida propia (mensajes, alumnos). |
| `Tema` *-- `Mensaje` | **Composición Temporal** | El mensaje **vive dentro** del Tema. Una vez que se crea el resumen, el mensaje original se "muere" (se borra para liberar memoria). |
| `Tema` o-- `Suscriptor` | **Agregación Débil** | El tema **guarda una referencia** a los alumnos. El alumno existe fuera de la asignatura; si borro la asignatura, el alumno sigue siendo un alumno. |

---

## 4. Manejo de Casos Límite (¡Los Problemas que Nos Piden!)

Así maneja este diseño las situaciones peliagudas:

| Caso Límite | Manejo en el Diseño |
| :--- | :--- |
| **Asignaturas sin alumnos suscritos** | El `Tema` acumula los mensajes, pero su `HashSet` de `Suscriptores` estará vacío (size = 0). Al intentar enviar el resumen, el bucle de envío no se ejecuta, ¡y listo! No se gasta ancho de banda. |
| **Alumnos sin asignatura inscrita** | El objeto `Suscriptor` existe, pero su referencia simplemente no estará en el `HashSet` de **ningún** `Tema`. No genera ningún error. |
| **Exalumnos (Darse de baja)** | Gracias al `HashSet`, la operación de dar de baja (`desuscribir`) es **O(1)**, ¡inmediata! El exalumno es eliminado al instante de la lista de notificaciones. |
| **Mensajes con Categoría Desconocida** | Si el `Controller` busca el `CategoríaID` en el `DirectorioTemas` y no lo encuentra, en lugar de crashear, lo envía a un `Tema` especial llamado "DeadLetter" o "SinClasificar". Así no se pierde el mensaje, y el admin puede revisarlo. |
| **Mensajes no enviados (Fallos de red)** | El `Resumen` no se borra ni se marca como completado hasta que la notificación sale del sistema. Si falla la red, el `Resumen` se queda en una cola de reintento. Los mensajes originales se mantienen en el `LinkedList` hasta que se confirma el envío. |