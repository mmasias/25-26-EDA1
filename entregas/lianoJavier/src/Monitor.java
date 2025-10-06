
public class Monitor {
        
        String nombre;
        Niño primerNiño;
        int numeroDeNiñosALaCola;
        Juego juego;

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

}
