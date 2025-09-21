public class Juego {

    private boolean iniciado = false;
    private int posicion = -1;
    private int numeroParticipantes = 0;

    public void iniciar(int numeroParticipantes) {
        this.iniciado = true;
        this.posicion = -1; 
        this.numeroParticipantes = numeroParticipantes;
        System.out.println("Juego iniciado con " + numeroParticipantes + " participantes");
    }

    public boolean estaIniciado() { return iniciado; }

    public int getPosicion() { return posicion; }

    public void siguientePaso() { posicion++; }

    public boolean esUltimoPaso() { return iniciado && posicion == numeroParticipantes - 1; }

    public void terminar() {
        iniciado = false;
        posicion = -1;
        numeroParticipantes = 0;
    }
}
