
public class Cocinero {

    private Pedido actual = null;
    private final EstructuraPedidos estructura;

    public Cocinero(EstructuraPedidos estructura) {
        this.estructura = estructura;
    }

    public void tomarSiguienteSiNecesario(int minuto) {
        if (actual == null && !estructura.isEmpty()) {
            actual = estructura.extraerMinimo();
        }
    }

    public Pedido procesarMinuto() {
        if (actual == null) {
            return null;
        }
        actual.decrementarMinuto();
        if (actual.getTiempoRestante() == 0) {
            Pedido terminado = actual;
            actual = null;
            return terminado;
        }
        return null;
    }

    public boolean estaOcupado() {
        return actual != null;
    }

    public Pedido getActual() {
        return actual;
    }
}
