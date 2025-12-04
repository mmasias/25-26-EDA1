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

    public Pedido extraerMinimo() {
        if (raiz == null) return null;

        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.izq != null) {
            comparaciones++;
            padre = actual;
            actual = actual.izq;
        }

        if (padre == null) {
            raiz = actual.der;
        } else {
            padre.izq = actual.der;
        }

        return actual.p;
    }

    public boolean vacio() {
        return raiz == null;
    }
}

