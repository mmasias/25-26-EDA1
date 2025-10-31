public class ListaInt extends ListaAbstracta {
        public ListaInt(int numeroMaximo) {
        this.NUMERO_MAXIMO = numeroMaximo;
        this.primerNodo = null;
    }

   
    public void add(int valor) {
        NodoInt nuevo = new NodoInt(valor, size());
        if (primerNodo == null) {
            primerNodo = nuevo;
        } else {
            NodoInt actual = (NodoInt) primerNodo;
            while (actual.getSiguiente() != null) {
                actual = (NodoInt) actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public NodoInt get(int indice) {
        NodoInt actual = (NodoInt) primerNodo;
        while (actual != null) {
            if (actual.getPosicion() == indice) {
                return actual;
            }
            actual = (NodoInt) actual.getSiguiente();
        }
        return null;
    }

   
    public int size() {
        int contador = 0;
        NodoInt actual = (NodoInt) primerNodo;
        while (actual != null) {
            contador++;
            actual = (NodoInt) actual.getSiguiente();
        }
        return contador;
    }
}
