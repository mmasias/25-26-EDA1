public EstructuraPedidosPendientes(int capacidadMaxima) {
    pedidos = new Pedido[capacidadMaxima];
    usados = 0;
    comparaciones = 0;
    }
    
    public boolean estaLlena() {
    return usados == pedidos.length;
    }
    
    public boolean estaVacia() {
    return usados == 0;
    }
    
    public int tamaño() {
    return usados;
    }
    
    public long getComparaciones() {
    return comparaciones;
    }
    
    public void agregar(Pedido p) {
    if (estaLlena()) {
    System.out.println("AVISO: estructura de pedidos llena, se descarta el pedido " + p.getId());
    return;
    }
    pedidos[usados] = p;
    usados++;
    }
    
    /**
    * Busca y extrae el pedido con menor tiempo restante.
    * Si está vacía, devuelve null.
    */
    public Pedido extraerMenorTiempo() {
    if (estaVacia()) {
    return null;
    }
    
    int indiceMin = 0;
    for (int i = 1; i < usados; i++) {
    comparaciones++;
    if (pedidos[i].getTiempoRestante() < pedidos[indiceMin].getTiempoRestante()) {
    indiceMin = i;
    }
    }
    
    Pedido min = pedidos[indiceMin];
    
    // Compactamos el array desplazando a la izquierda
    for (int i = indiceMin; i < usados - 1; i++) {
    pedidos[i] = pedidos[i + 1];
    }
    pedidos[usados - 1] = null;
    usados--;
    
    return min;
    }
    
    
    public class Cocinero<pedidoActual> {
    
    private Pedido pedidoActual;
    
    public boolean estaLibre() {
    return pedidoActual == null;
    }
    
    public void asignarPedido(Pedido pedido, int minutoActual) {
    pedidoActual = pedido;
    pedidoActual.setMinutoInicio(minutoActual);
    }
    
    /**
    * Procesa un minuto del pedido actual.
    * Devuelve el pedido terminado si acaba en este minuto, o null en caso contrario.
    */
    public Pedido procesarMinuto() {
    if (pedidoActual == null) {
    return null;
    }
    pedidoActual.reducirUnMinuto();
    if (pedidoActual.getTiempoRestante() == 0) {
    Pedido terminado = pedidoActual;
    pedidoActual = null;
    return terminado;
    }
    return null;
    }
    
    public Pedido getPedidoActual() {
    return pedidoActual;
    }
    }