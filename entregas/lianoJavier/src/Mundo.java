
public class Mundo {

  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante();

    restaurante.abrir();

    empezarSimulacion(restaurante);

    restaurante.resultadoFinal();

  }

  private static void empezarSimulacion(Restaurante restaurante) {
    while (restaurante.estaAbierto()) {
      if (llegaPersona()) {
        Pedido pedido = personaPide();
        restaurante.recoger(pedido);
      }

      restaurante.actualizar();
      restaurante.mostrarEstado();
    }
  }

  private static Pedido personaPide() {
    Pedido pedido = new Pedido();
    return pedido;
  }

  private static boolean llegaPersona() {
    return Math.random() <= 0.6;
  }
}
