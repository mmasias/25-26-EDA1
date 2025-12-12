# Entregable: Estructura de Datos – Fase *Whisper of the Gods*

## 1. Diagramas

El diseño del sistema se basa en clases conectadas mediante relaciones de uso y composición. Las estructuras de datos empleadas son exclusivamente **listas** y **colas**, tal como se permite en el temario de la asignatura.

> El diagrama UML completo está disponible en el archivo .

## 2. Justificación

En esta fase, las operaciones del sistema se apoyan en dos estructuras fundamentales:

- **Lista**: se utiliza para colecciones dinámicas cuyo tamaño no se conoce de antemano.
  - La lista de **asignaturas suscritas** en cada `Usuario` permite recorrerlas fácilmente y verificar si un correo pertenece a una asignatura de interés.
  - La lista de **usuarios registrados** permite identificar al destinatario activo.
  - El **catálogo de asignaturas disponibles** se mantiene en una lista para validar que las mencionadas en los correos son reales.
  
  Las listas son ideales porque permiten inserción y recorrido secuencial sin necesidad de tamaño fijo (a diferencia de los arrays), y son suficientes para el volumen reducido de datos en un entorno académico.

- **Cola**: se utiliza para gestionar el flujo de correos entrantes.
  - La `colaCorreos` en el `GestorIris` asegura que los mensajes se procesen en orden de llegada (FIFO).
  - Esto es crucial para mantener la coherencia temporal, especialmente con alertas de fechas límite.

Además, tras procesar un correo y generar un `MensajeIris`, este se **guarda en una base de datos externa**. Esta acción no depende de una estructura en memoria, pero garantiza que, aunque falle el envío a WhatsApp, la información no se pierde.

## 3. Compromisos

Algunas operaciones se han simplificado o pospuesto su implementación para mantener el diseño alineado con el nivel actual del proyecto:

- **Búsqueda lineal en lugar de indexada**: al comprobar si una asignatura está en la lista de suscripciones, se recorre toda la lista (coste O(n)). En un sistema con decenas de miles de asignaturas, esto sería ineficiente, pero en un contexto universitario con menos de 50, el impacto es imperceptible. No se usan árboles ni tablas hash porque no son necesarios ni están completamente cubiertos en el temario.

- **Sin reenvío automático**: aunque los mensajes se guardan en la base de datos, **no hay lógica de reintento si falla WhatsApp** en esta fase. Esto se implementará en *Golden Messenger*. El coste aceptado es la posible pérdida temporal de una notificación, pero al menos queda registrada.

- **Sin uso de arrays, pilas ni árboles complejos**: se evitan porque no aportan valor en esta etapa. Los arrays no son dinámicos; las pilas no encajan con el flujo FIFO; y los árboles implicarían una complejidad innecesaria.

El compromiso general es **priorizar la claridad, la seguridad y la alineación con el temario** sobre el rendimiento extremo o funcionalidades avanzadas.

## 4. Casos límite

El diseño maneja los siguientes escenarios de forma robusta:

- **Asignaturas sin alumnos suscritos**: si un correo menciona una asignatura a la que nadie está suscrito, el sistema **no genera ningún mensaje** y descarta el correo sin guardar rastro.

- **Alumnos sin asignatura inscrita**: si un usuario no ha seleccionado ninguna asignatura, su lista de suscripciones está vacía, por lo que **nunca recibirá notificaciones**. Esto es el comportamiento esperado.

- **Exalumnos**: si un exalumno ya no está en la lista de usuarios del sistema, no se le procesan correos. Si permanece en la lista pero sin suscripciones, se comporta como un usuario inactivo.

- **Mensajes que no provienen de una asignatura**: si un correo no permite identificar claramente una asignatura (por ejemplo, un mensaje genérico del rectorado), **no se procesa ni se envía**. Iris prefiere no enviar nada a enviar ruido.

- **Mensajes no enviados**: si el envío a WhatsApp falla, el `MensajeIris` **ya está guardado en la base de datos**. Aunque no se reenvía automáticamente en esta fase, la información no se pierde, cumpliendo con el principio de resiliencia básica.

En **todos los casos**, el sistema **nunca almacena el correo original ni datos sensibles**. Solo se guarda la información esencial, ya depurada y resumida.