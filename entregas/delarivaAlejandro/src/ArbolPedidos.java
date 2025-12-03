public class ArbolPedidos {
    private Pedido pedidoRaiz;
    private ArbolPedidos ramaMenor;
    private ArbolPedidos ramaMayor;

    public ArbolPedidos() {
        this.pedidoRaiz = null;
        this.ramaMenor = null;
        this.ramaMayor = null;
    }

    public boolean hayPedidos() {
        return pedidoRaiz != null;
    }

    public int insertarSegunPrioridad(Pedido nuevoPedido) {
        if (pedidoRaiz == null) {
            pedidoRaiz = nuevoPedido;
            return 0;
        }
        int comparaciones = 1;
        int resultado = compararPedidos(nuevoPedido, pedidoRaiz);
        if (resultado < 0) {
            if (ramaMenor == null) {
                ramaMenor = new ArbolPedidos();
            }
            comparaciones += ramaMenor.insertarSegunPrioridad(nuevoPedido);
        } else {
            if (ramaMayor == null) {
                ramaMayor = new ArbolPedidos();
            }
            comparaciones += ramaMayor.insertarSegunPrioridad(nuevoPedido);
        }
        return comparaciones;
    }

    public Pedido tomarPedidoConMayorPrioridad() {
        assert hayPedidos() : "Árbol de pedidos vacío";
        return tomarMinimoDesdeSubarbol(this);
    }

    private static Pedido tomarMinimoDesdeSubarbol(ArbolPedidos subarbol) {
        if (subarbol.ramaMenor == null) {
            Pedido resultado = subarbol.pedidoRaiz;
            if (subarbol.ramaMayor == null) {
                subarbol.pedidoRaiz = null;
            } else {
                subarbol.pedidoRaiz = subarbol.ramaMayor.pedidoRaiz;
                subarbol.ramaMenor = subarbol.ramaMayor.ramaMenor;
                subarbol.ramaMayor = subarbol.ramaMayor.ramaMayor;
            }
            return resultado;
        } else {
            return tomarMinimoDesdeSubarbol(subarbol.ramaMenor);
        }
    }

    private static int compararPedidos(Pedido primero, Pedido segundo) {
        if (primero.duracionPreparacion() < segundo.duracionPreparacion()) {
            return -1;
        } else if (primero.duracionPreparacion() > segundo.duracionPreparacion()) {
            return 1;
        } else if (primero.instanteLlegada() < segundo.instanteLlegada()) {
            return -1;
        } else if (primero.instanteLlegada() > segundo.instanteLlegada()) {
            return 1;
        } else if (primero.identificador() < segundo.identificador()) {
            return -1;
        } else if (primero.identificador() > segundo.identificador()) {
            return 1;
        } else {
            return 0;
        }
    }
}
