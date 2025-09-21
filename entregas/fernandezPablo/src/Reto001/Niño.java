import java.util.Random;

public class Niño {
    private String nombre;
    private String modo;
    private Random random;

    public Niño(String nombre, String modo) {
        this.nombre = nombre;
        this.modo = modo.toLowerCase();
        this.random = new Random();
    }

    public String getNombre() {
        return nombre;
    }

    public String transformar(String mensaje) {
        switch (modo) {
            case "quitarvocales":
                return mensaje.replaceAll("(?i)[aeiou]", "");
            case "sustituirsimbolos":
                return sustituirSimbolos(mensaje);
            case "eliminarpalabra":
                return eliminarPalabra(mensaje);
            default:
                return mensaje;
        }
    }

    private String sustituirSimbolos(String mensaje) {
        StringBuilder sb = new StringBuilder(mensaje);
        int numCambios = 1 + random.nextInt(3);
        for (int i = 0; i < numCambios && sb.length() > 0; i++) {
            int pos = random.nextInt(sb.length());
            char nuevo = (char)(33 + random.nextInt(15));
            sb.setCharAt(pos, nuevo);
        }
        return sb.toString();
    }

    private String eliminarPalabra(String mensaje) {
        String[] partes = mensaje.split(" ");
        if (partes.length <= 1) return mensaje;
        int idx = random.nextInt(partes.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            if (i == idx) continue;
            sb.append(partes[i]).append(" ");
        }
        return sb.toString().trim();
    }
}

