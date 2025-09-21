import java.util.Random;

public class GeneradorMensajes {

    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LONGITUD_MENSAJE = 10;
    private final Random generadorAleatorio;

    public GeneradorMensajes() {
        this.generadorAleatorio = new Random();
    }

   
    public String generarMensajeAleatorio() {
        char[] mensaje = new char[LONGITUD_MENSAJE];
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            int indice = generadorAleatorio.nextInt(LETRAS.length());
            mensaje[i] = LETRAS.charAt(indice);
        }
        return new String(mensaje);
    }

    public String generarMensajeDesdePalabra() {
        String[] palabras = {
            "HOLA", "CASA", "PERRO", "GATO", "AMOR",
            "FELIZ", "JUEGO", "NINO", "MAMA", "PAPA"
        };
        String palabra = palabras[generadorAleatorio.nextInt(palabras.length)];
        char[] mensaje = new char[LONGITUD_MENSAJE];
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            if (i < palabra.length()) mensaje[i] = Character.toUpperCase(palabra.charAt(i));
            else mensaje[i] = LETRAS.charAt(generadorAleatorio.nextInt(LETRAS.length()));
        }
        return new String(mensaje);
    }
}
