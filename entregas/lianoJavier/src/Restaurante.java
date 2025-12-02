
public class Restaurante {

  private static final int MINUTOS_POR_HORA = 60;
  private static final int HORA_APERTURA = 9, HORA_CIERRE = 21;

  private Cocinero cocinero;
  private Arbol arbol;

  private int tiempo;

  public Restaurante() {
    arbol = new Arbol();
    cocinero = new Cocinero(arbol);
  }

  public void abrir() {
    tiempo = HORA_APERTURA * MINUTOS_POR_HORA;
  }

  public void recoger(Pedido pedido) {
    arbol.insertar(pedido);
  }

  public boolean estaAbierto() {
    return tiempo <= HORA_CIERRE * MINUTOS_POR_HORA;
  }

  public void actualizar() {
    cocinero.actualizar();
  }

  public void mostrarEstado() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mostrarEstado'");
  }

  public void resultadoFinal() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resultadoFinal'");
  }

}
