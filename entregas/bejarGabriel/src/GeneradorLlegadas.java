import java.util.Random;

public class GeneradorLlegadas {
    private final Random rnd;

    public GeneradorLlegadas(Random rnd) {
        this.rnd = rnd;
    }

    public int llegadasEnMinuto(int minuto) {
        if (minuto >= 0 && minuto <= 9) {
            return rnd.nextInt(3);
        } else if (minuto >= 10 && minuto <= 29) {
            if ((minuto - 10) % 3 == 0) {
                return rnd.nextDouble() < 0.5 ? 1 : 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}