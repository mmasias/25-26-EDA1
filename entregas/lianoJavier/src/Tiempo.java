
public class Tiempo {

        private int minutos;

        public void iniciar() {
                minutos = 0;
        }

        public void siguiente() {
                minutos++;
        }

        public int getHora() {
                return minutos / 60;
        }

        public int getMinuto() {
                return minutos % 60;
        }

        public String getTiempo() {
                return String.format("%02d:%02d", getHora(), getMinuto());
        }

        public void imprimir() {
                Console.imprimirln(getTiempo());
        }

}
