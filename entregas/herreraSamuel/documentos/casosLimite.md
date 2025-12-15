# Gestión de Casos Límite y Errores 🛡️

Nuestro diseño está preparado para manejar situaciones atípicas sin detener el servicio.

## 1. Asignaturas "Fantasma" (Sin alumnos)
**Situación:** Llega un mensaje para una asignatura que existe en el árbol, pero nadie se ha suscrito aún.
* **Comportamiento:** El sistema encuentra el nodo en el árbol correctamente. Al intentar acceder a la lista de `suscriptores`, ve que el tamaño es 0 o la cabecera es `null`.
* **Resultado:** El bucle de envío de WhatsApps simplemente no arranca. El mensaje se procesa como "completado" sin enviar nada, sin generar errores.

## 2. Alumnos "En el Limbo" (Sin asignaturas)
**Situación:** Un alumno se registra en Iris pero aún no añade materias.
* **Comportamiento:** El objeto `Estudiante` existe en memoria, pero su lista `misAsignaturas` está vacía y no aparece en ninguna lista de `NodoAsignatura`.
* **Resultado:** El sistema lo ignora pasivamente. No recibe spam, pero está listo para cuando decida suscribirse.

## 3. Mensajes con Código Erróneo
**Situación:** Llega un correo con código "XYZ999" que no existe en nuestro plan de estudios.
* **Comportamiento:** El buscador del Árbol BST recorre las ramas. Al llegar a una hoja (null) sin encontrar coincidencia, detecta el fallo.
* **Resultado:** Se captura la excepción o el `null`. El mensaje se envía a una papelera de "No Entregados" para no bloquear la cola de mensajes válidos.

## 4. Fallo en el envío (WhatsApp caído)
**Situación:** Al recorrer la lista de alumnos, el envío al alumno número 5 falla.
* **Comportamiento:** El iterador de la lista está protegido. Si falla el envío actual, se registra el error, pero el iterador salta al `siguiente` nodo.
* **Resultado:** El fallo con un alumno no castiga al resto de la clase. Los demás sí reciben su mensaje.