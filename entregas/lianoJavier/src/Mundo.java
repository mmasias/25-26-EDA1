
public class Mundo {

  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante();

    restaurante.abrir();

    empezarSimulacion(restaurante);

    restaurante.resultadoFinal();

  }

  private static void empezarSimulacion(Restaurante restaurante) {
    while (restaurante.estaAbierto()) {
      Console.imprimirln("=====================================");

      if (llegaPersona()) {
        Pedido pedido = personaPide();
        restaurante.recoger(pedido);
        Console.imprimirln("Llega un pedido: " + pedido.getNombrePlato());
      }

      restaurante.actualizar();
    }
  }

  private static Pedido personaPide() {
    Pedido pedido = new Pedido()
        .platoAleatorio();
    return pedido;
  }

  private static boolean llegaPersona() {
    final double PROBABILIDAD_DE_LLEGADA = 0.6;
    return Math.random() <= PROBABILIDAD_DE_LLEGADA;
  }
}
