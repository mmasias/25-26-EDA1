import java.util.Random;

public class Niño {
    private String nombre;
    private Pizarra pizarrin;
    private final double probabilidadCambio0;
    private final double probabilidadCambio1;
    private final double probabilidadCambio2;
    private final Random random;

    public Niño(String nombre, double probabilidadCambio0, double probabilidadCambio1, double probabilidadCambio2, Random random) {
        this.nombre = nombre;
        this.probabilidadCambio0 = probabilidadCambio0;
        this.probabilidadCambio1 = probabilidadCambio1;
        this.probabilidadCambio2 = probabilidadCambio2;
        this.random = random;
    }

    public void recibirPizarrin(Pizarra pizarra) {
        this.pizarrin = pizarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirMensaje(String mensaje) {
        String mutado = modificarMensaje(mensaje);
        pizarrin.escribirMensaje(mutado);
    }

    private String modificarMensaje(String mensaje) {
        double probabilidad = random.nextDouble();
        int cambios = (probabilidad < probabilidadCambio0) ? 0 :
                      (probabilidad < probabilidadCambio0 + probabilidadCambio1 ? 1 : 2);

        if (cambios == 0) return mensaje;

        StringBuilder sb = new StringBuilder(mensaje);
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(sb.length());
            char nuevo;
            do {
                nuevo = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
            } while (nuevo == sb.charAt(pos));
            sb.setCharAt(pos, nuevo);
        }
        return sb.toString();
    }

    public String mostrarMensaje() {
        return pizarrin.leerMensaje();
    }

    public void limpiarPizarrin() {
        pizarrin.limpiar();
    }
}
