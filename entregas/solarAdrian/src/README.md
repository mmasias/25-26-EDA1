# INFORME DE MEJORAS Y REFACTORIZACIÓN: Reto RCCCF

## 1. Cambio de Estructura de Datos (Core Refactoring)
**Situación Inicial:**
Se utilizaba la clase `java.util.PriorityQueue` para gestionar los pedidos. Aunque eficiente, actuaba como una "caja negra" que ocultaba la lógica interna de ordenación y comparación.

**Mejora Implementada:**
Se ha sustituido la cola de prioridad por una **implementación manual de un Árbol Binario de Búsqueda (BST)** (`ArbolPedidos` y `Nodo`).
* **Justificación:** Esto permite un control total sobre el algoritmo de inserción y extracción.
* **Beneficio:** Permite instrumentar el código para contar **exactamente** cuántas comparaciones realiza el algoritmo para mantener el orden, cumpliendo con el requisito pedagógico de medir la eficiencia.

## 2. Precisión en el Cálculo de Tiempos (Métricas)
**Situación Inicial:**
El tiempo de espera se calculaba de forma aproximada sumando el tamaño de la cola en cada ciclo (basado en la Ley de Little). Esto no permitía saber cuánto esperó un pedido específico.

**Mejora Implementada:**
Se ha añadido el atributo `minutoLlegada` (Timestamp) a la clase `Pedido`.
* **Nueva Lógica:** Ahora el tiempo de espera se calcula con precisión matemática al finalizar cada pedido:
  `TiempoEspera = (MinutoSalida - MinutoLlegada) - TiempoPreparación`.
* **Beneficio:** Proporciona estadísticas reales y verificables del rendimiento del restaurante, eliminando estimaciones.

## 3. Optimización del Algoritmo de Selección (SJF)
**Situación Inicial:**
La lógica de priorización dependía del comparador nativo de Java.

**Mejora Implementada:**
Se ha codificado explícitamente la lógica **Shortest Job First (SJF)** en el método `extraerMinimo()` de la clase `ArbolPedidos`.
* **Funcionamiento:** El algoritmo recorre recursivamente o iterativamente la rama izquierda del árbol (donde residen los tiempos menores) para encontrar el pedido más rápido.
* **Beneficio:** Demuestra comprensión profunda de cómo funcionan las estructuras de datos jerárquicas para problemas de optimización.

## 4. Gestión de Datos y Escalabilidad
**Situación Inicial:**
La generación de pedidos utilizaba cadenas de texto y números "hardcodeados" (dispersos por el código), lo que dificultaba modificar el menú.

**Mejora Implementada:**
Se ha centralizado la configuración en Arrays constantes (`NOMBRES_PLATOS` y `RANGOS_TIEMPOS`) en la clase principal.
* **Beneficio:** Facilita el mantenimiento. Si se desea añadir un nuevo plato o cambiar los tiempos de preparación, solo se modifica una línea de código en la cabecera, sin tocar la lógica de negocio.

## 5. Simplificación del Entorno de Ejecución
**Situación Inicial:**
El código dependía de una declaración de `package`, obligando a una estructura de carpetas específica para su compilación.

**Mejora Implementada:**
Se ha eliminado la dependencia de paquetes.
* **Beneficio:** Portabilidad total. Los archivos `.java` pueden compilarse y ejecutarse desde cualquier directorio simple sin necesidad de crear jerarquías de carpetas complejas.

---
**Resumen:**
La refactorización ha transformado el proyecto de una simulación basada en librerías estándar a una **solución algorítmica personalizada**, mejorando la precisión de los datos obtenidos y permitiendo una auditoría exacta de la eficiencia (número de comparaciones) del sistema.