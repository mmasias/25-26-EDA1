public class Fila {

    private Nodo cabeza;
    private int numeroColumnas;

    public Fila(int columnasTotales) {
        this.numeroColumnas = Math.max(0, columnasTotales);
        this.cabeza = construirFilaVacia(this.numeroColumnas);
    }

    public boolean columnaValida(int indiceColumna) {
        return indiceColumna >= 0 && indiceColumna < numeroColumnas;
    }

    public boolean setValor(int indiceColumna, int valor) {
        if (!columnaValida(indiceColumna)) {
            return false;
        }

        Nodo nodoBuscado = buscarNodo(indiceColumna);
        if (nodoBuscado == null) {
            return false;
        }

        nodoBuscado.setDato(valor);
        return true;
    }

    public int getValor(int indiceColumna) {
        Nodo nodoBuscado = buscarNodo(indiceColumna);
        return nodoBuscado.getDato();
    }

    public void imprimir() {
        System.out.print("[");

        Nodo nodoActual = cabeza;
        for (int indiceColumna = 0; indiceColumna < numeroColumnas; indiceColumna++) {
            System.out.print(nodoActual.getDato());
            if (indiceColumna < numeroColumnas - 1) {
                System.out.print(", ");
            }
            nodoActual = nodoActual.getSiguiente();
        }

        System.out.println("]");
    }

    private Nodo buscarNodo(int indiceColumnaObjetivo) {
        Nodo nodoActual = cabeza;
        int posicionActual = 0;

        while (nodoActual != null && posicionActual < indiceColumnaObjetivo) {
            nodoActual = nodoActual.getSiguiente();
            posicionActual++;
        }

        return nodoActual;
    }

    private Nodo construirFilaVacia(int totalColumnas) {
        if (totalColumnas == 0) {
            return null;
        }

        Nodo primerNodo = new Nodo(0);
        Nodo nodoActual = primerNodo;

        for (int indice = 1; indice < totalColumnas; indice++) {
            Nodo nuevoNodo = new Nodo(0);
            nodoActual.setSiguiente(nuevoNodo);
            nodoActual = nodoActual.getSiguiente();
        }

        return primerNodo;
    }
}