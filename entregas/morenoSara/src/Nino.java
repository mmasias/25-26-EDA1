import java.util.Random;

public class Nino {
    private static Random random = new Random();

    private String nombre;

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public static String transmitir(String mensaje) {
        char[] chars = mensaje.toCharArray();

        if (random.nextBoolean()) {
            int pos = (int)(Math.random() * chars.length);
            chars[pos] = letraAleatoria();
        }

        if (random.nextInt(4) == 0) {
            int pos = (int)(Math.random() * chars.length);
            chars[pos] = letraAleatoria();
        }

        return new String(chars);
    }

    private static char letraAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx = (int)(Math.random() * letras.length());
        return letras.charAt(idx);
    }
}

