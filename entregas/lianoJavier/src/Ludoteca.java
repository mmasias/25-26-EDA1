public class Ludoteca {

	private Recepcionista lydia;
	private Directriz aisha;

	public Ludoteca() {
		this.lydia = new Recepcionista("Lydia");
		this.aisha = new Directriz("Aisha");
	}

	public void recibir(Niño niño) {
		lydia.recibir(niño);
	}

	public void actualizar() {
		if (!aisha.estaCompleta())
			aisha.pideNiño(lydia);
		else if (!aisha.estaJugando())
			aisha.juega();
		else
			aisha.siguienteRonda();
		if (aisha.juegoTerminado()) {
			while (aisha.getCola().hayNiños()) {
				Niño niño = aisha.getCola().sacar();
				lydia.recibir(niño);
			}
		}
	}

	public void imprimirEstado() {
		lydia.imprimirEstado();
		aisha.imprimirEstado();
	}

}
