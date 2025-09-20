public class TelefonoEscacharradoClient {
    public static void main(String[] args) {
        TelefonoEscacharrado telefono = new TelefonoEscacharrado();

        for (int minuto = 0; minuto < 120; minuto++) {
            telefono.lleganNinos();

            // Si hay suficientes niños y no hay juego en curso, se juega
            if (telefono.numNinos > 5 && telefono.juegoIniciado) {
                telefono.prepararNinos(); // <-- Solo aquí, al empezar la partida
                telefono.jugar();
                telefono.getResultados();
                // Reiniciar para permitir otro juego si siguen llegando niños
                telefono.juegoIniciado = false;
            }
        }
    }
}