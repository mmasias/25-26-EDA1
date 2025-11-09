import java.util.Random;

public class Niño {
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RND = new Random();
    private final String nombre;
    private Pizarra pizarrin;

    public Niño(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }

    public void recibirPizarrin(Pizarra p) { this.pizarrin = p; }
    public void limpiarPizarrin() { if (pizarrin != null) pizarrin.limpiar(); }

    public void recibirMensaje(String original) {
        int errores = 1 + RND.nextInt(2);
        char[] chars = original.toCharArray();
        for (int i = 0; i < errores && chars.length > 0; i++) {
            int idx = RND.nextInt(chars.length);
            char c = ALFABETO.charAt(RND.nextInt(ALFABETO.length()));
            chars[idx] = c;
        }
        pizarrin.escribirMensaje(new String(chars));
    }

    public String mostrarMensaje() { return (pizarrin == null) ? "" : pizarrin.leerMensaje(); }
}
