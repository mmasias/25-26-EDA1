public class Profesora {
    private String nombre;

    public Profesora(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static class Lydia extends Profesora {
        public Lydia() {
            super("Lydia");
        }

        public void recibirNiño(Niño nino, Niño[] esperando, int[] contadorEsperando, boolean juegoEnCurso, ColaNiños cola) {
            if (juegoEnCurso) {
                esperando[contadorEsperando[0]++] = nino;
            } else {
                cola.añadirNiño(nino);
            }
        }
    }

    public static class Aisha extends Profesora {
        public Aisha() {
            super("Aisha");
        }

        public void formarCola(Niño nino, ColaNiños cola) {
            cola.añadirNiño(nino);
        }
    }
}
