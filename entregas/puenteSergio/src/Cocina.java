package entregas.apellidoNombre.src.rcccf;

import java.util.PriorityQueue;
import java.util.Comparator;

public class Cocina {
    private PriorityQueue<Pedido> cola;
    private Pedido pedidoActual;
    private int pedidosCompletados;
    private int tiempoTotalEspera;
    private long contadorComparaciones;

    public Cocina() {
        Comparator<Pedido> comparadorConContador = (p1, p2) -> {
            contadorComparaciones++;
            return p1.compareTo(p2);
        };

        this.cola = new PriorityQueue<>(comparadorConContador);
        this.pedidosCompletados = 0;
        this.tiempoTotalEspera = 0;
        this.contadorComparaciones = 0;
    }

    public void anadirPedido(Pedido pedido) {
        cola.add(pedido);
    }

    public void tic(double tiempoActual) {
        if (pedidoActual == null && !cola.isEmpty()) {
            pedidoActual = cola.poll();
        }

        if (pedidoActual != null) {
            pedidoActual.disminuirTiempoRestante();

            if (pedidoActual.estaTerminado()) {
                pedidosCompletados++;
                tiempoTotalEspera += (tiempoActual - pedidoActual.getTiempoLlegada());
                pedidoActual = null;

                if (!cola.isEmpty()) {
                    pedidoActual = cola.poll();
                }
            }
        }
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public int getTamanoCola() {
        return cola.size();
    }

    public long getContadorComparaciones() {
        return contadorComparaciones;
    }

    public int getPedidosCompletados() {
        return pedidosCompletados;
    }

    public int getTiempoTotalEspera() {
        return tiempoTotalEspera;
    }

    public int getContadorPedidosPendientes() {
        return cola.size() + (pedidoActual != null ? 1 : 0);
    }
}
