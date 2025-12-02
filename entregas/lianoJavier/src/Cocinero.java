
public class Cocinero {

  private Arbol comandas;
  private Pedido comandaActual;

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
    if (!estaCocinando()) {
      comandaActual = comandas.sacarMinimo();
      tiempoCocinandoComanda = 0;
      int rangoTiempoPlato = Math
          .abs(TIEMPO_PREPARACION_PLATOS[comandaActual.getPlato()][1]
              - TIEMPO_PREPARACION_PLATOS[comandaActual.getPlato()][0]);
      tiempoDePreparacion = Console.randomIntInRange(rangoTiempoPlato);
    } else {
      cocinar();
    }
  }

  private void cocinar() {
    assert tiempoCocinandoComanda >= tiempoDePreparacion : "[Cocinero]: ¿Me he pasado del tiempo de Preparación?";

    tiempoCocinandoComanda++;

    if (haTerminada())
      entregarComanda();
  }

  private boolean haTerminada() {
    return tiempoCocinandoComanda == tiempoDePreparacion;
  }

  private void entregarComanda() {
    comandaActual = null;
  }

  private boolean estaCocinando() {
    return comandaActual == null;
  }

}
