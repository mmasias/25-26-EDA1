public class ArbolPedidos {

    private static class Nodo {
        Pedido p;
        Nodo izq, der;

        Nodo(Pedido p) {
            this.p = p;
        }
    }

    private Nodo raiz;
    public int comparaciones;   
}
