
## Reto005  Arquitectura para manejar la Solucion

### 1. El Controlador : `SimulacionCocina`
* **Rol:** Es el cerebro de todo. 
* **Responsabilidad:** Maneja el reloj (el bucle principal de 1 a 120 minutos). Coordina los eventos, como la llegada de nuevos pedidos, y le da órdenes al Modelo (Cocinero, Cola) y a la Vista (imprimir estado).

### 2. La Vista (View): `Vista`
* **Rol:** Es la "impresora" de la consola.
* **Responsabilidad:** Es una clase "tonta" que no toma decisiones. Su único trabajo es recibir datos del Controlador y mostrarlos de forma bonita, ya sea el estado minuto a minuto o el resumen final.

### 3. El Modelo (Model): `Pedido`, `Cocinero`, `ColaDePrioridad`
* **Rol:** Representa los datos y las reglas de negocio.
* **Responsabilidad:** Contiene la lógica interna.
    * `Pedido`: Almacena los datos de un pedido.
    * `Cocinero`: Maneja su propio estado (Libre/Ocupado).
    * `ColaDePrioridad`: La estructura de datos clave que almacena los pedidos pendientes.

---

##  Vista Pública (API) 


### 1. Clase: `SimulacionCocina` (El Controlador)

Es la clase principal que enciende todo. 

* Saber cómo `iniciarSimulacion()`.

### 2. Clase: `Vista` (La Vista)

Clase tonta que solo recibe órdenes para imprimir en pantalla.

**Responsabilidades Públicas:**
* Saber cómo `mostrarLlegadaPedido(...)`
* Saber cómo `mostrarEstado(...)`
* Saber cómo `mostrarResumenFinal(...)`
* Saber cómo `imprimirSeparador()` 

### 3. Clase: `Cocinero` (El Modelo)

Representa al cocinero y gestiona su estado interno.

**Responsabilidades Públicas:**
* Saber cómo `trabajarUnMinuto()`
* Saber cómo `asignarPedido(...)`
* Poder responder si `estaLibre()` 
* Poder informar cuál es su `getPedidoActual()` 
* Poder informar cuánto `getTiempoRestante()` 

### 4. Clase: `Pedido` (El Modelo)

Es un simple contenedor de datos.

**Responsabilidades Públicas:**
* Poder informar cuál es su `getTiempoPreparacion()`.
* Poder informar cuál es su `getTipoPlato()`.
* **Importante:** Saber cómo `compararse` con otro objeto `Pedido` (basándose en su tiempo de preparación). Esto es crucial para que la `ColaDePrioridad` sepa cómo ordenarlos.

### 5. Clase: `ColaDePrioridad` Donde El Modelo elegido es El Heap (Arboles)

Esta es la clase más importante del Modelo. Representa la "sala de espera" de los pedidos.

Su lógica interna (cómo implementa el Árbol Heap) es **totalmente privada y oculta** del resto del programa.

**Responsabilidades Públicas:**
* Saber cómo `agregarPedido(...)`, 
* Saber cómo `sacarPedidoPrioritario()`
* Poder responder si `estaVacia()` 
* Poder informar cuántos `getNumeroPedidos()`
* Poder informar el número total de `getComparacionesTotales()` 

---

## Lógica de Funcionamiento (Controlador)

La clase `SimulacionCocina` usará las Vistas Públicas de las otras clases para orquestar la simulación de la siguiente manera:

1.  La `SimulacionCocina` crea una instancia del `Cocinero`, una de la `ColaDePrioridad` y una de la `Vista`.
2.  Inicia su bucle principal de 1 a 120 (minutos).
3.  **En cada minuto del bucle:**
    1.  Le dice al `Cocinero` que `trabajarUnMinuto()`.
    2.  Decide (con 40% de probabilidad) si llega un nuevo pedido.
    3.  Si llega:
        * Crea un nuevo objeto `Pedido` (con sus datos aleatorios).
        * Le pasa el pedido a la `ColaDePrioridad` usando `agregarPedido()`.
        * Le dice a la `Vista` que `mostrarLlegadaPedido()`.
    4.  Pregunta al `Cocinero` si `estaLibre()` Y a la `ColaDePrioridad` si `!estaVacia()`.
    5.  Si ambas cosas son ciertas:
        * Le pide a la `ColaDePrioridad` el `sacarPedidoPrioritario()`.
        * Le da ese pedido al `Cocinero` usando `asignarPedido()`.
    6.  Al final del minuto, le pide los datos al `Cocinero` y a la `ColaDePrioridad` y se los pasa a la `Vista` para `mostrarEstado()`.
4.  **Al terminar el bucle:**
    1.  La `SimulacionCocina` calcula las estadísticas finales.
    2.  Le pide a la `ColaDePrioridad` el `getComparacionesTotales()`.
    3.  Le pasa todos estos datos a la `Vista` para `mostrarResumenFinal()`.