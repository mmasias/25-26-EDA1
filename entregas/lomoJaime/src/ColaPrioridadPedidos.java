public class ColaPrioridadPedidos {
    private Pedido[] pedidos;
    private int numeroDePedidos;

    public ColaPrioridadPedidos(int capacidadMaxima) {
        this.pedidos = new Pedido[capacidadMaxima];
        this.numeroDePedidos = 0;
    }

    public void agregar(Pedido pedido) {
        if (numeroDePedidos < pedidos.length) {
            pedidos[numeroDePedidos] = pedido;
            numeroDePedidos++;
        }
    }

    public Pedido extraerMinimo(ContextoSimulacion contexto, int minutoActual) {
        if (numeroDePedidos == 0) {
            return null;
        }

        contexto.sumarComparaciones(numeroDePedidos - 1);

        int indiceDelMinimo = 0;
        for (int i = 1; i < numeroDePedidos; i++) {
            if (pedidos[i].getTiempoPreparacion() < pedidos[indiceDelMinimo].getTiempoPreparacion()) {
                indiceDelMinimo = i;
            }
        }

        Pedido pedidoSeleccionado = pedidos[indiceDelMinimo];
        int tiempoDeEspera = minutoActual - pedidoSeleccionado.getTiempoLlegada();
        contexto.sumarTiempoEspera(tiempoDeEspera);

        pedidos[indiceDelMinimo] = pedidos[numeroDePedidos - 1];
        numeroDePedidos--;

        return pedidoSeleccionado;
    }

    public boolean estaVacia() {
        return numeroDePedidos == 0;
    }

    public int size() {
        return numeroDePedidos;
    }
}