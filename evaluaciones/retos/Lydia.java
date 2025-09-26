package evaluaciones.retos;

final class Lydia {
    private final java.util.Queue<Nino> sentados = new java.util.ArrayDeque<>();
    public void recibir(Nino n) { sentados.add(n); }
    public void vaciarHacia(ColaNinos cola) {
        while (!sentados.isEmpty()) cola.entrar(sentados.remove());
    }
}
