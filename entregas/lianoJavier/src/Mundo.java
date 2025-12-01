
public class Mundo {

  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante();

    restaurante.abrir();

    do {
      if (personaPide()) {
        Pedido pedido = new Pedido();
        restaurante.recibir(pedido);
      }
      restaurante.actualizar();
      restaurante.mostrarEstado();
    } while (restaurante.estaAbierto());

    restaurante.resultadoFinal();

  }

  private static boolean personaPide() {
    return Math.random() <= 0.6;
  }
}
