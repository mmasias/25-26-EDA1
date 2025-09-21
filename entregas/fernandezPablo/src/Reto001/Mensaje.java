import java.util.Random;

public class Mensaje {
    private String contenido;
    private static final String[] LISTA_PALABRAS = {
        "hola", "mundo", "telefono", "descacharrado", "java",
        "reto", "aprendizaje", "objetos", "cola", "niños"
    };
    private Random random;

    public Mensaje() {
        this.random = new Random();
        this.contenido = generarMensajePalabras(5);
    }

    public Mensaje(String contenido) {
        this.contenido = contenido;
        this.random = new Random();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    private String generarMensajePalabras(int cantidad) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            String palabra = LISTA_PALABRAS[random.nextInt(LISTA_PALABRAS.length)];
            sb.append(palabra);
            if (i < cantidad - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
