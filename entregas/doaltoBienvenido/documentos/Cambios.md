# Bitácora de Cambios: Refactorización y Mejora Técnica (Reto-007)

Este documento registra las modificaciones estructurales y lógicas realizadas en el proyecto para cumplir con los estándares de **Estructuras de Datos y Algoritmos 1**, eliminando vicios de programación y mejorando la cohesión del sistema.

---

## 📅 Resumen de Modificaciones

| Categoría | Descripción del Cambio | Justificación Técnica |
| :--- | :--- | :--- |
| **Arquitectura** | Renombrado de la clase interna `Lista` a `ListaEnlazada` dentro del paquete `Array`. | Evitar colisiones de nombres con la clase `Lista` del paquete de negocio y clarificar la jerarquía HOOD. |
| **Defensiva** | Sustitución de validaciones `if/println` por sentencias `assert`. | Implementar programación defensiva real. Las clases de lógica no deben gestionar la comunicación con el usuario. |
| **Estructura** | Eliminación del bloque `switch` y sus respectivos `break` en `Principal.java`. | Aplicar programación estructurada pura mediante una cadena de `if-else if`, evitando saltos de control incondicionales. |
| **Modularidad** | Extracción de la lógica de recorrido en el método privado `localizarNodo` en `ListaEnlazada`. | Mejorar la cohesión y reutilización de código en los métodos `obtener` y `modificar`. |
| **Encapsulamiento** | Revisión y restricción de visibilidad de atributos a `private`. | Garantizar la ocultación de información y proteger la integridad de los punteros (nodos). |

---

## 🛠️ Detalle Técnico de los Cambios

### 1. Gestión de Errores mediante Aserciones
Se han eliminado todas las salidas por consola (`System.out.println`) de las clases de estructura y lógica. 
* **Cambio**: El uso de `assert` garantiza que las precondiciones (como índices dentro de rango) sean validadas en tiempo de ejecución, deteniendo el programa si se detecta un error de programación.
* **Impacto**: Se separa completamente la interfaz de usuario de la lógica de las estructuras de datos.

### 2. Flujo de Control Estructurado
En la clase `Principal`, el control del menú se ha rediseñado para evitar el uso de `break`:
* Se utiliza un bucle `while` que evalúa la variable de control antes de cada iteración.
* La selección de opciones se realiza mediante una estructura alternativa múltiple (`if-else if`), lo que facilita el seguimiento del flujo de datos.

### 3. Mejora en la Reutilización (DRY)
Se identificó que el recorrido de la lista de nodos se repetía en múltiples métodos.
* **Acción**: Se creó `localizarNodo(int indice)` como método privado en `ListaEnlazada`.
* **Resultado**: Reducción de líneas de código y centralización de la lógica de iteración de punteros.

---