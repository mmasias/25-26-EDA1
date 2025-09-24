public class Niño {
    private String nombre;
    private Pizarra pizarrin;

    public Niño(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirPizarrin(Pizarra pizarra) {
        this.pizarrin = pizarra;
    }

    public void limpiarPizarrin() {
        if (pizarrin != null) {
            pizarrin.limpiar();
        }
    }

    public void recibirMensaje(String mensaje) {
    String modificado = modificarMensaje(mensaje);
    pizarrin.escribirMensaje(modificado);
    System.out.println("[" + nombre + "] recibe [" + mensaje + "] y escribe [" + modificado + "]");
    }

    public String mostrarMensaje() {
        return pizarrin.leerMensaje();
    }

    private String modificarMensaje(String mensaje) {
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = Math.min(2, mensaje.length());
        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int indice = i % mensaje.length();
                char letraAleatoria = ALFABETO.charAt((nombre.length() + i) % ALFABETO.length());
                mensaje = mensaje.substring(0, indice) + letraAleatoria + mensaje.substring(indice + 1);
            }
        }
        return mensaje;
    }
}
