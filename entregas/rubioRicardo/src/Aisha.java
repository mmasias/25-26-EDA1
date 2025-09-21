public class Aisha {

   
    private final ColaCircular fila = new ColaCircular(100);
    private final GeneradorMensajes generadorMensajes = new GeneradorMensajes();
    private String palabraInicial = "";

    public ColaCircular getFila() { return fila; }


    public void pedirNino(Lydia lydia) {
        if (lydia.tieneEsperando()) {
            Nino n = lydia.darNino();
            if (n != null) {
                fila.encolar(n);
                System.out.println("Aisha agrega " + n.getNombre() + " a la fila");
            }
        }
    }

    public void limpiarPizarra(Pizarra pizarra) {
        pizarra.limpiar();
        System.out.println("Aisha limpia la pizarra");
    }

    public void pedirLimpiarPizarrines() {
        Nino[] todos = fila.copiarCola();
        for (Nino n : todos) {
            if (n != null) n.limpiarPizarrin();
        }
        System.out.println("Aisha pide a los niños que limpien sus pizarrines");
    }

    public void escribirPalabraInicial() {
        if (Math.random() < 0.5) palabraInicial = generadorMensajes.generarMensajeDesdePalabra();
        else palabraInicial = generadorMensajes.generarMensajeAleatorio();
        System.out.println("Aisha escribe la palabra: " + palabraInicial);
    }

    public void mostrarPizarrinAlPrimerNino(Nino primerNino) {
        if (primerNino == null) return;
        System.out.println("Aisha muestra palabra al primer niño: " + palabraInicial);
        primerNino.recibirMensaje(palabraInicial);
    }


    public Nino[] sentarNinosParaJuego() {
        return fila.copiarCola();
    }

    public void vaciarFila() { fila.vaciar(); }

    public void imprimirLista() {
        System.out.print("Aisha: ");
        fila.imprimirEstado();
    }
}
