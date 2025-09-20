public class TelefonoEscacharrado {

    int tiempo = 0;
    int ninos = 0;
    boolean juegoIniciado;

    public void lleganNinos() {
        if (tiempo < 10) {
            int llegadas = (int) (Math.random() * 3);
        } else if (tiempo < 20) {
            int llegadas = (Math.random() < 0.5) ? 1 : 0;
        } else {
            int llegadas = 0;
        }

    }

    if (llegadas > 5 && !juegoIniciado) {
        ninos += llegadas;
        juegoIniciado = true;
        prepararNinos();
    }

    tiempo++:
}
