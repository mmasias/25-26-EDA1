import java.util.Random;

public class GeneradorLlegadas {

    private final Random rnd;
    private final double tasaMediaLlegadas;

    public GeneradorLlegadas(long semilla, double tasaMediaLlegadas) {
        if (tasaMediaLlegadas < 0) {
            throw new IllegalArgumentException("La tasa media de llegadas debe ser >= 0");
        }
        this.rnd = new Random(semilla);
        this.tasaMediaLlegadas = tasaMediaLlegadas;
    }

    public int llegadasEnMinuto(int minuto) {
        if (minuto < 0) {
            throw new IllegalArgumentException("El minuto no puede ser negativo");
        }

        return generarPoisson(tasaMediaLlegadas);
    }

    private int generarPoisson(double lambda) {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;

        do {
            k++;
            p *= rnd.nextDouble();
        } while (p > L);

        return k - 1;
    }
}