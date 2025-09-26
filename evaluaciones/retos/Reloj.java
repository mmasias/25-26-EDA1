package evaluaciones.retos;

final class Reloj {
    private int minuto = 0;
    private final int max;
    private final long semilla = 123456789L;

    Reloj(int max) { this.max = max; }
    boolean hayTiempo() { return minuto < max; }
    int actual() { return minuto; }
    void avanzar(int m) { minuto += m; if (minuto > max) minuto = max; }
    int total() { return max; }
    long semilla() { return semilla + minuto; }
}
