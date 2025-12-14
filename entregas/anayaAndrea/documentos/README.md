## Explicación del modelo de datos

El diagrama entidad–relación representa la estructura de datos necesaria para gestionar la recepción de correos electrónicos y su posterior tratamiento dentro del sistema.

El modelo se centra en almacenar los correos recibidos, relacionarlos con una asignatura y un profesor, y controlar qué usuarios reciben la información mediante un sistema de suscripciones.

---

## Entidades principales

### USUARIO
Representa a los usuarios registrados en el sistema.  
Cada usuario dispone de credenciales de acceso y puede suscribirse a una o varias asignaturas.

---

### ASIGNATURA
Define las distintas asignaturas disponibles en el sistema.  
Las asignaturas actúan como criterio de clasificación de los correos y como canal de suscripción para los usuarios.

---

### SUSCRIPCION
Gestiona la relación entre usuarios y asignaturas.  
Permite saber qué usuarios están suscritos a cada asignatura y, por tanto, quiénes deben recibir los mensajes asociados a ella.

---

### PROFESOR
Almacena la información de los profesores que envían los correos electrónicos.  
Un profesor puede estar asociado a varios mensajes procesados.

---

### MENSAJE_CORREO
Representa los correos electrónicos recibidos por el sistema.  
Se almacena el contenido original del correo junto con información básica como el remitente y la fecha de recepción.  
El atributo `procesado` indica si el correo ya ha sido tratado por el sistema.

---

### MENSAJE_PROCESADO
Representa el resultado del tratamiento de un correo electrónico.  
Cada mensaje procesado se asocia a:
- un correo original
- un profesor
- una asignatura

Esta entidad permite separar la información original del correo de la información ya clasificada y lista para su uso posterior.

---

## Relaciones entre entidades

- Un **USUARIO** puede tener varias **SUSCRIPCIONES**.
- Una **ASIGNATURA** puede estar asociada a varias **SUSCRIPCIONES**.
- Un **MENSAJE_CORREO** genera un único **MENSAJE_PROCESADO**.
- Un **PROFESOR** puede estar relacionado con varios **MENSAJES_PROCESADOS**.
- Una **ASIGNATURA** puede estar asociada a varios **MENSAJES_PROCESADOS**.

---

## Justificación del diseño

Este modelo permite:
- mantener los correos originales sin modificaciones
- clasificar la información por asignaturas
- controlar el acceso a la información mediante suscripciones
- separar claramente los datos originales de los datos procesados

La estructura es flexible y permite ampliar el sistema con nuevas asignaturas, usuarios o profesores sin modificar el diseño base.
