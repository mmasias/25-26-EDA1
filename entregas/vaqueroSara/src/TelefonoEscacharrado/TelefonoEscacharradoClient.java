public class TelefonoEscacharradoClient {
    public static void main(String[] args) {
        TelefonoEscacharrado telefono = new TelefonoEscacharrado();

        for (int minuto = 0; minuto < 120; minuto++) {
            telefono.lleganNinos();

            if (telefono.numNinos > 5 && telefono.juegoIniciado) {
                telefono.prepararNinos();
                telefono.jugar();
                telefono.getResultados();
                telefono.juegoIniciado = false;
            }
        }
    }
}