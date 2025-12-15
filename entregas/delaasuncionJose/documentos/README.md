# Diseño del Sistema de Suscripciones basado en Listas Enlazadas

## 1. Objetivo del Diseño

El objetivo de este diseño es modelar un sistema de suscripciones entre **Clientes** y **Asignaturas**, garantizando:

- Relación bidireccional entre clientes y asignaturas
- Gestión dinámica de altas y bajas
- Simplicidad estructural
- Bajo coste de inserción y eliminación

El sistema está pensado para escenarios con un **número pequeño o moderado de entidades**, priorizando claridad y facilidad de mantenimiento frente a escalabilidad extrema.

---

## 2. Estructura General del Sistema

El sistema se apoya en dos colecciones globales principales:

- `lista_clientes`: contiene todos los clientes registrados
- `lista_asignaturas`: contiene todas las asignaturas disponibles

Cada entidad mantiene referencias directas a las entidades relacionadas, formando una red de listas enlazadas.

### Relaciones clave

- Un **Cliente** conoce las asignaturas a las que está suscrito.
- Una **Asignatura** conoce los clientes que están suscritos a ella.
- El sistema central (`SistemaIris`) coordina búsquedas y operaciones.

---

## 3. Justificación del Uso de Listas Enlazadas

Se opta por listas enlazadas debido a las siguientes ventajas:

- Inserciones y eliminaciones en tiempo constante una vez localizada la posición
- No requieren redimensionamiento de memoria
- Implementación sencilla
- Flexibilidad ante cambios frecuentes

Este enfoque es adecuado cuando:
- Las colecciones no crecen de forma masiva
- Se prioriza la claridad sobre la optimización extrema

---

## 4. Complejidad de las Operaciones Principales

### 4.1 Envío de Mensajes

1. Búsqueda de la asignatura en `lista_asignaturas`: **O(Na)**
2. Iteración sobre los clientes suscritos: **O(Ns)**

**Complejidad total:** `O(Na + Ns)`

---

### 4.2 Suscripción de un Cliente

1. Búsqueda del cliente en `lista_clientes`: **O(Nc)**
2. Búsqueda de la asignatura en `lista_asignaturas`: **O(Na)**
3. Inserción de referencias cruzadas: **O(1)**

**Complejidad total:** `O(Nc + Na)`

---

### 4.3 Desuscripción de un Cliente

1. Eliminación de la asignatura en la lista del cliente: **O(Nci)**
2. Eliminación del cliente en la lista de la asignatura: **O(Nsa)**

**Complejidad total:** `O(N)`

---

## 5. Casos Límite y Manejo de Errores

### Asignaturas sin clientes
- La lista de suscriptores está vacía
- Coste de iteración: **O(1)**

### Clientes sin asignaturas
- La lista de asignaturas suscritas está vacía
- No se generan efectos secundarios

### Eliminación de un exalumno
1. Búsqueda del cliente en `lista_clientes`
2. Iteración sobre todas sus asignaturas
3. Eliminación cruzada en cada asignatura

**Coste total:** `O(N × A)`  
(A = asignaturas del cliente)

---

### Mensajes sin asignatura asociada
- No es posible enrutar el mensaje
- El sistema debe:
  - Rechazar el mensaje, o
  - Exigir un identificador válido de asignatura

---

### Mensajes no entregados
- Se almacenan en una cola:
  - `Asignatura.mensajes_pendientes`
- Permite reintentos o auditoría posterior

---

## 6. Sacrificios del Diseño

- Escalabilidad limitada en grandes volúmenes de datos
- Búsquedas lineales en listas globales
- No apto para sistemas de alta concurrencia sin optimización adicional

Este diseño es **intencionalmente simple**, ideal para:
- Prototipos
- Sistemas educativos
- Aplicaciones con volumen controlado
