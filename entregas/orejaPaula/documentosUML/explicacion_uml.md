# Diagrama de clases RCCCF – Explicación

Este diagrama modela el sistema de la cocina del **RCCCF** descrito en el enunciado.  
La idea general es:

- Simular minuto a minuto el funcionamiento de la cocina.
- Modelar pedidos, platos y sus tiempos de preparación.
- Medir tiempos de espera y número de comparaciones.
- Poder cambiar la estructura de datos que gestiona la cola de pedidos.

---

## 1. Clase `Simulation`

Es la clase que **controla toda la simulación**.

Atributos principales:
- `duracionJornada`: duración total en minutos.
- `probLlegada`: probabilidad de llegada de un pedido por minuto (40%).
- `tiempoActual`: minuto que se está procesando.
- `cocina`: objeto `Cocina` que gestiona la preparación de pedidos.
- `estadisticas`: acumula métricas finales.
- `random`: generador de valores aleatorios.
- `siguienteId`: asigna IDs únicos a los pedidos.

Método clave:
- `iniciar()`: ejecuta la simulación minuto a minuto.

Relaciones:
- `Simulation` **compone** una `Cocina` y unas `Estadisticas`.

---

## 2. Clase `Cocina`

Representa al **cocinero** y su comportamiento.

Atributos:
- `estructuraPedidos`: cualquier estructura que implemente `IEstructuraPedidos`.
- `pedidoActual`: pedido que se está preparando en este momento.

Métodos:
- `recibirPedido(p)`: añade un pedido a la cola.
- `seleccionarSiguientePedido(...)`: elige el pedido con menor tiempo de preparación.
- `procesarMinuto(...)`: avanza un minuto, decrementa tiempo restante y actualiza estadísticas.

Relaciones:
- La cocina **usa** una estructura de datos abstracta (`IEstructuraPedidos`).
- Tiene un `pedidoActual` que va cambiando.

---

## 3. Interfaz `IEstructuraPedidos` y sus implementaciones

Define el contrato de una **estructura de datos para pedidos pendientes**.

Métodos:
- `insertar(p)`: añade un pedido.
- `extraerMinimo()`: devuelve y elimina el pedido con menor tiempo restante.
- `estaVacia()`, `tamano()`: consultan el estado de la cola.
- `getComparaciones()`: cuántas comparaciones realizó la estructura.
- `resetComparaciones()`: reinicia el contador.

### Implementaciones:

### `ColaPrioridadPedidos`
- Basada en `PriorityQueue` (heap).
- Muy eficiente para extraer mínimos.

### `ListaLinealPedidos`
- Lista normal que busca el mínimo recorriendo todos los elementos.
- Permite comparar eficiencia vs. cola de prioridad.

Relaciones:
- Ambas clases **implementan** `IEstructuraPedidos`.

---

## 4. Clase `Pedido`

Modela un pedido real del restaurante.

Atributos:
- `id`: identificador único.
- `plato`: tipo de plato (enum `Plato`).
- `tiempoPreparacion`: tiempo total asignado.
- `tiempoRestante`: va disminuyendo con el tiempo.
- `instanteLlegada`: minuto en que llegó.
- `instanteInicio`: minuto en que el cocinero lo empezó.

Métodos:
- `decrementarTiempo()`: resta 1 al tiempo restante.
- `estaTerminado()`: indica si ya está listo.
- Getters y setter del instante de inicio.

Relaciones:
- `Pedido` **usa** `Plato`.

---

## 5. Enum `Plato`

Enum que representa los tipos de platos del enunciado:

- Bebida → 1–2 min  
- Café → 2–3 min  
- Colacao → 2–4 min  
- Bocadillo → 3–5 min  
- Ensalada → 5–8 min  

Atributos:
- `nombre`: nombre del plato.
- `minTiempo`, `maxTiempo`: rango de tiempo.

Métodos:
- `tiempoAleatorio()`: devuelve un tiempo dentro del rango.
- `aleatorio()`: devuelve un plato aleatorio entre los disponibles.

---

## 6. Clase `Estadisticas`

Acumula toda la información necesaria para generar el **resumen final**:

Atributos:
- `pedidosAtendidos`
- `pedidosPendientes`
- `tiempoTotalEspera`
- `comparacionesTotales`

Métodos:
- `registrarAtencion(p)`: suma el tiempo de espera del pedido.
- `sumarComparaciones(n)`: acumula comparaciones realizadas.
- `setPedidosPendientes(n)`
- `getTiempoMedioEspera()`

---



En resumen, este UML describe una arquitectura sólida, clara y extensible para simular la cocina del RCCCF y analizar distintas estructuras de datos para extraer el mínimo repetidamente.
