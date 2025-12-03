public class SimulacionCocina {
    public static void main(String[] args) {
        ColaDePrioridad cola = new ColaDePrioridad();
        Cocinero cocinero = new Cocinero();
        Vista vista = new Vista();
        String[] nombresPlatos = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
        int[] tiemposMin = { 1, 2, 2, 3, 5 };
        int[] tiemposMax = { 2, 3, 4, 5, 8 };
        int pedidosTotales = 0;
        int tiempoTotalEspera = 0;
        
        for (int minuto = 1; minuto <= 120; minuto++) {
            vista.mostrarTiempo(minuto);
            if (Math.random() < 0.40) {
                int indice = (int) (Math.random() * nombresPlatos.length);
                String nombre = nombresPlatos[indice];
                int min = tiemposMin[indice];
                int max = tiemposMax[indice];
                int tiempoReal = (int) (Math.random() * (max - min + 1)) + min;
                Pedido nuevoPedido = new Pedido(nombre, tiempoReal, minuto);
                cola.agregarPedido(nuevoPedido);
                vista.mostrarLlegadaPedido(nuevoPedido);
                pedidosTotales++;
            }
            cocinero.trabajar();
            if (cocinero.estaLibre() && !cola.estaVacia()) {
                Pedido siguiente = cola.sacarPedidoPrioritario();
                cocinero.asignarPedido(siguiente);
                int espera = minuto - siguiente.getMinutoLlegada();
                tiempoTotalEspera += espera;
            }
            vista.mostrarEstado(cola.getNumeroPedidos(), cocinero);
        }
        int pendientes = cola.getNumeroPedidos();
        int atendidos = pedidosTotales - pendientes;
        double tiempoMedio = (atendidos > 0) ? (double) tiempoTotalEspera / atendidos : 0;
        vista.mostrarResumen(
            atendidos, 
            pendientes, 
            tiempoTotalEspera,
            tiempoMedio,
            cola.getComparacionesTotales()
        );
    }
}