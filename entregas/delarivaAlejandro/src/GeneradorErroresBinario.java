import java.util.Random;

public class GeneradorErroresBinario extends GeneradorErrores {
    private final double pError;
    private final double pDos;

    public GeneradorErroresBinario(double pError, double pDos, Random rnd) {
        super(rnd);
        this.pError = pError;
        this.pDos = pDos;
    }

    @Override
    public String introducirErrores(String mensaje, int repeticiones) {
        String actual = mensaje;
        for (int i = 0; i < repeticiones; i++) {
            if (UtilTexto.bernoulli(rng, pError)) {
                int k = UtilTexto.bernoulli(rng, pDos) ? 2 : 1;
                actual = alterar(actual, k);
            }
        }
        return actual;
    }

    private String alterar(String s, int k) {
        char[] arr = s.toCharArray();
        if (k == 1) {
            int pos = rng.nextInt(10);
            arr[pos] = UtilTexto.letraAleatoriaDistinta(rng, arr[pos]);
        } else {
            int p1 = rng.nextInt(10);
            int p2;
            do { p2 = rng.nextInt(10); } while (p2 == p1);
            arr[p1] = UtilTexto.letraAleatoriaDistinta(rng, arr[p1]);
            arr[p2] = UtilTexto.letraAleatoriaDistinta(rng, arr[p2]);
        }
        return new String(arr);
    }
}
