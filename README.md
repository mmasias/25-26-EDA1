# 📘 EDA I – Reto-005-Minimalista

Este repositorio contiene la solución completa al **Reto RCCCF**, utilizando una implementación minimalista basada en **árboles binarios de búsqueda** para optimizar la selección de pedidos según su tiempo de preparación.

El proyecto se organiza en **dos bloques principales** para facilitar su revisión:

---

##  Documentos de referencia

Incluye toda la documentación relacionada con el diseño y análisis previo del sistema:

- Diagrama UML del RCCCF  
- Explicación del diagrama UML  
- Captura del UML generado  

 **Acceder a los documentos:**  
 [Documentos de referencia](https://github.com/Paula-Oreja/25-26-EDA1/blob/reto-005-refactorMinimalista/entregas/orejaPaula/documentosUML/README.md)

---

##  Documentación y Código Fuente del RCCCF

Contiene todas las clases desarrolladas para implementar el comportamiento del sistema, aplicando **árboles binarios**:

- **Pedido.java** → modelo de un pedido  
- **ArbolPedidos.java** → estructura Binary Search Tree  
- **Simulation.java** → lógica de la cocina, generación de pedidos y métricas  

**Acceder al código detallado:**  
👉 [Documentación y Código Fuente del RCCCF](https://github.com/Paula-Oreja/25-26-EDA1/blob/reto-005-refactorMinimalista/entregas/orejaPaula/src/README.md)

---

##  Estructura del repositorio

``` 
entregas/
└── orejaPaula/
    ├── documentos/
    │   └── README.md
    │
    ├── documentosUML/
    │   ├── diagrama_rcccf.puml
    │   ├── explicacion_uml.md
    │   ├── image.png
    │   └── README.md
    │
    ├── src/
    │   ├── ArbolPedidos.java
    │   ├── Pedido.java
    │   ├── Simulation.java
    │   └── README.md
    │
    └── README.md

``` 
##  Notas sobre la entrega

- [Enunciado](https://github.com/mmasias/25-26-EDA1/blob/main/evaluaciones/retos/005/README.md)

- [Refactorizado de mi código del Reto05](https://github.com/Paula-Oreja/25-26-EDA1/tree/reto-005)

- La simulación procesa los pedidos según **el menor tiempo de preparación**, utilizando un **árbol binario de búsqueda (BST)** para seleccionar siempre el pedido óptimo.

- El sistema registra **estadísticas básicas** durante la ejecución:
  - Pedidos atendidos
  - Tiempo total y medio de espera
  - Comparaciones realizadas en el árbol durante inserciones y extracciones

- El diseño **UML inicial** se usó como base para crear la arquitectura final, simplificando las clases y aplicando una estructura más minimalista que mejora la legibilidad y mantiene la lógica esencial del reto.
