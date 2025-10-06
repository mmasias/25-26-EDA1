
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
                if (aisha.puedeJugar()) aisha.juega(); else if (!aisha.estaJugando()) lydia.daNiñoA(aisha);
        }

        public void abrir() {
                lydia = new Monitor("Lydia");
                aisha = new Monitor("Aisha");
                dalsy = new Monitor("Dalsy");
        }

}
