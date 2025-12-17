public class Fila {
    Nodo cabeza;
    int columnas;

    public Fila(int columnasTotales) {
        columnas = columnasTotales;
        cabeza = construirFilaVacia(columnas);
    }

    public void set(int columna, int valor) {
        if (columna >= 0 && columna < columnas) {
            Nodo nodoActual = buscarNodo(columna);
            nodoActual.dato = valor;
        } else {
            System.out.println("Error: columna fuera de rango.");
        }
    }

    public int get(int columna) {
        if (columna >= 0 && columna < columnas) {
            Nodo nodoActual = buscarNodo(columna);
            return nodoActual.dato;
        } else {
            System.out.println("Error: columna fuera de rango.");
            return -1;
        }
    }

    public void imprimirFila() {
        Nodo nodoActual = cabeza;
        System.out.print("[");
        for (int columna = 0; columna < columnas; columna++) {
            System.out.print(nodoActual.dato);
            if (columna < columnas - 1) {
                System.out.print(", ");
            }
            nodoActual = nodoActual.siguiente;
        }
        System.out.print("]");
    }

    private Nodo buscarNodo(int columna) {
        Nodo nodoActual = cabeza;
        for (int columnaActual = 0; columnaActual < columna; columnaActual++) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual;
    }

    private Nodo construirFilaVacia(int columnas) {
        if (columnas == 0) {
            return null;
        }
        Nodo primerNodo = new Nodo(0);
        Nodo nodoActual = primerNodo;
        for (int columna = 1; columna < columnas; columna++) {
            nodoActual.siguiente = new Nodo(0);
            nodoActual = nodoActual.siguiente;
        }
        return primerNodo;
    }
}