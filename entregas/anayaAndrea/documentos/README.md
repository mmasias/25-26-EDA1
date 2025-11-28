# 🍳 Reto RCCCF - Simulación de Cocina Optimizada (Shortest Job First)

Este proyecto simula el funcionamiento del restaurante **RCCCF** (Restaurante de Comida del Centro Comercial y de la Comunidad de Frikis), el cual opera bajo un modelo de optimización de pedidos. A diferencia del clásico modelo **FIFO** (primero en llegar, primero en ser atendido), la cocina prioriza los pedidos con el **menor tiempo de preparación restante** (algoritmo **Shortest Job First**).

## 🎯 El Desafío

El principal desafío es modelar una cola de pedidos donde la prioridad cambia dinámicamente y se debe extraer repetidamente el **elemento mínimo**.

| Plato       | Tiempo de Preparación |
|------------|---------------------|
| Bebida     | 1-2 minutos         |
| Café       | 2-3 minutos         |
| Colacao    | 2-4 minutos         |
| Bocadillo  | 3-5 minutos         |
| Ensalada   | 5-8 minutos         |

### Reglas de la Simulación

- La jornada dura **120 minutos**.
- Cada minuto, hay un **40% de probabilidad** de que llegue un nuevo pedido.
- El cocinero siempre elige el plato pendiente con el **menor tiempo de preparación**.
- La preparación se procesa **minuto a minuto**.

## 🛠️ Solución Implementada: Min-Heap (Montículo de Mínimos)

La estructura de datos crítica para la eficiencia de esta simulación es la **cola de espera**.

### ❌ Opción Ineficiente (O(N))

Una lista o array tradicional (`ArrayPedidos` en el código original) obliga a recorrer toda la lista en cada minuto para encontrar el pedido más corto.  
Esto resulta en una complejidad de **O(N) por extracción**, generando un alto número de comparaciones totales.

### ✅ Opción Optimizada (O(log N))

El proyecto utiliza la clase `MinHeapPedidos`, que implementa un **Montículo de Mínimos (Min-Heap)**.

**Ventajas del Min-Heap:**

- **Inserción** (Llegada de Pedidos): O(log N)  
- **Extracción del Mínimo** (Selección del Cocinero): O(log N)

Gracias a que el pedido con el **menor tiempo de preparación siempre se mantiene en la raíz** del árbol, la extracción del mínimo es extremadamente rápida, reduciendo drásticamente el número de comparaciones necesarias a lo largo de la simulación.

## 📊 Métricas de Salida

La simulación muestra las siguientes métricas clave para evaluar el rendimiento del sistema de cocina y la eficiencia del algoritmo:

- **Pedidos atendidos:** Total de platos completados.  
- **Pedidos pendientes:** Platos que quedaron en cola al cierre.  
- **Tiempo medio de espera:** La métrica principal de satisfacción del cliente.  
- **Comparaciones totales:** El contador de eficiencia del algoritmo, demostrando el **ahorro computacional del Min-Heap** frente a una solución O(N).
