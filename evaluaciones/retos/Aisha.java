package evaluaciones.retos;

final class Aisha {
    private final ColaNinos cola;
    public Aisha(ColaNinos cola) { this.cola = cola; }
    public void recibir(Nino n) { cola.entrar(n); }
}