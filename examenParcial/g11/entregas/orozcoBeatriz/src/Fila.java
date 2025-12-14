public class Fila {
    Nodo cabeza;
    int numeroColumnas;

    public Fila(int columnasTotales) {
        numeroColumnas = Math.max(0, columnasTotales);
        cabeza = construirFilaVacia(numeroColumnas);
    }

    public void setValor(int indiceColumna, int valor) {
        if (indiceColumna < 0 || indiceColumna >= numeroColumnas) {
            System.out.println("Error: columna fuera de rango.");
            return;
        }
        Nodo nodoBuscado = buscarNodo(indiceColumna);
        if (nodoBuscado != null) nodoBuscado.dato = valor;
    }

    public int getValor(int indiceColumna) {
        if (indiceColumna < 0 || indiceColumna >= numeroColumnas) {
            System.out.println("Error: columna fuera de rango.");
            return 0;
        }
        Nodo nodoBuscado = buscarNodo(indiceColumna);
        return (nodoBuscado == null) ? 0 : nodoBuscado.dato;
    }

    public void imprimir() {
        System.out.print("[");
        Nodo nodoActual = cabeza;
        for (int indiceColumna = 0; indiceColumna < numeroColumnas; indiceColumna++) {
            System.out.print(nodoActual != null ? nodoActual.dato : 0);
            if (indiceColumna < numeroColumnas - 1) System.out.print(", ");
            if (nodoActual != null) nodoActual = nodoActual.siguiente;
        }
        System.out.println("]");
    }

    private Nodo buscarNodo(int indiceColumnaObjetivo) {
        Nodo nodoActual = cabeza;
        int posicionActual = 0;
        while (nodoActual != null && posicionActual < indiceColumnaObjetivo) {
            nodoActual = nodoActual.siguiente;
            posicionActual++;
        }
        return nodoActual;
    }

    private Nodo construirFilaVacia(int totalColumnas) {
        if (totalColumnas == 0) return null;
        Nodo primerNodo = new Nodo(0);
        Nodo nodoActual = primerNodo;
        for (int indice = 1; indice < totalColumnas; indice++) {
            nodoActual.siguiente = new Nodo(0);
            nodoActual = nodoActual.siguiente;
        }
        return primerNodo;
    }
}
