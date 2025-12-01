public class ListaPedidos {

    private NodoPedido primero;
    private int tamano;

    public ListaPedidos() {
        this.primero = null;
        this.tamano = 0;
    }

    public void insertarAlFinal(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoPedido actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }

    public Pedido obtenerEn(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            return null;
        }
        NodoPedido actual = primero;
        int indice = 0;
        while (indice < posicion) {
            actual = actual.getSiguiente();
            indice++;
        }
        return actual.getDato();
    }

    public Pedido eliminarEn(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            return null;
        }
        Pedido resultado;
        if (posicion == 0) {
            resultado = primero.getDato();
            primero = primero.getSiguiente();
        } else {
            NodoPedido anterior = primero;
            int indice = 0;
            while (indice < posicion - 1) {
                anterior = anterior.getSiguiente();
                indice++;
            }
            NodoPedido objetivo = anterior.getSiguiente();
            resultado = objetivo.getDato();
            anterior.setSiguiente(objetivo.getSiguiente());
        }
        tamano--;
        return resultado;
    }
}