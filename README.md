# ☕ Reto RCCCF: Simulación de Cocina Optimizada (Shortest Job First)

## 📌 Notas  a tener en cuenta: Eficiencia y Estructuras de Datos

Este ejercicio demuestra la importancia crítica de la selección de estructuras de datos para la **eficiencia del sistema**.

* **El Problema:** El sistema requiere extraer continuamente el elemento mínimo (pedido más rápido) de una colección dinámica.
* **La Solución:** Se descarta el uso de listas (`ArrayList`) por su ineficiencia en búsqueda ($O(n)$) y se implementa una **`PriorityQueue`** basada en un **Min-Heap**.
* **El Resultado:** Las operaciones de inserción y extracción se optimizan a un coste logarítmico **$O(\log n)$**.

> **Métrica de Control:** El sistema implementa un `Comparator` personalizado que intercepta y cuenta las comparaciones realizadas por el algoritmo de ordenación, permitiendo validar empíricamente la eficiencia de la solución.

---

## 🏗️ Arquitectura 

El proyecto sigue estrictamente el paradigma de **Programación Orientada a Objetos**, priorizando el encapsulamiento, la modularidad y el diseño descendente.

### 1. `Main` 
* **Responsabilidad:** Control del flujo temporal de la simulación.
* **Características:**
    * Bucle principal limpio (`for` de 1 a 480 minutos).
    * Uso de **constantes** (`final static`) para evitar *números mágicos* (duración, probabilidades).
    * Delegación total de la lógica de negocio a la clase `Cocina`.

### 2. `Cocina` 
1.  **Menor Tiempo Restante:** El pedido que requiera menos tiempo para finalizar tiene prioridad absoluta.
2.  **Orden de Llegada (FIFO):** En caso de empate en tiempo, se prioriza el pedido más antiguo (menor ID).

```java
@Override
public int compareTo(Pedido otro) {
    
    int comparacionTiempo = Integer.compare(this.tiempoRestante, otro.tiempoRestante);
    if (comparacionTiempo != 0) {
        return comparacionTiempo;
    }
   
    return Integer.compare(this.id, otro.id);
}
## ⚙️ Parámetros de la Simulación

| Parámetro | Valor | Descripción |
| :--- | :--- | :--- |
| **Duración** | `480` min | Jornada completa de 8 horas. |
| **Probabilidad** | `0.4` | 40% de probabilidad de llegada por minuto. |

### 🥗 Tipos de Plato y Distribución

| Tipo de Plato | Rango de Tiempo (min) |
| :--- | :--- |
| **Bebida** | 1 - 2 |
| **Café** | 2 - 3 |
| **Colacao** | 2 - 4 |
| **Bocadillo** | 3 - 5 |
| **Ensalada** | 5 - 8 |

---

## 📊 Métricas Finales (Salida)

Al finalizar la ejecución, el sistema genera un informe detallado con indicadores de rendimiento (**KPIs**):

* **Capacidad de Atención:** Relación entre el total de pedidos completados vs. los que quedaron pendientes.
* **Calidad de Servicio:** Tiempo medio de espera por cliente (métrica crítica de satisfacción).
* **Eficiencia Algorítmica:** Número total de comparaciones realizadas por el **Heap** (validador de la optimización).

```text
========================================
RESUMEN DE LA JORNADA
========================================
Pedidos atendidos        : XX
Pedidos pendientes       : YY
Tiempo total de espera   : ZZ minutos
Tiempo medio de espera   : AA.A minutos
Comparaciones totales    : BB (Validación de eficiencia O(log n))
========================================