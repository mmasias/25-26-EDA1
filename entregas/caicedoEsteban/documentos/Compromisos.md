# Análisis de Compromisos

## 1. Abstracción vs. Rendimiento Puro

- **Decisión:** Hemos introducido Interfaces (`IMessageBuffer`, `ISubscriptionRepository`).
- **Coste (Overhead):** Existe una micro-latencia por la "Desviación virtual" en Java/C++ al usar interfaces en lugar de clases concretas.
- **Beneficio (Clean Code):** Ganamos **Testabilidad**. Podemos crear "Mock Objects" de las estructuras de datos para probar el `IrisEngine` sin necesidad de crear datos reales. Para un proyecto universitario, la mantenibilidad y calidad del código superan la pérdida de nanosegundos en ejecución.

## 2. Consistencia vs. Disponibilidad

- **Decisión:** Uso de estructuras en memoria (`InMemory...`).
- **Sacrificio:** **Durabilidad**. Si el servidor se apaga, los datos en los `HashMap` se pierden.
- **Justificación Académica:** En el contexto del reto, se prioriza la **latencia** y la demostración de estructuras de datos dinámicas. En un entorno real, la implementación de la interfaz `IMessageBuffer` se sustituiría por una `RedisMessageBuffer` sin romper el código del `IrisEngine`.

## 3. Modelo "Push" vs. "Pull"

- **Decisión:** El sistema hace **Push** (envía emails).
- **Coste:** Si tenemos 10,000 alumnos en una asignatura, el bucle de envío puede tardar.
- **Alternativa Sacrificada:** Un modelo "Pull" (el alumno entra a una web a ver su resumen) sería más ligero para el servidor, pero menos útil para el alumno. Aceptamos el coste de envío para maximizar el valor del producto.
