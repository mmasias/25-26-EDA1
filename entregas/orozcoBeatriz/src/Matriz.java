public class Matriz {

    private final int numeroFilas;
    private final int numeroColumnas;
    private final ListaDeFilas listaDeFilas;

    public Matriz(int numeroFilas, int numeroColumnas) {
        if (numeroFilas < 0) {
            numeroFilas = 0;
        }
        if (numeroColumnas < 0) {
            numeroColumnas = 0;
        }
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;
        this.listaDeFilas = new ListaDeFilas(numeroFilas, numeroColumnas);
    }

    public boolean posicionValida(int indiceFila, int indiceColumna) {
        return indiceFila >= 0 && indiceFila < numeroFilas
                && indiceColumna >= 0 && indiceColumna < numeroColumnas;
    }

    public boolean filaValida(int indiceFila) {
        return indiceFila >= 0 && indiceFila < numeroFilas;
    }

    public boolean setValor(int indiceFila, int indiceColumna, int valor) {
        if (!posicionValida(indiceFila, indiceColumna)) {
            return false;
        }

        Fila filaActual = listaDeFilas.getFila(indiceFila);
        return filaActual.setValor(indiceColumna, valor);
    }

    public int getValor(int indiceFila, int indiceColumna) {
        Fila filaActual = listaDeFilas.getFila(indiceFila);
        return filaActual.getValor(indiceColumna);
    }

    public void imprimir() {
        for (int indiceFila = 0; indiceFila < numeroFilas; indiceFila++) {
            Fila filaActual = listaDeFilas.getFila(indiceFila);
            filaActual.imprimir();
        }
    }

    public Fila getFila(int indiceFila) {
        if (!filaValida(indiceFila)) {
            return null;
        }

        Fila filaOriginal = listaDeFilas.getFila(indiceFila);
        Fila filaCopia = new Fila(numeroColumnas);

        for (int indiceColumna = 0; indiceColumna < numeroColumnas; indiceColumna++) {
            filaCopia.setValor(indiceColumna, filaOriginal.getValor(indiceColumna));
        }

        return filaCopia;
    }
}
