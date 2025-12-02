public class TipoPlato {

    public static final TipoPlato BEBIDA = new TipoPlato("BEBIDA", 1, 2);
    public static final TipoPlato CAFE = new TipoPlato("CAFE", 1, 3);
    public static final TipoPlato COLACAO = new TipoPlato("COLACAO", 2, 4);
    public static final TipoPlato BOCADILLO = new TipoPlato("BOCADILLO", 4, 8);
    public static final TipoPlato ENSALADA = new TipoPlato("ENSALADA", 3, 6);

    private static final TipoPlato[] TODOS_LOS_TIPOS = {
            BEBIDA, CAFE, COLACAO, BOCADILLO, ENSALADA
    };

    private String nombreTipo;
    private int duracionMinima;
    private int duracionMaxima;

    private TipoPlato(String nombreTipo, int duracionMinima, int duracionMaxima) {
        this.nombreTipo = nombreTipo;
        this.duracionMinima = duracionMinima;
        this.duracionMaxima = duracionMaxima;
    }

    public String nombreTipo() {
        return nombreTipo;
    }

    public int duracionMinima() {
        return duracionMinima;
    }

    public int duracionMaxima() {
        return duracionMaxima;
    }

    public int duracionAleatoria() {
        int rango = duracionMaxima - duracionMinima + 1;
        return duracionMinima + (int) (Math.random() * rango);
    }

    public static TipoPlato elegirTipoAleatorio() {
        int indiceAleatorio = (int) (Math.random() * TODOS_LOS_TIPOS.length);
        return TODOS_LOS_TIPOS[indiceAleatorio];
    }
}
