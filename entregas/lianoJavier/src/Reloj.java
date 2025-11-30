public class Reloj {
        private static final int DURACION_JORNADA = 720;
        private int minutoActual;

        public Reloj() {
                minutoActual = 0;
        }

        public void avanzarMinuto() {
                minutoActual++;
        }

        public int obtenerMinutoActual() {
                return minutoActual;
        }

        public boolean enHorario() {
                return minutoActual <= DURACION_JORNADA;
        }

        public boolean esFinDeJornada() {
                return minutoActual >= DURACION_JORNADA;
        }
}
