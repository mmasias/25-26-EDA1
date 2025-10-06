import java.util.Random;

public class Niño {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;
    private final double probabilidadCambio0, probabilidadCambio1, probabilidadCambio2;
    private final Random random;

    public Niño(String nombre, int edad, double probabilidad0, double probabilidad1, double probabilidad2, Random random) {
        this.nombre = nombre;
        this.edad = edad;
        this.probabilidadCambio0 = probabilidad0;
        this.probabilidadCambio1 = probabilidad1;
        this.probabilidadCambio2 = probabilidad2;
        this.random = random;
        this.pizarrin = new Pizarra();
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public void recibirMensaje(String mensaje) {
        String modificado = modificarMensaje(mensaje);
        pizarrin.escribirMensaje(modificado);
    }

    private String modificarMensaje(String mensaje) {
        double probabilidad = random.nextDouble();
        int cambios = probabilidad < probabilidadCambio0 ? 0 :
                      probabilidad < probabilidadCambio0 + probabilidadCambio1 ? 1 : 2;

        if (cambios == 0) return mensaje;

        char[] caracteres = mensaje.toCharArray();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < cambios; i++) {
            int pos = random.nextInt(caracteres.length);
            char nuevo;
            do {
                nuevo = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
            } while (nuevo == caracteres[pos]);
            caracteres[pos] = nuevo;
        }

        return new String(caracteres);
    }

    public String mostrarMensaje() { return pizarrin.leerMensaje(); }
    public void limpiarPizarrin() { pizarrin.limpiar(); }
}
