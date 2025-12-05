public class ColaDePrioridad {

    private Nodo raiz;
    private int numElementos;
    private int comparaciones;

    public ColaDePrioridad() {
        this.raiz = null;
        this.numElementos = 0;
        this.comparaciones = 0;
    }

    public void agregarPedido(Pedido nuevoPedido) {
        numElementos++;
        
        if (raiz == null) {
            raiz = new Nodo(nuevoPedido);
            return;
        }

        Nodo actual = raiz;
        boolean insertado = false;

        while (!insertado) {
            comparaciones++;
            
            if (nuevoPedido.getTiempoDePreparacion() < actual.getPedido().getTiempoDePreparacion()) {
                if (actual.getIzquierda() == null) {
                    actual.setIzquierda(new Nodo(nuevoPedido));
                    insertado = true;
                } else {
                    actual = actual.getIzquierda();
                }
            } else {
                if (actual.getDerecha() == null) {
                    actual.setDerecha(new Nodo(nuevoPedido));
                    insertado = true;
                } else {
                    actual = actual.getDerecha();
                }
            }
        }
    }

    public Pedido sacarPedidoPrioritario() {
        if (estaVacia()) {
            return null;
        }
        numElementos--;

        if (raiz.getIzquierda() == null) {
            Pedido pedidoSacar = raiz.getPedido();
            raiz = raiz.getDerecha(); 
            return pedidoSacar;
        }

        Nodo padre = raiz;
        Nodo actual = raiz.getIzquierda();

        while (actual.getIzquierda() != null) {
            padre = actual;
            actual = actual.getIzquierda();
        }

        Pedido pedidoSacar = actual.getPedido();
        padre.setIzquierda(actual.getDerecha());

        return pedidoSacar;
    }

    public boolean estaVacia() {
        return raiz == null;
    }

    public int getNumeroPedidos() {
        return numElementos;
    }

    public int getComparacionesTotales() {
        return comparaciones;
    }
}