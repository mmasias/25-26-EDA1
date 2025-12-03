public class ArbolPedidos {

    private class Nodo {
        Pedido dato;
        Nodo izq, der;
        Nodo(Pedido p) { this.dato = p; }
    }

    private Nodo raiz;
    private int comparaciones = 0;
    private int cantidad = 0;

    public void insertar(Pedido p) {
        raiz = insertarRec(raiz, p);
        cantidad++;
    }

    private Nodo insertarRec(Nodo n, Pedido p) {
        if (n == null) return new Nodo(p);
        
        comparaciones++; 
        if (p.getTiempo() < n.dato.getTiempo()) n.izq = insertarRec(n.izq, p);
        else n.der = insertarRec(n.der, p);
        
        return n;
    }

    public Pedido sacarMinimo() {
        if (raiz == null) return null;
        cantidad--;
        
        if (raiz.izq == null) {
            Pedido p = raiz.dato;
            raiz = raiz.der; 
            return p;
        }

        Nodo padre = raiz;
        Nodo actual = raiz.izq;
        while (actual.izq != null) {
            comparaciones++; 
            padre = actual;
            actual = actual.izq;
        }
        
        Pedido p = actual.dato;
        padre.izq = actual.der; 
        return p;
    }

    public boolean vacio() { return raiz == null; }
    public int getPendientes() { return cantidad; }
    public int getComparaciones() { return comparaciones; }
}