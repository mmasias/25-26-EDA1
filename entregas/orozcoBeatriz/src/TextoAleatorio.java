import java.util.Random;

public class TextoAleatorio {
    private Random random = new Random();

    public String generar() {
        int longitud = 10;
        String resultado = "";
        for (int i = 0; i < longitud; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            resultado = resultado + letra;
        }
        return resultado;
    }
}