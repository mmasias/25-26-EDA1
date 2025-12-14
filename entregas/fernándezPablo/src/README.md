#  Justificación de Estructuras de Datos – Proyecto Iris

Iris es un sistema que recibe correos electrónicos académicos, procesa su contenido, genera un resumen claro y envía dicha información a un chat de WhatsApp.  
Además, mantiene un histórico de todos los mensajes enviados mediante una base de datos.

---

##  1. Recepción de Correos — Cola (Queue)

### - Módulo: `EmailReceiver`
### - Estructura usada: `Queue<Email> colaEntrada`

Los correos electrónicos entrantes se almacenan en una cola **FIFO** (First In, First Out).

**Justificación:**
- Los correos deben procesarse en el mismo orden en el que llegan.
- No se requiere acceso aleatorio, solo insertar al final y extraer del inicio.
- Es una estructura ideal para flujos continuos de datos.

---

##  2. Procesamiento del Correo — Pila (Stack)

### - Módulo: `EmailProcessor`
### - Estructura usada: `Stack<String> pilaAnalisis`

Durante el procesamiento del correo se utiliza una **pila** para analizar y limpiar el contenido.

**Uso de la pila:**
- El cuerpo del correo puede contener texto anidado (respuestas, citas, HTML).
- Se procesan primero los elementos más recientes o internos.
- Permite deshacer o retroceder en el análisis si es necesario.

**Justificación:**
- Modelo **LIFO** (Last In, First Out).
- Operaciones `push` y `pop` en tiempo O(1).

---

##  3. Formateo del Mensaje

### - Módulo: `MessageFormatter`

Este módulo transforma el resumen generado en un mensaje claro y legible, añadiendo:
- Nombre de la asignatura.
- Contenido esencial.
- Fecha del aviso.

No utiliza una estructura de datos compleja porque su función es puramente de transformación.

---

##  4. Envío a WhatsApp — Cola (Queue)

### - Módulo: `WhatsAppDispatcher`
### - Estructura usada: `Queue<Mensaje> colaEnvio`

Los mensajes preparados se almacenan en una cola antes de enviarse al chat de WhatsApp.

**Justificación:**
- WhatsApp procesa los mensajes uno a uno.
- El orden de envío debe respetar la cronología.
- Permite gestionar picos de mensajes sin perder información.

✔ FIFO garantiza el orden  
✔ Evita saturar el sistema de envío  

---

##  5. Historial de Mensajes — Lista (List)

### - Módulo: `MessageHistoryManager`
### - Estructura usada: `List<Mensaje> historial`

El sistema mantiene una lista enlazada con todos los mensajes enviados.

**Justificación:**
- Permite recorrer el historial en orden cronológico.
- Ideal para mostrar el historial completo o parcial.

✔ Estructura simple y eficiente  
✔ Adecuada para consultas secuenciales  

---

##  6. Base de Datos — Persistencia

### - Base de datos: `IrisDB`
### - Entidad: `MensajeRecord`

Cada mensaje enviado se guarda de forma permanente en la base de datos.

**Motivos:**
- Mantener un histórico persistente.
- Recuperar información tras reinicios del sistema.

La base de datos complementa la lista en memoria, asegurando durabilidad.

---

##  Flujo General del Sistema

1. El correo llega y se encola (`Queue<Email>`).
2. Se procesa usando una pila (`Stack<String>`).
3. Se genera un resumen.
4. Se formatea el mensaje.
5. Se encola para envío a WhatsApp (`Queue<Mensaje>`).
6. Se envía al chat.
7. Se guarda en el historial (`List<Mensaje>`) y en la base de datos.

---


