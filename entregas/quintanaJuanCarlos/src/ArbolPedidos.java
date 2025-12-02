
public class ArbolPedidos {

    private NodoPedido nodoRaiz;
    private int numeroPedidos;
    private Pedido pedidoExtraidoEnOperacion;

    public ArbolPedidos() {
        this.nodoRaiz = null;
        this.numeroPedidos = 0;
        this.pedidoExtraidoEnOperacion = null;
    }

    public void insertarPedido(Pedido nuevoPedido) {
        nodoRaiz = insertarRecursivo(nodoRaiz, nuevoPedido);
        numeroPedidos++;
    }

    public Pedido extraerPedidoConMenorDuracionTotal() {
        if (nodoRaiz == null) {
            return null;
        }
        pedidoExtraidoEnOperacion = null;
        nodoRaiz = extraerNodoMinimo(nodoRaiz);
        if (pedidoExtraidoEnOperacion != null) {
            numeroPedidos--;
        }
        return pedidoExtraidoEnOperacion;
    }

    public boolean estaVacio() {
        return numeroPedidos == 0;
    }

    public int cantidadPedidos() {
        return numeroPedidos;
    }

    public void incrementarTiempoEsperaEnPedidos() {
        incrementarTiempoEsperaRecursivo(nodoRaiz);
    }

    private NodoPedido insertarRecursivo(NodoPedido nodoActual, Pedido nuevoPedido) {
        if (nodoActual == null) {
            return new NodoPedido(nuevoPedido);
        }
        int resultadoComparacion = compararPedidos(nuevoPedido, nodoActual.pedido);
        if (resultadoComparacion < 0) {
            nodoActual.nodoIzquierdo = insertarRecursivo(nodoActual.nodoIzquierdo, nuevoPedido);
        } else {
            nodoActual.nodoDerecho = insertarRecursivo(nodoActual.nodoDerecho, nuevoPedido);
        }
        return nodoActual;
    }

    private NodoPedido extraerNodoMinimo(NodoPedido nodoActual) {
        if (nodoActual.nodoIzquierdo == null) {
            pedidoExtraidoEnOperacion = nodoActual.pedido;
            return nodoActual.nodoDerecho;
        }
        nodoActual.nodoIzquierdo = extraerNodoMinimo(nodoActual.nodoIzquierdo);
        return nodoActual;
    }

    private void incrementarTiempoEsperaRecursivo(NodoPedido nodoActual) {
        if (nodoActual == null) {
            return;
        }
        incrementarTiempoEsperaRecursivo(nodoActual.nodoIzquierdo);
        nodoActual.pedido.incrementarUnMinutoDeEspera();
        incrementarTiempoEsperaRecursivo(nodoActual.nodoDerecho);
    }

    private int compararPedidos(Pedido pedidoPrimero, Pedido pedidoSegundo) {
        int diferenciaDuracion = pedidoPrimero.duracionTotal() - pedidoSegundo.duracionTotal();
        if (diferenciaDuracion != 0) {
            return diferenciaDuracion;
        }
        int diferenciaLlegada = pedidoPrimero.instanteLlegada() - pedidoSegundo.instanteLlegada();
        return diferenciaLlegada;
    }
}
