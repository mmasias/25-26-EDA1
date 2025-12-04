
public class Restaurante {

  private static final int MINUTOS_POR_HORA = 60;
  private static final int HORA_APERTURA = 9, HORA_CIERRE = 21;

  private Cocinero cocinero;
  private Arbol comandas;

  private int pedidosAtendidos;
  private int tiempoTotalEspera;
  private int tiempo;

  public Restaurante() {
    comandas = new Arbol();
    cocinero = new Cocinero(comandas);
  }

  public void abrir() {
    tiempo = HORA_APERTURA * MINUTOS_POR_HORA;
  }

  public void recoger(Pedido pedido) {
    pedido.setTiempoLlegada(tiempo);
    comandas.insertar(pedido);
  }

  public boolean estaAbierto() {
    return tiempo <= HORA_CIERRE * MINUTOS_POR_HORA;
  }

  public void actualizar() {
    Console.imprimirln("[" + tiempo / 60 + ":" + tiempo % 60 + "]");
    Console.imprimirln("Número de comandas: " + comandas.getNumeroNodos());
    cocinero.actualizar();
    if (cocinero.tienePedidoListo()) {
      Pedido pedidoTerminado = cocinero.recogerPedido();
      pedidosAtendidos++;
      tiempoTotalEspera += (tiempo - pedidoTerminado.getTiempoLlegada());
    }
    tiempo++;
  }

  public void resultadoFinal() {
    Console.imprimirln("RESUMEN DE LA JORNADA");
    Console.imprimirln("========================================");
    Console.imprimirln("Pedidos atendidos        : " + pedidosAtendidos);
    Console.imprimirln("Pedidos pendientes       : " + comandas.getNumeroNodos());
    Console.imprimirln("Tiempo total de espera   : " + tiempoTotalEspera);
    Console.imprimirln(
        "Tiempo medio de espera   : " + (pedidosAtendidos > 0 ? (double) tiempoTotalEspera / pedidosAtendidos : 0));
    Console.imprimirln("========================================");
  }

}
