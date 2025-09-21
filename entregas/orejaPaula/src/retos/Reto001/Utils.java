import java.util.Random;

public class Utils {
    public static Mensaje generarMensajeAleatorio(int longitud) {
        Random random = new Random();
        char[] chars = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            chars[i] = (char) ('A' + random.nextInt(26));
        }
        return new Mensaje(new String(chars));
    }
}

