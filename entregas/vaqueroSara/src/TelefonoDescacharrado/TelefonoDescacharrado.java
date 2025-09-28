
public class TelefonoDescacharrado {

    private int tiempo = 0;
    private boolean juegoIniciado = false;
    private String mensajeOriginal;
    private String mensajeFinal;

    private Monitor lydia = new Monitor("Lydia");
    private Monitor aisha = new Monitor("Aisha");
    private Cola ninosParticipando = new Cola();

    public void lleganNinos() {
        int llegadas = 0;

        if (tiempo < 10) {
            llegadas = (int) (Math.random() * 3);
        } else if (tiempo < 30) {
            llegadas = (Math.random() < 0.5) ? 1 : 0;
        }

        for (int i = 0; i < llegadas; i++) {
            Nino nuevo = new Nino("Niño_" + tiempo + "_" + i);
            if (!juegoIniciado) {
                ninosParticipando.add(nuevo);
            } else {
                lydia.recibirNino(nuevo);
            }
        }

        if (ninosParticipando.size() >= 5 && !juegoIniciado) {
            juegoIniciado = true;
        }

        tiempo++;
    }

    public void prepararNinos() {
        aisha.limpiarPizarras();
        for (int i = 0; i < ninosParticipando.size(); i++) {
            ninosParticipando.get(i).borrarPizarrin();
        }
    }

    public void jugar() {
        mensajeOriginal = "Desarrollo";
        String mensaje = mensajeOriginal;
        System.out.println("Mensaje inicial: " + mensajeOriginal);

        for (int i = 0; i < ninosParticipando.size(); i++) {
            mensaje = ninosParticipando.get(i).escribirMensaje(mensaje);
        }

        mensajeFinal = mensaje;
        juegoIniciado = false;
    }

    public void resultados() {
        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println("Mensaje final: " + mensajeFinal);
    }

    public void pasarNinosDeLydia() {
        while (lydia.tieneNinos()) {
            ninosParticipando.add(lydia.getCola().remove());
        }
    }

    public int getTiempo() {
        return tiempo;
    }

    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }

    public void setJuegoIniciado(boolean b) {
        juegoIniciado = b;
    }

    public int getNumNinos() {
        return ninosParticipando.size();
    }
}
