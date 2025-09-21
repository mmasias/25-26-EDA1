// TelefonoDescacharrado.java
public class telefonoDescacharrado {
    private colaNiños colaNiños;
    private int minNiños;
    private int ronda;

    public telefonoDescacharrado(int minNiños) {
        this.colaNiños = new colaNiños();
        this.minNiños = minNiños;
        this.ronda = 1;
    }

    public void agregarNiño(Niño niño) {
        colaNiños.añadirNiño(niño);
    }

    public void jugarUnaRonda() {
        if (colaNiños.tamañoCola() < minNiños) {
            System.out.println("No hay suficientes niños para jugar.");
            return;
        }

        Mensaje mensaje = new Mensaje();
        System.out.println("=== Ronda " + ronda + " ===");
        System.out.println("Mensaje inicial: " + mensaje.getContenido());

        String mensajeActual = mensaje.getContenido();
        for (Niño niño : colaNiños.obtenerNiñosCola()) {
            mensajeActual = niño.transformar(mensajeActual);
            System.out.println(niño.getNombre() + " lo transforma en: " + mensajeActual);
        }

        System.out.println("Mensaje final: " + mensajeActual);
        ronda++;
    }
}
