public class ArbolPedidos {
    private Nodo raiz;
    private int cantidad;
    private int comparaciones;

    public ArbolPedidos() {
        this.raiz = null;
        this.cantidad = 0;
        this.comparaciones = 0;
    }

    public boolean estaVacio() {
        return this.raiz == null;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public int getComparaciones() {
        return this.comparaciones;
    }

    public void insertar(Pedido p) {
        if (this.raiz == null) {
            this.raiz = new Nodo(p);
        } else {
            Nodo actual = this.raiz;
            boolean insertado = false;

            while (!insertado) {
                this.comparaciones++; 
                if (p.getTiempoTotal() < actual.getPedido().getTiempoTotal()) {
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
        this.cantidad++;
    }

    public Pedido extraerMinimo() {
        if (this.raiz == null) return null;

        Pedido min;

        if (this.raiz.getIzquierdo() == null) {
            min = this.raiz.getPedido();
            this.raiz = this.raiz.getDerecho();
        } else {
            Nodo padre = this.raiz;
            while (padre.getIzquierdo().getIzquierdo() != null) {
                this.comparaciones++;
                padre = padre.getIzquierdo();
            }
            min = padre.getIzquierdo().getPedido();
            padre.setIzquierdo(padre.getIzquierdo().getDerecho());
        }
        
        this.cantidad--;
        return min;
    }
}