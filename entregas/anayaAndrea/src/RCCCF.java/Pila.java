public class Pila {
    private Nodo top;

    public void push(Pedido p) {
        Nodo nuevo = new Nodo(p);
        nuevo.sig = top;
        top = nuevo;
    }

    public Pedido pop() {
        if (top == null) return null;
        Pedido p = top.dato;
        top = top.sig;
        return p;
    }

    public boolean vacia() {
        return top == null;
    }
}
