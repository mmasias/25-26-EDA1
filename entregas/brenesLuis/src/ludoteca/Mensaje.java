import java.util.Random;

public class Mensaje {
    private String texto;
    private static Random random = new Random();

    public Mensaje(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public Mensaje copiarConErrores() {
        char[] letras = texto.toCharArray();
        int cambios = random.nextInt(3);
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(letras.length);
            char nuevaLetra;
            do {
                nuevaLetra = (char) ('A' + random.nextInt(26));
            } while (nuevaLetra == letras[pos]);
            letras[pos] = nuevaLetra;
        }
        return new Mensaje(new String(letras));
    }
}
