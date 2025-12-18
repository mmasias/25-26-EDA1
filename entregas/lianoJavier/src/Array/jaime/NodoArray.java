package Array.jaime;
public class NodoArray extends NodoAbstracto {
    private ArrayInt dato;

    public NodoArray(ArrayInt dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
    }

    public ArrayInt getDato() {
        return dato;
    }
}
