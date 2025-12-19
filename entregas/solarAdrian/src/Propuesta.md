# Reporte de Refactorización: Optimización de Estructura de Datos

Este documento detalla la reingeniería aplicada al código del examen parcial. La arquitectura original, basada en una estructura de nodos ligados, fue sustituida por un modelo de **almacenamiento contiguo**. Esta modificación elimina la sobrecarga de memoria innecesaria y alinea el comportamiento del programa con el funcionamiento real de los arreglos en bajo nivel.

---

## Análisis de la Arquitectura

El diseño actual se divide en dos capas principales para separar la lógica de almacenamiento de la lógica de negocio.

### 1. El Núcleo de Almacenamiento (Clase `Lista`)
Representa el nivel más bajo de la implementación. Tras eliminar la clase `Nodo`, esta clase gestiona los datos de forma lineal.

* **Eficiencia:** Al utilizar un array interno de enteros, se garantiza un acceso de tiempo constante ($O(1)$) a cualquier posición.
* **Gestión Directa:** Se encarga de la persistencia física de los valores en memoria.

### 2. Interfaz de Control (Clase `Array`)
Actúa como un **wrapper** o envoltorio de seguridad para el cliente. Su función es proporcionar una capa de abstracción sobre la lista interna.

* **Inmutabilidad de Tamaño:** Garantiza que la estructura mantenga una dimensión fija, emulando el comportamiento de un array estático.
* **Encapsulamiento:** Protege la integridad del sistema mediante validaciones perimetrales, evitando errores de acceso fuera de límites (*IndexOutOfBounds*).

---

## Flujo de Ejecución

### Inicialización y Construcción
Cuando se instancia un nuevo objeto `Array`, el sistema sigue este protocolo:
1.  **Definición de Capacidad:** Se reserva el espacio necesario según el parámetro ingresado.
2.  **Inicialización de Memoria:** Se crea la instancia de la lista interna.
3.  **Relleno de Seguridad:** Se puebla la estructura con valores nulos (ceros) para asegurar que el array esté en un estado consistente y listo para su uso inmediato.

### Protocolo de Lectura y Escritura (`set` y `get`)
Para manipular los datos, se sigue un flujo de tres pasos:

1.  **Validación de Índice:** Se comprueba que el índice solicitado sea mayor o igual a 0 y menor que el tamaño definido.
2.  **Delegación de Tarea:** Si la validación es exitosa, la clase `Array` redirige la petición a la `Lista`.
3.  **Acceso Directo:** Se recupera o modifica el valor en la posición de memoria exacta de forma instantánea.

---
> **Nota de refactorización:** La eliminación de la clase `Nodo` reduce la complejidad ciclomática del proyecto y mejora significativamente el uso de la caché del procesador al mantener los datos juntos en memoria.