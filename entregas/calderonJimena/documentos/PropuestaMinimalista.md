# Propuesta minimalista de la Simulación RCCCF

## Cambios principales de la propuesta inicial

### 1. **Estructura de datos: de "min-heap" a "árbol binario"**

- **Propuesta inicial**: se planteó usar un **min-heap** cuya estructura es  óptima para colas de prioridad.
- **Solución minimalista**: implementación de un **Árbol Binario**, ordenado por `tiempoTotalPreparacion`.
  - Inserción: recursiva (natural en árboles) o iterativa.
  - Extracción del mínimo: recorrido hasta el nodo más a la izquierda.
  - Se cuenta cada comparación entre tiempos para la métrica de eficiencia.
---
### 2. **Separación clara de clases**

- **Propuesta inicial**: se consideró fusionar lógica o usar clases anidadas.
- **Cambio final**: se respetó la **modularidad pedagógica**:
  - `Pedido`: datos de cada orden.
  - `Nodo`: componente del árbol.
  - `ArbolBinario`: gestiona inserción y extracción.
  - `Restaurant`: orquesta la simulación.
  - `Simulation`: punto de entrada (`main`).
---

## Conclusión

La implementación final cumple todos los requisitos del enunciado y refleja una evolución pedagógica hacia:

- **Claridad** (nombres, estructura),
- **Correctitud** (ABB para SJF),
- **Minimalismo sin sacrificar funcionalidad**.
