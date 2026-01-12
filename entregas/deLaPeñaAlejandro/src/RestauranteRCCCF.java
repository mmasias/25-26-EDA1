public class RestauranteRCCCF {

    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private static final int DURACION_JORNADA = 120; 

    private Cocinero cocinero;
    private ColaPrioridad cola;

    private int pedidosAtendidos;
    private int tiempoTotalEspera;

    public RestauranteRCCCF() {
        this.cocinero = new Cocinero();
        this.cola = new ColaPrioridad();
        this.pedidosAtendidos = 0;
        this.tiempoTotalEspera = 0;
    }

    public void abrir() {
        for (int minuto = 1; minuto <= DURACION_JORNADA; minuto++) {
            simularMinuto(minuto);
        }
        mostrarResumen();
    }

    private void simularMinuto(int minuto) {
        mostrarCabeceraMinuto(minuto);

        if (Math.random() < PROBABILIDAD_LLEGADA) {
            generarPedidoAleatorio();
        }

        if (cocinero.estaLibre() && !cola.estaVacia()) {
            Pedido siguiente = cola.obtenerSiguiente();
            cocinero.asignar(siguiente);
        }

        boolean estabaOcupado = !cocinero.estaLibre();
        cocinero.trabajar(); 
        
        if (estabaOcupado && cocinero.estaLibre()) {
            pedidosAtendidos++;
        }

        tiempoTotalEspera += cola.cantidadPendientes();

        mostrarEstadoActual();
    }

    private void generarPedidoAleatorio() {
        String[] tipos = {"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"};
        int[][] rangos = {{1, 2}, {2, 3}, {2, 4}, {3, 5}, {5, 8}};

        int indice = (int) (Math.random() * tipos.length);
        String tipo = tipos[indice];
        int min = rangos[indice][0];
        int max = rangos[indice][1];
        
        int tiempo = (int) (Math.random() * (max - min + 1)) + min;

        Pedido nuevoPedido = new Pedido(tipo, tiempo);
        cola.agregar(nuevoPedido);

        Utilidades.mensaje("Llega pedido: " + tipo + " (" + tiempo + " min)");
        Utilidades.saltarLinea();
    }

    private void mostrarCabeceraMinuto(int minuto) {
        Utilidades.mensaje("========================================");
        Utilidades.saltarLinea();
        Utilidades.mensaje("[" + (float) minuto + "]");
        Utilidades.saltarLinea();
    }

    private void mostrarEstadoActual() {
        Utilidades.mensaje("COLA: " + cola.cantidadPendientes() + " pedidos");
        Utilidades.saltarLinea();

        Utilidades.mensaje("Cocinero: ");
        if (cocinero.estaLibre()) {
            Utilidades.mensaje("[Libre]");
        } else {
            Utilidades.mensaje(cocinero.getPedidoActual().toString());
        }
        Utilidades.saltarLinea();
    }

    private void mostrarResumen() {
        Utilidades.saltarLinea();
        Utilidades.mensaje("RESUMEN DE LA JORNADA");
        Utilidades.mensaje("========================================");
        Utilidades.saltarLinea();
        
        Utilidades.mensaje("Pedidos atendidos        : " + pedidosAtendidos);
        Utilidades.saltarLinea();
        
        Utilidades.mensaje("Pedidos pendientes       : " + cola.cantidadPendientes());
        Utilidades.saltarLinea();
        
        Utilidades.mensaje("Tiempo total de espera   : " + tiempoTotalEspera + " minutos");
        Utilidades.saltarLinea();
        
        double media = pedidosAtendidos + cola.cantidadPendientes() > 0 
                ? (double) tiempoTotalEspera / (pedidosAtendidos + cola.cantidadPendientes()) 
                : 0.0;
                
        Utilidades.mensaje("Tiempo medio de espera   : " + String.format("%.1f", media) + " minutos");
        Utilidades.saltarLinea();
        
        Utilidades.mensaje("Comparaciones totales    : " + cola.getComparaciones());
        Utilidades.saltarLinea();
        Utilidades.mensaje("========================================");
        Utilidades.saltarLinea();
    }
}