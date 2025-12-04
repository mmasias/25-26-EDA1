public class Cocinero {

    private ArbolPedidos arbol;
    private Registro registro;
    private Pedido actual;
    private int tiempoEsperaTotal;

    public Cocinero() {
        arbol = new ArbolPedidos();
        registro = new Registro();
        actual = null;
        tiempoEsperaTotal = 0;
    }

    public void nuevoPedido(Pedido p) {
        arbol.insertar(p);
    }

    public void procesarMinuto(int minuto) {

        if (actual == null) {
            if (!arbol.estaVacio()) {
                actual = arbol.extraerMinimo();
                System.out.println("[MINUTO " + minuto + "] Empieza: " + actual);
            }
        }

        if (actual != null) {
            actual.reducirTiempo();

            if (actual.getTiempoRestante() == 0) {
                System.out.println("[MINUTO " + minuto + "] Terminado: " + actual.getNombre());
                registro.registrar(actual);
                tiempoEsperaTotal += (minuto - actual.getMomentoLlegada());
                actual = null;
            }
        }
    }

    public void resumenFinal() {
        registro.mostrarResumen(
                arbol.estaVacio() ? 0 : 1, 
                arbol.getComparaciones(),
                tiempoEsperaTotal
        );
    }

    public int pedidosPendientes() {
        return arbol.estaVacio() ? 0 : 1;
    }
}
