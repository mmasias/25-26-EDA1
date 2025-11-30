import java.util.*;

class Restaurante {
    private List<Pedido> colaPedidos;
    private Cocinero cocinero;
    
    private int pedidosAtendidos;
    private int tiempoTotalEspera;
    private long contadorComparaciones;

    Restaurante() {
        this.colaPedidos = new ArrayList<>();
        this.cocinero = new Cocinero();
        this.pedidosAtendidos = 0;
        this.tiempoTotalEspera = 0;
        this.contadorComparaciones = 0;
    }

    String recibirPedido(Pedido pedido) {
        this.colaPedidos.add(pedido);
        return "Llega pedido: " + pedido.getNombrePlato() + " (" + pedido.getTiempoTotal() + " min)";
    }

    private Pedido buscarYExtraerMasRapido() {
        if (this.colaPedidos.isEmpty()) return null;

        int indiceMejor = 0;

        for (int i = 1; i < this.colaPedidos.size(); i++) {
            this.contadorComparaciones++; 
            
            if (this.colaPedidos.get(i).getTiempoTotal() < this.colaPedidos.get(indiceMejor).getTiempoTotal()) {
                indiceMejor = i;
            }
        }

        return this.colaPedidos.remove(indiceMejor);
    }

    void gestionarTurno(int minutoActual) {
        if (!this.cocinero.estaLibre() && !this.colaPedidos.isEmpty()) {
            Pedido enCocina = this.cocinero.getPedidoActual();

            int mejorIndiceCola = -1;
            int mejorTiempoCola = Integer.MAX_VALUE;
            
            for (int i = 0; i < this.colaPedidos.size(); i++) {
                this.contadorComparaciones++;
                if (this.colaPedidos.get(i).getTiempoRestante() < mejorTiempoCola) {
                    mejorTiempoCola = this.colaPedidos.get(i).getTiempoRestante();
                    mejorIndiceCola = i;
                }
            }

             this.contadorComparaciones++;

             if (mejorIndiceCola != -1 && mejorTiempoCola < enCocina.getTiempoRestante()) {
                System.out.println("   >> PRIORIDAD: Pausando " + enCocina.getNombrePlato() + " (" + enCocina.getTiempoRestante() + "m) por pedido de " + mejorTiempoCola + "m");
                
                Pedido pausado = this.cocinero.dejarPedido();
                this.colaPedidos.add(pausado);
            }
        }

        if (this.cocinero.estaLibre() && !this.colaPedidos.isEmpty()) {
            Pedido siguiente = this.buscarYExtraerMasRapido();
            this.cocinero.asignarPedido(siguiente);
        }

        Pedido terminado = this.cocinero.trabajar();

        if (terminado != null) {
            this.pedidosAtendidos++;
            
            int espera = (minutoActual - terminado.getMinutoLlegada() + 1) - terminado.getTiempoTotal();
            if (espera < 0) espera = 0;

            this.tiempoTotalEspera += espera;
        }
    }

    String obtenerEstadoCocinero() {
        return "Cocinero: " + this.cocinero.obtenerEstado();
    }

    int obtenerTamanoCola() {
        return this.colaPedidos.size();
    }

    void mostrarReporteFinal() {
        double promedio = this.pedidosAtendidos > 0 
            ? (double) this.tiempoTotalEspera / this.pedidosAtendidos 
            : 0.0;

        double promedioRedondeado = Math.round(promedio * 10.0) / 10.0;

        System.out.println("========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + this.pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + this.colaPedidos.size());
        System.out.println("Tiempo total de espera   : " + this.tiempoTotalEspera + " min");
        System.out.println("Tiempo medio de espera   : " + promedioRedondeado + " min");
        System.out.println("Comparaciones SJF (O(N)): " + this.contadorComparaciones);
        System.out.println("========================================");
    }
}
