public class Main {
    // Generador global
    public static MiRandom rnd = new MiRandom();

    public static void main(String[] args) {
        Simulacion sim = new Simulacion(120); // 120 minutos
        sim.iniciar();
    }
}
