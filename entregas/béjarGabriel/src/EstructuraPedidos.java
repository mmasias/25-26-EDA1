
public interface EstructuraPedidos {

    void insertar(Pedido p);

    Pedido extraerMinimo();

    boolean estaVacia();

    int tamano();

    long getComparaciones();
}
