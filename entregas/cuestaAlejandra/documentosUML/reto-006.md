# Reto 006: pyIris – Diseño de Estructuras de Datos

Este documento describe las estructuras de datos propuestas para el sistema **Iris**, un redirector de mensajes académicos que resume y envía notificaciones a suscriptores por WhatsApp. El diseño se centra en el uso de **estructuras de datos clásicas** (árboles, colas, listas, pilas) para organizar la información de forma eficiente y clara.

---

## 1. Justificación

El sistema se organiza en torno a una **estructura jerárquica de asignaturas**, implementada mediante un **árbol** donde cada nodo representa una asignatura. Esta elección permite agrupar las asignaturas de forma lógica (por ejemplo, por titulación, departamento o curso), facilitando búsquedas y recorridos eficientes. Cada nodo del árbol contiene una referencia a un objeto `Asignatura`, que actúa como contenedor central de toda la información relacionada con esa materia.

Dentro de cada `Asignatura`, se utilizan tres estructuras de datos especializadas para gestionar los distintos aspectos del sistema:

- Una **cola de mensajes** (`ColaMensajes`) almacena los mensajes entrantes en orden de llegada (FIFO). Esta estructura garantiza que los mensajes se procesen en el orden en que fueron recibidos, lo cual es esencial para mantener la coherencia temporal en las comunicaciones académicas.

- Una **lista de alumnos** (`ListaAlumnos`) mantiene los suscriptores activos de la asignatura. Se elige una lista dinámica por su flexibilidad para insertar y eliminar alumnos en tiempo de ejecución, operaciones frecuentes en entornos académicos donde los estudiantes pueden inscribirse o darse de baja en cualquier momento.

- Una **pila de resúmenes** (`PilaResumenes`) almacena los resúmenes generados, siguiendo una política LIFO (último en entrar, primero en salir). Esta estructura es útil para acceder rápidamente al resumen más reciente, que es el más relevante para los usuarios, y permite una gestión eficiente del historial inmediato.

Cada una de estas colecciones encapsula instancias de tipos básicos del dominio: `Mensaje`, `Alumno` y `Resumen`. Esta separación de responsabilidades —donde cada estructura se especializa en un tipo de operación— garantiza que el sistema sea modular, fácil de mantener y eficiente en el uso de recursos.

---

## 2. Compromisos

El diseño prioriza la claridad conceptual y la adecuación a las operaciones clave del dominio académico, lo que implica aceptar ciertos compromisos:

- **No se optimiza para búsquedas cruzadas complejas**. Por ejemplo, encontrar todas las asignaturas a las que está suscrito un alumno requiere recorrer el árbol completo y consultar cada `ListaAlumnos`. Este costo es aceptable porque las operaciones más frecuentes son **por asignatura** (enviar un resumen a sus alumnos), no por alumno.

- **La pila de resúmenes limita el acceso al historial**. Al usar una pila, solo es eficiente acceder al resumen más reciente. Si en el futuro se requiere consultar resúmenes antiguos, se necesitará cambiar a una estructura como una lista o una cola circular. Por ahora, se asume que el interés del usuario se centra en la información más actual.

- **El árbol de asignaturas no se balancea automáticamente**. Si el número de asignaturas crece de forma desigual (muchas en una rama, pocas en otra), las búsquedas podrían degradarse a O(n). Este riesgo se considera bajo en el contexto académico, donde el número total de asignaturas por titulación es limitado y la estructura es relativamente plana.

- **No se incluye persistencia en esta fase**. Todas las estructuras residen en memoria, por lo que su contenido se pierde al reiniciar el sistema. Este compromiso se acepta para acelerar el desarrollo de la lógica central en la fase beta.

---

## 3. Diagramas

### Diagrama UML del sistema

El siguiente diagrama muestra la arquitectura basada en estructuras de datos compuestas. `Iris` actúa como coordinador del sistema. Las asignaturas se organizan en un árbol (`ArbolAsignaturas`), donde cada nodo (`NodoAsignatura`) contiene una instancia de `Asignatura`. Cada `Asignatura` posee tres colecciones especializadas que gestionan mensajes, alumnos y resúmenes.

```plantuml
@startuml
left to right direction

class Iris {
  + procesarMensajes()
  + enviarResumenes()
}

class ArbolAsignaturas {
  - raiz: NodoAsignatura
  + buscar(String codigo): NodoAsignatura
  + insertar(Asignatura a)
}

class NodoAsignatura {
  - asignatura: Asignatura
  - hijos: List<NodoAsignatura>
}

class Asignatura {
  - codigo: String
  - nombre: String
}

class ColaMensajes {
  - cola: Queue<Mensaje>
  + enqueue(Mensaje m)
  + dequeue(): Mensaje
}

class ListaAlumnos {
  - lista: List<Alumno>
  + add(Alumno a)
  + remove(Alumno a)
}

class PilaResumenes {
  - pila: Stack<Resumen>
  + push(Resumen r)
  + pop(): Resumen
}

class Mensaje {
  - contenido: String
  - fecha: Instant
}

class Alumno {
  - id: String
  - nombre: String
}

class Resumen {
  - texto: String
  - fechaGeneracion: Instant
}

' Relaciones
Iris --> ArbolAsignaturas : gestiona
ArbolAsignaturas --> NodoAsignatura : contiene
NodoAsignatura --> Asignatura : referencia

Asignatura *-- "1" ColaMensajes : contiene
Asignatura *-- "1" ListaAlumnos : contiene
Asignatura *-- "1" PilaResumenes : contiene

ColaMensajes --> Mensaje : almacena
ListaAlumnos --> Alumno : almacena
PilaResumenes --> Resumen : almacena

note top of Iris
  Coordinador principal del sistema.
end note

note right of ArbolAsignaturas
  Estructura jerárquica que organiza
  las asignaturas del sistema.
end note

@enduml