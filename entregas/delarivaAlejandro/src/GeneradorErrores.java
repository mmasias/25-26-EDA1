import java.util.Random;

public abstract class GeneradorErrores {

    protected final Random rng;

    public GeneradorErrores(Random random) {
        this.rng = random;
    }

    public abstract String introducirErrores(String mensaje, int repeticiones);
}
