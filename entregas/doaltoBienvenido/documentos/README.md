# PROPUESTA RETO 005

## [ENUNCIADO](https://github.com/mmasias/25-26-EDA1/blob/main/evaluaciones/retos/005/README.md)



## Clase `Pedido`

Representa un pedido en la cocina.

### Atributos privados
- `id` : int — Identificador del pedido.  
- `tipo` : String — Tipo de plato (Bebida, Café, etc.).  
- `tiempoPreparacion` : int — Tiempo total necesario para preparar el pedido.  
- `tiempoRestante` : int — Tiempo restante para completar el pedido.  
- `instanteLlegada` : int — Momento en que llega el pedido a la cola.  
- `instanteInicio` : int — Momento en que el pedido empieza a ser preparado.  

### Métodos públicos
- `Pedido(int id, String tipo, int tiempoPreparacion, int instanteLlegada)` — Constructor.  
- `int getId()`  
- `String getTipo()`  
- `int getTiempoPreparacion()`  
- `int getTiempoRestante()`  
- `int getInstanteLlegada()`  
- `void decrementarTiempoRestante()`  
- `void marcarComoIniciado(int instante)`  
- `boolean estaCompleto()`  
- `int compareTo(Pedido otro)` — Compara según tiempo de preparación y, en caso de empate, instante de llegada.  
- `String toString()`  

---

## Clase `NodoPedido`

Representa un nodo del árbol binario que contiene un pedido.

### Atributos privados
- `pedido` : Pedido — Pedido asociado al nodo.  
- `izquierdo` : NodoPedido — Hijo izquierdo.  
- `derecho` : NodoPedido — Hijo derecho.  
- `padre` : NodoPedido — Nodo padre (opcional, para facilitar el burbujeo).  

### Métodos públicos
- `NodoPedido(Pedido pedido)` — Constructor.

---

## Clase `ColaPedidos`

Representa la cola de pedidos pendientes, implementada como **árbol binario de mínima prioridad** para mantener la política SPT.

### Atributos privados
- `root` : NodoPedido — Nodo raíz del árbol.  
- `cantidadPedidos` : int — Número actual de pedidos en la cola.  

### Métodos públicos
- `ColaPedidos()` — Constructor.  
- `void insertar(Pedido pedido)` — Inserta el pedido en el árbol manteniendo la propiedad de Min-Heap.  
- `Pedido extraerMin()` — Devuelve y elimina el pedido con menor tiempo de preparación.  
- `Pedido peekMin()` — Devuelve el pedido con menor tiempo sin eliminarlo.  
- `int tamaño()` — Devuelve el número de pedidos en la cola.  
- `boolean estaVacia()` — Indica si la cola está vacía.  

---

## Clase `Simulacion`

Controla la simulación minuto a minuto.

### Atributos privados
- `colaPedidos` : ColaPedidos — Cola de pedidos pendientes.  
- `estadisticas` : Estadisticas — Objeto que acumula métricas de la simulación.  
- `semillaAleatoria` : double — Semilla para generación de números aleatorios.  

### Métodos públicos
- `Simulacion()`  
- `Simulacion(double semilla)`  
- `void ejecutar()`  
- `void ejecutar(int minutos)`  
- `int getPedidosAtendidos()`  
- `int getPedidosPendientes()`  
- `double getTiempoEsperaTotal()`  
- `double getTiempoEsperaMedio()`  
- `double getComparacionesTotales()`  

---

## Clase `TipoPlato`

Define los tipos de plato y sus tiempos de preparación.

### Atributos privados estáticos
- `BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA`  

### Métodos públicos estáticos
- `int getMin(String tipo)`  
- `int getMax(String tipo)`  
- `String muestrearTipo()`  
- `String muestrearTipo(double semilla)`  
- `int generarTiempoParaTipo(String tipo)`  
- `int generarTiempoParaTipo(String tipo, double semilla)`  

---

## Clase `Estadisticas`

Acumula métricas de la simulación.

### Atributos privados
- `totalPedidosAtendidos` : int  
- `totalPedidosPendientes` : int  
- `tiempoEsperaAcumulado` : double  
- `comparacionesRealizadas` : double  

### Métodos públicos
- `Estadisticas()` — Constructor.  
- `void registrarInicioServicio(Pedido pedido, int instanteInicio)`  
- `void registrarPedidoAtendido(Pedido pedido)`  
- `void registrarComparaciones(double cantidadComparaciones)`  
- `int getTotalPedidosAtendidos()`  
- `int getTotalPedidosPendientes()`  
- `double getTiempoEsperaAcumulado()`  
- `double getTiempoEsperaMedio()`  
- `double getComparacionesRealizadas()`  
- `String generarResumen()`  

---

## ESQUEMA – Diagrama UML


![Diagrama](../documentosUML/Esquema.png)
