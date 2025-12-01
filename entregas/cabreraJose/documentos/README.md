# Reto 005: Sistema de Gestión de Pedidos con Priority Queue

> **Asignatura:** Estructuras de Datos y Algoritmos I (EDA1)  
> **Curso:** 2025-2026  
> **Autor:** José Cabrera / JoseCabrera05

## 📋 Descripción del Problema

El reto consiste en **simular un sistema de procesamiento de pedidos** donde:
- Los pedidos **llegan continuamente** durante la simulación.
- Se requiere **extraer repetidamente el pedido con prioridad mínima** (menor costo, menor tiempo de espera, etc.).
- La colección de pedidos **cambia dinámicamente** (llegan nuevos, se procesan antiguos).

Este problema es un caso clásico de **Priority Queue**, donde necesitamos una estructura eficiente para mantener el elemento mínimo accesible mientras permitimos inserciones y eliminaciones dinámicas.

---

## Reflexión: Comparativa de Estructuras de Datos

### Análisis de Opciones

| Estructura | Inserción | Extracción Min | Ventajas | Desventajas |
|-----------|-----------|-----------------|----------|------------|
| **Array desordenado** | O(1) | O(n) | Inserción rápida | Búsqueda lenta |
| **Array ordenado** | O(n) | O(1) | Extracción rápida | Inserción lenta |
| **BST balanceado** | O(log n) | O(log n) | Equilibrado | Complejo; riesgo degeneración |
| **🏆 Min-Heap** | O(log n) | O(log n) | Garantizado; simple | Solo acceso al mínimo |

### Justificación: Min-Heap

Un **min-heap** es la estructura óptima porque:

1. **Acceso rápido al mínimo:** La raíz siempre contiene el elemento de mayor prioridad (mínimo costo/tiempo).
2. **Operaciones O(log n):** Tanto inserción como extracción están acotadas logarítmicamente.
3. **Estructura garantizada:** El árbol siempre está balanceado (altura = ⌊log n⌋), evitando degeneraciones.
4. **Implementación eficiente:** Se puede implementar con un simple array, sin necesidad de punteros o referencias.
5. **Parcialmente ordenado:** No requiere orden total (como BST), solo que el padre sea menor que sus hijos.

---

## 🏗️ Diagrama de Arquitectura
┌──────────────────────────────────┐
│  INTERFAZ (Main / Menú Usuario)  │
│  - Llegar nuevo pedido           │
│  - Procesar próximo pedido       │
│  - Ver estado del sistema        │
└──────────────┬───────────────────┘
               │
               ▼
┌──────────────────────────────────┐
│   GESTOR DE PEDIDOS              │
│  - agregarPedido(pedido)         │
│  - procesarPedidoMinimo()        │
│  - consultarEstado()             │
└──────────────┬───────────────────┘
               │
               ▼
┌──────────────────────────────────┐
│      MIN-HEAP (Priority Queue)   │
│                                  │
│    insert(E)   → O(log n) siftUp │
│    extractMin() → O(log n) siftDn│
│                                  │
│   Implementación: Array Compacto │
│   Posiciones:  [0, 1, 2, 3, ...] │
│   Estructura:                    │
│         0 (mínimo)               │
│        / \                       │
│       1   2                      │
│      / \ / \                     │
│     3  4 5  6                    │
└──────────────────────────────────┘

