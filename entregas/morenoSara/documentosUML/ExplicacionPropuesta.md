#  Diseño de Estructuras de Datos para Iris

Este documento describe el diseño básico del sistema **Iris**, el redirector de mensajes académicos inspirado en la mensajera de los dioses. Iris recibe correos confusos y largos, los **resume** y los **envía directamente a WhatsApp** de los estudiantes suscritos.

El diseño se basa exclusivamente en tres estructuras del temario de **Estructuras de Datos y Algoritmos**:

- **Colas** (FIFO)
- **Árboles**
- **Listas**

---

##  ¿Cómo funciona Iris?

1. **Un mensaje académico llega** (ej. un correo del campus virtual).
2. Se **almacena en una cola** para procesarlo en orden.
3. El sistema **identifica su categoría** (asignatura o tema) y lo ubica en un **árbol de categorías**.
4. Periódicamente, se **genera un resumen** con todos los mensajes de una categoría.
5. Se **buscan los alumnos suscritos** a esa categoría.
6. El resumen se **envía por WhatsApp** a cada uno de ellos.

---

##  Estructuras de Datos Usadas

### 1. **Cola de Mensajes**
- **Nombre**: ColaMensajes
- **Tipo**: Cola FIFO
- **Propósito**: Almacenar temporalmente los mensajes entrantes y procesarlos en orden de llegada.
- **Operaciones básicas**:
  - encolar(mensaje)
  - desencolar() → mensaje
  - esVacia() → booleano

> **¿Por qué una cola?**  
> Porque el orden importa: no queremos que un mensaje de ayer se procese después de uno de hoy.



### 2. **Árbol de Categorías**
- **Nombre**: ArbolCategorias (con nodos de tipo NodoCategoria)
- **Tipo**: Árbol general (cada nodo puede tener múltiples hijos)
- **Propósito**: Representar la jerarquía de asignaturas y temas (ej. *Estructuras de Datos → Árboles → Recorridos*).
- **Cada nodo contiene**:
  - ID y nombre de la categoría
  - Una **lista** de mensajes asociados
  - Una **lista** de subcategorías (hijos)

> **¿Por qué un árbol?**  
> Porque la organización académica es jerárquica: materias → temas → subtemas.



### 3. **Listas (usadas en múltiples lugares)**
- **En NodoCategoria**:
  - mensajes: lista de mensajes clasificados en esa categoría.
  - hijos: lista de subcategorías.
- **En el gestor de suscripciones**:
  - Lista de pares (alumnoID, categoriaID) para saber quién está suscrito a qué.
  - Al generar un resumen, se obtiene una **lista de alumnos destinatarios**.

>**¿Por qué listas?**  
> Porque no necesitamos orden especial ni acceso por índice; solo almacenar y recorrer colecciones.



##  Flujo Simplificado
Mensaje entrante
↓
[ColaMensajes] → (FIFO)
↓
Procesador: ubica mensaje en [ÁrbolCategorias]
↓
Generador: crea resumen usando lista de mensajes en el nodo
↓
Gestor de suscripciones: obtiene lista de alumnos suscritos
↓
Redirector: envía resumen a cada alumno (vía WhatsApp)



## Ventajas del Diseño

- **Simple y claro**: usa solo estructuras del temario.
- **Escalable**: se pueden agregar más categorías o alumnos sin cambiar la lógica.
- **Robusto ante casos límite**:
  - Si no hay suscritos → no se envía nada.
  - Si un mensaje no tiene categoría → se descarta o va a “Otros”.
  - Si un alumno se da de baja → se elimina de la lista de suscripciones.


