
public class Niño {

        private String nombre;
        private int edad;
        private Niño siguienteNiño;

        public Niño(String nombre, int edad) {
                this.nombre = nombre;
                this.edad = edad;
        }

        public void recibe(Niño niño) {
                if (siguienteNiño != null) siguienteNiño.recibe(niño); else siguienteNiño = niño;
        }

        public Niño pedirNiñoSiguiente() {
                return siguienteNiño;
        }

}
