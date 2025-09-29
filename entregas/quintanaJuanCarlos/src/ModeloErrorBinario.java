import java.util.Random;

public class ModeloErrorBinario extends ModeloError {
    private final double pError;
    private final double pDos;

    public ModeloErrorBinario(double pError, double pDos, Random rnd) {
        super(rnd);
        this.pError = pError;
        this.pDos = pDos;
    }

    @Override
    public String aplicarErrores(String original, int nCopias) {
        String actual = original;
        for (int i = 0; i < nCopias; i++) {
            if (UtilTexto.bernoulli(aleatorio, pError)) {
                int k = UtilTexto.bernoulli(aleatorio, pDos) ? 2 : 1;
                actual = alterarK(actual, k);
            }
        }
        return actual;
    }

    private String alterarK(String s, int k) {
        char[] arr = s.toCharArray();
        if (k == 1) {
            int pos = aleatorio.nextInt(10);
            arr[pos] = UtilTexto.letraAleatoriaDistinta(aleatorio, arr[pos]);
        } else {
            int p1 = aleatorio.nextInt(10);
            int p2;
            do { p2 = aleatorio.nextInt(10); } while (p2 == p1);
            arr[p1] = UtilTexto.letraAleatoriaDistinta(aleatorio, arr[p1]);
            arr[p2] = UtilTexto.letraAleatoriaDistinta(aleatorio, arr[p2]);
        }
        return new String(arr);
    }
}
