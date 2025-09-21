import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Mensaje {
    private final String contenido; 
    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Mensaje(String contenido) {
        if (contenido.length() != 10)
            throw new IllegalArgumentException("El mensaje debe tener exactamente 10 letras.");
        this.contenido = contenido.toUpperCase();
    }

    public static Mensaje aleatorio(Random rnd) {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(ALFABETO.charAt(rnd.nextInt(ALFABETO.length())));
        }
        return new Mensaje(sb.toString());
    }

    public String getContenido() { return contenido; }

    public String deformar(Random rnd, int cambios) {
        if (cambios <= 0) return contenido;
        char[] arr = contenido.toCharArray();
        int len = arr.length;
        Set<Integer> posiciones = new HashSet<>();
        while (posiciones.size() < Math.min(cambios, len)) {
            posiciones.add(rnd.nextInt(len));
        }
        for (int pos : posiciones) {
            char original = arr[pos];
            char nuevo;
            do {
                nuevo = ALFABETO.charAt(rnd.nextInt(ALFABETO.length()));
            } while (nuevo == original);
            arr[pos] = nuevo;
        }
        return new String(arr);
    }

    public int hamming(String otro) {
        if (otro.length() != contenido.length())
            throw new IllegalArgumentException("Longitudes distintas al calcular Hamming.");
        int d = 0;
        for (int i = 0; i < contenido.length(); i++)
            if (contenido.charAt(i) != otro.charAt(i)) d++;
        return d;
    }

    @Override
    public String toString() {
        return contenido;
    }
}