
public class Cocinero {

    private Pedido pedidoActual;
    private int atendidos;

    public Cocinero() {
        this.pedidoActual = null;
        this.atendidos = 0;
    }

    public boolean estaLibre() {
        return pedidoActual == null;
    }

    public void recibirPedido(Pedido p) {
        this.pedidoActual = p;
    }

    public void trabajar() {
        if (pedidoActual != null) {
            pedidoActual.cocinar();

            if (pedidoActual.estaTerminado()) {
                System.out.println("   *** PLATO TERMINADO: " + pedidoActual.getTipo());
                this.atendidos++;
                this.pedidoActual = null;
            }
        }
    }

    public int getAtendidos() {
        return atendidos;
    }
}
