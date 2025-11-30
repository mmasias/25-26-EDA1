public interface IEstructuraPedidos {
    void insertar(Pedido p);
    Pedido extraerMinimo();
    boolean estaVacia();
    int tamano();
    long getComparaciones();
    void resetComparaciones();
}



