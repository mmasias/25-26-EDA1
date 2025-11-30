import java.util.Random;

public class Simulation {

    private final int duracionJornada;
    private final double probLlegada;
    private int tiempoActual;
    private final Cocina cocina;
    private final Estadisticas estadisticas;
    private final Random random;
    private int siguienteId;

    public Simulation(int duracionJornada, double probLlegada, IEstructuraPedidos estructura) {
        this.duracionJornada = duracionJornada;
        this.probLlegada = probLlegada;
        this.tiempoActual = 0;
        this.cocina = new Cocina(estructura);
        this.estadisticas = new Estadisticas();
        this.random = new Random();
        this.siguienteId = 1;
    }
}


