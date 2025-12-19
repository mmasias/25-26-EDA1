public class Lista {
    private Nodo cabeza;
    private int tamano;

    public Lista() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(Object valor) {
        insertar(valor, tamano);
    }

    public void insertar(Object valor, int posicion) {
        assert posicion >= 0 && posicion <= tamano : "Error: Posición fuera de rango para insertar";

        Nodo nuevo = new Nodo(valor);

        if (posicion == 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            int contador = 0;
            boolean buscando = true;

            while (buscando) {
                if (contador == posicion - 1) {
                    buscando = false;
                } else {
                    actual = actual.getSiguiente();
                    contador++;
                }
            }
            
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }

    public Object obtener(int indice) {
        assert indice >= 0 && indice < tamano : "Error: Índice fuera de rango al obtener";

        Nodo actual = cabeza;
        int contador = 0;
        boolean buscando = true;
        Object datoEncontrado = null;

        while (buscando) {
            if (contador == indice) {
                datoEncontrado = actual.getDato();
                buscando = false;
            } else {
                actual = actual.getSiguiente();
                contador++;
            }
        }
        return datoEncontrado;
    }

    public void modificar(int indice, Object nuevoValor) {
        assert indice >= 0 && indice < tamano : "Error: Índice fuera de rango al modificar";

        Nodo actual = cabeza;
        int contador = 0;
        boolean buscando = true;

        while (buscando) {
            if (contador == indice) {
                actual.setDato(nuevoValor);
                buscando = false;
            } else {
                actual = actual.getSiguiente();
                contador++;
            }
        }
    }

    public void eliminar(int indice) {
        assert indice >= 0 && indice < tamano : "Error: Índice fuera de rango al eliminar";

        if (indice == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo actual = cabeza;
            int contador = 0;
            boolean buscando = true;

            while (buscando) {
                if (contador == indice - 1) {
                    Nodo nodoAEliminar = actual.getSiguiente();
                    actual.setSiguiente(nodoAEliminar.getSiguiente());
                    buscando = false;
                } else {
                    actual = actual.getSiguiente();
                    contador++;
                }
            }
        }
        tamano--;
    }

    public int tamano() {
        return tamano;
    }

    public void mostrar() {
        Nodo actual = cabeza;
        System.out.print("[ ");
        boolean recorriendo = (actual != null);

        while (recorriendo) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
            if (actual == null) {
                recorriendo = false;
            }
        }
        System.out.println("]");
    }
    
    public void mostrarPosicionesValidas() {
        System.out.println("Rango válido actual: 0 a " + tamano);
    }
}   