
public enum TipoPlato {

    BEBIDA(1, 2),
    CAFE(1, 3),
    COLACAO(2, 4),
    BOCADILLO(4, 8),
    ENSALADA(3, 6);

    private int duracionMinima;
    private int duracionMaxima;

    TipoPlato(int duracionMinima, int duracionMaxima) {
        this.duracionMinima = duracionMinima;
        this.duracionMaxima = duracionMaxima;
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
        TipoPlato[] valores = values();
        int indiceAleatorio = (int) (Math.random() * valores.length);
        return valores[indiceAleatorio];
    }
}
