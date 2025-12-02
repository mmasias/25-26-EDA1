
public class Cocinero {

  private Arbol comandas;
  private Pedido comandaActual;

  public Cocinero(Arbol comandas) {
    this.comandas = comandas;
  }

  public void actualizar() {
    if (!estaCocinando()) {
      comandaActual = comandas.sacar();
    } else {
      cocinar();
    }
  }

  private void cocinar() {
    comandaActual.actualizar();

    if (comandaActual.terminada()) {
      entregarComanda();
    }
  }

  private void entregarComanda() {
    comandaActual = null;
  }

  private boolean estaCocinando() {
    return comandaActual == null;
  }

}
