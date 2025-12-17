public class ListaComoArray {

    private Nodo cabeza;
    private int capacidad;
    private int contadorElementosUsados;
    private NodoIndice cabezaIndice;

    private static class NodoIndice {
        int indice;
        Nodo referenciaNodo;
        NodoIndice siguiente;

        NodoIndice(int indiceGuardado, Nodo nodoGuardado) {
            indice = indiceGuardado;
            referenciaNodo = nodoGuardado;
            siguiente = null;
        }
    }

    public ListaComoArray(int capacidadMaxima) {
        capacidad = capacidadMaxima;
        cabeza = construirListaVacia(capacidad);
        contadorElementosUsados = 0;
        cabezaIndice = null;
    }

    public void set(int posicion, int valor) {
        if (posicion >= 0 && posicion < capacidad) {
            if (contadorElementosUsados == capacidad) {
                System.out.println("No se pueden añadir más elementos: capacidad completa.");
                return;
            }
            Nodo nodoActual = buscarNodo(posicion);
            NodoIndice existe = buscarIndice(posicion);
            if (existe == null) {
                registrarIndiceSiNoExiste(posicion, nodoActual);
            }
            nodoActual.dato = valor;
        } else {
            System.out.println("Error: posición fuera de rango.");
        }
    }

    public int get(int posicion) {
        if (posicion >= 0 && posicion < capacidad) {
            Nodo nodoActual = buscarNodo(posicion);
            return nodoActual.dato;
        } else {
            System.out.println("Error: posición fuera de rango.");
            return -1;
        }
    }

    public void imprimir() {
        Nodo nodoActual = cabeza;
        System.out.print("[");
        for (int posicion = 0; posicion < capacidad; posicion++) {
            System.out.print(nodoActual.dato);
            if (posicion < capacidad - 1) {
                System.out.print(", ");
            }
            nodoActual = nodoActual.siguiente;
        }
        System.out.println("]");
    }

    private Nodo buscarNodo(int posicion) {
        Nodo nodoActual = cabeza;
        for (int posicionActual = 0; posicionActual < posicion; posicionActual++) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual;
    }

    private Nodo construirListaVacia(int capacidad) {
        if (capacidad == 0) {
            return null;
        }
        Nodo primerNodo = new Nodo(0);
        Nodo nodoActual = primerNodo;
        for (int posicion = 1; posicion < capacidad; posicion++) {
            nodoActual.siguiente = new Nodo(0);
            nodoActual = nodoActual.siguiente;
        }
        return primerNodo;
    }

    private NodoIndice buscarIndice(int indiceBuscado) {
        NodoIndice nodoIndiceActual = cabezaIndice;
        while (nodoIndiceActual != null) {
            if (nodoIndiceActual.indice == indiceBuscado) {
                return nodoIndiceActual;
            }
            nodoIndiceActual = nodoIndiceActual.siguiente;
        }
        return null;
    }

    private void registrarIndiceSiNoExiste(int indiceNuevo, Nodo nodoObjetivo) {
        NodoIndice existente = buscarIndice(indiceNuevo);
        if (existente == null) {
            NodoIndice nuevoIndice = new NodoIndice(indiceNuevo, nodoObjetivo);
            nuevoIndice.siguiente = cabezaIndice;
            cabezaIndice = nuevoIndice;
            contadorElementosUsados = contadorElementosUsados + 1;
        }
    }
}