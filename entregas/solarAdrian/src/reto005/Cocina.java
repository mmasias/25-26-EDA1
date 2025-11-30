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
        // Si no hay pedido en proceso, intentamos coger uno
        if (pedidoEnProceso == null && !colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.poll();
            // Nota: El tiempo de espera se podría calcular aquí o al final.
            // Si asumimos que "tiempo de espera" es el tiempo en cola:
            // Pero el diagrama no tiene "tiempoActual" en Cocina, así que no podemos
            // calcular espera exacta
            // a menos que se pase el tiempo actual o el pedido tenga su hora de llegada.
            // El diagrama de Pedido no tiene "horaLlegada".
            // Revisando el prompt original: "Tiempo total de espera acumulado".
            // Sin hora de llegada en Pedido, es difícil calcular espera real.
            // Voy a asumir que el cálculo se hará simplificado o que el usuario aceptará
            // añadir horaLlegada después.
            // O quizás "tiempo de espera" se refiere a ciclos pasados en cola?
            // Para seguir el diagrama ESTRICTAMENTE, no puedo añadir campos a Pedido.
            // Pero sin hora de llegada, ¿cómo sé cuánto esperó?
            // Quizás el "tiempoEsperaAcumulado" se suma cada minuto por cada pedido en
            // cola?
            // Sí, eso es: Sumatoria de (N pedidos en cola) cada minuto = Tiempo total de
            // espera.
            // Es una forma válida de calcularlo (Little's Law).
        }

        if (pedidoEnProceso != null) {
            pedidoEnProceso.procesarMinuto();

            if (pedidoEnProceso.estaTerminado()) {
                finalizarPedidoActual();
            }
        }

        // Sumar 1 minuto de espera por cada pedido que sigue en la cola
        tiempoEsperaAcumulado += colaPedidos.size();
    }

    private void finalizarPedidoActual() {
        pedidosAtendidos++;
        pedidoEnProceso = null;
        // Inmediatamente tomar el siguiente si hay (según reglas)
        if (!colaPedidos.isEmpty()) {
            pedidoEnProceso = colaPedidos.poll();
            // Si lo tomamos "inmediatamente" en el mismo minuto, ¿deberíamos procesarlo
            // también?
            // "Cuando termina un pedido, inmediatamente toma el siguiente".
            // Normalmente en simulación discreta por minutos, si termina en el minuto M,
            // el siguiente empieza en el M+1 o en el mismo M si sobró tiempo?
            // Asumiremos que lo toma para estar listo para el siguiente minuto.
        }
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
