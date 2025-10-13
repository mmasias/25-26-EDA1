
public class Cola {

        Niño[] niños;

        public Cola() {
                this.niños = new Niño[0];
        }

        public void poner(Niño niño) {
                Niño[] nuevosNiños = new Niño[niños.length + 1];
                System.arraycopy(niños, 0, nuevosNiños, 0, niños.length);
                nuevosNiños[nuevosNiños.length - 1] = niño;
                this.niños = nuevosNiños;
        }

        public Niño sacar() {
                Niño niñoSacado = niños[0];
                Niño[] nuevosNiños = new Niño[niños.length - 1];
                System.arraycopy(niños, 1, nuevosNiños, 0, nuevosNiños.length);
                this.niños = nuevosNiños;
                return niñoSacado;
        }

        public boolean hayNiños() {
                return niños.length > 0;
        }

        public Niño[] toArray() {
                return niños;
        }

        public Niño getPosicion(int posicion) {
                return niños[posicion - 1];
        }

        public int getCantidad() {
                return niños.length;
        }

}
