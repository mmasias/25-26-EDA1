
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
    assert raiz != null : "No se puede sacar el mínimo de un árbol vacío";

    numeroDeNodos--;

    if (raiz.getIzquierdo() == null) {
      Pedido pedido = raiz.getPedido();
      raiz = raiz.getDerecho();
      return pedido;
    }

    Nodo padre = null;
    Nodo actual = raiz;

    while (actual.getIzquierdo() != null) {
      padre = actual;
      actual = actual.getIzquierdo();
    }

    Pedido pedido = actual.getPedido();
    padre.setIzquierdo(actual.getDerecho());

    return pedido;
  }

  public int getNumeroNodos() {
    return numeroDeNodos;
  }

  public boolean hayNodos() {
    return numeroDeNodos != 0;
  }

  public void print() {
    if (raiz != null) {
      Console.imprimirln(raiz.getPedido().getNombrePlato() + " (" + raiz.getPedido().getRangoTiempo() + ")");
      raiz.print(0);
    }
  }

}
