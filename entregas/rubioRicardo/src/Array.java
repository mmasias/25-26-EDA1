public class Array {
    private ListaPorBloquesEnteros lista;
    private int tamañoFijo;

    public Array() {
        lista = null;
        tamañoFijo = 0;
    }

    public void fijarTamaño(int tamaño) {
        if (tamañoFijo > 0) {
            return;
        }
        tamañoFijo = tamaño;
        lista = new ListaPorBloquesEnteros(tamaño); 
        for (int i = 0; i < tamañoFijo; i++) {
            lista.agregarAlFinal(0);
        }
    }

    public boolean tipoDato() {
        return true; 
    }

    public void Acceso(int indice) {
        if (indice < 0 || indice >= tamañoFijo) {
            System.out.println("Índice fuera de rango");
            return;
        }
        System.out.println("Valor en posición " + indice + ": " + lista.obtener(indice));
    }

    public void modificar(int indice, int nuevoValor) {
        if (indice < 0 || indice >= tamañoFijo) {
            System.out.println("Índice fuera de rango");
            return;
        }
        lista.reemplazar(indice, nuevoValor);
    }

    public void mostrar() {
        System.out.print("Contenido del array con bloques: ");
        int[] datos = lista.aArray();
        System.out.print("[ ");
        for (int valor : datos) {
            System.out.print(valor + " ");
        }
        System.out.println("]");
    }
}
