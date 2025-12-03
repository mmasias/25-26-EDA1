public enum TipoPlato {
    BEBIDA(1, 2, "Bebida"),
    CAFE(2, 3, "Café"),
    COLACAO(2, 4, "Colacao"),
    BOCADILLO(3, 5, "Bocadillo"),
    ENSALADA(5, 8, "Ensalada");

    private int tiempoMinimo;
    private int tiempoMaximo;
    private String nombrePlato;

    TipoPlato(int minimo, int maximo, String nombre) {
        this.tiempoMinimo = minimo;
        this.tiempoMaximo = maximo;
        this.nombrePlato = nombre;
    }

    public String nombrePlato() {
        return nombrePlato;
    }

    public int generarDuracionPreparacion() {
        int rango = tiempoMaximo - tiempoMinimo + 1;
        return tiempoMinimo + (int) (Math.random() * rango);
    }
}
