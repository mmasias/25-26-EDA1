public class ListaDeFilas {

    private NodoFila cabezaFila;
    private int totalFilas;
    private int totalColumnas;
    private int contadorCeldasUsadas;
    private NodoCeldaUsada cabezaCeldasUsadas;

    private static class NodoFila {
        Fila fila;
        NodoFila siguiente;

        NodoFila(Fila fila) {
            this.fila = fila;
            this.siguiente = null;
        }
    }

    private static class NodoCeldaUsada {
        int filaGuardada;
        int columnaGuardada;
        NodoCeldaUsada siguiente;

        NodoCeldaUsada(int fila, int columna) {
            filaGuardada = fila;
            columnaGuardada = columna;
            siguiente = null;
        }
    }

    public ListaDeFilas(int cantidadFilas, int cantidadColumnas) {
        totalFilas = cantidadFilas;
        totalColumnas = cantidadColumnas;
        cabezaFila = construirListaDeFilas(totalFilas, totalColumnas);
        contadorCeldasUsadas = 0;
        cabezaCeldasUsadas = null;
    }

    public void set(int fila, int columna, int valor) {
        if (fila >= 0 && fila < totalFilas) {
            Fila filaActual = buscarFila(fila);
            if (!existeCeldaUsada(fila, columna)) {
                registrarCeldaUsada(fila, columna);
            }
            filaActual.set(columna, valor);
        } else {
            System.out.println("Error: fila fuera de rango.");
        }
    }

    public int get(int fila, int columna) {
        if (fila >= 0 && fila < totalFilas) {
            Fila filaActual = buscarFila(fila);
            return filaActual.get(columna);
        } else {
            System.out.println("Error: fila fuera de rango.");
            return -1;
        }
    }

    public void imprimirTodo() {
        NodoFila nodoFilaActual = cabezaFila;
        System.out.println("[");
        for (int filaActual = 0; filaActual < totalFilas; filaActual++) {
            System.out.print("  ");
            nodoFilaActual.fila.imprimirFila();
            if (filaActual < totalFilas - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
            nodoFilaActual = nodoFilaActual.siguiente;
        }
        System.out.println("]");
    }

    private NodoFila construirListaDeFilas(int cantidadFilas, int cantidadColumnas) {
        if (cantidadFilas == 0) {
            return null;
        }
        NodoFila primerNodoFila = new NodoFila(new Fila(cantidadColumnas));
        NodoFila nodoFilaActual = primerNodoFila;
        for (int filaActual = 1; filaActual < cantidadFilas; filaActual++) {
            nodoFilaActual.siguiente = new NodoFila(new Fila(cantidadColumnas));
            nodoFilaActual = nodoFilaActual.siguiente;
        }
        return primerNodoFila;
    }

    private Fila buscarFila(int numeroFila) {
        NodoFila nodoFilaActual = cabezaFila;
        for (int filaActual = 0; filaActual < numeroFila; filaActual++) {
            nodoFilaActual = nodoFilaActual.siguiente;
        }
        return nodoFilaActual.fila;
    }

    private boolean existeCeldaUsada(int fila, int columna) {
        NodoCeldaUsada actual = cabezaCeldasUsadas;
        while (actual != null) {
            if (actual.filaGuardada == fila && actual.columnaGuardada == columna) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    private void registrarCeldaUsada(int fila, int columna) {
        NodoCeldaUsada nuevo = new NodoCeldaUsada(fila, columna);
        nuevo.siguiente = cabezaCeldasUsadas;
        cabezaCeldasUsadas = nuevo;
        contadorCeldasUsadas = contadorCeldasUsadas + 1;
    }
}