package v001;

import utils.Console;

import java.util.Random;

class Niño {
    private static final int MINIMO_ERRORES = 1;
    private static final int MAXIMO_ERRORES = 2;
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

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
        new Console().writeln("[" + nombre + "] recibe [" + mensaje + "] y ha escrito [" + pizarrin.leerMensaje() + "]");        
    }

    private String modificarMensaje(String mensaje) {
        Random random = new Random();

        int errores = random.nextInt(MAXIMO_ERRORES - MINIMO_ERRORES + 1) + MINIMO_ERRORES;

        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int indiceAleatorio = random.nextInt(mensaje.length());
                char letraAleatoria = ALFABETO.charAt(random.nextInt(ALFABETO.length()));

                mensaje = mensaje.substring(0, indiceAleatorio) + letraAleatoria + mensaje.substring(indiceAleatorio + 1);
            }
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