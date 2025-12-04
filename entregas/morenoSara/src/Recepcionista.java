public class Recepcionista {

    public void recibirPedido(Pedido pedido, Cocinero cocinero) {
        cocinero.colocarPedidosEnLista(pedido);
    }

    public Pedido entregarSiguientePedido() {
        return null;
    }
}
