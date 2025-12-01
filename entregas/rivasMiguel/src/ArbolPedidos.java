public class ArbolPedidos {

    private Nodo raiz;
    private int cantidad;
    private int comparaciones;

    public ArbolPedidos() {}

    public void insertar(Pedido pedido) {
        raiz = insertarRec(raiz, pedido);
        cantidad++;
        imprimirArbol();
    }

    private Nodo insertarRec(Nodo nodo, Pedido pedido) {
        if (nodo == null) return new Nodo(pedido);

        comparaciones++;
        if (pedido.getTiempoPreparacion() < nodo.pedido.getTiempoPreparacion())
            nodo.left = insertarRec(nodo.left, pedido);
        else
            nodo.right = insertarRec(nodo.right, pedido);

        return nodo;
    }

    public Pedido consultarMinimo() {
        if (raiz == null) return null;
        Nodo n = raiz;
        while (n.left != null) {
            comparaciones++;
            n = n.left;
        }
        return n.pedido;
    }

    public Pedido extraerMinimo() {
        if (raiz == null) return null;

        Nodo[] r = extraerMin(raiz, null);
        raiz = r[0];
        cantidad--;

        imprimirArbol();

        return r[1].pedido;
    }

    private Nodo[] extraerMin(Nodo nodo, Nodo padre) {
        if (nodo.left != null) {
            comparaciones++;
            return extraerMin(nodo.left, nodo);
        }

        if (padre == null)
            return new Nodo[]{ nodo.right, nodo };

        padre.left = nodo.right;
        return new Nodo[]{ raiz, nodo };
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    public int getCantidadPedidos() {
        return cantidad;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public void imprimirArbol() {
        System.out.println("ÁRBOL ACTUAL:");
        imprimirRec(raiz, 0);
        System.out.println("--------------------------");
    }

    private void imprimirRec(Nodo nodo, int nivel) {
        if (nodo == null) return;

        imprimirRec(nodo.right, nivel + 1);

        for (int i = 0; i < nivel; i++) System.out.print("   ");
        System.out.println(nodo.pedido.getTiempoPreparacion() +
                " (" + nodo.pedido.getTipo() + ")");

        imprimirRec(nodo.left, nivel + 1);
    }
}
