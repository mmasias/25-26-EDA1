public class ListaComoArrayExamen {

    private static final int INCREMENTO_UNIDAD = 1;
    private static final int FACTOR_DOBLE_CAPACIDAD = 2;
    private static final int VALOR_INICIAL_NODO = 0;
    private static final int CAPACIDAD_MINIMA_INICIAL = 1;
    private static final int CAPACIDAD_POR_DEFECTO = 4;

    private ArraySimuladoPorLista datos;
    private int tamanio;

    private static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int valorInicial) {
            int valorAsignado;
            valorAsignado = valorInicial;
            dato = valorAsignado;
            siguiente = null;
        }
    }

    private static class ArraySimuladoPorLista {
        private Nodo cabeza;
        private int capacidad;

        ArraySimuladoPorLista(int capacidadInicial) {
            int capacidadConstruida;
            if (capacidadInicial <= 0) {
                capacidadConstruida = CAPACIDAD_MINIMA_INICIAL;
            } else {
                capacidadConstruida = capacidadInicial;
            }
            capacidad = capacidadConstruida;
            cabeza = construirListaVacia(capacidad);
        }

        int longitud() {
            int resultado;
            resultado = capacidad;
            return resultado;
        }

        int obtener(int posicionObjetivo) {
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

        void establecer(int posicionObjetivo, int valorNuevo) {
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

        void copiarHacia(ArraySimuladoPorLista destino, int cantidadCopiada) {
            int posicionActual;
            int valorTemporal;
            posicionActual = 0;
            while (posicionActual < cantidadCopiada) {
                valorTemporal = obtener(posicionActual);
                destino.establecer(posicionActual, valorTemporal);
                posicionActual = posicionActual + INCREMENTO_UNIDAD;
            }
        }

        private Nodo buscarNodo(int posicionObjetivo) {
            Nodo nodoActual;
            int posicionRecorrida;
            nodoActual = cabeza;
            posicionRecorrida = 0;
            while (posicionRecorrida < posicionObjetivo) {
                nodoActual = nodoActual.siguiente;
                posicionRecorrida = posicionRecorrida + INCREMENTO_UNIDAD;
            }
            return nodoActual;
        }

        private Nodo construirListaVacia(int cantidadNodos) {
            Nodo primerNodo;
            Nodo nodoActual;
            int nodosCreados;
            if (cantidadNodos == 0) {
                return null;
            }
            primerNodo = new Nodo(VALOR_INICIAL_NODO);
            nodoActual = primerNodo;
            nodosCreados = INCREMENTO_UNIDAD;
            while (nodosCreados < cantidadNodos) {
                nodoActual.siguiente = new Nodo(VALOR_INICIAL_NODO);
                nodoActual = nodoActual.siguiente;
                nodosCreados = nodosCreados + INCREMENTO_UNIDAD;
            }
            return primerNodo;
        }
    }

    public ListaComoArrayExamen(int capacidadInicial) {
        ArraySimuladoPorLista estructuraInicial;
        int capacidadConstruida;
        if (capacidadInicial <= 0) {
            capacidadConstruida = CAPACIDAD_POR_DEFECTO;
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
        minimoNecesario = tamanio + INCREMENTO_UNIDAD;
        asegurarCapacidad(minimoNecesario);
        datos.establecer(tamanio, valorNuevo);
        tamanio = tamanio + INCREMENTO_UNIDAD;
    }

    public void insertar(int posicionObjetivo, int valorNuevo) {
        boolean posicionValida;
        int minimoNecesario;
        int posicionActual;
        int valorTemporal;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo <= tamanio;
        if (posicionValida) {
            minimoNecesario = tamanio + INCREMENTO_UNIDAD;
            asegurarCapacidad(minimoNecesario);
            posicionActual = tamanio;
            int posicionAnterior;
            posicionAnterior = posicionActual - INCREMENTO_UNIDAD;
            while (posicionActual > posicionObjetivo) {
                valorTemporal = datos.obtener(posicionAnterior);
                datos.establecer(posicionActual, valorTemporal);
                posicionActual = posicionActual - INCREMENTO_UNIDAD;
                posicionAnterior = posicionActual - INCREMENTO_UNIDAD;
            }
            datos.establecer(posicionObjetivo, valorNuevo);
            tamanio = tamanio + INCREMENTO_UNIDAD;
        } else {
            System.out.println("Posición fuera de rango.");
        }
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

    public void establecer(int posicionObjetivo, int valorNuevo) {
        boolean posicionDentroDeTamanio;
        boolean posicionFinalValida;
        posicionDentroDeTamanio = posicionObjetivo >= 0 && posicionObjetivo < tamanio;
        posicionFinalValida = posicionObjetivo == tamanio && posicionObjetivo >= 0 && posicionObjetivo < datos.longitud();
        if (posicionDentroDeTamanio) {
            datos.establecer(posicionObjetivo, valorNuevo);
        } else {
            if (posicionFinalValida) {
                agregar(valorNuevo);
            } else {
                System.out.println("Posición fuera de rango.");
            }
        }
    }

    public int eliminarEn(int posicionObjetivo) {
        boolean posicionValida;
        int valorEliminado;
        int posicionActual;
        int posicionSiguiente;
        int valorSiguiente;
        posicionValida = posicionObjetivo >= 0 && posicionObjetivo < tamanio;
        if (posicionValida) {
            valorEliminado = datos.obtener(posicionObjetivo);
            posicionActual = posicionObjetivo;
            while (posicionActual < tamanio - INCREMENTO_UNIDAD) {
                posicionSiguiente = posicionActual + INCREMENTO_UNIDAD;
                valorSiguiente = datos.obtener(posicionSiguiente);
                datos.establecer(posicionActual, valorSiguiente);
                posicionActual = posicionActual + INCREMENTO_UNIDAD;
            }
            int ultimaPosicionValida;
            ultimaPosicionValida = tamanio - INCREMENTO_UNIDAD;
            datos.establecer(ultimaPosicionValida, VALOR_INICIAL_NODO);
            tamanio = tamanio - INCREMENTO_UNIDAD;
        } else {
            System.out.println("Posición fuera de rango.");
            valorEliminado = -1;
        }
        return valorEliminado;
    }

    public void imprimir() {
        int posicionActual;
        System.out.print("[");
        posicionActual = 0;
        while (posicionActual < tamanio) {
            System.out.print(obtener(posicionActual));
            int ultimaPosicion;
            ultimaPosicion = tamanio - INCREMENTO_UNIDAD;
            if (posicionActual < ultimaPosicion) {
                System.out.print(", ");
            }
            posicionActual = posicionActual + INCREMENTO_UNIDAD;
        }
        System.out.println("]");
    }

    public void set(int posicionObjetivo, int valorNuevo) {
        establecer(posicionObjetivo, valorNuevo);
    }

    public int get(int posicionObjetivo) {
        int valorLeido;
        valorLeido = obtener(posicionObjetivo);
        return valorLeido;
    }

    private void asegurarCapacidad(int minimoNecesario) {
        int capacidadActual;
        int nuevaCapacidad;
        ArraySimuladoPorLista nuevoArray;
        capacidadActual = datos.longitud();
        if (minimoNecesario <= capacidadActual) {
            nuevaCapacidad = capacidadActual;
        } else {
            nuevaCapacidad = capacidadActual * FACTOR_DOBLE_CAPACIDAD;
            if (nuevaCapacidad < minimoNecesario) {
                nuevaCapacidad = minimoNecesario;
            }
            nuevoArray = new ArraySimuladoPorLista(nuevaCapacidad);
            datos.copiarHacia(nuevoArray, tamanio);
            datos = nuevoArray;
        }
    }
}