# Simulación de Restaurante (Optimización de Colas)

Este proyecto implementa una simulación en **Java** para optimizar el flujo de trabajo en una cocina de alta demanda.  
El sistema sustituye el modelo tradicional FIFO por un modelo inteligente basado en el **tiempo de preparación restante**.

---

## Contexto del Problema

En un sistema tradicional (como una caja de supermercado), los clientes se atienden en orden.  
En una cocina, esto puede causar ineficiencia.

### Ejemplo de problema

- Llega una **Ensalada** (8 min)  
- Justo después llega un **Café** (2 min)

**Problema con FIFO:** El Café debe esperar 8 minutos innecesariamente.

**Objetivo de la simulación:**  
El cocinero debe seleccionar siempre el pedido con **menor tiempo de preparación restante**, maximizando el número de pedidos atendidos y minimizando la espera.

---

## Solución Técnica: Transformación de la Cola

Para resolver este problema, se descarta el uso de estructuras lineales como `ArrayList` y se implementa una **Cola de Prioridad (PriorityQueue)**.

### Comparativa de Estructuras

| Estructura          | Comportamiento                                                                      | Eficiencia                         |
|---------------------|--------------------------------------------------------------------------------------|------------------------------------|
| **Lista Normal**    | Busca el pedido más rápido recorriendo uno por uno.                                 | Baja (costoso computacionalmente)  |
| **Cola de Prioridad** | Funciona como un *min-heap*: el pedido más rápido “flota” a la cima automáticamente. | Alta (acceso inmediato)            |

---

## Arquitectura del Código

El proyecto sigue el paradigma de **Programación Orientada a Objetos (POO)** y se estructura en los siguientes componentes:

---

### 1. **Clase Main** (Punto de Entrada)

- Inicia la simulación.
- Crea la instancia del `Restaurante`.
- Define la duración de la jornada (ej.: 480 minutos).

---

### 2. **Clase Restaurante** (El Gestor)

Es el **cerebro del sistema**.

**Responsabilidades:**
- Administrar la cola de pedidos.
- Asignar trabajo al cocinero minuto a minuto.
- Registrar métricas y mostrar el resumen final.

**Elementos clave:**

- `PriorityQueue<Pedido>` → Estructura central.
- `Comparator` personalizado → Ordena por `tiempoRestante`.
- Métrica interna → Cuenta cuántas comparaciones realiza la cola para medir eficiencia.

---

### 3. **Clase Cocinero** (El Trabajador)

Representa al empleado que procesa la comida.

- Puede estar **libre** u **ocupado**.
- Procesa un pedido reduciendo su tiempo restante cada minuto.
- Cuando termina, queda disponible para el siguiente pedido más rápido.

---

### 4. **Clase Pedido** (La Entidad)

Unidad de trabajo que fluye por el sistema.

- Contiene `tipo`, `tiempoRestante`, `minutoLlegada` e ID único.
- `tiempoRestante` es **la clave de prioridad**.
- Se decrementa conforme el cocinero trabaja.

---

### 5. **Enum TipoPlato** (Configuración)

Define el menú del restaurante:

- Bebida  
- Café  
- ColaCao  
- Bocadillo  
- Ensalada  

Cada tipo tiene un **rango de tiempo de preparación aleatorio** que genera variabilidad en el sistema.
