public class ListaDeFilas {

    private ListaComoArray[] filas;
    private int totalFilas;
    private int totalColumnas;

    public ListaDeFilas(int cantidadFilas, int cantidadColumnas) {
        int filaActual;

        this.totalFilas = cantidadFilas;
        this.totalColumnas = cantidadColumnas;

        this.filas = new ListaComoArray[this.totalFilas];

        filaActual = 0;
        while (filaActual < this.totalFilas) {
            this.filas[filaActual] = new ListaComoArray(this.totalColumnas);
            filaActual = filaActual + 1;
        }
    }

    public void set(int fila, int columna, int valor) {
        this.validarFila(fila);
        this.filas[fila].set(columna, valor);
    }

    public int get(int fila, int columna) {
        int resultado;

        this.validarFila(fila);
        resultado = this.filas[fila].get(columna);

        return resultado;
    }

    public void imprimirTodo() {
        int filaActual;

        filaActual = 0;
        System.out.println("[");

        while (filaActual < this.totalFilas) {
            System.out.print("  ");
            this.filas[filaActual].imprimir();

            if (filaActual < this.totalFilas - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }

            filaActual = filaActual + 1;
        }

        System.out.println("]");
    }

    private void validarFila(int fila) {
        boolean fueraDeRango;

        fueraDeRango = fila < 0 || fila >= this.totalFilas;

        if (fueraDeRango) {
            throw new IndexOutOfBoundsException();
        }
    }
}
