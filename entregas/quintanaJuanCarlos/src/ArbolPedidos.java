public class ArbolPedidos {

    private NodoPedido raiz;
    private int cantidad;
    private Pedido pedidoExtraido;

    public ArbolPedidos() {
        this.raiz = null;
        this.cantidad = 0;
        this.pedidoExtraido = null;
    }

    public void insertarPedido(Pedido nuevoPedido) {
        raiz = insertarRecursivo(raiz, nuevoPedido);
        cantidad++;
    }

    public Pedido extraerPedidoConMenorDuracionTotal() {
        if (raiz == null) {
            return null;
        }
        pedidoExtraido = null;
        raiz = extraerNodoMinimo(raiz);
        if (pedidoExtraido != null) {
            cantidad--;
        }
        return pedidoExtraido;
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    public int cantidadPedidos() {
        return cantidad;
    }

    public void incrementarEsperaEnTodosLosPedidos() {
        incrementarEsperaRecursivo(raiz);
    }

    private NodoPedido insertarRecursivo(NodoPedido actual, Pedido nuevoPedido) {
        if (actual == null) {
            return new NodoPedido(nuevoPedido);
        }
        int comparacion = compararPedidos(nuevoPedido, actual.pedido);
        if (comparacion < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, nuevoPedido);
        } else {
            actual.derecho = insertarRecursivo(actual.derecho, nuevoPedido);
        }
        return actual;
    }

    private NodoPedido extraerNodoMinimo(NodoPedido actual) {
        if (actual.izquierdo == null) {
            pedidoExtraido = actual.pedido;
            return actual.derecho;
        }
        actual.izquierdo = extraerNodoMinimo(actual.izquierdo);
        return actual;
    }

    private void incrementarEsperaRecursivo(NodoPedido actual) {
        if (actual == null) {
            return;
        }
        incrementarEsperaRecursivo(actual.izquierdo);
        actual.pedido.aumentarUnMinutoDeEspera();
        incrementarEsperaRecursivo(actual.derecho);
    }

    private int compararPedidos(Pedido primero, Pedido segundo) {
        int diferenciaDuracion = primero.duracionTotal() - segundo.duracionTotal();
        if (diferenciaDuracion != 0) {
            return diferenciaDuracion;
        }
        return primero.instanteLlegada() - segundo.instanteLlegada();
    }
}
