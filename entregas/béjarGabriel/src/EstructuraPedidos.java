
public interface EstructuraPedidos {

    void insertar(Pedido p);

    Pedido extraerMinimo();

    boolean isEmpty();

    int size();

    long getComparaciones();
}
