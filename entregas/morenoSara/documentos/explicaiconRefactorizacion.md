# Cambios realizados en la Simulación del Restaurante

En la versión anterior los pedidos pendientes se realizaba mediante una lista ordenada (FilaPedidos.java), lo que nos obligaba a recorrer la lista para insertar pedidos según su tiempo de preparación y contar las comparaciones realizadas. En esta versión, se utiliza un **árbol binario** para mejorar la organización del código y la eficiencia.


## 1. Cambio de estructura de datos de lista a árbol binario

- **Antes:**  
  FilaPedidos era una lista de tipo List<Pedido> ordenada por tiempo de preparación. Cada inserción recorría la lista desde el inicio hasta encontrar la posición correcta.  
- **Ahora:**  
  Se introducen las clases ArbolPedidos.java y NodoPedido.java para gestionar los pedidos mediante un **árbol binario de búsqueda (BST)** basado en el tiempo restante de preparación.  

### Ventajas:
-> Permite **insertar pedidos de manera más estructurada** en la jerarquía del árbol.  
-> Utiliza la **relación jerárquica** entre pedidos de menor y mayor tiempo de preparación.  
-> Mantiene la funcionalidad de seleccionar siempre el **pedido con menor tiempo**.


## 2. Introducción de NodoPedido

- NodoPedido actúa como **contenedor de un Pedido** y referencia a sus hijos izquierdo y derecho.  
- Separar Pedido de NodoPedido mantiene la **información del pedido independiente** de la estructura del árbol, mejorando la claridad y la extensibilidad.


## 3. Actualización de la clase Cocinero

- **Antes:** utilizaba FilaPedidos para almacenar la cola de pedidos pendientes.  
- **Ahora:** utiliza ArbolPedidos, lo que refleja que el cocinero siempre selecciona el pedido con menor tiempo de preparación del BST.  
- Los métodos principales mantienen la misma lógica, pero operan sobre el árbol en lugar de la lista.


## 4. Modificación de RCCCF

- La lógica de simulación (simularJornada) y la generación de pedidos se mantienen idénticas.  
- La extracción de pedidos ahora se realiza a través de ArbolPedidos.
- El cálculo de comparaciones totales se basa en las comparaciones realizadas en el árbol al insertar los nodos.

