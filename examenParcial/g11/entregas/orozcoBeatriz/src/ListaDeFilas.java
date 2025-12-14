public class ListaDeFilas {
    private final int numeroFilas;
    private final int numeroColumnas;
    private final Fila[] arregloDeFilas;

    public ListaDeFilas(int numeroFilas, int numeroColumnas) {
        this.numeroFilas = Math.max(0, numeroFilas);
        this.numeroColumnas = Math.max(0, numeroColumnas);
        this.arregloDeFilas = new Fila[this.numeroFilas];
        for (int indiceFila = 0; indiceFila < this.numeroFilas; indiceFila++) {
            this.arregloDeFilas[indiceFila] = new Fila(this.numeroColumnas);
        }
    }

    public Fila getFila(int indiceFila) {
        if (indiceFila < 0 || indiceFila >= numeroFilas) return null;
        return arregloDeFilas[indiceFila];
    }
}
