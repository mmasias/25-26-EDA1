public class ArbolPedidos {

    private Nodo raiz;
    private int cantidad;
    private int comparaciones;

    public ArbolPedidos() {
        this.raiz = null;
        this.cantidad = 0;
        this.comparaciones = 0;
    }

    public void insertar(Pedido pedido) {
        Nodo nuevoNodo = new Nodo(pedido);

        if (raiz == null) {
            raiz = nuevoNodo;
            cantidad++;
        } else {
            Nodo actual = raiz;
            boolean insertado = false;

            while (!insertado) {
                comparaciones++;

                if (pedido.getTiempoPreparacion() < actual.getPedido().getTiempoPreparacion()) {
                    
                    if (actual.getLeft() == null) {
                        actual.setLeft(nuevoNodo);
                        insertado = true;
                    } else {
                        actual = actual.getLeft();
                    }
                
                } 
                else {
                    
                    if (actual.getRight() == null) {
                        actual.setRight(nuevoNodo);
                        insertado = true;
                    } else {
                        actual = actual.getRight();
                    }
                }
            }
            cantidad++;
        }
        
        imprimirArbol();
    }

    public Pedido consultarMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo actual = raiz;
        
        while (actual.getLeft() != null) {
            comparaciones++;
            actual = actual.getLeft();
        }
        
        return actual.getPedido();
    }

    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Nodo actual = raiz;
        Nodo padre = null;

        while (actual.getLeft() != null) {
            comparaciones++;
            padre = actual;
            actual = actual.getLeft();
        }

        Pedido pedidoEliminado = actual.getPedido();

        if (padre == null) {
            raiz = actual.getRight();
        } else {
            padre.setLeft(actual.getRight());
        }

        cantidad--;
        imprimirArbol();
        
        return pedidoEliminado;
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
        if (nodo == null) {
            return;
        }

        imprimirRec(nodo.getRight(), nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("   ");
        }
        System.out.println(nodo.getPedido().getTiempoPreparacion() + " (" + nodo.getPedido().getTipo() + ")");

        imprimirRec(nodo.getLeft(), nivel + 1);
    }
}