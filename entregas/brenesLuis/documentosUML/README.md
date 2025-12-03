# Propuesta de Diseño: RCCCF (Lista Enlazada)

## Concepto
Se implementa una **Lista Enlazada** para gestionar los pedidos. A diferencia de una cola FIFO, esta estructura permite buscar y extraer el pedido con **menor tiempo de preparación**, ignorando el orden de llegada.

## Componentes del Diagrama

1.  **`ListaPedidos` y `Nodo` (Estructura):**
comparando tiempos para encontrar el mínimo. Aquí se cuenta la métrica de **comparaciones** para demostrar la (in)eficiencia del algoritmo.

2.  **`Cocinero` (Lógica):**
    Encapsula el estado del trabajador (libre/ocupado) y gestiona el proceso de cocción del pedido actual.

3.  **`Pedido` (Datos):**
    Objeto simple que almacena el tipo de plato y tiempos (total y restante).

4.  **`Simulacion` (Control):**
    Ejecuta el bucle temporal (120 min), coordina la llegada aleatoria de pedidos y asigna trabajo al cocinero.