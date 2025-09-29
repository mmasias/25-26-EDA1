public class ListaNiños {
    private static class Nodo {
        Niño valor;
        Nodo siguiente;
        Nodo(Niño valor) {
            this.valor = valor;
        }
    }

    private Nodo primero;
    private Nodo ultimo;
    private int tamaño;

    public void añadir(Niño niño) {
        Nodo nuevo = new Nodo(niño);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        tamaño++;
    }

    public Niño sacar() {
        if (tamaño == 0) return null;
        Niño valor = primero.valor;
        primero = primero.siguiente;
        if (primero == null) {
            ultimo = null;
        }
        tamaño--;
        return valor;
    }

    public Niño obtener(int indice) {
        if (indice < 0 || indice >= tamaño) return null;
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean vacia() {
        return tamaño == 0;
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        tamaño = 0;
    }

    public void mostrar() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.print(actual.valor.getNombre() + " / ");
            actual = actual.siguiente;
        }
    }
}
