package entregas.caicedoFernando.src;

public class ColaPedidos {
    private NodoPedido cabeza;
    private int tamano;

    public ColaPedidos() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int obtenerTamano() {
        return tamano;
    }

    public void agregar(Pedido pedido) {
        NodoPedido nuevoNodo = new NodoPedido(pedido);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamano++;
    }

    public ResultadoExtraccion extraerMinimo() {
        if (estaVacia()) {
            return new ResultadoExtraccion(null, 0);
        }

        int conteoComparaciones = 0;
        NodoPedido nodoActual = cabeza.siguiente;
        NodoPedido nodoAnterior = cabeza;
        
        NodoPedido nodoMinimo = cabeza;
        NodoPedido nodoAnteriorAlMinimo = null;

        while (nodoActual != null) {
            conteoComparaciones++;
            if (nodoActual.pedido.tiempoPreparacion < nodoMinimo.pedido.tiempoPreparacion) {
                nodoMinimo = nodoActual;
                nodoAnteriorAlMinimo = nodoAnterior;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }

        if (nodoAnteriorAlMinimo == null) {
            cabeza = nodoMinimo.siguiente;
        } else {
            nodoAnteriorAlMinimo.siguiente = nodoMinimo.siguiente;
        }

        tamano--;
        return new ResultadoExtraccion(nodoMinimo.pedido, conteoComparaciones);
    }
}