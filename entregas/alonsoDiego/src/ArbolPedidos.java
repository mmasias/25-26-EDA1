public class ArbolPedidos {
    private  Nodo raiz;
    private int tamaño;
    private int comparaciones;

    public ArbolPedidos(){
        this.raiz = null;
        this.tamaño = 0;
        this.comparaciones=0;
    }

    public void insertar(Pedido p) {
        if (raiz == null) {
            raiz = new Nodo(p);
        } else {
            Nodo actual = raiz;
            boolean insertado = false;

            while (!insertado) {
                comparaciones++;

                if (p.getTiempoPreparacion() < actual.getTiempoPreparacion()) {
                    if (actual.getIzquierdo() == null) {
                        actual.setIzquierdo(new Nodo(p));
                        insertado = true;
                    } else {
                        actual = actual.getIzquierdo();
                    }
                } else {
                    if (actual.getDerecho() == null) {
                        actual.setDerecho(new Nodo(p));
                        insertado = true;
                    } else {
                        actual = actual.getDerecho();
                    }
                }
            }
        }
        tamaño++;
    }
    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }

        Pedido min = null;

        if (raiz.getIzquierdo() == null) {
            min = raiz.getPedido();
            raiz = raiz.getDerecho();
        } else {
            Nodo padre = raiz;
            Nodo actual = raiz.getIzquierdo();

            while (actual.getIzquierdo() != null) {
                comparaciones++;
                padre = actual;
                actual = actual.getIzquierdo();
            }

            min = actual.getPedido();
            padre.setIzquierdo(actual.getDerecho());
        }

        tamaño--;
        return min;
    }
    public boolean isEmpty(){
        return tamaño == 0;
    }
    public int getComparaciones(){
        return comparaciones;
    }
}
