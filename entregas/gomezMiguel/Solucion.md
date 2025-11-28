## Mi Propuesta de Diseño para la Simulación de la Cocina

#Mi idea central es simular una cocina donde el cocinero es súper eficiente: siempre atiende el pedido con el menor tiempo restante de preparación (política SRPT).
El verdadero reto para mí es medir con precisión el coste de esa eficiencia. Necesito saber cuántas comparaciones hace el algoritmo en total para encontrar ese pedido "mínimo". Para que este conteo sea explícito y transparente, he decidido implementar la cola de pedidos pendientes usando un Árbol Binario de Búsqueda orientado a mínimos (BST-min).
Esto me permite hacer dos cosas esenciales:
Contar Comparaciones: Poner contadores directos dentro de los métodos insertar y extraerMin.
Garantizar Prioridad: Asegurar que el pedido más fácil de terminar siempre esté en la raíz izquierda del árbol.
ESQUEMA – Mis Componentes Principales
Aquí defino las clases y sus funciones clave en la simulación:
Clase Pedido
Este es el dato que se mueve, el corazón de la simulación. Tiene toda la información de estado.
Atributos privados
id : int — Identificador único del pedido.
tipo : String — Tipo de plato (Café, Ensalada, etc.).
tiempoPreparacion : int — El tiempo total que se le asignó.
tiempoRestante : int — El tiempo que queda por preparar (mi prioridad).
instanteLlegada : int — El minuto exacto en que entró.
instanteInicio : int — El minuto en que el cocinero lo tomó por primera vez.
Métodos públicos
Pedido(int id, String tipo, int tiempoPreparacion, int instanteLlegada) — Constructor inicial.
int getId(), String getTipo(), etc. — Los getters de rigor.
void decrementarTiempoRestante() — Resta 1 (pasa un minuto).
void marcarComoIniciado(int instante)
boolean estaCompleto() — ¡Para saber si ya podemos entregarlo!
int compareTo(Pedido otro) — ¡Crucial! Compara por tiempoRestante y, si empatan, desempata por instanteLlegada (FIFO). Esta lógica define el orden en el BST-min.
String toString()
Clase NodoPedido
Es la estructura auxiliar para construir mi BST-min. Simple y al grano.
Atributos privados
pedido : Pedido — El contenido del nodo.
izquierdo : NodoPedido — Hijo izquierdo.
derecho : NodoPedido — Hijo derecho.
padre : NodoPedido — Lo incluyo para simplificar la reestructuración cuando borre un nodo.
Métodos públicos
NodoPedido(Pedido pedido) — Constructor.
Clase ColaPedidos
Este es el cerebro del conteo y la gestión de prioridad. Es mi BST-min instrumentado.
Atributos privados
root : NodoPedido — La raíz de mi árbol.
cantidadPedidos : int — El tamaño de la cola en tiempo real.
comparacionesInsercion : long — Contador A: Aquí sumo cada comparación al añadir un pedido.
comparacionesExtraccion : long — Contador B: Aquí sumo cada comparación al sacar el mínimo y reestructurar el árbol.
Métodos públicos
ColaPedidos() — Inicializa todo, incluyendo los contadores a cero.
void insertar(Pedido pedido) — Inserta el pedido, sumando al comparacionesInsercion en cada paso del árbol.
Pedido extraerMin() — Devuelve y elimina el nodo más a la izquierda (el mínimo). Suma al comparacionesExtraccion durante la búsqueda y la reestructura.
Pedido peekMin() — Mira el mínimo sin sacarlo.
int tamaño(), boolean estaVacia().
long getComparacionesTotales() — La métrica final que necesito: A + B.
Clase Simulacion
Es el orquestador de la jornada de 120 minutos.
Atributos privados
colaPedidos : ColaPedidos — La cola que estoy midiendo.
estadisticas : Estadisticas — Donde guardo los resultados.
cocineroOcupado : boolean — Para saber si está disponible o no.
pedidoActual : Pedido — El que está sobre la tabla de trabajo.
randomSeed : double — Por si necesito replicar el mismo experimento.
Métodos públicos
Simulacion(), Simulacion(double semilla).
void ejecutar() / void ejecutar(int minutos) — El ciclo for principal.
void procesarMinuto(int minuto) — La lógica de chequeo de llegadas y cambio de pedidos.
Getters para todas las métricas finales (getPedidosAtendidos(), getTiempoEsperaMedio(), etc.).
Clase TipoPlato
Es una clase auxiliar estática para manejar la aleatoriedad de los pedidos.
Atributos privados estáticos
BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA — Las constantes de los tipos de plato.
RANGOS_TIEMPO : Map<String, Range> — Un mapa con los rangos [Min, Max] de tiempo para cada plato.
Métodos públicos estáticos
int getMin(String tipo), int getMax(String tipo).
String muestrearTipo() / String muestrearTipo(double semilla) — Para elegir un tipo al azar (equiprobable).
int generarTiempoParaTipo(String tipo) / int generarTiempoParaTipo(String tipo, double semilla) — Para asignar el tiempo aleatorio dentro de su rango.
Clase Estadisticas
Mi informe final. Aquí recojo todos los datos relevantes.
Atributos privados
totalPedidosAtendidos, totalPedidosPendientes : int.
tiempoEsperaAcumulado : long — La suma de (instanteInicio - instanteLlegada) para todos los atendidos.
comparacionesRealizadas : long — El resultado que obtengo de ColaPedidos.getComparacionesTotales().
pedidosCompletados : List<Pedido> — (Opcional) Un registro de todos los pedidos terminados para cálculos detallados.
Métodos públicos
Estadisticas() — Constructor.
void registrarInicioServicio(Pedido pedido, int instanteInicio).
void registrarPedidoAtendido(Pedido pedido).
void registrarComparaciones(long cantidadComparaciones) — Recibe el total del BST-min.
Getters para todas las métricas.
double getTiempoEsperaMedio() — Simplemente el acumulado dividido por los atendidos.
String generarResumen() — El reporte final de la simulación.

