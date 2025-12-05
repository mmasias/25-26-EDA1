public class Random {
    private final Random random;

    public Random(long semilla) {
        this.random = new Random(semilla);
    }

    public boolean probabilidad(double p) {
        assert p >= 0.0 && p <= 1.0 : "Probabilidad fuera de rango";
        return random.nextDouble() < p;
    }

    public int siguienteEntero(int min, int max) {
        assert min <= max : "Rango inválido";
        return min + random.nextInt(max - min + 1);
    }
}