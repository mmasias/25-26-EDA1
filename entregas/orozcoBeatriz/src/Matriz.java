public class Matriz {
    private final int numeroFilas;
    private final int numeroColumnas;
    private final ListaDeFilas listaDeFilas;

    public Matriz(int numeroFilas, int numeroColumnas) {
        if (numeroFilas < 0) numeroFilas = 0;
        if (numeroColumnas < 0) numeroColumnas = 0;
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;
        this.listaDeFilas = new ListaDeFilas(numeroFilas, numeroColumnas);
    }

    public void setValor(int indiceFila, int indiceColumna, int valor) {
        if (indiceFila < 0 || indiceFila >= numeroFilas || indiceColumna < 0 || indiceColumna >= numeroColumnas) {
            System.out.println("Error: posición fuera de rango.");
            return;
        }
        Fila filaActual = listaDeFilas.getFila(indiceFila);
        if (filaActual != null) {
            filaActual.setValor(indiceColumna, valor);
        }
    }

    public int getValor(int indiceFila, int indiceColumna) {
        if (indiceFila < 0 || indiceFila >= numeroFilas || indiceColumna < 0 || indiceColumna >= numeroColumnas) {
            System.out.println("Error: posición fuera de rango.");
            return 0;
        }
        Fila filaActual = listaDeFilas.getFila(indiceFila);
        return (filaActual == null) ? 0 : filaActual.getValor(indiceColumna);
    }

    public void imprimir() {
        for (int indiceFila = 0; indiceFila < numeroFilas; indiceFila++) {
            Fila filaActual = listaDeFilas.getFila(indiceFila);
            if (filaActual == null) {
                System.out.println("[]");
            } else {
                filaActual.imprimir();
            }
        }
    }

    public Fila getFila(int indiceFila) {
        if (indiceFila < 0 || indiceFila >= numeroFilas) return null;

        Fila filaOriginal = listaDeFilas.getFila(indiceFila);
        if (filaOriginal == null) return null;

        Fila filaCopia = new Fila(numeroColumnas);
        for (int indiceColumna = 0; indiceColumna < numeroColumnas; indiceColumna++) {
            filaCopia.setValor(indiceColumna, filaOriginal.getValor(indiceColumna));
        }
        return filaCopia;
    }
}
