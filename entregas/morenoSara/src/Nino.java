import java.util.Random;

public class Nino {
    private static Random random = new Random();
    private String nombre;

    public Nino(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static String transmitirPaso(String mensaje, String nombreNino, int paso) {
        char[] chars = mensaje.toCharArray();

        if (random.nextBoolean()) {
            int pos = (int)(Math.random() * chars.length);
            chars[pos] = letraAleatoria();
        }

        if (random.nextInt(4) == 0) {
            int pos = (int)(Math.random() * chars.length);
            chars[pos] = letraAleatoria();
        }

        String nuevo = new String(chars);
        System.out.println("Paso " + paso + " - " + nombreNino + " escribió: " + nuevo);
        return nuevo;
    }

    private static char letraAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx = (int)(Math.random() * letras.length());
        return letras.charAt(idx);
    }
}

