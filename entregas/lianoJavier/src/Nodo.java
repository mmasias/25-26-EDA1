
public class Nodo {

  private Pedido pedidoNodo;
  private Nodo izquierdo;
  private Nodo derecho;

  private int idNodo;

  public Nodo(Pedido pedido, int numeroComanda) {
    pedidoNodo = pedido;
    this.idNodo = numeroComanda;
  }

  public void insertar(Pedido pedido, int numeroComanda) {
    if (pedidoNodo.esMenor(pedido)) {
      insertarIzquierda(pedido, numeroComanda);
    } else {
      insertarDerecha(pedido, numeroComanda);
    }
  }

  private void insertarDerecha(Pedido pedido, int numeroComanda) {
    if (derecho == null) {
      derecho = new Nodo(pedido, numeroComanda);
    } else {
      derecho.insertar(pedido, numeroComanda);
    }
  }

  private void insertarIzquierda(Pedido pedido, int numeroComanda) {
    if (izquierdo == null) {
      izquierdo = new Nodo(pedido, numeroComanda);
    } else {
      izquierdo.insertar(pedido, numeroComanda);
    }
  }

  public Nodo sacarPedidoMinimo() {
    Nodo nodoASacar;

    if (hayNodoIzquierdo())
      nodoASacar = izquierdo.sacarPedidoMinimo();
    else if (hayNodoDerecho())
      nodoASacar = derecho.sacarPedidoMinimo();
    else
      nodoASacar = this;

    return nodoASacar;
  }

  private boolean hayNodoDerecho() {
    return derecho != null;
  }

  private boolean hayNodoIzquierdo() {
    return izquierdo != null;
  }

  public Pedido getPedido() {
    return pedidoNodo;
  }

  public Nodo sacarArbolMayor() {
    return derecho;
  }

  public boolean esIgualNumeroComanda(Nodo nodoExtraido) {
    return idNodo == nodoExtraido.getIdNodo();
  }

  private int getIdNodo() {
    return idNodo;
  }

}
