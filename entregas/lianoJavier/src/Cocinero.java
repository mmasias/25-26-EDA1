
public class Cocinero {

  private Arbol comandas;
  private Pedido comandaActual;

  private boolean estaCocinando;

  final static int[] TIEMPO_PREPARACION_BEBIDA = { 1, 2 };
  final static int[] TIEMPO_PREPARACION_CAFE = { 2, 3 };
  final static int[] TIEMPO_PREPARACION_COLACAO = { 2, 4 };
  final static int[] TIEMPO_PREPARACION_BOCADILLO = { 3, 5 };
  final static int[] TIEMPO_PREPARACION_ENSALADA = { 5, 8 };

  private final static int[][] TIEMPO_PREPARACION_PLATOS = {
      TIEMPO_PREPARACION_BEBIDA,
      TIEMPO_PREPARACION_CAFE,
      TIEMPO_PREPARACION_COLACAO,
      TIEMPO_PREPARACION_BOCADILLO,
      TIEMPO_PREPARACION_ENSALADA
  };

  private int tiempoCocinandoComanda;
  private int tiempoDePreparacion;

  public Cocinero(Arbol comandas) {
    this.comandas = comandas;
  }

  public void actualizar() {
    if (!estaCocinando) {
      if (comandas.hayNodos()) {
        comandaActual = comandas.sacarMinimo();

        if (comandaActual != null) {
          tiempoCocinandoComanda = 0;

          tiempoDePreparacion = Console.randomInt(
              TIEMPO_PREPARACION_PLATOS[comandaActual.getPlato()][0],
              TIEMPO_PREPARACION_PLATOS[comandaActual.getPlato()][1]);

          estaCocinando = true;
        }
      }
    } else {
      cocinar();
      Console.imprimirln(
          "Cocinero: " + comandaActual.getNombrePlato() + "(" + (tiempoDePreparacion - tiempoCocinandoComanda)
              + " minutos restantes)");
    }
  }

  public boolean tienePedidoListo() {
    return estaCocinando && haTerminada();
  }

  public Pedido recogerPedido() {
    Pedido pedidoTerminado = comandaActual;
    entregarComanda();
    return pedidoTerminado;
  }

  private void cocinar() {
    tiempoCocinandoComanda++;
  }

  private boolean haTerminada() {
    return tiempoCocinandoComanda >= tiempoDePreparacion;
  }

  private void entregarComanda() {
    comandaActual = null;
    estaCocinando = false;
  }

}
