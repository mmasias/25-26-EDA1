public class Mundo {

        Tiempo tiempo;
        Ludoteca ludoteca;

        public Mundo() {
                tiempo = new Tiempo();
                ludoteca = new Ludoteca();
        }

        public static void main(String[] args) {
                Mundo mundo = new Mundo();
                mundo.iniciar();
                mundo.actualizar();
        }

        private void actualizar() {
                if (ludoteca.estaAbierta(tiempo)) {
                        Niño nuevoNiño = crearNiño();
                        ludoteca.recibir(nuevoNiño);
                        ludoteca.actualizar();
                }
                tiempo.siguiente();
                if (tiempo.getHora() < 24) actualizar();
        }

        private void iniciar() {
                tiempo.iniciar();
                ludoteca.abrir();
        }

        private Niño crearNiño() {
                return new Niño(darNombreNiño(), darEdadNiño());
        }

        private int darEdadNiño() {
                int EDAD_MAXIMA = 14;
                return (int) (Math.random() * EDAD_MAXIMA) + 1;
        }

        private String darNombreNiño() {
                int idAleatorio = (int) (Math.random() * 1000);
                String[] nombres = { "Ana", "Luis", "María", "Carlos", "Sofía", "Javier", "Lucía", "Miguel", "Elena",
                                "David" };
                return nombres[idAleatorio % nombres.length];
        }
}