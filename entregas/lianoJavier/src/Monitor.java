
public class Monitor {
        
        private static final int NIÑOS_NECESARIOS_PARA_JUGAR = 5;
        private String nombre;
        private Niño primerNiño;
        private int numeroDeNiñosALaCola;
        private Juego juego;

        public Monitor(String nombre) {
                this.nombre = nombre;
        }

        public void recibe(Niño niño) {
                ponerALaCola(niño);
                numeroDeNiñosALaCola++;
        }

        private void ponerALaCola(Niño niño) {
                if (primerNiño != null) primerNiño.recibe(niño); else primerNiño = niño;
        }

        public void daNiñoA(Monitor otroMonitor) {
                assert primerNiño != null : "No hay niños para dar";
                Niño niñoSiguiente = primerNiño.recibir();
                Niño niñoElegido = primerNiño;
                primerNiño = niñoSiguiente;
                otroMonitor.recibe(niñoElegido);
        }

        public boolean estaJugando() {
                return juego != null;
        }

        public boolean puedeJugar() {
                return numeroDeNiñosALaCola >= NIÑOS_NECESARIOS_PARA_JUGAR;
        }

        public void juega() {
                assert puedeJugar() : "No hay niños suficientes para jugar";
                assert !estaJugando() : "El monitor ya está jugando";
                juego = new Juego(primerNiño);
        }

}
