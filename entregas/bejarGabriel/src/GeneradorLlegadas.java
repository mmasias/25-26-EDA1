import java.util.Random;

class GeneradorLlegadas {
    private final Random rnd;
    private final double tasaMedia;
    private final double[] probabilidadesPorFase = {0.7, 0.4, 0.2}; // Fases: <10 min, 10-30, >30

    public GeneradorLlegadas(long semilla, double tasaMedia) {
        this.rnd = new Random(semilla);
        this.tasaMedia = tasaMedia;
    }

    public int llegadasEnMinuto(int minuto) {
        double prob;
        if (minuto < 10) {
            prob = probabilidadesPorFase[0];
        } else if (minuto < 30) {
            prob = probabilidadesPorFase[1];
        } else {
            prob = probabilidadesPorFase[2];
        }
        double L = Math.exp(-tasaMedia * prob);
        int k = 0;
        double p = 1.0;
        do {
            k++;
            p *= rnd.nextDouble();
        } while (p > L);
        return Math.max(0, k - 1);
    }
}