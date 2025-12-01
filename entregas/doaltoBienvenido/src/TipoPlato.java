import java.util.Random;

public class TipoPlato {
    private static final String BEBIDA = "Bebida";
    private static final String CAFE = "Cafe";
    private static final String COLACAO = "Colacao";
    private static final String BOCADILLO = "Bocadillo";
    private static final String ENSALADA = "Ensalada";
    
    private static final int NUMERO_TIPOS_PLATO = 5;
    private static final int INDICE_BEBIDA = 0;
    private static final int INDICE_CAFE = 1;
    private static final int INDICE_COLACAO = 2;
    private static final int INDICE_BOCADILLO = 3;

    private static final int MIN_TIEMPO_BEBIDA = 1;
    private static final int MAX_TIEMPO_BEBIDA = 2;
    private static final int MIN_TIEMPO_CAFE = 2;
    private static final int MAX_TIEMPO_CAFE = 3;
    private static final int MIN_TIEMPO_COLACAO = 2;
    private static final int MAX_TIEMPO_COLACAO = 4;
    private static final int MIN_TIEMPO_BOCADILLO = 3;
    private static final int MAX_TIEMPO_BOCADILLO = 5;
    private static final int MIN_TIEMPO_ENSALADA = 5;
    private static final int MAX_TIEMPO_ENSALADA = 8;

    public static String muestrearTipo() {
        Random random = new Random();
        int indiceTipoPlato = random.nextInt(NUMERO_TIPOS_PLATO);
        switch (indiceTipoPlato) {
            case INDICE_BEBIDA: return BEBIDA;
            case INDICE_CAFE: return CAFE;
            case INDICE_COLACAO: return COLACAO;
            case INDICE_BOCADILLO: return BOCADILLO;
            default: return ENSALADA;
        }
    }

    public static int generarTiempoParaTipo(String tipo) {
        Random random = new Random();
        switch (tipo) {
            case BEBIDA: return MIN_TIEMPO_BEBIDA + random.nextInt(MAX_TIEMPO_BEBIDA - MIN_TIEMPO_BEBIDA + 1);
            case CAFE: return MIN_TIEMPO_CAFE + random.nextInt(MAX_TIEMPO_CAFE - MIN_TIEMPO_CAFE + 1);
            case COLACAO: return MIN_TIEMPO_COLACAO + random.nextInt(MAX_TIEMPO_COLACAO - MIN_TIEMPO_COLACAO + 1);
            case BOCADILLO: return MIN_TIEMPO_BOCADILLO + random.nextInt(MAX_TIEMPO_BOCADILLO - MIN_TIEMPO_BOCADILLO + 1);
            default: return MIN_TIEMPO_ENSALADA + random.nextInt(MAX_TIEMPO_ENSALADA - MIN_TIEMPO_ENSALADA + 1);
        }
    }
}