public class Arbol {
    
    private Nodo raiz;
    private int contadorComparaciones;
    
    public Arbol () {
        this.raiz = null;
        this.contadorComparaciones = 0;
    }
    
    public void insertar(Pedido pedido) {
        if (raiz == null) {
            raiz = new Nodo (pedido);
            return;
        }
        
        Nodo actual = raiz;
        
        while (true) {
            contadorComparaciones++;
            
            int comparacion = pedido.compararCon(actual.pedido);
            
            if (comparacion < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(pedido);
                    break;
                } else {
                    actual = actual.izquierdo;
                }
                
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(pedido);
                    break;
                } else {
                    actual = actual.derecho;
                }
            }
        }
    }
    
    public Pedido extraerMinimo() {
        if (raiz == null) {
            return null;
        }
        
        contadorComparaciones++;
        
        if (raiz.izquierdo == null) {
            Pedido pedidoMinimo = raiz.pedido;
            raiz = raiz.derecho;
            return pedidoMinimo;
        }
        
        Nodo padre = raiz;
        Nodo actual = raiz.izquierdo;
        
        while (actual.izquierdo != null) {
            contadorComparaciones++;
            padre = actual;
            actual = actual.izquierdo;
        }
        
        contadorComparaciones++;
        
        Pedido pedidoMinimo = actual.pedido;
        padre.izquierdo = actual.derecho;
        
        return pedidoMinimo;
    }
    
    public boolean estaVacio() {
        return raiz == null;
    }
    
    public int contarPendientes() {
        return contarNodos(raiz);
    }
    
    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho);
    }
    
    public int getContadorComparaciones() {
        return contadorComparaciones;
    }
}