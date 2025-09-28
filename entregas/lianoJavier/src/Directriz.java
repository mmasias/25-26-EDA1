
public class Directriz extends Monitor {

    private Partida partida;
    private Pizarra pizarrin;

    public Directriz(String nombre) {
        super(nombre);
        partida = new Partida(this, getCola());
    }

    public boolean estaJugando() {
        return partida.isTerminada();
    }

    public void juega() {
        this.coger(new Pizarra());
        darPizarrines();

        partida.iniciar();
    }

    private void coger(Pizarra pizarra) {
        this.pizarrin = pizarra;
    }

    private void darPizarrines() {
        Niño[] niños = colaNiños.toArray();
        for (Niño niño : niños) {
            niño.coger(pizarrin);
        }
    }

    public void siguienteRonda() {
        partida.siguienteRonda();
    }

    protected void imprimirEstado() {
        Console.imprimir("Directriz " + nombre + " con " + colaNiños.getCantidad() + " niños en cola.");
        for (Niño niño : colaNiños.toArray()) {
            Console.imprimir(" - " + niño.getNombre());
        }
    }

    final static int LONGITUD_PALABRA = 10;

    public String inventarPalabra() {
        return "LOSSECRETO";
    }

    public void enseñarPizarrin(Niño niño) {
        niño.escribirEnPizarrin(niño.leer(pizarrin));
    }

    public void escribirEnPizarrin(String palabraSecreta) {
        pizarrin.escribir(palabraSecreta);
    }

    public void pideNiño(Monitor otroMonitor) {
        if (otroMonitor.getCola().hayNiños() && colaNiños.getCantidad() == partida.getMaximoJugadores()) {
            Niño niño = otroMonitor.getCola().sacar();
            this.colaNiños.poner(niño);
        }
    }

    public boolean estaCompleta() {
        return colaNiños.getCantidad() == partida.getMaximoJugadores();
    }

}
