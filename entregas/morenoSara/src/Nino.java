import java.util.Random;

public class Nino {
    private String nombre;
    private static Random random = new Random();

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static String transmitirPaso(String mensaje, String nombreNino, int paso) {
        char[] chars = mensaje.toCharArray();

        if (random.nextBoolean()) {
            int pos = random.nextInt(chars.length);
            chars[pos] = letraAleatoria();
        }
        if (random.nextInt(4) == 0) {
            int pos = random.nextInt(chars.length);
            chars[pos] = letraAleatoria();
        }

        String nuevo = new String(chars);
        System.out.println("Paso " + paso + " - " + nombreNino + " escribió: " + nuevo);
        return nuevo;
    }

    private static char letraAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx = random.nextInt(letras.length());
        return letras.charAt(idx);
    }
}

