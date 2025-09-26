package evaluaciones.retos;

final class Llegadas {
    private final Reloj reloj;
    private final java.util.Random rng = new java.util.Random(0);

    Llegadas(Reloj reloj) { this.reloj = reloj; }

    int generar(int minuto) {
        if (minuto < 10) return rng.nextInt(3);
        if (minuto < 30) {
            if ((minuto - 10) % 3 == 0) return rng.nextBoolean() ? 1 : 0;
            return 0;
        }
        return 0;
    }
}
