import java.util.Random;

public class Simulacion {

    private ArbolPedidos arbolPedidos;
    private PresentacionConsola presentacionConsola;
    private Random random;
    private Pedido pedido;

    private static final int JORNADA_MINUTOS = 720;
    private static final double PROBABILIDAD_LLEGADA = 0.4;
    private int pedidosAtendidos;
    private int tiempoEsperaTotal;

    public Simulacion() {
        this.arbolPedidos = new ArbolPedidos();
        this.presentacionConsola = new PresentacionConsola();
        this.random = new Random();
        this.pedido = null;

        pedidosAtendidos = 0;
        tiempoEsperaTotal = 0;
    }

    public void start() {
        for (int minuto = 1; minuto <= JORNADA_MINUTOS; minuto++) {

            presentacionConsola.imprimirCabeceraMinuto(minuto);

            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                Pedido nuevo = generarPedidoAleatorio(minuto);
                arbolPedidos.insertar(nuevo);
                presentacionConsola.imprimirLlegadaPedido(nuevo);
            }

            if (pedido == null && !arbolPedidos.estaVacio()) {
                pedido = arbolPedidos.extraerMinimo();
            }

            presentacionConsola.imprimirEstado(arbolPedidos.getTamano(), pedido);
            procesarCocina(minuto);
        }

        int pendientes = arbolPedidos.getTamano() + (pedido != null ? 1 : 0);
        
        presentacionConsola.imprimirResumen(
            pedidosAtendidos, 
            pendientes, 
            tiempoEsperaTotal, 
            arbolPedidos.getComparaciones()
        );
    }

    private void procesarCocina(int minutoActual) {
        if (pedido != null) {
            pedido.cocinarUnMinuto();

            if (pedido.estaTerminado()) {
                pedidosAtendidos++;
                
                int tiempoEnCola = (minutoActual - pedido.getMinutoLlegada()) 
                                   - pedido.getTiempoPreparacion();
                
                tiempoEsperaTotal += Math.max(0, tiempoEnCola);
                
                pedido = null;
            }
        }
    }

    private Pedido generarPedidoAleatorio(int minutoActual) {
        int tipo = random.nextInt(5);
        String nombre = "";
        int tiempo = 0;

        switch (tipo) {
            case 0: nombre = "Bebida";    tiempo = 1 + random.nextInt(2); break;
            case 1: nombre = "Café";      tiempo = 2 + random.nextInt(2); break;
            case 2: nombre = "Colacao";   tiempo = 2 + random.nextInt(3); break;
            case 3: nombre = "Bocadillo"; tiempo = 3 + random.nextInt(3); break;
            case 4: nombre = "Ensalada";  tiempo = 5 + random.nextInt(4); break;
        }
        return new Pedido(nombre, tiempo, minutoActual);
    }
}
