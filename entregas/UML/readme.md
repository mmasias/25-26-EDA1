# pyIris: Análisis de Arquitectura Funcional (Temario EDA)


## 1. Justificación de Estructuras por Funcionalidad

La elección de las estructuras de datos se basa en sus propiedades funcionales intrínsecas para optimizar las operaciones clave del flujo de mensajes:

| Módulo / Operación | Estructura Utilizada | Propiedad Clave | Justificación Operacional |
| :--- | :--- | :--- | :--- |
| **Recepción de Mensajes** | **Cola (FIFO)** | **Orden de Llegada** | El `BufferDeEntrada` (Cola) garantiza que los mensajes se procesen en el orden exacto en que llegaron, asegurando la integridad del flujo (Primero en Entrar, Primero en Salir). |
| **Clasificación de Fuentes** | **Árbol** | **Jerarquía / Búsqueda Eficiente** | La `EstructuraJerarquica` (Árbol) modela las fuentes de mensajes de forma lógica y permite una **búsqueda rápida (logarítmica)** de cada `NodoFuente`. |
| **Almacenamiento en Fuente** | **Lista** | **Agrupación** | Cada `NodoFuente` utiliza listas (`MensajesAcumulados`) para agrupar todos los mensajes clasificados bajo esa fuente, sin imponer reglas estrictas de extracción. |
| **Envío a Clientes** | **Lista** | **Conjunto de Suscripción** | El `RegistroDeClientes` devuelve una lista de suscriptores que debe ser **recorrida secuencialmente** por el `ServicioDeEnvio` para realizar la notificación. |

## 2. Compromisos y Costes Aceptados

El diseño aprovecha la eficiencia del Árbol para la clasificación, pero asume los costes asociados a la naturaleza secuencial de las Listas en las operaciones de gestión:

| Operación / Estructura | Compromiso Aceptado |
| :--- | :--- |
| **Búsqueda de Categoría** | El coste de búsqueda en el **Árbol** es **Logarítmico** ($\approx$ rápido), lo cual se acepta como el principal beneficio de eficiencia del diseño, evitando el recorrido lineal. |
| **Obtención de Clientes** | Se acepta la necesidad de **recorrer secuencialmente** la lista de clientes para enviar el resumen a cada uno. |
| **Gestión de Hijos/Subfuentes** | La búsqueda de una sub-fuente dentro de un `NodoFuente` requiere **recorrer la Lista de Hijos** de manera secuencial. |
| **Baja de Suscriptor** | La eliminación de un registro de suscripción requiere un **trabajo proporcional** (recorrer la lista interna del `RegistroDeClientes`) para encontrar y eliminar el registro. |

## 3. Manejo de Casos Límite

El manejo de casos límite se basa en el estado de las estructuras (vacías, raíz, nulo) para garantizar la robustez del sistema.

| Caso Límite | Estructura Involucrada | Manejo del Diseño Propuesto |
| :--- | :--- | :--- |
| **Fuente No Registrada** | `EstructuraJerarquica` (Árbol) | El método `BuscarNodo(IDFuente)` del Árbol **falla** (retorna nulo). El `ProcesadorDeMensajes` detecta esta ausencia de nodo válido y descarta el mensaje. |
| **Asignaturas sin alumnos suscritos** | `RegistroDeClientes` (Lista) | El método `ObtenerDestinatarios()` devuelve una **lista vacía**. El `ServicioDeEnvio` recibe esta lista vacía y no realiza ninguna acción de envío. |
| **Exsuscriptores (Baja)** | Estructura de Suscripción | El registro del suscriptor es **eliminado** de la estructura subyacente del `RegistroDeClientes`. |
| **Cola Vacía** | `BufferDeEntrada` (Cola) | El `ProcesadorDeMensajes` verifica **`BufferDeEntrada.esVacia()`**. Si es verdadero, el ciclo de procesamiento se detiene. |