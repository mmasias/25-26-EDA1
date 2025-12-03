public class ArbolPedidos {
    private Nodo raiz;
    private int comparaciones;
    private int tamaño;

    public ArbolPedidos() {
        this.raiz = null;
        this.comparaciones = 0;
        this.tamaño = 0;
    }

    public void insertar(Pedido pedido) {
        assert pedido != null : "No se puede insertar un pedido nulo";
        if (raiz == null) {
            raiz = new Nodo(pedido);
        } else {
            Nodo actual = raiz;
            while (true) {
                comparaciones++;
                if (pedido.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
                    if (actual.getIzquierdo() == null) {
                        actual.setIzquierdo(new Nodo(pedido));
                        break;
                    } else {
                        actual = actual.getIzquierdo();
                    }
                } else {
                    if (actual.getDerecho() == null) {
                        actual.setDerecho(new Nodo(pedido));
                        break;
                    } else {
                        actual = actual.getDerecho();
                    }
                }
            }
        }
        tamaño++;
    }

    public Pedido extraerMinimo() {
        assert tamaño > 0 : "No se puede extraer de un árbol vacío";
        Pedido min;

        if (raiz.getIzquierdo() == null) {
            min = raiz.getPedido();
            raiz = raiz.getDerecho();
        } else {
            Nodo padre = raiz;
            while (padre.getIzquierdo().getIzquierdo() != null) {
                comparaciones++;
                padre = padre.getIzquierdo();
            }
            min = padre.getIzquierdo().getPedido();
            padre.setIzquierdo(padre.getIzquierdo().getDerecho());
        }
        tamaño--;
        return min;
    }

    public boolean estaVacio() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public void incrementarEspera() {
        if (raiz == null) return;
        Nodo[] cola = new Nodo[tamaño];
        int inicio = 0, fin = 0;
        cola[fin++] = raiz;
        while (inicio < fin) {
            Nodo actual = cola[inicio++];
            actual.getPedido().incrementarEspera();
            if (actual.getIzquierdo() != null) cola[fin++] = actual.getIzquierdo();
            if (actual.getDerecho() != null) cola[fin++] = actual.getDerecho();
        }
    }

    public int tiempoTotalEspera() {
        if (raiz == null) return 0;
        int total = 0;
        Nodo[] cola = new Nodo[tamaño];
        int inicio = 0, fin = 0;
        cola[fin++] = raiz;
        while (inicio < fin) {
            Nodo actual = cola[inicio++];
            total += actual.getPedido().getTiempoEspera();
            if (actual.getIzquierdo() != null) cola[fin++] = actual.getIzquierdo();
            if (actual.getDerecho() != null) cola[fin++] = actual.getDerecho();
        }
        return total;
    }
}