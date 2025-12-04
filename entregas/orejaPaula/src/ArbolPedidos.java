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

    public void insertar(Pedido p) {
        raiz = insertarRec(raiz, p);
    }

    private Nodo insertarRec(Nodo n, Pedido p) {
        if (n == null) {
            return new Nodo(p);
        }
        comparaciones++;
        if (p.tiempoPreparacion < n.p.tiempoPreparacion) {
            n.izq = insertarRec(n.izq, p);
        } else {
            n.der = insertarRec(n.der, p);
        }
        return n;
    }
}
