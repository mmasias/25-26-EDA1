public class TelefonoEscacharradoClient {
    public static void main(String[] args) {
        TelefonoEscacharrado telefono = new TelefonoEscacharrado();

        while (telefono.tiempo < 120) {
            telefono.lleganNinos();

            while (telefono.numNinos > 5 && telefono.juegoIniciado && telefono.tiempo < 120) {
                telefono.prepararNinos();
                telefono.jugar();
                telefono.getResultados();
                telefono.juegoIniciado = false;
            }
        }
    }
}