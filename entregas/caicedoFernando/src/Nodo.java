public class Nodo extends NodoAbstracto {
    private Object dato;

    public Nodo(Object dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }
}