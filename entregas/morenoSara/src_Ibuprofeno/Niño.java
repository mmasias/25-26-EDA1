
import java.util.Random;

class Niño {
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
        if (pizarrin == null) return;

        String modificado = modificarMensaje(mensaje);
        pizarrin.escribirMensaje(modificado);
        new Console().writeln("[" + nombre + "] recibe [" + mensaje + "] y escribe [" + modificado + "]");
    }

    private String modificarMensaje(String mensaje) {
        Random random = new Random();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = random.nextInt(2) + 1;

        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int indice = random.nextInt(mensaje.length());
                char letra = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
                mensaje = mensaje.substring(0, indice) + letra + mensaje.substring(indice + 1);
            }
        }
        return mensaje;
    }

    public String mostrarMensaje() {
        return (pizarrin != null) ? pizarrin.leerMensaje() : "";
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) pizarrin.limpiar();
    }
}

