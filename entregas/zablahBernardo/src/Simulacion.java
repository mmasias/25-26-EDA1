import java.util.Random;

public class Simulacion {
    private static final int JORNADA_MINUTOS = 720;
    private static final double PROBABILIDAD_LLEGADA = 0.4;

    private ArbolPedidos arbolPedidos;
    private PresentacionConsola presentacionConsola;
    private Random random;
    private Pedido pedido;

    private int pedidosAtendidos;
    private int tiempoEsperaTotal;

    public Simulacion() {
        this.arbolPedidos = new ArbolPedidos();
        this.presentacionConsola = new PresentacionConsola();
        this.random = new Random();
        this.pedido = null;
        
        this.pedidosAtendidos = 0;
        this.tiempoEsperaTotal = 0;
    }


    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
