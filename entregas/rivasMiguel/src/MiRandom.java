public class MiRandom {
    private static final long MODULO = 2147483647L;
    private static final long MULTIPLICADOR = 48271L;
    
    private static final double PRECISION = 10000.0;

    private long seed;

    public MiRandom() {
        this.seed = System.currentTimeMillis() % MODULO;
    }

    public int nextInt(int max) {
        if (max <= 0) {
            return 0;
        }

        this.seed = (this.seed * MULTIPLICADOR) % MODULO;
        
        return (int) (this.seed % max);
    }

    public double nextDouble() {
        this.seed = (this.seed * MULTIPLICADOR) % MODULO;
        
        return (double) (this.seed % PRECISION) / PRECISION;
    }
}