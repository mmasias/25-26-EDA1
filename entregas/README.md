# Diseño de Estructuras de Datos: Plataforma Campus -> WhatsApp





## 1. Justificación de Estructuras y Operaciones



|  Operación | Estructura Elegida | Justificación |
| :--- | :--- | :--- |
| **Gestión de Usuarios (Login)** | **Árbol Binario de Búsqueda** | Inicialmente pense en una lista enlazada pero a medida que crece la plataforma la cantidad de usarios con una lista no es lo suficientemente eficiente para ser una decision correcta es mejor optar por este arbo, que tiene compeljidad de busqueda de $O(\log n)$. |
| **Estructura Académica** | **Árbol General** | La universidad tiene una jerarquía (Universidad $\to$ Grado $\to$ Asignatura). Un array o lista lineal no representa esta relación. esto nos permitiria buscar por ejemplo en Ingenieria Informatica. |
| **Suscripciones del Alumno** | **Lista Enlazada Propia (ListaDeAsignaturas)** | La cantidad de asignaturas de un alumno es dinámica y pequeña. El uso de memoria dinámica (punteros) es más eficiente que reservar un Array estático con espacios vacíos. La inserción es $O(1)$. |
| **Cola de Envíos** | **(ColaDeMensajes)** | El orden es el de una cola el de llegada. Los mensajes deben salir hacia WhatsApp en el mismo orden en que fueron generados para mantener la coherencia de la conversación (First In, First Out). |
| **Auditoría (Log)** | **Base de Datos** | Una vez procesado y desencolado el mensaje, se quiere guardar un registro de esto en la base de datos en este caso un Log. |

---

## 2. Compromisos (Trade-offs)



### A. Sacrificio de Acceso Aleatorio
* **El Compromiso:** Usando listas enlazadas tenemos que recorrer nodo a nodo y perdemos el acceso aleatorio.
* **Coste Aceptado:** Dado que un alumno promedio no tiene un alto numero de asignaturas, el coste computacional de recorrer la lista es no es comparable frente a la flexibilidad ganada en la gestión de memoria.

### B. Complejidad en la Búsqueda del Catálogo
* **El Compromiso:** Buscar una asignatura específica en el Árbol General sin conocer su facultad requiere un recorrido completo , lo cual puede ser costoso ($O(n)$) en el peor caso.
* **Coste Aceptado:** Se asume que la estructura académica es estática (cambia poco) y que las búsquedas suelen venir filtradas por Grado, optimizando el recorrido.

### C. Gestión Manual de Memoria
* **El Compromiso:** Asumimos la responsabilidad total de gestionar los punteros `siguiente` y `hijos`. Un error de implementación podría causar pérdidas de referencias  o ciclos infinitos.


---

## 3. Gestión de Casos Límite

###  Asignaturas sin alumnos suscritos
* **Comportamiento:** La clase `Asignatura` contiene una `ListaDeUsuarios`. Si está vacía, el puntero `cabeza` es `null`.
* **Manejo:** El algoritmo de notificación verifica si `cabeza == null`. De ser así, aborta el proceso inmediatamente. 

###  Alumnos sin asignaturas inscritas
* **Comportamiento:** El objeto `Usuario` tiene una `ListaDeAsignaturas` con `cabeza = null`.
* **Manejo:** El usuario puede hacer login correctamente , pero al no tener suscripciones, nunca se le llegan a enviar  notificación. El sistema permanece estable.

###  Gestión de Exalumnos 
* **Comportamiento:** Se utiliza la operación `eliminarNodo` del Árbol Binario de Usuarios.
* **Manejo:** Al eliminar al usuario, se rompen los enlaces. Para mantener la consistencia, se implementa una limpieza en las listas de las asignaturas: cuando una asignatura intenta notificar a un usuario que ya no existe, detecta el error y elimina ese nodo de su lista local de suscriptores.

###  Mensajes Institucionales (No provienen de Asignatura)
* **Comportamiento:** Mensajes de Rectorado o Alertas de Seguridad.
* **Manejo:** Se utiliza un nodo especial en la raíz del Árbol General identificado como `ADMIN`. El sistema permite inyectar mensajes en la `ColaDeMensajes` .

###  Fallo en el Envío (API WhatsApp caída)
* **Comportamiento:** El mensaje sale de la Cola pero la API devuelve error.
* **Manejo:** El sistema registra el mensaje en la Base de Datos con el estado `ERROR_ENVIO`. Esto permite que un proceso secundario recupere estos registros y los vuelva a encolar posteriormente (Reintentos).
