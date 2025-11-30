public class Random {

    private final Random rnd;

    public Random() {
        this.rnd = new Random();
    }

    public Random(long seed) {
        this.rnd = new Random(seed);
    }

    public boolean prob(double p) {
        return rnd.nextDouble() < p;
    }

    public int nextIntRange(int min, int max) {
        return min + rnd.nextInt(max - min + 1);
    }

    public int choice(int n) {
        return rnd.nextInt(n);
    }
}
