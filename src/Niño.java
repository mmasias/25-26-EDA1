public class Niño {
    private String nombre;
    private String pizarra;
    private static final int NUMERO_LETRAS = 26;
    private static final int CAMBIOS_MAXIMOS = 3;

    public Niño(String nombre) {
        this.nombre = nombre;
        this.pizarra = "";
    }

    public void limpiarPizarra() {
        this.pizarra = "";
    }

    public String escribirEnPizarra(String mensaje, java.util.Random random) {
        this.pizarra = mensaje;

        char[] chars = pizarra.toCharArray();
        int cambios = random.nextInt(CAMBIOS_MAXIMOS + 1); 
        for (int i = 0; i < cambios; i++) {
            int posicion = random.nextInt(chars.length);
            char nuevaLetra = (char) ('A' + random.nextInt(NUMERO_LETRAS));
            chars[posicion] = nuevaLetra;
        }
        this.pizarra = new String(chars);
        return this.pizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPizarra() {
        return pizarra;
    }
}
