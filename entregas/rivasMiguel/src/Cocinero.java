public class Cocinero {

    private ArbolPedidos arbol;
    private Pedido actual;
    private int comparacionesTotales;

    public Cocinero() {
        arbol = new ArbolPedidos();
    }

    public void agregarPedido(Pedido pedido) {
        arbol.insertar(pedido);
    }

    public Pedido getPedidoActual() {
        return actual;
    }

    public int getPedidosPendientes() {
        return arbol.getCantidadPedidos();
    }

    public int getComparacionesTotales() {
        return comparacionesTotales + arbol.getComparaciones();
    }

    public void procesarMinuto() {
        if (actual == null) {
            if (!arbol.estaVacio()) {
                actual = arbol.extraerMinimo();
            }
            return;
        }

        actual.reducirTiempo();
        if (actual.getTiempoRestante() == 0) {
            actual = null;
            if (!arbol.estaVacio()) {
                actual = arbol.extraerMinimo();
            }
        }
    }
}
