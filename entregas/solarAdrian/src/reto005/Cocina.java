package reto005;

import java.util.PriorityQueue;

public class Cocina {
    private PriorityQueue<Pedido> colaPedidos;
    private Pedido pedidoEnProceso;
    private int pedidosAtendidos;
    private int tiempoEsperaAcumulado;

    public Cocina() {
        this.colaPedidos = new PriorityQueue<>();
        this.pedidoEnProceso = null;
        this.pedidosAtendidos = 0;
        this.tiempoEsperaAcumulado = 0;
    }

    public void recibirPedido(Pedido p) {
        colaPedidos.add(p);
    }

    public void trabajar() {

        if (pedidoEnProceso == null && !colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.poll();

        }

        if (pedidoEnProceso != null) {
            pedidoEnProceso.procesarMinuto();

            if (pedidoEnProceso.estaTerminado()) {
                finalizarPedidoActual();
            }
        }

        tiempoEsperaAcumulado += colaPedidos.size();
    }

    private void finalizarPedidoActual() {
        pedidosAtendidos++;
        pedidoEnProceso = null;
       
        if (!colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.poll();
        
    }

    public void imprimirEstado() {
        System.out.println("COLA: " + colaPedidos.size() + " pedidos");
        if (pedidoEnProceso != null) {
            System.out.println(
                    "Cocinero: [" + pedidoEnProceso + " - Restante: " + pedidoEnProceso.getTiempoRestante() + " min]");
        } else {
            System.out.println("Cocinero: [Esperando]");
        }
    }

    public void mostrarResumen() {
        System.out.println("\nRESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + colaPedidos.size());
        System.out.println("Tiempo total de espera   : " + tiempoEsperaAcumulado + " minutos");

        double medio = (pedidosAtendidos + colaPedidos.size()) > 0
                ? (double) tiempoEsperaAcumulado / (pedidosAtendidos + colaPedidos.size())
                : 0;

        System.out.printf("Tiempo medio de espera   : %.1f minutos\n", medio);
        System.out.println("Comparaciones totales    : " + Pedido.comparacionesTotales);
        System.out.println("========================================");
    }
}
