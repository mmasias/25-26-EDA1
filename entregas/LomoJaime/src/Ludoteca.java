public class Ludoteca {
    private Lydia lydia = new Lydia();
    private Aisha aisha = new Aisha();
    private int tiempo = 0;
    private static final int DURACION = 120;

    public void iniciarSimulacion() {
        while (tiempo < DURACION) {
            
            lydia.recibirNiños(tiempo);

            while (!aisha.filaCompleta() && lydia.tieneEsperando()) {
                aisha.recibirChild(lydia.darChild());
            }

            if (aisha.filaCompleta()) {
                int duracionJuego = jugarRonda();
                tiempo += duracionJuego;
            } else {
                tiempo++;
            }
        }
    }

    private int jugarRonda() {
        System.out.println("\n--- Nueva ronda ---");

        aisha.limpiarPizarraDelSalon();
        aisha.limpiarPizarrines();

        String mensajeInicial = aisha.escribirMensajeInicial();
        System.out.println("Mensaje inicial: " + mensajeInicial);

        String mensajeFinal = aisha.pasarMensaje(mensajeInicial);

       
        aisha.escribirMensajeFinalEnSalon(mensajeFinal);

        int duracion = aisha.tamañoFila() + 2;

       
        aisha.recibirNuevosDeLydia(lydia);

        return duracion;
    }
}
