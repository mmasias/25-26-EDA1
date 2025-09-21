import java.util.Random;

public abstract class ModeloError {
    protected final Random aleatorio;
    public ModeloError(Random rnd) { this.aleatorio = rnd; }

    public abstract String aplicarErrores(String original, int nCopias);
}
