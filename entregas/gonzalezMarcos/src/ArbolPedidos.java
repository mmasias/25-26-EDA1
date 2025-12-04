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
        if (raiz == null) {
            raiz = new Nodo(pedido);
        } else {
            insertarRecursivo(raiz, pedido);
        }
        tamaño++;
    }

    private void insertarRecursivo(Nodo actual, Pedido pedido) {
        comparaciones++;
        if (pedido.getTiempoPreparacion() < actual.getTiempoPreparacion()) {
            if (actual.getIzquierdo() == null) {
                actual.setIzquierdo(new Nodo(pedido));
            } else {
                insertarRecursivo(actual.getIzquierdo(), pedido);
            }
        } else {
            if (actual.getDerecho() == null) {
                actual.setDerecho(new Nodo(pedido));
            } else {
                insertarRecursivo(actual.getDerecho(), pedido);
            }
        }
    }

    public Pedido extraerMinimo() {
        if (raiz == null)
            return null;

        Pedido minPedido;

        if (raiz.getIzquierdo() == null) {
            minPedido = raiz.getPedido();
            raiz = raiz.getDerecho();
        } else {
            Nodo padre = raiz;
            Nodo actual = raiz.getIzquierdo();

            while (actual.getIzquierdo() != null) {
                comparaciones++;
                padre = actual;
                actual = actual.getIzquierdo();
            }

            minPedido = actual.getPedido();
            padre.setIzquierdo(actual.getDerecho());
        }

        tamaño--;
        return minPedido;
    }

    public void incrementarEspera() {
        incrementarEsperaRecursivo(raiz);
    }

    private void incrementarEsperaRecursivo(Nodo nodo) {
        if (nodo != null) {
            nodo.incrementarEspera();
            incrementarEsperaRecursivo(nodo.getIzquierdo());
            incrementarEsperaRecursivo(nodo.getDerecho());
        }
    }

    public int tiempoTotalEspera() {
        return calcularEsperaTotal(raiz);
    }

    private int calcularEsperaTotal(Nodo nodo) {
        if (nodo == null)
            return 0;
        return nodo.getTiempoEspera() + calcularEsperaTotal(nodo.getIzquierdo())
                + calcularEsperaTotal(nodo.getDerecho());
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public void printTree() {
        printTree(raiz, "", true);
    }

    private void printTree(Nodo nodo, String indent, boolean last) {
        if (nodo != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "│ ";
            }
            System.out.println(nodo.getPedido().getTipo() + " (" + nodo.getTiempoPreparacion() + "m)");

            printTree(nodo.getIzquierdo(), indent, false);
            printTree(nodo.getDerecho(), indent, true);
        }
    }
}