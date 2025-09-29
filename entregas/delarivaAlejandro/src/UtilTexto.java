import java.util.Random;

public class UtilTexto {

    public static boolean esDiezMayusculas(String texto) {
        if (texto == null || texto.length() != 10) return false;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c < 'A' || c > 'Z') return false;
        }
        return true;
    }

    public static String generarAleatorio10(Random rnd) {
        char[] letras = new char[10];
        for (int i = 0; i < letras.length; i++) {
            letras[i] = (char) ('A' + rnd.nextInt(26));
        }
        return new String(letras);
    }

    public static char letraAleatoriaDistinta(Random rnd, char original) {
        char nueva;
        do {
            nueva = (char) ('A' + rnd.nextInt(26));
        } while (nueva == original);
        return nueva;
    }

    public static int distanciaHamming(String a, String b) {
        int distancia = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) distancia++;
        }
        return distancia;
    }

    public static int enteroUniforme(Random rnd, int bajo, int alto) {
        return bajo + rnd.nextInt(alto - bajo + 1);
    }

    public static boolean bernoulli(Random rnd, double p) {
        return rnd.nextDouble() < p;
    }
}
