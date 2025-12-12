public class Main {

    private static final int DURACION_SIMULACION = 120;

    public static final MiRandom rnd = new MiRandom();

    public static void main(String[] args) {
        Simulacion sim = new Simulacion(DURACION_SIMULACION);
        
        sim.iniciar();
    }
}