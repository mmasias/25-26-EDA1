#  Documentación y Código Fuente del RCCCF Minimalista

A continuación se listan las clases implementadas en la versión final del proyecto, organizadas de forma minimalista y utilizando un **Árbol Binario de Búsqueda** como estructura principal.

---

##  Modelo
- **[Clase Pedido](https://github.com/Paula-Oreja/25-26-EDA1/blob/reto-005-refactorMinimalista/entregas/orejaPaula/src/Pedido.java)**  
  Representa un pedido con su tiempo de preparación, momento de llegada y tiempo restante.

---

## Estructura de datos
- **[Clase ArbolPedidos](https://github.com/Paula-Oreja/25-26-EDA1/blob/reto-005-refactorMinimalista/entregas/orejaPaula/src/ArbolPedidos.java)**  
  Implementa un Árbol Binario de Búsqueda (BST) para almacenar los pedidos y extraer el pedido con menor tiempo de preparación.

---

## Ejecución de la simulación
- **[Clase Simulation](https://github.com/Paula-Oreja/25-26-EDA1/blob/reto-005-refactorMinimalista/entregas/orejaPaula/src/Simulation.java)**  
  Gestiona el ciclo de la jornada, las llegadas de pedidos, el procesamiento minuto a minuto y el resumen final.

---

###  Nota
Esta versión reduce el proyecto a su forma más sencilla y clara:
- sin interfaces,
- sin clases intermedias,
- usando un único árbol binario para resolver el problema de obtener el mínimo de forma repetida.

La implementación se centra en el objetivo pedagógico del ejercicio:  
**elegir la estructura de datos adecuada para minimizar comparaciones al seleccionar el próximo pedido.**
