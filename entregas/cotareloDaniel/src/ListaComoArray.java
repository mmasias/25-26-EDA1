public class ListaComoArray {

    private Nodo cabeza;
    private int capacidad;

    public ListaComoArray(int capacidadMaxima) {
        this.capacidad = capacidadMaxima;
        this.cabeza = this.construirListaVacia(this.capacidad);
    }

    public void set(int posicion, int valor) {
        Nodo nodoActual;

        this.validarIndice(posicion);

        nodoActual = this.buscarNodo(posicion);
        nodoActual.dato = valor;
    }

    public int get(int posicion) {
        Nodo nodoActual;
        int resultado;

        this.validarIndice(posicion);

        nodoActual = this.buscarNodo(posicion);
        resultado = nodoActual.dato;

        return resultado;
    }

    public void imprimir() {
        Nodo nodoActual;
        int posicionActual;

        nodoActual = this.cabeza;
        posicionActual = 0;

        System.out.print("[");

        while (posicionActual < this.capacidad) {
            System.out.print(nodoActual.dato);

            if (posicionActual < this.capacidad - 1) {
                System.out.print(", ");
            }

            nodoActual = nodoActual.siguiente;
            posicionActual = posicionActual + 1;
        }

        System.out.println("]");
    }

    private void validarIndice(int posicion) {
        boolean fueraDeRango;

        fueraDeRango = posicion < 0 || posicion >= this.capacidad;

        if (fueraDeRango) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Nodo buscarNodo(int posicion) {
        Nodo nodoActual;
        int posicionActual;

        nodoActual = this.cabeza;
        posicionActual = 0;

        while (posicionActual < posicion) {
            nodoActual = nodoActual.siguiente;
            posicionActual = posicionActual + 1;
        }

        return nodoActual;
    }

    private Nodo construirListaVacia(int capacidad) {
        Nodo primerNodo;
        Nodo nodoActual;
        int posicionActual;

        if (capacidad == 0) {
            primerNodo = null;
        } else {
            primerNodo = new Nodo(0);
            nodoActual = primerNodo;
            posicionActual = 1;

            while (posicionActual < capacidad) {
                nodoActual.siguiente = new Nodo(0);
                nodoActual = nodoActual.siguiente;
                posicionActual = posicionActual + 1;
            }
        }

        return primerNodo;
    }
}