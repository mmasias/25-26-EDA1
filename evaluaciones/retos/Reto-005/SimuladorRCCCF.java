import java.util.Random;

public class SimuladorRCCCF<EstructuraPedidosPendientes, Cocinero> {

private static final int MINUTOS_JORNADA = 120;
private static final double PROB_LLEGADA = 0.4; 
private static final int CAPACIDAD_MAX_PEDIDOS = 500; 

private static final String[] NOMBRES_PLATO = {
"Bebida", "Café", "Colacao", "Bocadillo", "Ensalada"
};

private static final int[] MIN_TIEMPO = {
1, 2, 2, 3, 5
};

private static final int[] MAX_TIEMPO = {
2, 3, 4, 5, 8
};

private final Cocinero cocinero = null;
private final EstructuraPedidosPendientes pendientes = null;

private int pedidosAtendidos;
private int pedidosCreados;
private long tiempoTotalEspera; 
    public Cocinero getCocinero() {
        return cocinero;
    }

}