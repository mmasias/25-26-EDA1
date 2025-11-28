public class ColaPrioridadPedidos {

    private Pedido[] pedidos;
    private int numeroPedidos;
    private int numeroComparaciones;

    public ColaPrioridadPedidos(int capacidadMaxima) {
        pedidos = new Pedido[capacidadMaxima];
        numeroPedidos = 0;
        numeroComparaciones = 0;
    }

    public void agregarPedido(Pedido pedido) {
        pedidos[numeroPedidos] = pedido;
        numeroPedidos = numeroPedidos + 1;
    }

    public boolean estaVacia() {
        return numeroPedidos == 0;
    }

    public Pedido extraerPedidoMenorTiempo() {
        int indiceMenor;
        int indiceActual;

        indiceMenor = 0;
        indiceActual = 1;

        while (indiceActual < numeroPedidos) {
            numeroComparaciones = numeroComparaciones + 1;

            if (pedidos[indiceActual].obtenerTiempoRestante() < pedidos[indiceMenor].obtenerTiempoRestante()) {
                indiceMenor = indiceActual;
            }

            indiceActual = indiceActual + 1;
        }

        Pedido pedidoMenor;
        pedidoMenor = pedidos[indiceMenor];

        desplazarElementosHaciaIzquierda(indiceMenor);

        numeroPedidos = numeroPedidos - 1;

        return pedidoMenor;
    }

    private void desplazarElementosHaciaIzquierda(int indiceInicio) {
        int i;
        i = indiceInicio;

        while (i < numeroPedidos - 1) {
            pedidos[i] = pedidos[i + 1];
            i = i + 1;
        }
    }

    public int obtenerNumeroPedidosEnCola() {
        return numeroPedidos;
    }

    public int obtenerNumeroComparaciones() {
        return numeroComparaciones;
    }
}