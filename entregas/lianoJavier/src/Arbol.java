
public class Arbol {

  private Nodo raiz;
  private int idNodo;
  private int numeroDeNodos;

  public Arbol() {
    numeroDeNodos = 0;
    idNodo = 0;
  }

  public void insertar(Pedido pedido) {
    numeroDeNodos++;
    if (raiz == null)
      raiz = new Nodo(pedido, idNodo);
    else {
      raiz.insertar(pedido, idNodo);
    }
    idNodo++;
  }

  public Pedido sacarMinimo() {
    numeroDeNodos--;
    Nodo nodoExtraido = raiz.sacarPedidoMinimo();
    if (raiz.esIgualNumeroComanda(nodoExtraido))
      raiz = raiz.sacarArbolMayor();
    return nodoExtraido.getPedido();
  }

  public int getNumeroNodos() {
    return numeroDeNodos;
  }

  public boolean hayNodos() {
    return numeroDeNodos != 0;
  }

}
