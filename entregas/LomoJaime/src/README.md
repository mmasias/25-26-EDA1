# Entregable: Estructura de Datos – Fase *Whisper of the Gods*

## 1. Diagramas

He diseñado el sistema usando clases conectadas entre sí, y solo he utilizado las estructuras de datos que hemos visto en clase: **listas** y **colas**. 


## 2. Justificación

He elegido estas estructuras porque encajan perfectamente con lo que necesito hacer en esta fase del proyecto, y porque son las que mas adecuadas he visto sin tener que darle muchas vueltas y que sea claro.

- **Lista**: la uso para colecciones que no tienen un tamaño fijo. Por ejemplo:
  - Cada **usuario** tiene una lista de **asignaturas a las que está suscrito**. Así puedo recorrerla fácilmente y comprobar si un correo es relevante.
  - Tengo una lista de **todos los usuarios** para identificar al que está activo.
  - También tengo una lista con el **catálogo de asignaturas disponibles**, para asegurarme de que las que aparecen en los correos son reales.

  Me pareció la mejor opción porque no sé de antemano cuántas asignaturas va a elegir cada uno, y las listas me permiten añadir o quitar sin problemas. Con un array tendría que adivinar el tamaño, y eso no es práctico.

- **Cola**: la uso para procesar los correos en el orden en que llegan. Guardo los correos entrantes en una `Cola<String>` y los proceso uno a uno, del primero al último. Esto es importante porque si no, una alerta de entrega podría procesarse después de que ya haya pasado la fecha límite, solo por el orden en que se leyó ( Como comentaste en clase dijiste que el molaria meter lo de la alertas de correos refiriendose a entregas que caducan pronto la fecha de entrega, asi que he intentado añadirlo).

Además, cada vez que genero un mensaje ya resumido (`MensajeIris`), lo guardo en una base de datos externa. Esto no requiere una estructura especial en memoria, pero me da un respaldo por si falla el envío a WhatsApp.

## 3. Compromisos

Sí, he decidido no implementar algunas cosas, y lo he hecho a propósito para no complicar el núcleo del sistema ya que ultimamente en clase se ha insistido con la mas claridad y simpleza que la complicacion de las cosas.

- **Uso búsqueda lineal**: cuando compruebo si una asignatura está en la lista de suscripciones, recorro toda la lista. Sé que eso es O(n), pero en una universidad normal no hay más de 30–50 asignaturas, así que no noto la diferencia. Usar un árbol sería innecesario y, además, aún no lo domino del todo bajo mi punto de vista, asi que si tuviera que implementar el codigo a lo mejor se me compliaria.

- **No reenvío si falla WhatsApp**: aunque guardo una copia del mensaje en la base de datos, no tengo lógica de reintento en esta fase. Por ahora, asumo que el envío funciona, pero al menos la información no se pierde del todo.

- **No uso arrays, pilas ni árboles**: los arrays no son dinámicos; las pilas no encajan con el flujo que necesito; y los árboles los dejé para cuando realmente los necesite (por ejemplo, si el sistema crece mucho).

En resumen, prefiero tener un código claro, seguro y alineado con lo que ya sabemos, y si hace flata añadir alguna cosa teneindo ya una base se puede ir implementando poco a poco.

## 4. Casos límite

He pensado en varios escenarios extremos, y el diseño que he hecho los maneja sin romperse:

- **Asignaturas sin alumnos suscritos**: si llega un correo de una asignatura a la que nadie está suscrito, **no genero ninguna notificación**. El sistema lo ignora y no guarda nada.

- **Alumnos sin asignatura inscrita**: si alguien se loguea pero no elige ninguna asignatura, su lista está vacía, así que **nunca recibirá mensajes**. Eso es justo lo que debería pasar.

- **Exalumnos**: si un exalumno ya no está en mi lista de usuarios, no se le procesan correos. Si por algún motivo sigue en la lista pero sin suscripciones, se comporta como un usuario inactivo: no recibe nada.

- **Mensajes que no mencionan asignaturas**: si un correo es genérico (por ejemplo, del rectorado) y no se puede asociar a ninguna asignatura, **no lo proceso ni lo envío**. Prefiero no enviar nada a mandar ruido.

- **Mensajes no enviados a WhatsApp**: si falla el envío, el mensaje **ya está guardado en la base de datos**. Aunque en esta fase no lo reenvío automáticamente, al menos queda registrado, así que no se pierde la información esencial.

En **ningún caso** guardo el correo original ni ningún dato sensible. Solo almaceno lo que el usuario necesita: profesor, asignatura, resumen claro y, si aplica, la fecha límite.