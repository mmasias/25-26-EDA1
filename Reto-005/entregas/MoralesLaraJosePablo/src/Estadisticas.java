class Estadisticas {
    
    private int pedidosAtendidos;
    private int pedidosGenerados;
    private int tiempoTotalEspera;
    
    public Estadisticas() {
        this.pedidosAtendidos = 0;
        this.pedidosGenerados = 0;
        this.tiempoTotalEspera = 0;
    }
    
    public void registrarLlegada() {
        pedidosGenerados++;
    }
    
    public void registrarCompletado() {
        pedidosAtendidos++;
    }
    
    public void acumularTiempoEspera(int pedidosEnCola) {
        tiempoTotalEspera += pedidosEnCola;
    }
    
    public void mostrarResumen(int comparacionesTotales) {
        int pedidosPendientes = pedidosGenerados - pedidosAtendidos;
        
        System.out.println("\n========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.printf("Pedidos atendidos        : %d%n", pedidosAtendidos);
        System.out.printf("Pedidos pendientes       : %d%n", pedidosPendientes);
        System.out.printf("Tiempo total de espera   : %d minutos%n", tiempoTotalEspera);
        System.out.println("========================================");
    }
}