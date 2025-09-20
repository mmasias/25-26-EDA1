
public class TelefonoEscacharrado {

    int tiempo = 0;
    int numNinos = 0;
    boolean juegoIniciado = false;
    Nino[] ninosParticipando = new Nino[100];
    Profesora profesora = new Profesora();
    String mensajeFinal;

    public void lleganNinos() {
        int llegadas;

        if (tiempo < 10) {
            llegadas = (int) (Math.random() * 3);
            System.out.println("Llegan " + llegadas + " niños. La profe Lydia les saluda");
        } else if (tiempo < 20) {
            llegadas = (Math.random() < 0.5) ? 1 : 0;
            System.out.println("Llegan " + llegadas + " niños. La profe Lydia les saluda");
        } else {
            llegadas = 0;
        }

        for (int i = 0; i < llegadas; i++) {
            if (numNinos < ninosParticipando.length) {
                ninosParticipando[numNinos] = new Nino();
                numNinos++;
            }
        }

        if (numNinos > 5 && !juegoIniciado) {
            juegoIniciado = true;
            // quitar prepararNinos() de aquí
        }

        tiempo++;
    }

    public void prepararNinos() {
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].hacerFila();
            ninosParticipando[i].borrarPizarrin();
        }
    }

    public void jugar() {
        profesora.setMensajeOriginal();
        String mensaje = profesora.mensajeOriginal;
        for (int i = 0; i < numNinos; i++) {
            mensaje = ninosParticipando[i].escribirMensaje(mensaje);
            tiempo++; // Cada niño tarda 1 minuto
        }
        mensajeFinal = mensaje;
        System.out.println("Mensaje final: " + mensajeFinal);
        tiempo++; // El último niño escribe en la pizarra (1 minuto más)
    }

    public void getResultados() {
        System.out.println("Mensaje original: " + profesora.mensajeOriginal);
        System.out.println("Mensaje final: " + mensajeFinal);
    }
}
