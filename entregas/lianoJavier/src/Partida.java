
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
                directriz.enseñarPizarrin(jugadores.getPosicion(posicion));
                posicion++;
        }

        public void siguienteRonda() {
                if (posicion != jugadores.toArray().length) {
                        jugadores.getPosicion(posicion).enseñarPizarrin(jugadores.getPosicion(posicion + 1));
                        posicion++;
                } else {
                        jugadores.getPosicion(posicion).escribirEnPizarra();
                        jugando = false;
                }
        }

        public boolean isTerminada() {
                return !jugando;
        }

        public int getMaximoJugadores() {
                return MAXIMO_JUGADORES;
        }

}
