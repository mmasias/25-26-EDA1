public abstract class NodoAbstracto {
    protected int posicion;
    protected NodoAbstracto siguiente;

    public int getPosicion() {
        return posicion;
    }

    public NodoAbstracto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoAbstracto siguiente) {
        this.siguiente = siguiente;
    }
    
    public abstract Object getDato(); 
}