
public class Partida {

        private Directriz directriz;
        private Cola jugadores;
        private int posicion = 1;
        private final int MAXIMO_JUGADORES = 5;
        private boolean jugando = false;

        public Partida(Directriz directriz, Cola cola) {
                this.directriz = directriz;
                this.jugadores = cola;
        }

        public void iniciar() {
                String palabraSecreta = directriz.inventarPalabra();
                directriz.escribirEnPizarrin(palabraSecreta);
                jugando = true;
                directriz.enseñarPizarrin(Cola.getPosicion(posicion));
                posicion++;
        }

        public void siguienteRonda() {
                if (posicion != jugadores.toArray().length) {
                        Cola.getPosicion(posicion).enseñarPizarrin(Cola.getPosicion(posicion + 1));
                        posicion++;
                } else {
                        Cola.getPosicion(posicion).escribirEnPizarra();
                        jugando = false;
                }
        }

        public boolean isTerminada() {
                return !jugando;
        }

        public Object getMaximoJugadores() {
                return MAXIMO_JUGADORES;
        }

}
