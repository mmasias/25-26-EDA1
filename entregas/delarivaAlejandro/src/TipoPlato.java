public class TipoPlato {

    private String nombrePlato;
    private int tiempoMinimo;
    private int tiempoMaximo;

    public static final TipoPlato BEBIDA     = new TipoPlato("Bebida",     1, 2);
    public static final TipoPlato CAFE       = new TipoPlato("Café",       2, 3);
    public static final TipoPlato COLACAO    = new TipoPlato("Colacao",    2, 4);
    public static final TipoPlato BOCADILLO  = new TipoPlato("Bocadillo",  3, 5);
    public static final TipoPlato ENSALADA   = new TipoPlato("Ensalada",   5, 8);

    private TipoPlato(String nombre, int minimo, int maximo) {
        this.nombrePlato = nombre;
        this.tiempoMinimo = minimo;
        this.tiempoMaximo = maximo;
    }

    public String nombrePlato() {
        return nombrePlato;
    }

    public int generarDuracionPreparacion() {
        int rango = tiempoMaximo - tiempoMinimo + 1;
        return tiempoMinimo + (int) (Math.random() * rango);
    }
}
