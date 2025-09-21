public class Niño {
    private String nombre;
    private String pizarra;
    private final int NUMERO_LETRAS = 26;
    private final int CAMBIOS_MAXIMOS = 3;

    public Niño(String nombre) {
        this.nombre = nombre;
        this.pizarra = "";
    }

    public void limpiarPizarra() {
        pizarra = "";
    }

    public String escribirEnPizarra(String mensaje, java.util.Random random) {
        pizarra = mensaje;
        char[] chars = pizarra.toCharArray();
        int cambios = random.nextInt(CAMBIOS_MAXIMOS);
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(chars.length);
            char nueva = (char) ('A' + random.nextInt(NUMERO_LETRAS));
            chars[pos] = nueva;
        }
        pizarra = new String(chars);
        return pizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPizarra() {
        return pizarra;
    }
}
