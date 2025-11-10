public class Array {
    private Lista lista;
    private int tamañoFijo;

    public Array() {
        lista = new Lista();
        tamañoFijo = 0;
    }

    public void fijarTamaño(int tamaño) {
        if (tamañoFijo > 0) {
            return;
        }
        tamañoFijo = tamaño;
        for (int i = 0; i < tamañoFijo; i++) {
            lista.agregar(0);
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

    public void posicion(Nodo nodo) {
        Nodo actual = lista.inicial;
        int indice = 0;
        while (actual != null) {
            if (actual == nodo) {
                System.out.println("Nodo encontrado en posición " + indice);
                return;
            }
            actual = actual.getSiguienteNodo();
            indice++;
        }
        System.out.println("Nodo no encontrado");
    }

    public void mostrar() {
        System.out.print("Contenido del array simulado: ");
        lista.mostrar();
    }
}
