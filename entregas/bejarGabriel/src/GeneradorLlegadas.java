import java.util.Random;

class GeneradorLlegadas {
    private final Random rnd;
    private final double tasaMedia;
    public GeneradorLlegadas(long semilla, double tasaMedia) {
        this.rnd = new Random(semilla);
        this.tasaMedia = tasaMedia;
    }
    public int llegadasEnMinuto(int minuto) {
        double L = Math.exp(-tasaMedia);
        int k = 0;
        double p = 1.0;
        do {
            k++;
            p *= rnd.nextDouble();
        } while (p > L);
        return k - 1;
    }
}