# Documentación de diseño: Simulación RCCCF

## 1. Arquitectura del sistema

La solución se divide en tres componentes principales: el Modelo de Datos (`Pedido`), el Gestor Lógico (`Cocina`) y el Motor de Tiempo (`Simulacion`).

### A. Clase `Pedido` (Modelo de datos)

Debe ser comparable para que la cola de prioridad lo ordene automáticamente.

Responsabilidad: almacenar el estado del plato y definir la lógica de comparación.

Atributos:

- `tipo` (String): Nombre del plato (por ejemplo, "Café", "Ensalada").
- `tiempoTotal` (int): Tiempo original de preparación.
- `tiempoRestante` (int): Tiempo pendiente (se actualiza cada minuto).
- `prioridad` (int): Valor de ordenamiento (equivalente a `tiempoTotal`).

Métodos clave:

- `procesarMinuto()`: reduce `tiempoRestante` en 1.
- `compareTo(Pedido otro)`: (crítico) define que un pedido es "menor" (más prioritario) si su tiempo de preparación es más corto. Aquí se puede contar el número de comparaciones realizadas.

### B. Clase `Cocina` (Gestor lógico)

Actúa como el contenedor inteligente que abstrae la estructura de datos.

Responsabilidad: gestionar la cola de espera y asignar trabajo al cocinero.


Métodos clave:

- `recibirPedido(Pedido p)`: inserta el pedido en la cola.
- `trabajar()`: ejecuta la lógica del minuto actual (cocinar, finalizar pedido, sacar siguiente).
- `obtenerSiguiente()`: recibe el siguiente pedido con el menor tiempo de realización.

### C. Clase `Simulacion` (Main)

Contiene el punto de entrada (`main`) y el bucle que controla el paso del tiempo.

Flujo de ejecución:

- Bucle de tiempo (minuto 0 → fin de jornada).
- Generación aleatoria de demanda (por ejemplo, 40% de probabilidad de nueva llegada cada minuto).
- Llamada a `cocina.trabajar()` cada minuto.
- Visualización de la cola en consola y cálculo de estadísticas al final.


## 2. Resumen de métricas

Al finalizar la jornada el sistema debe reportar:

- **Pedidos atendidos**: contador incrementado cuando `tiempoRestante == 0`.
- **Pedidos pendientes**: tamaño final de la cola (`colaPedidos.size()`).
- **Tiempo medio y total de espera**: Calculo de la media y el total del tiempo de espera
- **Eficiencia del algoritmo**: valor final de `Pedido.comparacionesTotales`.


