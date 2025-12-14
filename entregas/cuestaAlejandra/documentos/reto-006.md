# Reto 006: pyIris – Diseño de Estructuras de Datos

Este documento describe las estructuras de datos propuestas para el sistema **Iris**, un redirector de mensajes académicos que resume y envía notificaciones a suscriptores por WhatsApp.

---
## 1. Justificación

El sistema se apoya en estructuras de datos cuidadosamente elegidas para soportar cada operación de forma eficiente. Al recibir un mensaje entrante, se crea un objeto `Message` que almacena todos los metadatos relevantes, incluyendo el código de la asignatura (`subjectCode`), que actúa como categoría para el enrutamiento posterior. Para identificar rápidamente a los destinatarios, se utiliza un mapa concurrente llamado `subjectToSubscribers`, que asocia cada asignatura con un conjunto de identificadores de alumnos suscritos; esta estructura permite acceder a los suscriptores en tiempo constante O(1), lo cual es esencial para mantener baja latencia incluso con miles de usuarios. 
El proceso de resumen transforma un `Message` en un objeto `Summary`, separando claramente el contenido crudo del mensaje final listo para enviar; esta separación facilita la evolución del motor de resumen —ya sea con reglas fijas o modelos de lenguaje— sin afectar otras partes del sistema. El envío se delega a una clase `WhatsAppSender`, que recibe el `Summary` y se encarga de la integración con el servicio externo, manteniendo el código modular y testeable. Las operaciones de suscripción y desuscripción actualizan tanto el registro de la relación `Subscription` como dos mapas centrales (`subjectToSubscribers` y `studentToSubjects`), lo que permite búsquedas eficientes en ambas direcciones: desde una asignatura a sus alumnos, y desde un alumno a sus asignaturas. Finalmente, cualquier mensaje que no incluya un `subjectCode` válido se descarta inmediatamente durante el procesamiento inicial, evitando errores posteriores y manteniendo la integridad del flujo de datos.

---

## 2. Compromisos

En esta fase beta del proyecto, se han aceptado varios compromisos conscientes para priorizar simplicidad, velocidad de desarrollo y enfoque en el núcleo del producto. En primer lugar, el sistema no incluye persistencia: todos los datos —suscripciones, mensajes pendientes, estado de procesamiento— residen únicamente en memoria, lo que implica que se pierden en caso de reinicio del servicio; este costo es aceptable temporalmente, ya que en fases posteriores se integrará una base de datos. En segundo lugar, no se implementa un mecanismo de reintento para mensajes no enviados: si falla la llamada a la API de WhatsApp (por número inválido, caída del servicio, etc.), la notificación se pierde y solo se registra en un log de errores; esto sacrifica la garantía de entrega a cambio de una arquitectura más simple y robusta frente a fallos externos. En tercer lugar, el resumen se genera de forma inmediata, sin agrupar múltiples mensajes de la misma asignatura en un único resumen periódico (por ejemplo, diario); esto puede generar un mayor volumen de notificaciones, pero reduce la latencia y evita la complejidad de colas temporales. Por último, se mantiene una doble indexación mediante dos mapas (`subjectToSubscribers` y `studentToSubjects`), lo que requiere actualizar ambas estructuras en cada suscripción o desuscripción; aunque esto añade algo de complejidad al código de gestión de suscriptores, el beneficio es un acceso extremadamente rápido en ambas direcciones, lo cual es crítico para la experiencia del usuario.

---
## 3. Diagramas

### Diagrama UML del sistema

El siguiente diagrama muestra las clases principales y sus relaciones:

```plantuml
@startuml
left to right direction

class Message {
  - String id
  - String subjectCode
  - String sender
  - String content
  - Instant timestamp
  - boolean processed
  + getters & setters
}

class Subscription {
  - String studentId
  - String subjectCode
  - Instant subscribedAt
  - boolean active
  + getters & setters
}

class Summary {
  - String subjectCode
  - String sender
  - String summarizedContent
  - Instant createdAt
  - List<String> recipients
  + getters & setters
}

class IrisEngine {
  - Map<String, Set<String>> subjectToSubscribers
  - Map<String, Set<String>> studentToSubjects
  - Map<String, List<Message>> pendingMessagesBySubject
  + void processMessage(Message msg)
  + void subscribe(String studentId, String subjectCode)
  + void unsubscribe(String studentId, String subjectCode)
  - String extractSummary(String content)
}

class WhatsAppSender {
  + void send(Summary summary)
}

class Main {
  + static void main(String[] args)
}

Main --> IrisEngine : crea y usa
Message --> IrisEngine : procesado por
Subscription --> IrisEngine : gestionado por
Summary --> IrisEngine : generado por
Summary --> WhatsAppSender : enviado por
@enduml

