# Justificación del Diseño de Estructuras de Datos - Proyecto Iris

Este documento detalla las decisiones de arquitectura seleccionadas para **Iris**.

## 1. Arquitectura General
El sistema utiliza una arquitectura de **Referencias Cruzadas**. Mantenemos dos índices principales ordenados (Asignaturas y Alumnos) y los conectamos mediante listas de punteros. Esto permite navegar de la Asignatura al Alumno (para enviar) y del Alumno a la Asignatura (para borrar) sin duplicar la información personal.

## 2. Selección de Estructuras por Operación

### A. Almacenamiento y Búsqueda (El Catálogo)
**Operación:** Encontrar una Asignatura o un Alumno específico por su ID.
* **Estructura:** **Árbol Binario de Búsqueda (BST)**.
* **Justificación:**
    * **Velocidad:** Nos permite encontrar un dato "bajando por las ramas" (decidiendo si es mayor o menor) en lugar de revisar cada elemento uno por uno como en una lista simple.
    * **Crecimiento:** A diferencia de un Array fijo, el árbol permite agregar nuevos alumnos o carreras indefinidamente sin tener que reconstruir la estructura ni reservar memoria sobrante.

### B. Recepción de Alertas
**Operación:** Gestionar las notificaciones que llegan del campus virtual.
* **Estructura:** **Cola (Queue)**.
* **Justificación:**
    * **Orden Estricto:** Necesitamos garantizar el orden de llegada (FIFO). La primera notificación que entra es la primera que debe ser procesada.
    * **Seguridad:** Actúa como un "colchón". Si llegan 50 notificaciones en un segundo, la cola las guarda y permite que el sistema las procese a su ritmo sin colapsar.

### C. Enviar Notificación (De Asignatura a Alumnos)
**Operación:** Una asignatura avisa a todos sus suscriptores.
* **Estructura:** **Lista Enlazada (dentro de la Asignatura)**.
* **Justificación:**
    * **Recorrido:** La operación principal aquí es recorrer secuencialmente a *todos* los usuarios de la lista para enviarles el mensaje. Las listas enlazadas son ideales para esto.
    * **Referencias:** La lista no guarda copias de los datos (nombre, teléfono), sino que apunta directamente al Alumno en el árbol principal. Si un alumno actualiza su teléfono, todas las asignaturas acceden automáticamente al dato nuevo.

### D. Darse de Baja (De Alumno a Asignaturas)
**Operación:** Un alumno quiere cancelar todas sus suscripciones.
* **Estructura:** **Lista Enlazada (dentro del Alumno)**.
* **Justificación (Diseño Bidireccional):**
    * Esta lista permite que el alumno "conozca" en qué asignaturas está inscrito.
    * **Eficiencia:** Sin esta lista, para borrar a un alumno tendríamos que buscar su nombre en todas las asignaturas de la universidad (lo cual sería muy lento). Con esta lista, vamos directo a las 4 o 5 asignaturas que le corresponden y lo borramos inmediatamente.

### E. Salida de Mensajes
**Operación:** Entrega final a WhatsApp.
* **Estructura:** **Cola (Queue)**.
* **Justificación:**
    * Separa el proceso de búsqueda del proceso de envío. Permite que el programa siga buscando destinatarios internamente mientras un proceso secundario se encarga de enviar los mensajes a la velocidad que permita la red o la API de WhatsApp.

---

## 3. Alternativas Descartadas

1.  **Arrays / Arreglos Fijos:**
    * Se descartaron porque el número de alumnos y asignaturas en una universidad cambia constantemente. Usar un tamaño fijo desperdiciaría memoria o nos quedaría corto.
2.  **Duplicación de Datos:**
    * Se pensó en guardar el teléfono dentro de la asignatura para enviar más rápido. Se descartó porque complicaba el mantenimiento: si el alumno cambiaba de número, había que actualizarlo en muchos sitios a la vez.

---

## 4. Gestión de Casos Límite

| Caso | Manejo por el Diseño |
| :--- | :--- |
| **Asignatura sin alumnos** | La asignatura existe en el árbol, pero su lista de suscriptores está vacía. El proceso de envío simplemente no arranca. |
| **Alumno sin asignaturas** | El alumno existe en la base de datos (árbol), pero ninguna asignatura apunta a él. Está inactivo y no consume recursos de envío. |
| **Baja de Alumno** | Gracias a que el alumno tiene su propia lista de asignaturas, el sistema localiza exactamente dónde está inscrito y elimina su referencia sin tener que escanear todo el sistema. |
| **Mensajes Generales** | Se manejan creando una "Asignatura Global" a la que todos los alumnos se conectan automáticamente al registrarse. |

## 5. Compromisos (Trade-offs)

* **Mayor consumo de memoria:** Al usar punteros y listas dobles (bidireccionales), consumimos un poco más de memoria RAM que si usáramos estructuras simples, pero ganamos mucha velocidad y flexibilidad para borrar usuarios.
* **Inserción más lenta:** Al inscribir a un alumno, tenemos que escribir el dato en dos sitios (en la lista de la asignatura y en la lista del alumno). Aceptamos este pequeño coste extra al principio para que luego los envíos y borrados sean instantáneos.