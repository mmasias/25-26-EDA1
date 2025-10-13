
public class Directriz extends Monitor {

    private Partida partida;
    private Pizarra pizarrin;

    public Directriz(String nombre) {
        super(nombre);
        partida = new Partida(this, getCola());
    }

    public boolean estaJugando() {
        return !partida.isTerminada();
    }

    public boolean juegoTerminado() {
    	return partida.isTerminada();
    }
   
    public void reset() {
    	partida.reset();
    }

    public void juega() {
    	Console.imprimir("Iniciando juego en Aisha");
        Console.imprimirLinea();
    	this.coger(new Pizarra());
    	darPizarrines();
   
    	partida.iniciar();
    	Console.imprimir("Juego iniciado, jugando=" + !partida.isTerminada());
        Console.imprimirLinea();
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
    	Console.imprimir("Avanzando ronda, terminada antes=" + partida.isTerminada());
        Console.imprimirLinea();
    	partida.siguienteRonda();
    	if (partida.isTerminada()) {
    		Console.imprimir("Juego terminado");
            Console.imprimirLinea();
    	}
    }

    protected void imprimirEstado() {
        Console.imprimir("Directriz " + nombre + " con " + colaNiños.getCantidad() + " niños en cola.");
        Console.imprimirLinea();
        for (Niño niño : colaNiños.toArray()) {
            Console.imprimir(" - " + niño.getNombre());
            Console.imprimirLinea();
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
    	Console.imprimir("Directriz pideNiño: Lydia tiene " + otroMonitor.getCola().getCantidad() + ", Aisha tiene " + colaNiños.getCantidad());
    	Console.imprimirLinea();
    	if (otroMonitor.getCola().hayNiños() && colaNiños.getCantidad() < partida.getMaximoJugadores()) {
    		Niño niño = otroMonitor.getCola().sacar();
    		this.colaNiños.poner(niño);
    		Console.imprimir("Niño transferido a Aisha: " + niño.getNombre());
    		Console.imprimirLinea();
    	} else {
    		Console.imprimir("No se transfiere: Lydia vacía o Aisha completa");
    		Console.imprimirLinea();
    	}
    }

    public boolean estaCompleta() {
        return colaNiños.getCantidad() == partida.getMaximoJugadores();
    }

}
