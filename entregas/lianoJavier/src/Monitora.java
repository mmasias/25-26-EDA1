
public class Monitora {

    private String nombre;

    private boolean jugando;
    private static final int NIÑOS_MINIMOS_PARA_JUGAR = 5;
    private String pizarrin;

    private Niño primerNiño;
    private int niñosEnLaCola;

    private boolean haJugado;

    public Monitora(String nombre) {
        this.nombre = nombre;
    }

    public void recibe(Niño niño) {
        niño.estaCon(this);
        if (tieneNiño()) {
            primerNiño = niño;
        }
        else
            primerNiño.recibe(niño);
        niñosEnLaCola++;
    }

    private boolean tieneNiño() {
        return primerNiño != null;
    }

    public boolean estaJugando() {
        return jugando;
    }

    public void pasarNiñosA(Monitora otroMonitor) {
        if (tieneNiño()) {
            otroMonitor.recibe(primerNiño);
            primerNiño = null;
        }
        niñosEnLaCola = 0;
    }

    public boolean puedeJugar() {
        return niñosEnLaCola >= NIÑOS_MINIMOS_PARA_JUGAR && !estaJugando();
    }

    public void juega() {
        if (puedeJugar()) {
            jugando = true;
            limpiaPizarra();
            mandarLimpiarPizarrines();
            escribe("LOSSECRETO");
        }
    }

    private void mandarLimpiarPizarrines() {
        pizarrin = "";
        primerNiño.limpiarPizarrin();
    }

    private void escribe(String mensaje) {
        pizarrin = mensaje;
    }

    private void limpiaPizarra() {
        Console.clear();
    }

    public void siguienteRonda() {
        if (!haJugado) {
            enseñar(pizarrin, primerNiño);
            haJugado = true;
        } else
            primerNiño.jugar();
    }

    private void enseñar(String pizarrin, Niño niño) {
        niño.lee(pizarrin);
    }

    public void terminarJuego() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'terminarJuego'");
    }

}
