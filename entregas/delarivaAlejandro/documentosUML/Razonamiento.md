# Reto 006 – PyIris/Iris

### Diagramas

El sistema Iris se organiza mediante un árbol de asignaturas. 

Cada asignatura gestiona de forma independiente sus mensajes, alumnos y mensajes resumidos mediante estructuras de datos específicas.

El diagrama UML muestra cómo el backend organiza los datos para trabajar rápido:

- Árbol AVL de asignaturas para localizar la asignatura por código rápido
- Árbol AVL de suscripciones para obtener qué usuarios reciben cada asignatura
- Árbol AVL de usuarios para acceder rápido a datos del usuario c
- Cola de envíos para asegurar que los mensajes de WhatsApp se envían en orden y de forma controlada

### Justificación de las estructuras de datos utilizadas

En el backend de la aplicación se emplean árboles AVL y colas con el objetivo de optimizar el acceso a la información y el procesamiento de los mensajes.

En primer lugar, se utiliza un árbol AVL para las asignaturas, ya que cada correo recibido debe asociarse rápidamente a una asignatura concreta a partir de su código. Las operaciones de búsqueda, inserción y eliminación se realizan en tiempo O(log n), garantizando un rendimiento eficiente incluso cuando el número de asignaturas aumenta.

Por otro lado, las suscripciones se gestionan mediante otro árbol AVL, donde cada asignatura se asocia a sus usuarios suscritos. Esta estructura permite obtener de forma rápida todos los usuarios que deben recibir un mensaje relacionado con una asignatura concreta, manteniendo así la eficiencia del sistema y evitando recorridos completos e innecesarios.

Además, se emplea un árbol AVL de usuarios para acceder de manera eficiente a la información de cada usuario mediante un identificador y un número de WhatsApp, lo que facilita la generación de los mensajes a enviar sin penalizar el rendimiento.

Finalmente, los mensajes preparados para su envío se almacenan en una cola de envíos de WhatsApp, ya que el envío debe realizarse en el mismo orden en que se procesan los correos, esta permite encolar y desencolar mensajes en intervalos tiempo 0 y 1, asegurando un flujo ordenado y sencillo de procesamiento.

En conjunto, el uso de árboles AVL garantiza búsquedas rápidas y escalabilidad del sistema, mientras que la cola asegura un envío seccuencial y controlado de los mensajes, logrando un backend eficiente y bien estructurado.

### Casos límite

- **Correo sin asignatura detectada** 
    No puede buscar en ArbolAVLAsignaturas, así que el correo queda sin clasificar y no se generan envíos.

- **Asignatura inexistente** 
    buscarAsignatura() devuelve “no encontrada”, por lo que no se reenvía nada.

- **Asignatura sin suscripciones**
    ArbolAVLSuscripciones devuelve vacío para esa asignatura, igual a 0 envíos.

- **Usuario no existe o dado de baja**  
    Al intentar resolverlo en ArbolAVLUsuarios no aparece y se marca como inconsistencia.

- **Suscripciones duplicadas**  
    Si la clave del usuario ya existe en el árbol de esa asignatura, el árbol evita duplicados por clave y garantiza que no se repita el envíos.

- **Muchos correos recibidos de forma consecutiva**  
    Los envios se almacenan en `ColaEnviosWhatsapp`  y se procesan en orden sin mezclarse.

- **Fallos de envío por Whatsapp**
    El envío puede quedarse sin completar y el mensaje puede ser reintentado o desplazado a una cola de fallos para su posterior revisión.
