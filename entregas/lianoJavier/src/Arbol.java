
public class Arbol {

  private Nodo raiz;
  private int numeroComanda;

  public void insertar(Pedido pedido) {
    if (raiz == null)
      raiz = new Nodo(pedido, numeroComanda);
    else {
      raiz.insertar(pedido, numeroComanda);
    }
    numeroComanda++;
  }

  public Pedido sacarMinimo() {
    Nodo nodoExtraido = raiz.sacarPedidoMinimo();
    if (raiz.esIgualNumeroComanda(nodoExtraido))
      raiz = raiz.sacarArbolMayor();
    return nodoExtraido.getPedido();
  }

}
