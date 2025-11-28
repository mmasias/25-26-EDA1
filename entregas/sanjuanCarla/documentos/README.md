# PROPUESTA DE SOLUCION

## Introducción
El reto RCCCF modela una cocina de restaurante con un único cocinero que, en lugar de atender los pedidos por orden de llegada (FIFO), siempre selecciona el pedido pendiente con menor tiempo de preparación. Este enfoque busca maximizar el número de pedidos completados y minimizar los tiempos de espera.

El sistema debe simularse durante una jornada de 120 minutos, considerando que cada minuto hay un 40 % de probabilidad de que llegue un nuevo pedido. Cada pedido pertenece a una de cinco categorías con tiempos de preparación predefinidos.

Este problema representa un caso clásico donde la elección de la estructura de datos impacta directamente en la eficiencia del algoritmo, especialmente en la operación crítica: extraer repetidamente el mínimo de una colección dinámica.

## Requisitos del Sistema
Simular la llegada aleatoria de pedidos (40 % por minuto).
Cada pedido tiene un tipo y un tiempo de preparación aleatorio dentro de un rango.
El cocinero procesa un pedido minuto a minuto.
Al terminar, elige el pedido pendiente con menor tiempo de preparación.
Mostrar en consola, por minuto:
Llegadas de pedidos.
Número de pedidos en cola.
Pedido actual y su tiempo restante.
Al finalizar la jornada, mostrar:
Pedidos atendidos y pendientes.
Tiempo total y medio de espera.
Número total de comparaciones realizadas (métrica de eficiencia).

## Diseño de la Solución
### Clases
Pedido
Representa un pedido con:

* Tipo: nombre del plato.
* TiempoPreparacion: tiempo total necesario.
* TiempoRestante: tiempo aún por procesar.
* TiempoLlegada: minuto en que llegó (para cálculo de espera).

* Implementa Comparable<Pedido> comparando primero por tiempoPreparacion y, en caso de empate, por
TiempoLlegada (para evitar sesgos).

Simulation
Clase principal que:

* Inicializa la simulación.
* Gestiona la cola de pedidos (PriorityQueue<Pedido>).
* Ejecuta el bucle minuto a minuto.
* Muestra el estado y genera el resumen final.

## Ventajas de la Propuesta
Eficiencia algorítmica: operaciones que evitan el cuello de botella de búsquedas lineales.

Claridad conceptual: la cola de prioridad refleja fielmente la lógica del sistema (“siempre el más corto primero”).

Cumplimiento de métricas:  el número de comparaciones se mantiene bajo, permitiendo evaluación cuantitativa del rendimiento.

Escalabilidad: el enfoque sigue siendo eficiente incluso con cientos de pedidos.

## Conclusión
Este reto ilustra de forma práctica por qué la elección de la estructura de datos es fundamental en el diseño de algoritmos eficientes. Mientras que una lista tradicional llevaría a un número cuadrático de comparaciones, el uso de una cola de prioridad basada en heap reduce drásticamente ese costo, alineándose con los principios enseñados en Estructura de Datos y Algoritmos I.

La solución propuesta no solo cumple con los requisitos funcionales, sino que también optimiza la métrica de eficiencia solicitada, demostrando una comprensión profunda de los conceptos clave de la asignatura.
