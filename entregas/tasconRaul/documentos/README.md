# Iris Message Redirector – Documentación de Diseño (Reto 006)

Esta es mi solución  y las estructuras de datos propuestas para el sistema **Iris Message Redirector**. El diseño sigue un enfoque orientado a objetos para garantizar un desacoplamiento eficiente entre la recepción, clasificación y distribución de mensajes.

---

## Descripción General

El sistema actúa como un intermediario inteligente que captura mensajes de un emisor, los clasifica y distribuye resúmenes periódicos a los suscriptores interesados.

**Flujo de Datos (Pipeline Vertical):**

1.  **Recepción**: El `Emisor` deposita mensajes en una cola de entrada (`ColaMensajesPendientes`).
2.  **Procesamiento**: El `Procesador` consume la cola FIFO, garantizando orden secuencial.
3.  **Clasificación**: Cada mensaje se inserta en el `ArbolCategorias` según su código de asignatura o tema.
4.  **Resumen**: Periódicamente, los nodos del árbol generan un objeto `ResumenMensajes`.
5.  **Enrutamiento**: El `GestorSuscripciones` identifica a los destinatarios (O(1)) para cada categoría.
6.  **Distribución**: El `Redirector` envía el resumen final a la lista de clientes obtenida.

---

## Justificación de Estructuras

Para cada operación crítica del sistema, se ha seleccionado una estructura de datos específica justificando su eficiencia y funcionalidad.

### 1. Recepción de Mensajes
* **Estructura:** Cola FIFO (*Queue*)
* **Componente:** `ColaMensajesPendientes`
* **Justificación:**
  Es imprescindible procesar los mensajes en el **estricto orden en que llegaron** para mantener la coherencia de la conversación. Además, la cola actúa como un *buffer* que amortigua picos de tráfico, evitando que el servidor se sature si llegan mil mensajes en un segundo.

### 2. Clasificación Sistematica
* **Estructura:** Árbol General (*N-ary Tree*)
* **Componente:** `ArbolCategorias`
* **Justificación:**
  Las asignaturas y temas suelen tener jerarquías (ej. *Ciencias → Matemáticas → Álgebra*). Un árbol permite organizar estas relaciones lógicamente. Los mensajes se agrupan en los nodos (`NodoCategoria`), facilitando la generación de resúmenes parciales o totales por rama.

### 3. Búsqueda de Suscriptores
* **Estructura:** Tabla Hash / Diccionario (*Map*)
* **Componente:** `GestorSuscripciones`
* **Justificación:**
  Necesitamos velocidad. Un mapa asocia una **Clave** (`CategoriaID`) directamente a un **Valor** (`Lista de Clientes`). Esto permite obtener los destinatarios en tiempo constante $O(1)$. Si usáramos una lista simple, tendríamos que recorrer todas las suscripciones cada vez ($O(N)$), lo cual es ineficiente.

### 4. Auditoría
* **Estructura:** Lista Enlazada (*Linked List*)
* **Componente:** `HistorialMensajes`
* **Justificación:**
  El historial es una estructura de "solo escritura" (*append-only*) donde la **inserción rápida al final** es la prioridad. No se requiere acceso aleatorio frecuente.