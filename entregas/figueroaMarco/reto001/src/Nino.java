import java.util.Random;

public class Nino {
    
    private static final double PROBABILIDAD_ERROR = 0.3;
    private static final int MIN_CAMBIOS = 1;
    private static final int MAX_CAMBIOS = 2;
    private static final String LETRAS_DISPONIBLES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private final String nombre;
    private String pizarrin;
    private final int tiempoLlegada;
    private final Random random;
    
    public Nino(String nombre, int tiempoLlegada) {
        this.nombre = validarNombre(nombre);
        this.tiempoLlegada = tiempoLlegada;
        this.pizarrin = "";
        this.random = new Random();
    }
    
    public String recibirMensaje(String mensaje) {
        limpiarPizarrin();
        
        if (mensaje == null || mensaje.isEmpty()) {
            return "";
        }
        
        String mensajeDeformado = aplicarDeformacion(mensaje);
        this.pizarrin = mensajeDeformado;
        
        return this.pizarrin;
    }
    
    private String aplicarDeformacion(String mensajeOriginal) {
        if (random.nextDouble() >= PROBABILIDAD_ERROR) {
            return mensajeOriginal;
        }
        
        char[] caracteres = mensajeOriginal.toCharArray();
        int numeroCambios = random.nextInt(MAX_CAMBIOS - MIN_CAMBIOS + 1) + MIN_CAMBIOS;
        
        for (int i = 0; i < numeroCambios && caracteres.length > 0; i++) {
            int posicion = random.nextInt(caracteres.length);
            char nuevaLetra = LETRAS_DISPONIBLES.charAt(random.nextInt(LETRAS_DISPONIBLES.length()));
            caracteres[posicion] = nuevaLetra;
        }
        
        return new String(caracteres);
    }
    
    public String mostrarPizarrin() {
        return this.pizarrin;
    }
    
    public void limpiarPizarrin() {
        this.pizarrin = "";
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public int getTiempoLlegada() {
        return this.tiempoLlegada;
    }
    
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del niño no puede ser nulo o vacío");
        }
        return nombre.trim();
    }
    
    @Override
    public String toString() {
        return String.format("Niño %s (llegó en minuto %d)", nombre, tiempoLlegada);
    }
}