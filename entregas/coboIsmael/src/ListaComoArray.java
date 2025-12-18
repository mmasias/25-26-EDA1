public class ListaComoArray {

    private Nodo cabeza;
    private int capacidad;

    private static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int valorInicial) {
            dato = valorInicial;
            siguiente = null;
        }
    }

    public ListaComoArray(int capacidadMaxima) {
        int capacidadConstruccion;
        capacidadConstruccion = capacidadMaxima;
        capacidad = capacidadConstruccion;
        cabeza = construirListaVacia(capacidad);
    }

    public void set(int posicionObjetivo, int valor) {
        boolean posicionValida;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo < capacidad;
        if (posicionValida) {
            Nodo nodoObjetivo;
            nodoObjetivo = buscarNodo(posicionObjetivo);
            nodoObjetivo.dato = valor;
        } else {
            System.out.println("Error: posición fuera de rango.");
        }
    }

    public int get(int posicionObjetivo) {
        boolean posicionValida;
        int resultado;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo < capacidad;
        if (posicionValida) {
            Nodo nodoObjetivo;
            nodoObjetivo = buscarNodo(posicionObjetivo);
            resultado = nodoObjetivo.dato;
        } else {
            System.out.println("Error: posición fuera de rango.");
            resultado = -1;
        }
        return resultado;
    }

    public void imprimir() {
        Nodo nodoActual;
        int posicionRecorrida;
        nodoActual = cabeza;
        System.out.print("[");
        for (posicionRecorrida = 0; posicionRecorrida < capacidad; posicionRecorrida = posicionRecorrida + 1) {
            System.out.print(nodoActual.dato);
            if (posicionRecorrida < capacidad - 1) {
                System.out.print(", ");
            }
            nodoActual = nodoActual.siguiente;
        }
        System.out.println("]");
    }

    private Nodo buscarNodo(int posicionObjetivo) {
        Nodo nodoActual;
        int posicionRecorrida;
        nodoActual = cabeza;
        for (posicionRecorrida = 0; posicionRecorrida < posicionObjetivo; posicionRecorrida = posicionRecorrida + 1) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual;
    }

    private Nodo construirListaVacia(int capacidadConstruccion) {
        Nodo primerNodo;
        Nodo nodoActual;
        int posicionRecorrida;
        if (capacidadConstruccion == 0) {
            return null;
        }
        primerNodo = new Nodo(0);
        nodoActual = primerNodo;
        for (posicionRecorrida = 1; posicionRecorrida < capacidadConstruccion; posicionRecorrida = posicionRecorrida + 1) {
            nodoActual.siguiente = new Nodo(0);
            nodoActual = nodoActual.siguiente;
        }
        return primerNodo;
    }

    public static void main(String[] args) {
        ListaComoArray lista;
        int elementoLeido;
        lista = new ListaComoArray(5);
        lista.set(0, 10);
        lista.set(1, 20);
        lista.set(2, 30);
        lista.set(3, 40);
        lista.set(4, 50);
        lista.set(5, 60);
        lista.imprimir();
        elementoLeido = lista.get(2);
        System.out.println("Elemento en posición 2: " + elementoLeido);
    }
}