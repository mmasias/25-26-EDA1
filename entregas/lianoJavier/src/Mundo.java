
public class Mundo {

  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante();

    restaurante.abrir();

    empezarSimulacion(restaurante);

    restaurante.resultadoFinal();

  }

  private static void empezarSimulacion(Restaurante restaurante) {
    boolean saltarPregunta = false;

    while (restaurante.estaAbierto()) {
      Console.imprimirln("=====================================");

      if (llegaPersona()) {
        Pedido pedido = personaPide();
        restaurante.recoger(pedido);
        Console.imprimirln("Llega un pedido: " + pedido.getNombrePlato());
      }

      restaurante.actualizar();

      if (!saltarPregunta) {
        Console.imprimirln("[L] Ver Arbol | [T] Terminar Simulacion | [Enter] Siguiente Minuto");
        String input = Console.leerTexto();

        if (input.equalsIgnoreCase("L")) {
          restaurante.mostrarArbol();
        } else if (input.equalsIgnoreCase("T")) {
          saltarPregunta = true;
        }
      }
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
