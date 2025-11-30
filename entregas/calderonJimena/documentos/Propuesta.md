# Propuesta de Implementación – Simulación del RCCCF

Esta implementación modela el sistema del RCCCF bajo el criterio SJF (Shortest Job First), demostrando cómo la elección de la estructura de datos impacta directamente en la eficiencia del algoritmo, medida mediante el número de comparaciones.

## Diseño de la Solución

### 1. **Clases principales**
| Clase | Responsabilidad |
|------|------------------|
| `Simulation` | Clase principal con `main()`. Inicia la simulación. |
| `Restaurant` | Núcleo de la lógica: gestiona tiempo, llegadas, procesamiento y resumen. |
| `Pedido` | Representa un pedido: tipo, tiempo total, tiempo restante, minuto de llegada. |
| `MinHeap` | Implementación manual de una **cola de prioridad mínima** por tiempo de preparación. |
| `ListaPedidos` | Estructura auxiliar tipo "ArrayList" hecha con arreglos redimensionables. |

---
## ¿Qué se implementa y por qué?

### Árboles

#### ¿Qué se usa?
- Un **árbol binario completo implícito**, implementado mediante un arreglo. Conocido como **min-heap**: la raíz siempre contiene el elemento de menor tiempo en prepararse.

#### ¿Por qué?
- El cocinero siempre debe seleccionar el pedido pendiente más corto y esto requiere extraer repetidamente el mínimo de una colección que crece y se reduce.


### Listas

#### ¿Qué se usa?
- Una **lista dinámica implementada con un arreglo redimensionable**.

#### ¿Por qué?
- El heap **no puede construirse sobre una estructura estática**; necesita crecer al insertar. El arreglo redimensionable permite: acceso O(1) por índice (clave para la representación implícita del árbol) e inserción amortizada O(1).

### Colas

#### ¿Porque no se usa una cola?
- Aunque el enunciado menciona "cola de pedidos", **no es una cola FIFO**. En una cola FIFO, los pedidos se atenderían en orden de llegada y no como se pide.

###  Pilas
#### ¿Porque no se usa una pila?
- Una pila procesa el último elemento insertado primero (LIFO). El problema no requiere revertir el orden, deshacer acciones ni explorar en profundidad.

---
## Resumen

- **Árbol (min-heap)** → estructura adecuada para SJF y mayor eficiencia logarítmica.
- **Lista (arreglo dinámico)** → base para implementar el heap sin depender del JDK.
- **Cola FIFO** → inadecuada para este dominio.
- **Pila** → no aplicable.
