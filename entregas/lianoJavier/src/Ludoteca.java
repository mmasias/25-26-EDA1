
public class Ludoteca {

        Monitor lydia;
        Monitor aisha;
        Monitor dalsy;

        public boolean estaAbierta(Tiempo tiempo) {
                final int HORA_APERTURA = 10, HORA_CIERRE = 12;
                return tiempo.getHora() >= HORA_APERTURA && tiempo.getHora() < HORA_CIERRE;
        }

        public void recibir(Niño niño) {
                lydia.recibe(niño);
        }

        public void actualizar() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
        }

        public void abrir() {
                lydia = new Monitor("Lydia");
                aisha = new Monitor("Aisha");
                dalsy = new Monitor("Dalsy");
        }

}
