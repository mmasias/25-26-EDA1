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
    
    public class ListaComoArray {
    
        private ArraySimuladoPorLista datos;
        private int tamanio;

        public ListaComoArray(int capacidadInicial) {
            ArraySimuladoPorLista estructuraInicial;
            int capacidadConstruida;
            if (capacidadInicial <= 0) {
                capacidadConstruida = 4;
            } else {
                capacidadConstruida = capacidadInicial;
            }
            estructuraInicial = new ArraySimuladoPorLista(capacidadConstruida);
            datos = estructuraInicial;
            tamanio = 0;
        }

        public int obtenerTamanio() {
            int resultado;
            resultado = tamanio;
            return resultado;
        }

        public boolean estaVacia() {
            boolean vacia;
            vacia = tamanio == 0;
            return vacia;
        }
    }
    public class ListaComoArray {
        private ArraySimuladoPorLista datos;
        private int tamanio;

        public ListaComoArray(int capacidadInicial) {
            ArraySimuladoPorLista estructuraInicial;
            int capacidadConstruida;
            if (capacidadInicial <= 0) {
                capacidadConstruida = 4;
            } else {
                capacidadConstruida = capacidadInicial;
            }
            estructuraInicial = new ArraySimuladoPorLista(capacidadConstruida);
            datos = estructuraInicial;
            tamanio = 0;
        }

        public int obtenerTamanio() {
            int resultado;
            resultado = tamanio;
            return resultado;
        }

        public boolean estaVacia() {
            boolean vacia;
            vacia = tamanio == 0;
            return vacia;
        }

        public void agregar(int valorNuevo) {
            int minimoNecesario;
            minimoNecesario = tamanio + 1;
            asegurarCapacidad(minimoNecesario);
            datos.establecer(tamanio, valorNuevo);
            tamanio = tamanio + 1;
        }

        public int obtener(int posicionObjetivo) {
            boolean posicionValida;
            int valorLeido;
            posicionValida = posicionObjetivo >= 0 && posicionObjetivo < tamanio;
            if (posicionValida) {
                valorLeido = datos.obtener(posicionObjetivo);
            } else {
                System.out.println("Posición fuera de rango.");
                valorLeido = -1;
            }
            return valorLeido;
        }

        private void asegurarCapacidad(int minimoNecesario) {
            int capacidadActual;
            int nuevaCapacidad;
            ArraySimuladoPorLista nuevoArray;
            capacidadActual = datos.longitud();
            if (minimoNecesario > capacidadActual) {
                nuevaCapacidad = capacidadActual * 2;
                if (nuevaCapacidad < minimoNecesario) {
                    nuevaCapacidad = minimoNecesario;
                }
                nuevoArray = new ArraySimuladoPorLista(nuevaCapacidad);
                copiarDatos(nuevoArray);
                datos = nuevoArray;
            }
        }

        private void copiarDatos(ArraySimuladoPorLista destino) {
            int indice;
            int valorTemporal;
            indice = 0;
            while (indice < tamanio) {
                valorTemporal = datos.obtener(indice);
                destino.establecer(indice, valorTemporal);
                indice = indice + 1;
            }
        }
    }
    public class ListaComoArray {
        private ArraySimuladoPorLista datos;
        private int tamanio;

        public ListaComoArray(int capacidadInicial) {
            ArraySimuladoPorLista estructuraInicial;
            int capacidadConstruida;
            if (capacidadInicial <= 0) {
                capacidadConstruida = 4;
            } else {
                capacidadConstruida = capacidadInicial;
            }
            estructuraInicial = new ArraySimuladoPorLista(capacidadConstruida);
            datos = estructuraInicial;
            tamanio = 0;
        }

        public int get(int posicionObjetivo) {
            int valorLeido;
            valorLeido = obtener(posicionObjetivo);
            return valorLeido;
        }

        public void set(int posicionObjetivo, int valorNuevo) {
            establecer(posicionObjetivo, valorNuevo);
        }

        private void establecer(int posicionObjetivo, int valorNuevo) {
            boolean posicionValida;
            posicionValida = posicionObjetivo >= 0 && posicionObjetivo < tamanio;
            if (posicionValida) {
                datos.establecer(posicionObjetivo, valorNuevo);
            } else {
                System.out.println("Posición fuera de rango.");
            }
        }
    }
    
    public class ListaComoArray {
        private ArraySimuladoPorLista datos;
        private int tamanio;

        public void imprimir() {
            int indice;
            System.out.print("[");
            indice = 0;
            while (indice < tamanio) {
                System.out.print(obtener(indice));
                if (indice < tamanio - 1) {
                    System.out.print(", ");
                }
                indice = indice + 1;
            }
            System.out.println("]");
        }
    }
}
