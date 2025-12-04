public class ArbolBinario {
    private Nodo raiz;
    private int comparaciones = 0;

    public boolean esVacio() { 
        return raiz == null; 
    }

    public void insertar(Pedido pedido) {
        Nodo nuevo = new Nodo(pedido);

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo actual = raiz;
            boolean insertado = false;

            while (!insertado) {
                comparaciones++;
                if (pedido.esMasRapidoQue(actual.pedido())) {
                    if (actual.izquierda() == null) {
                        actual.crearNodoIzquierda(nuevo);
                        insertado = true;
                    } else {
                        actual = actual.izquierda();
                    }
                } else {
                    if (actual.derecha() == null) {
                        actual.crearNodoDerecha(nuevo);
                        insertado = true;
                    } else {
                        actual = actual.derecha();
                    }
                }
            }
        }
    }

    public Pedido extraerMasRapido() {

        if (raiz.izquierda() == null) {
            Pedido pedido = raiz.pedido();
            raiz = raiz.derecha();
            return pedido;
        }

        Nodo padre = raiz;
        Nodo actual = raiz.izquierda();

        while (actual.izquierda() != null) {
            padre = actual;
            actual = actual.izquierda();
        }

        Pedido pedido = actual.pedido();
        padre.crearNodoIzquierda(actual.derecha());
        
        return pedido;
    }

    public int comparaciones(){
        return comparaciones;
    }
}