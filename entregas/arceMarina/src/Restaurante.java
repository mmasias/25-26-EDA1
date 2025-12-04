public class Restaurante {
    private ArbolPedidos cola; 
    private Cocinero cocinero;
    
    private int pedidosAtendidos;
    private int tiempoTotalEspera;

    public Restaurante() {
        this.cola = new ArbolPedidos();
        this.cocinero = new Cocinero();
        this.pedidosAtendidos = 0;
        this.tiempoTotalEspera = 0;
    }

    public void recibirPedido(Pedido p) {
        this.cola.insertar(p);
        System.out.println("Llega pedido: " + p.getNombrePlato() + " (" + p.getTiempoTotal() + " min)");
    }

    public void gestionarTurno(int minutoActual) {
        if (this.cocinero.estaLibre() && !this.cola.estaVacio()) {
            Pedido siguiente = this.cola.extraerMinimo();
            this.cocinero.asignarPedido(siguiente);
        }

        Pedido terminado = this.cocinero.trabajar();

        if (terminado != null) {
            this.pedidosAtendidos++;
            int espera = (minutoActual - terminado.getMinutoLlegada()) - terminado.getTiempoTotal();
            if (espera < 0) espera = 0;
            this.tiempoTotalEspera += espera;
        }
    }

    public int getCantidadCola() {
        return this.cola.getCantidad();
    }

    public String getTextoEstadoCocinero() {
        return "Cocinero: " + this.cocinero.getTextoEstado();
    }

    public void mostrarReporteFinal() {
        System.out.println("========================================");
        System.out.println("RESUMEN DE LA JORNADA");
        System.out.println("========================================");
        System.out.println("Pedidos atendidos        : " + this.pedidosAtendidos);
        System.out.println("Pedidos pendientes       : " + (this.cola.getCantidad() + (this.cocinero.estaLibre() ? 0 : 1)));
        System.out.println("Tiempo total de espera   : " + this.tiempoTotalEspera + " minutos");
        
        double promedio = 0;
        if (this.pedidosAtendidos > 0) {
            promedio = (double) this.tiempoTotalEspera / this.pedidosAtendidos;
        }
        // Redondeo manual para que salga como tu ejemplo (un decimal)
        double promedioRedondeado = Math.round(promedio * 10.0) / 10.0;

        System.out.println("Tiempo medio de espera   : " + promedioRedondeado + " minutos");
        System.out.println("Comparaciones totales    : " + this.cola.getComparaciones());
        System.out.println("========================================");
    }
}