
public class Pedido {

  final static int BEBIDA = 1, CAFE = 2, COLACAO = 3, BOCADILLO = 4, ENSALADA = 5;
  final static String[] NOMBRE_PLATOS = { "Bebida", "Cafe", "Colacao", "Bocadillo", "Ensalada" };

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

  private int plato;

  public boolean esMenor(Pedido pedido) {
    final int MAXIMO = 1;
    return TIEMPO_PREPARACION_PLATOS[plato][MAXIMO] < TIEMPO_PREPARACION_PLATOS[pedido.getPlato()][MAXIMO];
  }

  public Pedido platoAleatorio() {
    this.plato = Console.randomIntInRange(NOMBRE_PLATOS.length);
    return this;
  }

  public int getPlato() {
    return plato;
  }

  public String getNombrePlato() {
    return NOMBRE_PLATOS[plato];
  }

}
