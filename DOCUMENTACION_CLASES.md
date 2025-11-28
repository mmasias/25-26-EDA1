# 📚 Documentación de Clases (Vista Pública)

Este documento describe la interfaz pública de las clases implementadas en el sistema de simulación RCCCF.

## 1. Clase `Main`
Punto de entrada de la aplicación. Controla el flujo de la simulación.

### Métodos Públicos
*   `public static void main(String[] args)`
    *   Inicia la simulación, ejecuta el bucle temporal de 480 minutos y genera el reporte final.

---

## 2. Clase `Cocina`
Gestiona la lógica de negocio, la cola de prioridad y el procesamiento de pedidos.

### Constructor
*   `public Cocina()`
    *   Inicializa la cola de prioridad con un comparador personalizado (para métricas) y reinicia los contadores.

### Métodos Públicos
*   `public void anadirPedido(Pedido pedido)`
    *   Inserta un nuevo pedido en la cola de espera.
*   `public void tic(double tiempoActual)`
    *   Avanza un minuto en la simulación. Gestiona la asignación de pedidos al cocinero y reduce el tiempo restante del pedido en curso.
*   `public Pedido getPedidoActual()`
    *   Devuelve el pedido que se está procesando actualmente (o `null` si no hay ninguno).
*   `public int getTamanoCola()`
    *   Devuelve el número de pedidos esperando en la cola.
*   `public long getContadorComparaciones()`
    *   Devuelve el número total de comparaciones realizadas por la estructura de datos (métrica de eficiencia).
*   `public int getPedidosCompletados()`
    *   Devuelve la cantidad de pedidos finalizados.
*   `public int getTiempoTotalEspera()`
    *   Devuelve la suma acumulada de los tiempos de espera de todos los pedidos completados.
*   `public int getContadorPedidosPendientes()`
    *   Devuelve el total de pedidos no finalizados (en cola + en preparación).

---

## 3. Clase `Pedido`
Representa una orden individual con sus propiedades y lógica de comparación.

### Constructor
*   `public Pedido(String tipo, int tiempoTotalPreparacion, double tiempoLlegada)`
    *   Crea un nuevo pedido con un ID único autogenerado.

### Métodos Públicos
*   `public int getId()`
    *   Obtiene el identificador único.
*   `public String getTipo()`
    *   Obtiene el tipo de plato (ej. "Café", "Ensalada").
*   `public int getTiempoTotalPreparacion()`
    *   Obtiene el tiempo inicial necesario para preparar el plato.
*   `public int getTiempoRestante()`
    *   Obtiene los minutos que faltan para terminar el pedido.
*   `public void disminuirTiempoRestante()`
    *   Resta 1 minuto al tiempo restante (si es mayor a 0).
*   `public boolean estaTerminado()`
    *   Devuelve `true` si el tiempo restante es 0.
*   `public double getTiempoLlegada()`
    *   Obtiene el momento en que el pedido ingresó al sistema.
*   `public int compareTo(Pedido otro)`
    *   Implementación de `Comparable`. Define la prioridad **SJF**:
        1.  Menor tiempo restante.
        2.  Menor ID (orden de llegada) en caso de empate.
*   `public String toString()`
    *   Representación textual simple del pedido.
*   `public String aCadenaEstado()`
    *   Representación textual detallada para el estado del cocinero (incluye tiempo restante).
