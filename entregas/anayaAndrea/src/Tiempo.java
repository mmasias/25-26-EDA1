import java.util.Random;

public class Tiempo {
    public static String generarMensaje(Random random) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        return sb.toString();
    }

    public static String deformar(String mensaje, Random random) {
        char[] chars = mensaje.toCharArray();
        int cambios = random.nextInt(3); 
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(chars.length);
            chars[pos] = letras.charAt(random.nextInt(letras.length()));
        }
        return new String(chars);
    }
}
