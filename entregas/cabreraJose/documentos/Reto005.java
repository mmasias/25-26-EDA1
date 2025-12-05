import java.util.Random;

public class Reto005RCCCF {
  private static final int DURACION_JORNADA_MINUTOS = 120;
  private static final double PROBABILIDAD_LLEGADA = 0.4;

  private static final String[] NOMBRES_PLATOS = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
  private static final int[][] TIEMPOS_PREP = {{1,2}, {2,2}, {2,3}, {3,3}, {5,4}};

  private static long comparacionesRealizadas = 0;

  public static void main(String[] args) {
    Random generador = new Random();
    ArbolPedidos pedidosPendientes = new ArbolPedidos();

    int tiempoActual = 0;
    int identificadorSiguientePedido = 1;

    Pedido pedidoEnCocina = null;

    int numeroPedidosAtendidos = 0;
    int numeroPedidosPendientes = 0;
    long tiempoTotalEspera = 0;

    while (tiempoActual < DURACION_JORNADA_MINUTOS) {
      System.out.println("========================================");
      System.out.println("[" + (tiempoActual + 1) + ".0]");

      if (generador.nextDouble() < PROBABILIDAD_LLEGADA) {
        int tipoPlato = generador.nextInt(NOMBRES_PLATOS.length);
        int tiempoPreparacion = generarTiempoPreparacion(tipoPlato, generador);
        pedidosPendientes.insertar(new Pedido(identificadorSiguientePedido++, tipoPlato, tiempoPreparacion, tiempoActual));
        numeroPedidosPendientes++;
        System.out.println("Llega pedido: " + NOMBRES_PLATOS[tipoPlato] + " (" + tiempoPreparacion + " min)");
      }

      if (pedidoEnCocina == null) {
        pedidoEnCocina = pedidosPendientes.extraerMinimo();
        if (pedidoEnCocina != null) {
          numeroPedidosPendientes--;
          int tiempoEspera = tiempoActual - pedidoEnCocina.instanteLlegada;
          tiempoTotalEspera += tiempoEspera;
        }
      }

      System.out.println("COLA: " + pedidosPendientes.tamaño() + " pedidos");
      System.out.println("Cocinero: " + (pedidoEnCocina != null ? 
        "[" + NOMBRES_PLATOS[pedidoEnCocina.tipoPlato] + " - " + pedidoEnCocina.tiempoRestante + " min restantes]" : 
        "[sin pedido]"));

      if (pedidoEnCocina != null && --pedidoEnCocina.tiempoRestante <= 0) {
        numeroPedidosAtendidos++;
        pedidoEnCocina = null;
      }

      tiempoActual++;
    }

    if (pedidoEnCocina != null) numeroPedidosPendientes++;
    
    double tiempoMedio = numeroPedidosAtendidos > 0 ? (double) tiempoTotalEspera / numeroPedidosAtendidos : 0.0;
    
    System.out.println("========================================\n\nRESUMEN DE LA JORNADA");
    System.out.println("========================================");
    System.out.println("Pedidos atendidos        : " + numeroPedidosAtendidos);
    System.out.println("Pedidos pendientes       : " + numeroPedidosPendientes);
    System.out.println("Tiempo total de espera   : " + tiempoTotalEspera + " minutos");
    System.out.println("Tiempo medio de espera   : " + String.format("%.1f", tiempoMedio) + " minutos");
    System.out.println("Comparaciones totales    : " + comparacionesRealizadas);
    System.out.println("========================================");
  }

  private static int generarTiempoPreparacion(int tipoPlato, Random generador) {
    return TIEMPOS_PREP[tipoPlato][0] + generador.nextInt(TIEMPOS_PREP[tipoPlato][1]);
  }

  static final class Pedido {
    final int id;
    final int tipoPlato;
    final int instanteLlegada;
    int tiempoRestante;

    Pedido(int id, int tipoPlato, int tiempoPreparacion, int instanteLlegada) {
      this.id = id;
      this.tipoPlato = tipoPlato;
      this.tiempoRestante = tiempoPreparacion;
      this.instanteLlegada = instanteLlegada;
    }
  }

  static final class NodoPedido {
    Pedido pedido;
    NodoPedido hijoIzquierdo;
    NodoPedido hijoDerecho;

    NodoPedido(Pedido pedido) {
      this.pedido = pedido;
    }
  }

  static final class ArbolPedidos {
    private NodoPedido raiz;
    private int tamaño;

    public void insertar(Pedido pedido) {
      raiz = insertarRecursivo(raiz, pedido);
      tamaño++;
    }

    private NodoPedido insertarRecursivo(NodoPedido actual, Pedido pedido) {
      if (actual == null) {
        return new NodoPedido(pedido);
      }
      comparacionesRealizadas++;
      if (pedido.tiempoRestante < actual.pedido.tiempoRestante) {
        actual.hijoIzquierdo = insertarRecursivo(actual.hijoIzquierdo, pedido);
      } else {
        actual.hijoDerecho = insertarRecursivo(actual.hijoDerecho, pedido);
      }
      return actual;
    }

    public Pedido extraerMinimo() {
      if (raiz == null) return null;
      NodoPedido padre = null;
      NodoPedido actual = raiz;

      while (actual.hijoIzquierdo != null) {
        comparacionesRealizadas++;
        padre = actual;
        actual = actual.hijoIzquierdo;
      }

      if (padre == null) {
        raiz = actual.hijoDerecho;
      } else {
        padre.hijoIzquierdo = actual.hijoDerecho;
      }

      tamaño--;
      return actual.pedido;
    }

    public int tamaño() {
      return tamaño;
    }
  }
}
