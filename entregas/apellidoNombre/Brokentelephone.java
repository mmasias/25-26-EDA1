public class BrokenTelephone {
    private static final int TOTAL_MINUTES = 120;
    private static final int FIRST_PHASE = 10;
    private static final int SECOND_PHASE_START = 10;
    private static final int SECOND_PHASE_DURATION = 20;
    private static final int MESSAGE_LENGTH = 10;
    private static final double[] CHANGE_PROBS = {0.7, 0.25, 0.05};

    private long seed;
    private int minute = 0;
    private int queueSize = 0;
    private int waiting = 0;
    private boolean inGame = false;
    private int gameEnd = -1;

    private Record[] games = new Record[200];
    private int gameCount = 0;

    public BrokenTelephone(long seed) {
        if (seed == -1) seed = System.currentTimeMillis();
        this.seed = seed;
    }

    private int nextInt(int n) {
        seed = (seed * 1664525 + 1013904223) & 0xFFFFFFFFL;
        return (int)((seed >>> 16) % n);
    }

    private double nextDouble() {
        return (nextInt(10000)) / 10000.0;
    }

    public static void main(String[] args) {
        long seed = args.length > 0 ? Long.parseLong(args[0]) : -1;
        new BrokenTelephone(seed).run();
    }

    private void run() {
        while (true) {
            int arrivals = arrivals(minute);
            if (arrivals > 0) {
                if (inGame) waiting += arrivals;
                else queueSize += arrivals;
            }
            if (!inGame && queueSize > 5) startGame();
            if (inGame && minute == gameEnd) endGame();
            minute++;
            if (minute >= TOTAL_MINUTES && !inGame && waiting == 0 && queueSize <= 5 && !canArrive(minute)) break;
        }
        printGames();
    }

    private void startGame() {
        int n = queueSize;
        int duration = n + 2;
        String start = message();
        String end = pass(start, n);
        games[gameCount++] = new Record(minute, n, duration, start, end);
        inGame = true;
        gameEnd = minute + duration - 1;
        queueSize = 0;
    }

    private void endGame() {
        inGame = false;
        queueSize += waiting;
        waiting = 0;
    }

    private int arrivals(int m) {
        if (m < FIRST_PHASE) return nextInt(3);
        if (m >= SECOND_PHASE_START && m < SECOND_PHASE_START + SECOND_PHASE_DURATION)
            return (m - SECOND_PHASE_START) % 3 == 0 && nextDouble() < 0.5 ? 1 : 0;
        return 0;
    }

    private boolean canArrive(int m) {
        return m <= SECOND_PHASE_START + SECOND_PHASE_DURATION - 1;
    }

    private String message() {
        char[] msg = new char[MESSAGE_LENGTH];
        for (int i = 0; i < MESSAGE_LENGTH; i++) {
            msg[i] = (char) ('A' + nextInt(26));
        }
        return new String(msg);
    }

    private String pass(String msg, int n) {
        String cur = msg;
        for (int i = 0; i < n; i++) cur = copy(cur);
        return cur;
    }

    private String copy(String s) {
        int c = changes();
        if (c == 0) return s;
        char[] a = s.toCharArray();
        boolean[] used = new boolean[a.length];
        for (int i = 0; i < c; i++) {
            int p;
            do p = nextInt(a.length); while (used[p]);
            used[p] = true;
            a[p] = (char) ('A' + nextInt(26));
        }
        return new String(a);
    }

    private int changes() {
        double r = nextDouble();
        if (r < CHANGE_PROBS[0]) return 0;
        r -= CHANGE_PROBS[0];
        if (r < CHANGE_PROBS[1]) return 1;
        return 2;
    }

    private void printGames() {
        for (int i = 0; i < gameCount; i++) {
            Record g = games[i];
            int h = dist(g.start, g.end);
            System.out.printf("Game %d: start=%d, size=%d, duration=%d\n", i + 1, g.startMin, g.size, g.duration);
            System.out.println("  start: " + g.start);
            System.out.println("  end  : " + g.end);
            System.out.println("  errors: " + h);
        }
    }

    private int dist(String a, String b) {
        int n = Math.min(a.length(), b.length());
        int d = 0;
        for (int i = 0; i < n; i++) if (a.charAt(i) != b.charAt(i)) d++;
        return d + Math.abs(a.length() - b.length());
    }

    private static class Record {
        int startMin, size, duration;
        String start, end;
        Record(int s, int sz, int d, String st, String e) { startMin = s; size = sz; duration = d; start = st; end = e; }
    }
}
