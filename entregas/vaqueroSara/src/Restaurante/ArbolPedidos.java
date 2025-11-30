package Restaurante;

class ArbolPedidos {
    private Pedido raiz;
    public int comparaciones = 0;

    public void insertar(Pedido nuevo) {
        raiz = insertarRec(raiz, nuevo);
    }

    private Pedido insertarRec(Pedido nodo, Pedido nuevo) {
        if (nodo == null) return nuevo;
        comparaciones++;
        if (nuevo.tiempoPreparacion < nodo.tiempoPreparacion) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, nuevo);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, nuevo);
        }
        return nodo;
    }

    public Pedido extraerMin() {
        if (raiz == null) return null;
        Pedido nodo = raiz, padre = null;
        while (nodo.izquierdo != null) {
            comparaciones++;
            padre = nodo;
            nodo = nodo.izquierdo;
        }
        if (padre == null) {
            raiz = nodo.derecho;  
        } else {
            padre.izquierdo = nodo.derecho; 
        }
        nodo.izquierdo = nodo.derecho = null;
        return nodo;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int contarNodos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(Pedido nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodosRec(nodo.izquierdo) + contarNodosRec(nodo.derecho);
    }
}
