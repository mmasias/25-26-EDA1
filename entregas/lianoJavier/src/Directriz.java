
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imprimirEstado'");
    }

    public String inventarPalabra() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inventarPalabra'");
    }

    public void enseñarPizarrin(Object posicion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enseñarPizarrin'");
    }

    public void escribirEnPizarrin(String palabraSecreta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escribirEnPizarrin'");
    }

}
