
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
    if (pedido.esMenor(pedidoNodo)) {
      insertarIzquierda(pedido, numeroComanda);
    } else {
      insertarDerecha(pedido, numeroComanda);
    }
  }

  private void insertarDerecha(Pedido pedido, int numeroComanda) {
    if (!hayNodoDerecho()) {
      derecho = new Nodo(pedido, numeroComanda);
    } else {
      derecho.insertar(pedido, numeroComanda);
    }
  }

  private void insertarIzquierda(Pedido pedido, int numeroComanda) {
    if (!hayNodoIzquierdo()) {
      izquierdo = new Nodo(pedido, numeroComanda);
    } else {
      izquierdo.insertar(pedido, numeroComanda);
    }
  }

  public Nodo getIzquierdo() {
    return izquierdo;
  }

  public void setIzquierdo(Nodo izquierdo) {
    this.izquierdo = izquierdo;
  }

  public Nodo getDerecho() {
    return derecho;
  }

  public void setDerecho(Nodo derecho) {
    this.derecho = derecho;
  }

  public void print(int nivel) {
    String indent = "";
    for (int i = 0; i < nivel; i++) {
      indent += "    ";
    }

    if (hayNodoIzquierdo()) {
      Console.imprimirln(indent + "- L: " + izquierdo.getPedido().getNombrePlato() + " ("
          + izquierdo.getPedido().getRangoTiempo() + ")");
      izquierdo.print(nivel + 1);
    }

    if (hayNodoDerecho()) {
      Console.imprimirln(indent + "- R: " + derecho.getPedido().getNombrePlato() + " ("
          + derecho.getPedido().getRangoTiempo() + ")");
      derecho.print(nivel + 1);
    }
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
