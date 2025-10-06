import java.util.Random;

public class Mensaje {
    private final char[] letras;

    public Mensaje(String texto) {
        this.letras = new char[10];
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            if (i < texto.length()) {
                letras[i] = Character.toUpperCase(texto.charAt(i));
            } else {
                // Alterna consonante/vocal
                if (i % 2 == 0)
                    letras[i] = consonantes.charAt(rand.nextInt(consonantes.length()));
                else
                    letras[i] = vocales.charAt(rand.nextInt(vocales.length()));
            }
        }
    }

    public Mensaje(char[] letras) {
        this.letras = new char[10];
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            if (i < letras.length) {
                this.letras[i] = Character.toUpperCase(letras[i]);
            } else {
                if (i % 2 == 0)
                    this.letras[i] = consonantes.charAt(rand.nextInt(consonantes.length()));
                else
                    this.letras[i] = vocales.charAt(rand.nextInt(vocales.length()));
            }
        }
    }

    public static Mensaje mensajeAleatorio(Random mensajeAleatorio) {
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
        char[] letras = new char[10];

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                letras[i] = consonantes.charAt(mensajeAleatorio.nextInt(consonantes.length()));
            else
                letras[i] = vocales.charAt(mensajeAleatorio.nextInt(vocales.length()));
        }
        return new Mensaje(letras);
    }

    public Mensaje copiar() {
        char[] copia = new char[10];
        for (int i = 0; i < 10; i++)
            copia[i] = letras[i];
        return new Mensaje(copia);
    }

    public void cambiarUnaLetra(Random mensajeAleatorio) {
        int posicion = mensajeAleatorio.nextInt(10);
        char original = letras[posicion], nueva;
        do { 
            nueva = (char) ('A' + mensajeAleatorio.nextInt(26)); 
        } while (nueva == original);
        letras[posicion] = nueva;
    }

    public void cambiarDosLetras(Random mensajeAleatorio) {
        int posicion1 = mensajeAleatorio.nextInt(10), posicion2;
        do { posicion2 = mensajeAleatorio.nextInt(10); } while (posicion2 == posicion1);

        char old1 = letras[posicion1], old2 = letras[posicion2];
        char new1, new2;

        do { new1 = (char) ('A' + mensajeAleatorio.nextInt(26)); } while (new1 == old1);
        do { new2 = (char) ('A' + mensajeAleatorio.nextInt(26)); } while (new2 == old2);

        letras[posicion1] = new1;
        letras[posicion2] = new2;
    }

    public int letrasErroneas(Mensaje otraLetra) {
        int count = 0;
        for (int i = 0; i < 10; i++)
            if (letras[i] != otraLetra.letras[i]) count++;
        return count;
    }

    @Override
    public String toString() {
        return new String(letras); 
    }
}
