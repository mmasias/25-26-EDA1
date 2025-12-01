
public class ListaPedidos {

    private Nodo cabeza;
    private int talla;
    private long comparaciones;

    public ListaPedidos() {
        this.cabeza = null;
        this.talla = 0;
        this.comparaciones = 0;
    }

    public void insert(Pedido p) {
        Nodo nuevo = new Nodo(p);
        nuevo.siguiente = this.cabeza;
        this.cabeza = nuevo;
        this.talla++;
    }

    public Pedido extraerMasRapido() {
        if (estaVacia()) {
            return null;
        }

        Nodo actual = cabeza;
        Nodo anterior = null;
        Nodo ganador = cabeza;
        Nodo anteriorGanador = null;

        while (actual != null) {
            this.comparaciones++;

            if (actual.dato.getTiempoTotal() < ganador.dato.getTiempoTotal()) {
                ganador = actual;
                anteriorGanador = anterior;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        if (anteriorGanador == null) {
            this.cabeza = this.cabeza.siguiente;
        } else {
            anteriorGanador.siguiente = ganador.siguiente;
        }

        this.talla--;
        return ganador.dato;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int size() {
        return talla;
    }

    public long getComparaciones() {
        return comparaciones;
    }
}
