public class Ninio {
    private final int identificador;
    private final int minutoDeLlegada;

    public Ninio(int identificador, int minutoDeLlegada) {
        this.identificador = identificador;
        this.minutoDeLlegada = minutoDeLlegada;
    }

    public int obtenerIdentificador() {
        return identificador;
    }

    public int obtenerMinutoDeLlegada() {
        return minutoDeLlegada;
    }

    public String toString() {
        return "Niño#" + identificador + "(llegó:" + minutoDeLlegada + ")";
    }
}
