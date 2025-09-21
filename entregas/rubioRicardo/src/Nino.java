import java.util.Random;

public class Nino {

    private static final double PROBABILIDAD_ERROR = 0.3;
    private static final int MIN_CAMBIOS = 1;
    private static final int MAX_CAMBIOS = 2;
    private static final String LETRAS_DISPONIBLES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String nombre;
    private String pizarrin;
    private final int minutoLlegada;
    private final Random generadorAleatorio;

    public Nino(String nombre, int minutoLlegada) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Niño" : nombre.trim();
        this.minutoLlegada = minutoLlegada;
        this.pizarrin = "";
        this.generadorAleatorio = new Random();
    }

    
    public String recibirMensaje(String mensaje) {
        limpiarPizarrin();
        if (mensaje == null || mensaje.isEmpty()) {
            this.pizarrin = "";
            return this.pizarrin;
        }
        this.pizarrin = aplicarDeformacionSiCorresponde(mensaje);
        return this.pizarrin;
    }

    private String aplicarDeformacionSiCorresponde(String mensajeOriginal) {
        if (generadorAleatorio.nextDouble() >= PROBABILIDAD_ERROR) {
            return mensajeOriginal;
        }
        char[] caracteres = mensajeOriginal.toCharArray();
        int numeroCambios = generadorAleatorio.nextInt(MAX_CAMBIOS - MIN_CAMBIOS + 1) + MIN_CAMBIOS;
        for (int i = 0; i < numeroCambios && caracteres.length > 0; i++) {
            int posicion = generadorAleatorio.nextInt(caracteres.length);
            char nuevaLetra = LETRAS_DISPONIBLES.charAt(generadorAleatorio.nextInt(LETRAS_DISPONIBLES.length()));
            caracteres[posicion] = nuevaLetra;
        }
        return new String(caracteres);
    }

    public String mostrarPizarrin() {
        return pizarrin;
    }

    public void limpiarPizarrin() {
        this.pizarrin = "";
    }

    public String getNombre() { return nombre; }

    public int getMinutoLlegada() { return minutoLlegada; }

    @Override
    public String toString() {
        return String.format("Niño %s (llegó en minuto %d)", nombre, minutoLlegada);
    }
}
