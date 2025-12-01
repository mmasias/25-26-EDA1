
public class Estadisticas {
    public int pedidosAtendidos = 0;
    public long tiempoTotalEspera = 0;
    public long comparacionesTotales = 0;

    public void registrarPedidoTerminado(int minutoActual, Pedido pedidoCompletado) {
        this.pedidosAtendidos++;
  
        int tiempoEspera = minutoActual - pedidoCompletado.getTiempoPreparacion() - pedidoCompletado.getMinutoLlegada();
        this.tiempoTotalEspera += tiempoEspera;
    }

  
    public void registrarComparacion() {
        this.comparacionesTotales++;
    }

   
    public double getTiempoMedioEspera() {
        if (pedidosAtendidos == 0) {
            return 0.0;
        }
        return (double) tiempoTotalEspera / pedidosAtendidos;
    }
}
