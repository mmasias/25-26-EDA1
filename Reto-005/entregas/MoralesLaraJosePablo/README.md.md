# Propuesta de Solución - Sistema RCCCF (Restaurante)

## 1. Análisis del Problema

### Naturaleza del sistema
El RCCCF requiere un sistema de **cola de prioridad dinámica** donde:
- Los elementos (pedidos) llegan de forma aleatoria durante la jornada
- Se debe extraer siempre el elemento con **mínima prioridad** (menor tiempo de preparación)
- La estructura debe soportar **inserciones y extracciones dinámicas**
- Es crítico medir la **eficiencia computacional** (número de comparaciones)

### Desafío clave
A diferencia de una cola FIFO tradicional, aquí el orden de procesamiento depende de la **prioridad intrínseca** de cada pedido, no de su orden de llegada. 
Sin embargo, el orden de llegada actúa como criterio de desempate.

---

## 2. Decisiones de Diseño

### 2.1 Estructura de datos: Árbol Binario de Búsqueda (BST) Simple

**Justificación:**
- Permite mantener los pedidos ordenados según prioridad
- Inserción eficiente: O(log n) en promedio
- Extracción del mínimo: O(log n) en promedio
- El nodo más a la izquierda siempre contiene el pedido con menor tiempo

**Por qué no otras estructuras:**
- **Array/Lista ordenada**: Inserción O(n) - ineficiente para llegadas dinámicas
- **Cola simple**: No respeta prioridades
- **BST balanceado**: Complejidad adicional innecesaria dado el patrón aleatorio esperado

### 2.2 Criterio de ordenación: Clave compuesta jerárquica

**Prioridad 1**: Tiempo de preparación (menor primero)  
**Prioridad 2**: Orden de llegada / Timestamp (desempate FIFO)

**Clave del nodo**: `(tiempo_preparacion, identificador_secuencial)`

**Regla de comparación:**
```
Si tiempo_A < tiempo_B → A tiene mayor prioridad
Si tiempo_A == tiempo_B → comparar por identificador (el menor tiene mayor prioridad)
```

### 2.3 Métrica de eficiencia: Comparaciones totales

**Contabilizar:**
- Comparaciones durante **inserción** de nuevos pedidos
- Comparaciones durante **búsqueda y extracción** del mínimo
- Cada visita a un nodo para decisión de navegación = 1 comparación

---

## 3. Arquitectura del Sistema

### 3.1 Componentes principales
```
┌─────────────────────────────────────────────────┐
│           SIMULADOR DE JORNADA                  │
│  - Control del tiempo (120 minutos)             │
│  - Generación aleatoria de llegadas (40% prob)  │
│  - Coordinación cocinero-cola                   │
└─────────────────┬───────────────────────────────┘
                  │
         ┌────────┴────────┐
         │                 │
         ▼                 ▼
┌─────────────────┐  ┌─────────────────┐
│  GENERADOR DE   │  │    COCINERO     │
│    PEDIDOS      │  │  - Procesa      │
│  - Tipos plato  │  │  - Extrae min   │
│  - Tiempos      │  │  - Decrementa   │
│  - Timestamp    │  │    tiempo       │
└────────┬────────┘  └────────┬────────┘
         │                    │
         │    ┌───────────────┘
         │    │
         ▼    ▼
    ┌──────────────────┐
    │   COLA (BST)     │
    │  - Insertar      │
    │  - Extraer min   │
    │  - Contar comp   │
    └──────────────────┘
```
### 3.2 Entidades del dominio

#### **Pedido**
- `tipo`: String (Bebida, Café, Colacao, Bocadillo, Ensalada)
- `tiempo_preparacion`: Entero (minutos totales requeridos)
- `tiempo_restante`: Entero (minutos que faltan para completar)
- `id_orden`: Entero (timestamp o secuencial)
- `minuto_llegada`: Entero (para estadísticas de espera)

#### **Árbol BST (Cola de pedidos)**
- `raiz`: Referencia al nodo raíz
- `contador_comparaciones`: Entero acumulador

- **Operaciones:**
  - `insertar(pedido)`: Añade pedido manteniendo orden BST
  - `extraer_minimo()`: Retorna y elimina el pedido de menor prioridad
  - `esta_vacio()`: Verifica si hay pedidos pendientes
  - `contar_pendientes()`: Retorna número de nodos

#### **Cocinero**
- `pedido_actual`: Referencia al pedido en proceso (o null)
- **Operaciones:**
  - `tomar_pedido(cola_bst)`: Extrae el mínimo de la cola
  - `procesar()`: Decrementa tiempo_restante del pedido actual
  - `ha_terminado()`: Verifica si tiempo_restante == 0

#### **Simulador**
- `minuto_actual`: Entero (0-120)
- `cola`: Instancia del BST
- `cocinero`: Instancia del Cocinero
- `generador`: Instancia del generador de pedidos
- `estadisticas`: Objeto para acumular métricas