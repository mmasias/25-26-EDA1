### Clase Cliente

* **public static void main(String[] args)**
    Punto de entrada del programa. Crea una instancia de `JornadaRestaurante` y llama a su método `ejecutar()` para iniciar la simulación interactiva.

---

### Clase JornadaRestaurante

* **+ JornadaRestaurante()**
    Constructor. Prepara la jornada inicializando el `Cocinero`, la `ColaPedidos` (como un árbol binario vacío), el generador aleatorio y todas las métricas a cero.
* **+ void ejecutar()**
    Inicia y controla el bucle principal de la simulación (de 9:00 a 21:00). Gestiona las llegadas y el trabajo de la cocina minuto a minuto. **Pausa la ejecución** en cada ciclo esperando que el usuario presione ENTER para avanzar o 'Q' para terminar anticipadamente. Al finalizar, imprime el resumen.

---

### Clase Cocinero

* **+ Cocinero()**
    Constructor. Inicializa al cocinero en estado "libre" (sin `pedidoActual`).
* **+ boolean estaLibre()**
    Devuelve `true` si el cocinero no está procesando ningún pedido en este momento.
* **+ void tomarPedido(Pedido pedido)**
    Asigna un nuevo pedido al cocinero para que empiece a trabajar en él inmediatamente.
* **+ Pedido trabajar()**
    Simula un minuto de trabajo. Reduce el `tiempoRestante` del pedido actual. Si el tiempo llega a 0, devuelve el pedido terminado; de lo contrario, devuelve `null`.
* **+ Pedido obtenerPedidoActual()**
    Devuelve el objeto `Pedido` que el cocinero está preparando actualmente.

---

### Clase Pedido

* **+ Pedido(String tipo, int tiempoPreparacion, int minutoLlegada)**
    Constructor para crear nuevos pedidos con sus características iniciales.
* **+ String tipo**
    Atributo público. El tipo de plato (ej. "Café", "Ensalada").
* **+ int tiempoPreparacion**
    Atributo público. El tiempo total requerido para preparar el plato. **Es el criterio de ordenamiento** dentro del árbol de pedidos.
* **+ int tiempoRestante**
    Atributo público. El tiempo que falta para completar el pedido (se reduce por el cocinero).

---

### Clase ColaPedidos (Árbol Binario)

* **+ ColaPedidos()**
    Constructor. Inicializa la estructura del árbol estableciendo la `raiz` en `null` y el tamaño en 0.
* **+ void agregar(Pedido pedido)**
    Inserta un nuevo pedido en el **Árbol Binario de Búsqueda**. Recorre la estructura recursivamente comparando el `tiempoPreparacion` para colocar los tiempos menores a la izquierda y los mayores o iguales a la derecha.
* **+ boolean estaVacia()**
    Devuelve `true` si el árbol no tiene nodos (la raíz es `null`).
* **+ int obtenerTamano()**
    Devuelve el contador actual de pedidos pendientes en el árbol.
* **+ ResultadoExtraccion extraerMinimo()**
    Busca el nodo situado más a la **izquierda** del árbol (el de menor tiempo), lo elimina de la estructura reajustando los punteros del padre y devuelve el pedido encontrado junto con las comparaciones realizadas.

---

### Clase NodoPedido

* **+ NodoPedido(Pedido pedido)**
    Constructor. Crea un nodo para el árbol que contiene el pedido y referencias nulas a sus hijos.
* **+ Pedido pedido**
    Atributo público. El objeto de datos almacenado en este nodo.
* **+ NodoPedido izquierda**
    Atributo público. Referencia al hijo izquierdo (pedidos con **menor** tiempo de preparación).
* **+ NodoPedido derecha**
    Atributo público. Referencia al hijo derecho (pedidos con **mayor o igual** tiempo de preparación).

---

### Clase ResultadoExtraccion

* **+ ResultadoExtraccion(Pedido pedidoMinimo, int comparaciones)**
    Constructor. Crea un objeto auxiliar para devolver dos valores simultáneamente desde `extraerMinimo()`.
* **+ Pedido pedidoMinimo**
    Atributo público. El pedido con el menor tiempo de preparación que fue extraído del árbol.
* **+ int comparaciones**
    Atributo público. El número de nodos que se tuvieron que visitar (profundidad) para encontrar el elemento mínimo.