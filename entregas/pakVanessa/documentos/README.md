# Proyecto Iris - Memoria Técnica Resumida

## 1. Justificación de Estructuras
El diseño del sistema combina varias estructuras de datos para gestionar mensajes de correo y su reenvío a WhatsApp de manera eficiente:

| Operación | Estructura Elegida | ¿Por qué esta estructura? |
| :--- | :--- | :--- |
| **Recoger Correos de Gmail** | **Cola** | Los correos se procesan en orden de llegada (FIFO), garantizando que se gestionen en el mismo orden que fueron recibidos. |
| **Organizar Mensajes** | **Árbol Binario de Búsqueda** | Permite buscar rápidamente mensajes por asunto, remitente o fecha, evitando recorrer todos los correos secuencialmente. |
| **Reestructurar Contenido** | **Lista Enlazada** | Cada correo puede tener múltiples secciones (encabezado, cuerpo, archivos adjuntos). La lista enlazada permite insertarlas y modificarlas dinámicamente sin reestructurar toda la memoria. |
| **Enviar a WhatsApp** | **Pila** | Para notificaciones urgentes o recientes, se quiere priorizar los mensajes más nuevos, mostrando primero lo más reciente (LIFO). |

## 2. Compromisos (Trade-offs)
Para mantener un balance entre simplicidad y eficiencia, se aceptaron algunos costes:

* **Velocidad de búsqueda de un correo específico:** Al usar Listas para secciones del mensaje, encontrar un fragmento concreto es secuencial. **Motivo:** Prioriza la flexibilidad para modificar y reestructurar el contenido.
* **Redundancia de datos en WhatsApp:** Si un mensaje se envía a múltiples usuarios, se copia en varias Pilas. **Motivo:** Cada usuario gestiona su lectura de manera independiente, evitando bloqueos o conflictos de acceso concurrente.

## 3. Manejo de Casos Límite
El sistema contempla situaciones excepcionales para evitar fallos o pérdida de información:

* **Correos sin remitente válido:** Se descartan o registran en un log de errores, evitando interrupciones en el flujo.
* **Mensajes duplicados:** Se detectan mediante el Árbol y se evita el envío repetido a WhatsApp.
* **Usuarios sin conexión:** Los mensajes permanecen en la Pila hasta que la conexión se restablezca.
* **Mensajes vacíos o con solo adjuntos:** Se procesan y notifican correctamente, incluso si el cuerpo del correo está vacío.
* **Exalumnos o contactos inactivos:** Se eliminan de las Pilas correspondientes para detener notificaciones innecesarias.

## 4. Estructura del Proyecto
