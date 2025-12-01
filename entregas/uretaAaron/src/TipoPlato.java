import java.util.Random;

public enum DishType {
    BEBIDA(1, 2),
    CAFE(2, 3),
    COLACAO(2, 4),
    BOCADILLO(3, 5),
    ENSALADA(5, 8);

    private final int minTime;
    private final int maxTime;

    DishType(int minTime, int maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public int generatePrepTime(Random r) {
        return minTime + r.nextInt(maxTime - minTime + 1);
    }
}
