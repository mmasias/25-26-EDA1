import java.util.Random;

public enum Plato {
    BEBIDA("Bebida", 1, 2),
    CAFE("Café", 2, 3),
    COLACAO("Colacao", 2, 4),
    BOCADILLO("Bocadillo", 3, 5),
    ENSALADA("Ensalada", 5, 8);

    private static final Random RANDOM = new Random();

    private final String nombre;
    private final int minTiempo;
    private final int maxTiempo;

    Plato(String nombre, int minTiempo, int maxTiempo) {
        this.nombre = nombre;
        this.minTiempo = minTiempo;
        this.maxTiempo = maxTiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinTiempo() {
        return minTiempo;
    }

    public int getMaxTiempo() {
        return maxTiempo;
    }

    public int tiempoAleatorio() {
        if (minTiempo == maxTiempo) {
            return minTiempo;
        }
        return minTiempo + RANDOM.nextInt(maxTiempo - minTiempo + 1);
    }

    public static Plato aleatorio() {
        Plato[] valores = values();
        int i = RANDOM.nextInt(valores.length);
        return valores[i];
    }
}




