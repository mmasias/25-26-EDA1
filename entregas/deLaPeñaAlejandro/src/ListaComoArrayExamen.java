public class ListaComoArrayExamen {

    private ArraySimuladoPorLista datos;
    private int tamanio;

    private static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int valorInicial) {
            int valor;
            valor = valorInicial;
            dato = valor;
            siguiente = null;
        }
    }

    public class ArraySimuladoPorLista {
        private Nodo cabeza;
        private int capacidad;

        public ArraySimuladoPorLista(int capacidadInicial) {
            int capacidadConstruida;
            if (capacidadInicial <= 0) {
                capacidadConstruida = 1;
            } else {
                capacidadConstruida = capacidadInicial;
            }
            capacidad = capacidadConstruida;
            cabeza = construirListaVacia(capacidad);
        }

        private Nodo construirListaVacia(int cantidad) {
            Nodo primerNodo;
            Nodo nodoActual;
            int creados;
            if (cantidad == 0) {
                return null;
            }
            primerNodo = new Nodo(0);
            nodoActual = primerNodo;
            creados = 1;
            while (creados < cantidad) {
                nodoActual.siguiente = new Nodo(0);
                nodoActual = nodoActual.siguiente;
                creados = creados + 1;
            }
            return primerNodo;
        }
    }
    
    public class ArraySimuladoPorLista {
    private Nodo cabeza;
    private int capacidad;

    public ArraySimuladoPorLista(int capacidadInicial) {
        int capacidadConstruida;
        if (capacidadInicial <= 0) {
            capacidadConstruida = 1;
        } else {
            capacidadConstruida = capacidadInicial;
        }
        capacidad = capacidadConstruida;
        cabeza = construirListaVacia(capacidad);
    }

    public int longitud() {
        int resultado;
        resultado = capacidad;
        return resultado;
    }

    public int obtener(int posicionObjetivo) {
        boolean posicionValida;
        Nodo nodoObjetivo;
        int valorLeido;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo < capacidad;
        if (posicionValida) {
            nodoObjetivo = buscarNodo(posicionObjetivo);
            valorLeido = nodoObjetivo.dato;
        } else {
            System.out.println("Posición fuera de rango.");
            valorLeido = -1;
        }
        return valorLeido;
    }

    public void establecer(int posicionObjetivo, int valorNuevo) {
        boolean posicionValida;
        Nodo nodoObjetivo;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo < capacidad;
        if (posicionValida) {
            nodoObjetivo = buscarNodo(posicionObjetivo);
            nodoObjetivo.dato = valorNuevo;
        } else {
            System.out.println("Posición fuera de rango.");
        }
    }

    private Nodo buscarNodo(int posicionObjetivo) {
        Nodo nodoActual;
        int indice;
        nodoActual = cabeza;
        indice = 0;
        while (indice < posicionObjetivo) {
            nodoActual = nodoActual.siguiente;
            indice = indice + 1;
        }
        return nodoActual;
    }

    private Nodo construirListaVacia(int cantidad) {
        Nodo primerNodo;
        Nodo nodoActual;
        int creados;
        if (cantidad == 0) {
            return null;
        }
        primerNodo = new Nodo(0);
        nodoActual = primerNodo;
        creados = 1;
        while (creados < cantidad) {
            nodoActual.siguiente = new Nodo(0);
            nodoActual = nodoActual.siguiente;
            creados = creados + 1;
        }
        return primerNodo;
        }
    }
}
