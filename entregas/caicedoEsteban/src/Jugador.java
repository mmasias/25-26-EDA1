import java.util.Random;

public class Jugador {
    private final String nombre;
    private String pizarra;
    private final Random random;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.pizarra = "";
        this.random = new Random();
    }

    public String getNombre() {
        return nombre;
    }

    public void limpiarPizarra() {
        pizarra = "";
    }

    public String escribirEnPizarra(String mensaje) {
        if (mensaje.isEmpty()) {
            return mensaje;
        }

        int posicion = random.nextInt(mensaje.length());
        char nuevaLetra = (char) ('A' + random.nextInt(26));

        StringBuilder sb = new StringBuilder(mensaje);
        sb.setCharAt(posicion, nuevaLetra);

        pizarra = sb.toString();
        return pizarra;
    }

    @Override
    public String toString() {
        return nombre + " escribió: " + pizarra;
    }
}
