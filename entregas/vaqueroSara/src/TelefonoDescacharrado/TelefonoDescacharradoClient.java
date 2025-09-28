public class TelefonoDescacharradoClient {
    public static void main(String[] args) {
        TelefonoDescacharrado telefono = new TelefonoDescacharrado();

        while (telefono.getTiempo() < 120) {
            telefono.lleganNinos();

            while (telefono.getNumNinos() >= 5 && telefono.isJuegoIniciado()) {
                telefono.prepararNinos();
                telefono.jugar();
                telefono.resultados();
                telefono.setJuegoIniciado(false);
                telefono.pasarNinosDeLydia();
            }
        }
    }
}
