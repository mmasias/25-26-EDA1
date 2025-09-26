package evaluaciones.retos;

final class ColaNinos {
    private final java.util.Deque<Nino> cola = new java.util.ArrayDeque<>();
    private Juego juego = null;

    public void entrar(Nino n) { cola.addLast(n); }
    public int tamanio() { return cola.size(); }
    public java.util.List<Nino> extraerTodos() {
        final java.util.List<Nino> lista = new java.util.ArrayList<>(cola);
        cola.clear();
        return lista;
    }
    public void iniciarJuego(Juego j) { this.juego = j; }
    public void terminarJuego() { this.juego = null; }
    public boolean juegoEnCurso() { return juego != null; }
}