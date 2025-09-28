
public class Directriz extends Monitor {

    private Partida partida;

    public Directriz(String nombre) {
        super(nombre);
        partida = new Partida(getCola());
    }

    public boolean estaJugando() {
        return partida.isTerminada();
    }

    public void juega() {
        partida.iniciar();
    }

    public void siguienteRonda() {
        partida.siguienteRonda();
    }

    protected void imprimirEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imprimirEstado'");
    }

}
