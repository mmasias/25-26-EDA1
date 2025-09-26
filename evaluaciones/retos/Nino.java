package evaluaciones.retos;

final class Nino {
    private static int contador = 1;
    private final int id;
    Nino() { id = contador++; }
    public int id() { return id; }
}
