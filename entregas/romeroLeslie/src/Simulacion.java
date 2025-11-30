import java.util.Random;

public class Simulacion {
    
    public static void main(String[] args) {
        int duracionSimulacion = 120; 
        double probabilidadLlegada = 0.40;
        
        Cocina cocina = new Cocina(); 
        Random aleatorio = new Random();
        int pedidosTotalesGenerados = 0;
        int proximoId = 1;

        System.out.println("========================================");
        System.out.println("SIMULACIÓN RCCCF (Prioridad: Menor Tiempo Prep)");
        System.out.println("========================================");

    
        for (int minutoActual = 1; minutoActual <= duracionSimulacion; minutoActual++) {
            System.out.println("\n--------------------[" + String.format("%d", minutoActual) + ".0]--------------------");

            
            if (aleatorio.nextDouble() < probabilidadLlegada) {
                Pedido nuevoPedido = new Pedido(proximoId++, minutoActual);
                cocina.recibirPedido(nuevoPedido);
                pedidosTotalesGenerados++;
                System.out.println("Llega pedido: [" + nuevoPedido.getTipo() + " - " + nuevoPedido.getTiempoPrep() + " min | ID:" + nuevoPedido.getId() + "]");
            }

            cocina.asignarPedido(minutoActual);

            cocina.procesarMinuto(); 
            
            System.out.println("COLA: " + cocina.pedidosEnCola() + " pedidos");
            
            if (cocina.getActual() != null) {
                Pedido actual = cocina.getActual();
                System.out.println("Cocinero: [" + actual.getTipo() + " - " + actual.getTiempoRestante() + " min restantes | ID:" + actual.getId() + "]");
            } else {
                 System.out.println("Cocinero: [Libre]");
            }
        }
        int pedidosAtendidos = cocina.getPedidosAtendidos();
        int pedidosPendientes = cocina.pedidosEnCola() + (cocina.getActual() != null ? 1 : 0);
        int tiempoEsperaTotal = cocina.getTiempoEsperaTotal();
        double tiempoMedioEspera = 0.0;
        

        boolean seAtendieronPedidos = pedidosAtendidos > 0;
        if (seAtendieronPedidos) {
            tiempoMedioEspera = (double)tiempoEsperaTotal / pedidosAtendidos;
        }
        
        System.out.println("\n========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos generados        : " + pedidosTotalesGenerados);
        System.out.println("Pedidos atendidos        : " + pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + pedidosPendientes);
        System.out.println("Tiempo total de espera   : " + tiempoEsperaTotal + " minutos");
        System.out.println("Tiempo medio de espera   : " + String.format("%.1f", tiempoMedioEspera) + " minutos");
        System.out.println("Comparaciones totales    : " + cocina.getComparaciones());
        System.out.println("========================================");
    }
}