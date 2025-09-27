public class TelefonoDescacharrado {

    int tiempo = 0;
    int numNinos = 0;
    boolean juegoIniciado = false;
    Nino[] ninosParticipando = new Nino[100];
    Lydia lydia = new Lydia();
    Aisha aisha = new Aisha();
    String mensajeFinal;
    String mensajeOriginal;

    public void lleganNinos() {
        int llegadas = 0;

        if (tiempo < 10) {
            llegadas = (int) (Math.random() * 3);
        } else if (tiempo < 30) {
            llegadas = (Math.random() < 0.5) ? 1 : 0;
        }

        for (int i = 0; i < llegadas; i++) {
            Nino nuevo = new Nino();
            if (!juegoIniciado) {
                if (numNinos < ninosParticipando.length) {
                    ninosParticipando[numNinos++] = nuevo;
                }
            } else {
                lydia.recibirNino(nuevo);
            }
        }

        if (numNinos > 5 && !juegoIniciado) {
            juegoIniciado = true;
        }

        tiempo++;
    }

    public void prepararNinos() {
        aisha.limpiarPizarra();
        for (int i = 0; i < numNinos; i++) {
            ninosParticipando[i].hacerFila();
            ninosParticipando[i].borrarPizarrin();
        }
        tiempo++;
    }

    public void jugar() {
        mensajeOriginal = "Desarrollo";
        System.out.println("Mensaje inicial en el pizarrín: " + mensajeOriginal);
        String mensaje = mensajeOriginal;
        tiempo++;

        for (int i = 0; i < numNinos; i++) {
            mensaje = ninosParticipando[i].escribirMensaje(mensaje);
            tiempo++; 
        }

        mensajeFinal = mensaje;
        tiempo++; 
    }

    public void getResultados() {
        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println("Mensaje final en la pizarra: " + mensajeFinal);
    }

    public void pasarNinosDeLydia() {
        numNinos = lydia.pasarNinosACola(ninosParticipando, numNinos);
    }
}
