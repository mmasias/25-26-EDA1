
public class Restaurante {

  private static final int MINUTOS_POR_HORA = 60;
  private static final int HORA_APERTURA = 9, HORA_CIERRE = 21;

  private Cocinero cocinero;
  private Arbol comandas;

  private int tiempo;

  public Restaurante() {
    comandas = new Arbol();
    cocinero = new Cocinero(comandas);
  }

  public void abrir() {
    tiempo = HORA_APERTURA * MINUTOS_POR_HORA;
  }

  public void recoger(Pedido pedido) {
    comandas.insertar(pedido);
  }

  public boolean estaAbierto() {
    return tiempo <= HORA_CIERRE * MINUTOS_POR_HORA;
  }

  public void actualizar() {
    cocinero.actualizar();
  }

  public void resultadoFinal() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resultadoFinal'");
  }

}
