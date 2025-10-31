public class NodoInt extends NodoAbstracto  {
    private int dato;

    public NodoInt(int dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
}
