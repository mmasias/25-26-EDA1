
public class Pizarra {

        private String palabraSecreta;

        public String getTexto() {
                return palabraSecreta;
        }

        public void escribir(String palabra) {
                this.palabraSecreta = palabra;
        }

}
