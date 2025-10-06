

import utils.Console;

import java.util.Random;

class Niño {
    private String nombre;

    public Niño(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    private String modificarMensaje(String mensaje) {
        Random random = new Random();
        final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

        int errores = random.nextInt(2) + 1;
        
        for (int i = 0; i < errores; i++) {
            if (mensaje.length() > 0) {
                int indiceAleatorio = random.nextInt(mensaje.length());
                char letraAleatoria = ALFABETO.charAt(random.nextInt(ALFABETO.length()));
                
                mensaje = mensaje.substring(0, indiceAleatorio) + letraAleatoria + mensaje.substring(indiceAleatorio + 1);
            }
        }
        return mensaje;
    }

}