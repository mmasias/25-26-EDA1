import java.io.Console;

public class CustomArray {
    private static final Console console = System.console();

    private int tamaño;
    private ListaEnteros elementos;

    public CustomArray(int tamaño) {
        this.tamaño = tamaño;
        this.elementos = new ListaEnteros();

        console.printf("El tipo de dato será 'entero' (ajustado por la implementación de ListaEnteros).\n");

        for (int i = 0; i < tamaño; i++) {
            elementos.insertar(0, i);
        }
    }

    public void mostrarArray() {
        console.printf("Contenido del array:\n");
        elementos.mostrar();
    }

    public void mostrarElemento(int posicion) {
        if (posicion < 0 || posicion >= elementos.getCantidadElementos()) {
            console.printf("Error: la posición %d está fuera de rango.\n", posicion);
            return;
        }
        int valor = elementos.obtener(posicion);
        console.printf("Elemento en posición %d: %d\n", posicion, valor);
    }

    public void modificarElemento(int posicion, int nuevoValor) {
        if (posicion < 0 || posicion >= elementos.getCantidadElementos()) {
            console.printf("Error: la posición %d no existe en el array.\n", posicion);
            return;
        }

        elementos.eliminar(posicion);
        elementos.insertar(nuevoValor, posicion);

        console.printf("Elemento modificado en posición %d.\n", posicion);
    }

    public void eliminarElemento(int posicion) {
        if (posicion < 0 || posicion >= elementos.getCantidadElementos()) {
            console.printf("Error: la posición %d no existe en el array.\n", posicion);
            return;
        }
        elementos.eliminar(posicion);
        console.printf("Elemento en posición %d eliminado.\n", posicion);
    }

    public int getTamaño() {
        return elementos.getCantidadElementos();
    }
}