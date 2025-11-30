public enum Plato {
    BEBIDA("Bebida", 1, 2),
    CAFE("Café", 2, 3),
    COLACAO("Colacao", 2, 4),
    BOCADILLO("Bocadillo", 3, 5),
    ENSALADA("Ensalada", 5, 8);

    private final String nombre;
    private final int minTiempo;
    private final int maxTiempo;

    Plato(String nombre, int minTiempo, int maxTiempo) {
        this.nombre = nombre;
        this.minTiempo = minTiempo;
        this.maxTiempo = maxTiempo;
    }
}
