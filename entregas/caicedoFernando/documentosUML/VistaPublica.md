### Clase Cliente

* **public static void main(String[] args)**
    Punto de entrada del programa. Crea una instancia de `JornadaRestaurante` y llama a su método `ejecutar()` para iniciar la simulación.

---

### Clase JornadaRestaurante

* **+ JornadaRestaurante()**
    Constructor. Prepara la jornada inicializando el `Cocinero`, la `ColaPedidos` vacía, el generador aleatorio y todas las métricas a cero.
* **+ void ejecutar()**
    Inicia y controla el bucle principal de la simulación (de 9:00 a 21:00), gestionando las llegadas y el trabajo de la cocina en cada minuto. Al finalizar, imprime el resumen.

---

### Clase Cocinero

* **+ Cocinero()**
    Constructor. Inicializa al cocinero en estado "libre" (sin `pedidoActual`).
* **+ boolean estaLibre()**
    Devuelve `true` si el cocinero no está procesando ningún pedido en este momento.
* **+ void tomarPedido(Pedido pedido)**
    Asigna un nuevo pedido al cocinero para que empiece a trabajar en él.
* **+ Pedido trabajar()**
    Simula un minuto de trabajo del cocinero. Reduce el `tiempoRestante` del pedido actual. Si el pedido se termina (tiempo llega a 0), lo devuelve.
* **+ Pedido obtenerPedidoActual()**
    Devuelve el `Pedido` en el que el cocinero está trabajando actualmente.

---

### Clase Pedido

* **+ Pedido(String tipo, int tiempoPreparacion, int minutoLlegada)**
    Constructor público para crear nuevos pedidos.
* **+ String tipo**
    Atributo público. El tipo de plato (ej. "Café", "Bocadillo").
* **+ int tiempoPreparacion**
    Atributo público. El tiempo original asignado para preparar este pedido.
* **+ int tiempoRestante**
    Atributo público. El tiempo que falta para completar el pedido (se reduce por el cocinero).
* **+ int minutoLlegada**
    Atributo público. El minuto de la simulación en que el pedido entró a la cola.

---

### Clase ColaPedidos

* **+ ColaPedidos()**
    Constructor. Inicializa una cola de pedidos vacía (pone la `cabeza` en `null`).
* **+ void agregar(Pedido pedido)**
    Añade un nuevo pedido a la cola (lo inserta al inicio de la lista enlazada).
* **+ boolean estaVacia()**
    Devuelve `true` si no hay ningún pedido en la cola de espera.
* **+ int obtenerTamano()**
    Devuelve el número actual de pedidos que están en la cola.
* **+ ResultadoExtraccion extraerMinimo()**
    Busca en toda la lista el pedido con el menor `tiempoPreparacion`, lo elimina de la lista y lo devuelve (junto con el conteo de comparaciones).

---

### Clase NodoPedido

* **+ NodoPedido(Pedido pedido)**
    Constructor. Crea un nuevo eslabón de la lista enlazada para contener un pedido.
* **+ Pedido pedido**
    Atributo público. El `Pedido` almacenado en este nodo.
* **+ NodoPedido siguiente**
    Atributo público. Referencia al siguiente nodo de la lista (o `null` si es el último).

---

### Clase ResultadoExtraccion

* **+ ResultadoExtraccion(Pedido pedidoMinimo, int comparaciones)**
    Constructor. Crea un objeto para empaquetar los dos valores que devuelve `extraerMinimo()`.
* **+ Pedido pedidoMinimo**
    Atributo público. El `Pedido` que se extrajo de la cola.
* **+ int comparaciones**
    Atributo público. El número de comparaciones que costó encontrar ese pedido mínimo.