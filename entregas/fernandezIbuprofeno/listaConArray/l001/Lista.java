package l001;

public class Lista {
    private int[][] nodos;
    private int cabeza;
    private int cola;
    private int libres;
    private int tamaño;
    private int siguienteNuevo;

    public Lista() {
        nodos = new int[5][2];
        cabeza = -1;
        cola = -1;
        libres = -1;
        tamaño = 0;
        siguienteNuevo = 0;
    }

    public void agregar(int valor) {
        int nuevoIndice = obtenerEspacioLibre();
        nodos[nuevoIndice][0] = valor;
        nodos[nuevoIndice][1] = -1;

        if (cabeza == -1) {
            cabeza = nuevoIndice;
            cola = nuevoIndice;
        } else {
            nodos[cola][1] = nuevoIndice;
            cola = nuevoIndice;
        }
        tamaño++;
    }

    public int obtener(int posicion) {
        assert posicion >= 0 && posicion < tamaño : "Precondición violada: posición fuera de rango";

        int actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = nodos[actual][1];
        }
        return nodos[actual][0];
    }

    public void modificar(int posicion, int nuevoValor) {
        assert posicion >= 0 && posicion < tamaño : "Precondición violada: posición fuera de rango";

        int actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = nodos[actual][1];
        }
        nodos[actual][0] = nuevoValor;
    }

    public void eliminar(int posicion) {
        assert posicion >= 0 && posicion < tamaño : "Precondición violada: posición fuera de rango";

        if (posicion == 0) {
            int indiceEliminado = cabeza;
            cabeza = nodos[cabeza][1];
            if (cabeza == -1) {
                cola = -1;
            }
            liberarEspacio(indiceEliminado);
        } else {
            int actual = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                actual = nodos[actual][1];
            }
            int indiceEliminado = nodos[actual][1];
            nodos[actual][1] = nodos[indiceEliminado][1];

            if (indiceEliminado == cola) {
                cola = actual;
            }

            liberarEspacio(indiceEliminado);
        }
        tamaño--;
    }

    public int tamaño() {
        return tamaño;
    }

    private void liberarEspacio(int indice) {
        nodos[indice][1] = libres;
        libres = indice;
    }

    private void redimensionar() {
        int[][] nuevoArray = new int[nodos.length * 2][2];
        for (int i = 0; i < nodos.length; i++) {
            nuevoArray[i][0] = nodos[i][0];
            nuevoArray[i][1] = nodos[i][1];
        }
        nodos = nuevoArray;
    }

    private int obtenerEspacioLibre() {
        if (libres != -1) {
            int indice = libres;
            libres = nodos[libres][1];
            return indice;
        }

        if (siguienteNuevo >= nodos.length) {
            redimensionar();
        }

        return siguienteNuevo++;
    }

    public void mostrarEstructura() {
        System.out.println("Cabeza: " + cabeza + " / Cola: " + cola + " / Libres: " + libres + " / Siguiente nuevo: " + siguienteNuevo);
        System.out.println("Índice | Dato | Siguiente");
        for (int i = 0; i < nodos.length; i++) {
            System.out.println("  " + i + "    |  " + nodos[i][0] + "   |    " + nodos[i][1]);
        }
        System.out.println("==".repeat(10));
    }

    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.agregar(1);        lista.mostrarEstructura();
        lista.agregar(2);        lista.mostrarEstructura();
        lista.agregar(3);        lista.mostrarEstructura();

        lista.eliminar(0);      lista.mostrarEstructura();
        lista.eliminar(0);      lista.mostrarEstructura();
        lista.eliminar(0);      lista.mostrarEstructura();

        lista.agregar(666);     lista.mostrarEstructura();

        System.out.println("Tamaño: " + lista.tamaño());
        System.out.println("Elemento en 1: " + lista.obtener(0));
        
        lista.mostrarEstructura();
        
        System.out.println();
        System.out.println("ELIMINANDO POSICIÓN 1");
        lista.eliminar(0);
        
        lista.mostrarEstructura();
        
        System.out.println();
        System.out.println("AGREGANDO NUEVO ELEMENTO 99");
        lista.agregar(99);
        
        lista.mostrarEstructura();
        
    }
}
