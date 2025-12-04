public class ArbolPedidos {
    private Nodo raiz;
    public int comparaciones = 0;
    public int cantidadPedidos = 0;

    public ArbolPedidos() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(Pedido nuevoPedido) {
        
        assert nuevoPedido != null : "Error: Intento de insertar pedido nulo en el árbol";

        Nodo nuevoNodo = new Nodo(nuevoPedido);
        cantidadPedidos++;

        if (raiz == null) {
            raiz = nuevoNodo;
            return;
        }

        Nodo actual = raiz;
        while (true) {
            comparaciones++; 
            
            assert actual != null : "Error: nodo actual es nulo en recorrido";

            if (nuevoPedido.tiempoTotal < actual.pedido.tiempoTotal) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = nuevoNodo;
                    return;
                }
                actual = actual.izquierdo;
            } else {
                if (actual.derecho == null) {
                    actual.derecho = nuevoNodo;
                    return;
                }
                actual = actual.derecho;
            }
        }
    }

    public Pedido extraerPrioritario() {
        if (raiz == null) return null; 

        cantidadPedidos--;
        Nodo padre = null;
        Nodo actual = raiz;

        while (actual.izquierdo != null) {
            comparaciones++;
            padre = actual;
            actual = actual.izquierdo;
        }
        
        
        assert actual != null && actual.pedido != null : "Error: Nodo extraído inválido";

        Pedido pedidoPrioritario = actual.pedido;

        if (padre == null) {
            raiz = actual.derecho; 
        } else {
            padre.izquierdo = actual.derecho; 
        }

        return pedidoPrioritario;
    }
}