# Simulación de Restaurante (Implementación con ArrayList)

Este proyecto implementa una simulación en **Java** para optimizar el flujo de trabajo en una cocina, aplicando la estrategia **SJF (Shortest Job First)** mediante una **Búsqueda Lineal** sobre una lista estándar (`ArrayList`).

---

## Contexto del Problema

En un sistema de colas tradicional (FIFO), un pedido rápido puede quedar bloqueado detrás de uno lento.

**Ejemplo:**
- Ensalada → 8 min  
- Café → 2 min (pero queda esperando detrás)

 **Objetivo:**  
El cocinero debe seleccionar siempre el pedido con **menor tiempo de preparación restante** para reducir tiempos de espera y aumentar la productividad.

---

## 💡 Solución Técnica: Búsqueda Lineal

En esta versión del proyecto, la optimización se implementa, sino con una estructura simple:

### 🔍 Lógica del Algoritmo

1. **Almacenamiento**  
   Los pedidos se guardan en un `ArrayList` a medida que llegan.

2. **Selección del Siguiente Pedido**  
   Cuando el cocinero queda libre, el sistema **recorre toda la lista** de principio a fin.

3. **Comparación**  
   Se compara el tiempo restante de cada pedido para encontrar el más rápido.

4. **Extracción**  
   Se elimina ese pedido de la lista y se asigna al cocinero.

> **Nota de Ingeniería:**  
> A diferencia de una PriorityQueue (que mantiene el mínimo automáticamente), este enfoque requiere revisar **N elementos por búsqueda**, lo cual implica un coste **O(N)** por cada asignación de trabajo.

---

## Arquitectura del Código

El sistema sigue un diseño orientado a objetos con roles claramente definidos:

---

### 1. **Main** (Punto de Entrada)

- Inicializa la simulación.
- Configura la duración de la jornada (ej.: 480 minutos).

---

### 2. **Restaurante** (Gestor de Lógica)

Es el componente más importante y el que cambia en esta versión.

**Responsabilidades:**

- Usa `List<Pedido>` (`ArrayList`) para almacenar todos los pedidos pendientes.
- Implementa el método **`buscarYExtraerMasRapido()`**, encargado de:
  - Recorrer toda la lista
  - Comparar el tiempo restante de cada pedido
  - Seleccionar el más rápido
  - Incrementar un contador interno de comparaciones

**Métrica incluida:**
- Número de comparaciones realizadas → Permite medir la eficiencia real del algoritmo.

---

### 3. **Cocinero** (El Trabajador)

- Representa el recurso que procesa pedidos minuto a minuto.
- No conoce la lógica de selección.
- Solo recibe un pedido y lo procesa hasta completarlo.

---

### 4. **Pedido** (La Entidad)

Contiene toda la información relevante sobre cada trabajo:

- `tipo` → Referencia al plato solicitado.
- `tiempoRestante` → Valor crítico utilizado para determinar la prioridad.
- `minutoLlegada` → Para métricas y análisis de espera.

---

### 5. **TipoPlato** (Configuración)

Enum que centraliza el menú del restaurante:

- Bebida  
- Café  
- ColaCao  
- Bocadillo  
- Ensalada  

Cada uno define un rango de tiempo de preparación aleatorio para la simulación.
