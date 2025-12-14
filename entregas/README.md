# Reto 006 - pyIris

### 2. Estructuras de Datos

Estructura Elegida | Operación Crítica | Justificación |
:--- | :--- | :--- |
Árbol Binario de Búsqueda (BST) | Búsqueda (Login) |  Eficiencia de autenticación sobre grandes volúmenes de usuarios. |
| Árbol General (N-ary Tree) | Navegación |  Modelado fiel de la jerarquía (Uni $\rightarrow$ Grado $\rightarrow$ Asignatura). |
 Cola (Queue - FIFO) | Encolar/Desencolar |  Garantiza el orden cronológico estricto de los mensajes. |
 Lista Enlazada Simple | Inserción/Borrado |  Gestión dinámica y eficiente de memoria para una cantidad pequeña y variable de asignaturas por alumno. |
 Base de Datos (Log) | Registro | Trazabilidad, auditoría y gestión de reintentos de envío. |

 ### 3. Compromisos (Trade-offs)


#### A. Sacrificio de Acceso Aleatorio
* **El Compromiso:** Usando listas enlazadas tenemos que recorrer nodo a nodo y perdemos el acceso aleatorio.
* **Coste Aceptado:** Dado que un alumno promedio no tiene un alto numero de asignaturas, el coste computacional de recorrer la lista es no es comparable frente a la flexibilidad ganada en la gestión de memoria.

#### B. Complejidad en la Búsqueda del Catálogo
* **El Compromiso:** Buscar una asignatura específica en el Árbol General sin conocer su facultad requiere un recorrido completo , lo cual puede ser costoso ($O(n)$) en el peor caso.
* **Coste Aceptado:** Se asume que la estructura académica es estática (cambia poco) y que las búsquedas suelen venir filtradas por Grado, optimizando el recorrido.

#### C. Gestión Manual de Memoria
* **El Compromiso:** Asumimos la responsabilidad total de gestionar los punteros `siguiente` y `hijos`. Un error de implementación podría causar pérdidas de referencias  o ciclos infinitos.

### 4. Gestión de Casos Límite (Edge Cases)

 El Problema de la Asignatura Fantasma (Sin alumnos)
* **Escenario:** Un profesor envía un mensaje a una asignatura optativa que, este semestre, no tiene ningún matriculado.
* **Manejo del Sistema:**
    * El sistema localiza el nodo Asignatura.
    * Verifica el puntero a la lista de suscriptores.
    * Al detectar `cabeza == NULL`, el redirector aborta la creación de instancias de mensajes individuales.
* **Resultado:** Se evita el procesamiento inútil y se registra una advertencia en el Log ("Envío omitido: Sin audiencia").

 El Alumno "Oyente" (Sin asignaturas inscritas)
* **Escenario:** Un usuario se registra en la plataforma pero aún no ha formalizado su matrícula.
* **Manejo del Sistema:**
    * El Usuario se crea correctamente en el BST (Login funcional).
    * Su `ListaDeAsignaturas` se inicializa vacía.
* **Resultado:** El sistema es estable. El alumno puede entrar, ver su perfil, pero el motor de distribución simplemente nunca encontrará su referencia al recorrer las listas de las asignaturas. No recibe spam, ni genera errores.

Consistencia de Datos en Exalumnos
* **Escenario:** Un alumno se da de baja (se elimina del BST), pero su referencia sigue existiendo dentro de la lista de suscriptores de una Asignatura (Puntero colgante).
* **Manejo del Sistema (Estrategia Lazy):**
    * No recorremos todas las asignaturas para borrar al alumno inmediatamente (sería muy costoso computacionalmente). En su lugar:
    * Se elimina al usuario del BST principal.
    * Cuando una asignatura intenta enviar un mensaje, si detecta un ID de usuario que ya no valida contra el BST o cuyo envío falla, el sistema realiza una autolimpieza: elimina ese nodo específico de la lista de la asignatura en ese momento.

Comunicados Institucionales (Mensajes sin Asignatura)
* **Escenario:** El Rectorado necesita enviar un aviso de "Cierre por Alerta Meteorológica" a toda la universidad, no a una asignatura específica.
* **Manejo del Sistema:**
    * Se introduce un nodo raíz especial `ADMIN` en el Árbol General. Este nodo tiene permisos de Broadcast.
    * El sistema permite inyectar mensajes en la `ColaDeMensajes` con un flag `GLOBAL`.
    * El redirector interpreta este flag e itera sobre el BST de Usuarios completo, en lugar de buscar en listas de asignaturas individuales.

Fallos de Infraestructura Externa (API WhatsApp Caída)
* **Escenario:** Iris procesa el mensaje correctamente, lo desencola, pero al contactar con la API de WhatsApp, esta devuelve `500 Server Error`.
* **Manejo del Sistema:**
    * El mensaje no se pierde.
    * Se marca en la Base de Datos con estado `RETRY_PENDING` y un timestamp.
    * Un proceso secundario (Worker) revisa cada 5 minutos los mensajes en este estado e intenta re-encolarlos con prioridad alta.
    * Si falla tras 3 intentos, se marca como `FAILED` y se alerta al administrador del sistema.