public class Cocinero {
    private Pedido actual;

    public boolean libre() {
        return actual == null;
    }

    public void asignar(Pedido p) {
        this.actual = p;
    }

    public void trabajar() {
        if (actual != null) {
            actual.restarMinuto();
            if (actual.terminado()) actual = null;
        }
    }

    public Pedido getPedido() {
        return actual;
    }
}