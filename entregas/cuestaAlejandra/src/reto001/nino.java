import java.util.Random;

class Nino {
    String nombre;
    String pizarrin;
    Random random = new Random();

    public Nino(String nombre) {
        this.nombre = nombre;
        this.pizarrin = "";
    }

    public void limpiarPizarrin() {
        this.pizarrin = "";
    }

    public void recibirMensaje(String mensaje) {
        StringBuilder deformado = new StringBuilder(mensaje);

        // Posible deformación: cambiar 0, 1 o 2 letras
        int cambios = random.nextInt(3);
        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(mensaje.length());
            char letraRandom = (char) ('A' + random.nextInt(26));
            deformado.setCharAt(pos, letraRandom);
        }

        this.pizarrin = deformado.toString();
    }

    public String mostrarMensaje() {
        return this.pizarrin;
    }
}