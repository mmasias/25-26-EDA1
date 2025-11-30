package entregas.munozManuel.src;

class Main {
    public static void main(String[] args) {
        Arbol arbol1 = new Arbol();
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        Pedido pedido3 = new Pedido();
        Pedido pedido4 = new Pedido();
        Pedido pedido5 = new Pedido();
        Pedido pedido6 = new Pedido();
        Pedido pedido7 = new Pedido();
        Pedido pedido8 = new Pedido();
        Pedido pedido9 = new Pedido();

        arbol1.insertarPedido(pedido1);
        arbol1.insertarPedido(pedido2);
        arbol1.insertarPedido(pedido3);
        arbol1.insertarPedido(pedido4);
        arbol1.insertarPedido(pedido5);
        arbol1.insertarPedido(pedido6);
        arbol1.insertarPedido(pedido7);
        arbol1.insertarPedido(pedido8);
        arbol1.insertarPedido(pedido9);

        arbol1.recorrerArbol();
    }
}
