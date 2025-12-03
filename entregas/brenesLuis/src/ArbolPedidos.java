
public class ArbolPedidos {

    private Nodo raiz;
    private int tamano;
    private int comparaciones;

    public ArbolPedidos() {
        this.raiz = null;
        this.tamano = 0;
        this.comparaciones = 0;
    }

    public void insertar(Pedido nuevo) {
        this.tamano++;
        if (this.raiz == null) {
            this.raiz = new Nodo(nuevo);
            return;
        }

        Nodo actual = this.raiz;
        while (true) {
            if (nuevo.getTiempoPreparacion() < actual.pedido.getTiempoPreparacion()) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(nuevo);
                    return;
                }
                actual = actual.izquierdo;
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(nuevo);
                    return;
                }
                actual = actual.derecho;
            }
        }
    }

    public Pedido extraerMinimo() {
        if (this.raiz == null) {
            return null;
        }
        this.tamano--;

        Nodo padre = null;
        Nodo actual = this.raiz;

        while (actual.izquierdo != null) {
            this.comparaciones++;
            padre = actual;
            actual = actual.izquierdo;
        }

        if (padre == null) {
            this.raiz = actual.derecho;
        } else {
            padre.izquierdo = actual.derecho;
        }

        return actual.pedido;
    }

    public boolean estaVacio() {
        return this.raiz == null;
    }

    public int getTamano() {
        return tamano;
    }

    public int getComparaciones() {
        return comparaciones;
    }
}
