import utils.Scanner;
import java.util.Random;

class Niño {
    private String nombre;
    private int edad;
    private Pizarra pizarrin;

    public Niño(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.pizarrin = new Pizarra();
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public void recibirMensaje(String mensaje) {
        pizarrin.escribirMensaje(modificarMensaje(mensaje));
        System.out.println("[" + nombre + "] recibe [" + mensaje + "] y ha escrito [" + pizarrin.leerMensaje() + "]");
    }

    private String modificarMensaje(String mensaje) {
        Random random = new Random();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
        int errores = random.nextInt(2) + 1;
        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int idx = random.nextInt(mensaje.length());
                char letra = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
                mensaje = mensaje.substring(0, idx) + letra + mensaje.substring(idx + 1);
            }
        }
        return mensaje;
    }

    public String mostrarMensaje() { return pizarrin.leerMensaje(); }
    public void limpiarPizarrin() { pizarrin.limpiar(); }
    public void presentarse() { System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años"); }
    public void presentarseNombre() { System.out.println("Hola, soy " + nombre); }
}