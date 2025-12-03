public class TipoPlato {

    public static final TipoPlato BEBIDA = new TipoPlato("BEBIDA", 1, 2);
    public static final TipoPlato CAFE = new TipoPlato("CAFE", 1, 3);
    public static final TipoPlato COLACAO = new TipoPlato("COLACAO", 2, 4);
    public static final TipoPlato BOCADILLO = new TipoPlato("BOCADILLO", 4, 8);
    public static final TipoPlato ENSALADA = new TipoPlato("ENSALADA", 3, 6);

    private static final TipoPlato[] TIPOS = {
            BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA
    };

    private String nombre;
    private int duracionMinima;
    private int duracionMaxima;

    private TipoPlato(String nombre, int duracionMinima, int duracionMaxima) {
        this.nombre = nombre;
        this.duracionMinima = duracionMinima;
        this.duracionMaxima = duracionMaxima;
    }

    public String nombre() {
        return nombre;
    }

    public int duracionAleatoria() {
        int rango = duracionMaxima - duracionMinima + 1;
        return duracionMinima + (int) (Math.random() * rango);
    }

    public static TipoPlato elegirTipoAleatorio() {
        int indice = (int) (Math.random() * TIPOS.length);
        return TIPOS[indice];
    }
}
