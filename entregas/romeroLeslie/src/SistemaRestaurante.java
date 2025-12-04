import java.util.Random;
import java.util.Scanner;

public class SistemaRestaurante { 
    
    private ArbolPedidos arbolPedidos; 
    private Consola visor; 
    private Random random; 
    private Pedido pedidoEnCocina; 

    private static final int HORAS_SERVICIO_MINUTOS = 720;
    private static final double PROBABILIDAD_LLEGADA = 0.4; 
    
    private int pedidosAtendidos;
    private int tiempoEsperaAcumulado;

    public SistemaRestaurante() {
        this.arbolPedidos = new ArbolPedidos();
        this.visor = new Consola();
        this.random = new Random();

        pedidosAtendidos = 0;
        tiempoEsperaAcumulado = 0;
    }

    public void iniciarServicio() {
        Scanner escaner = new Scanner(System.in); 
        
        for (int minuto = 1; minuto <= HORAS_SERVICIO_MINUTOS; minuto++) {
            
            System.out.print(">> Presiona ENTER para avanzar al minuto " + minuto + "...");
            escaner.nextLine();

            visor.imprimirCabeceraMinuto(minuto);

            if (random.nextDouble() < PROBABILIDAD_LLEGADA) {
                Pedido nuevo = generarPedidoAleatorio(minuto);
                arbolPedidos.insertar(nuevo);
                visor.imprimirLlegadaPedido(nuevo);
            }

            if (pedidoEnCocina == null && !arbolPedidos.estaVacio()) {
                pedidoEnCocina = arbolPedidos.obtenerMaxPrioridad();
            }

            visor.imprimirEstado(arbolPedidos.getTamano(), pedidoEnCocina);
            
            
            procesarCocina(minuto); 
        }
        
        escaner.close();
        
      
        int pendientes = arbolPedidos.getTamano() + (pedidoEnCocina != null ? 1 : 0);
        
        visor.imprimirResumen(
                pedidosAtendidos,
                pendientes,
                tiempoEsperaAcumulado,
                arbolPedidos.getComparaciones());
    }

    private void procesarCocina(int minutoActual) {
        if (pedidoEnCocina != null) {
            pedidoEnCocina.cocinarUnMinuto();

            if (pedidoEnCocina.estaTerminado()) {
                pedidosAtendidos++;

                int tiempoEnCola = (minutoActual - pedidoEnCocina.getMinutoLlegada())
                        - pedidoEnCocina.getTiempoPreparacionTotal();

                tiempoEsperaAcumulado += Math.max(0, tiempoEnCola);

                pedidoEnCocina = null;
            }
        }
    }

    private Pedido generarPedidoAleatorio(int minutoActual) {
        int tipo = random.nextInt(5);
        String nombre = "";
        int tiempo = 0;

        switch (tipo) {
            case 0 -> {
                nombre = "Tostada";
                tiempo = 2 + random.nextInt(1);
            }
            case 1 -> {
                nombre = "Ensalada César";
                tiempo = 4 + random.nextInt(2);
            }
            case 2 -> {
                nombre = "Sopa del Día";
                tiempo = 3 + random.nextInt(2);
            }
            case 3 -> {
                nombre = "Plato Combinado";
                tiempo = 7 + random.nextInt(3);
            }
            case 4 -> {
                nombre = "Postre Especial";
                tiempo = 5 + random.nextInt(4);
            }
        }
        return new Pedido(nombre, tiempo, minutoActual);
    }
}