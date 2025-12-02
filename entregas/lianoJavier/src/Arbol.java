
public class Arbol {

  private Nodo raiz;

  public void insertar(Pedido pedido) {
    if (raiz == null)
      raiz = new Nodo(pedido);
    else {
      raiz.insertar(pedido);
    }
  }

  public Pedido sacar() {
    return raiz.sacarMenor();
  }

}
