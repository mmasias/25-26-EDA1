import java.util.Random;

public class Niño {
    private String nombre;
    private Pizarra pizarrin;

    public Niño(String nombre) {
        this.nombre = nombre;
    }

    public void recibirPizarrin(Pizarra pizarra) {
        this.pizarrin = pizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirMensaje(String mensaje) {
        pizarrin.escribirMensaje(modificarMensaje(mensaje));
        System.out.println("[" + nombre + "] recibe [" + mensaje + "] y escribe [" + pizarrin.leerMensaje() + "]");
    }

    private String modificarMensaje(String mensaje) {
        Random random = new Random();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = random.nextInt(2) + 1;
        for (int i = 0; i < errores; i++) {
            int idx = random.nextInt(mensaje.length());
            char letra = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
            mensaje = mensaje.substring(0, idx) + letra + mensaje.substring(idx + 1);
        }
        return mensaje;
    }

    public String mostrarMensaje() {
        return pizarrin.leerMensaje();
    }

    public void limpiarPizarrin() {
        pizarrin.limpiar();
    }
}
