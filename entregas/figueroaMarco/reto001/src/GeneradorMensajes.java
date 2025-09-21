import java.util.Random;

public class GeneradorMensajes {
    
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LONGITUD_MENSAJE = 10;
    private final Random random;
    
    public GeneradorMensajes() {
        this.random = new Random();
    }
    
    public String generarMensaje() {
        char[] mensaje = new char[LONGITUD_MENSAJE];
        
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            int indice = random.nextInt(LETRAS.length());
            mensaje[i] = LETRAS.charAt(indice);
        }
        
        return new String(mensaje);
    }
    
    public String generarMensajePalabra() {
        String[] palabras = {
            "HOLA", "CASA", "PERRO", "GATO", "AMOR",
            "FELIZ", "JUEGO", "NINO", "MAMA", "PAPA"
        };
        
        String palabra = palabras[random.nextInt(palabras.length)];
        
        if (palabra.length() == LONGITUD_MENSAJE) {
            return palabra;
        }
        
        char[] mensaje = new char[LONGITUD_MENSAJE];
        
        for (int i = 0; i < palabra.length(); i++) {
            mensaje[i] = palabra.charAt(i);
        }
        
        for (int i = palabra.length(); i < LONGITUD_MENSAJE; i++) {
            int indice = random.nextInt(LETRAS.length());
            mensaje[i] = LETRAS.charAt(indice);
        }
        
        return new String(mensaje);
    }
}
