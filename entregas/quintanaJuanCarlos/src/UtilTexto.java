import java.util.Random;

public class UtilTexto {
    public static boolean esDiezMayusculas(String s) {
        if (s == null || s.length() != 10) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 'A' || c > 'Z') return false;
        }
        return true;
    }

    public static String textoAleatorio10(Random rnd) {
        char[] c = new char[10];
        for (int i = 0; i < 10; i++) c[i] = (char) ('A' + rnd.nextInt(26));
        return new String(c);
    }

    public static char letraAleatoriaDistinta(Random rnd, char original) {
        char r;
        do { r = (char) ('A' + rnd.nextInt(26)); }
        while (r == original);
        return r;
    }

    public static int distanciaHamming(String a, String b) {
        int d = 0;
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) != b.charAt(i)) d++;
        return d;
    }

    public static int enteroUniforme(Random rnd, int bajo, int alto) {
        return bajo + rnd.nextInt(alto - bajo + 1);
    }

    public static boolean bernoulli(Random rnd, double p) {
        return rnd.nextDouble() < p;
    }
}
