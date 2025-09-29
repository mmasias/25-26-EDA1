import java.io.Console;
import java.util.Random;
import javax.management.monitor.Monitor;

class Ludoteca {
    private final int duracionSimulacion;
    private final GeneradorLlegadas generadorLlegadas;
    private final Estadisticas estadisticas;
    private final Random rnd;
    private final Monitor lydia;
    private final Monitor aisha;

    public Ludoteca(int duracionSimulacion, GeneradorLlegadas generadorLlegadas, Estadisticas estadisticas, Random rnd) {
        this.duracionSimulacion = duracionSimulacion;
        this.generadorLlegadas = generadorLlegadas;
        this.estadisticas = estadisticas;
        this.rnd = rnd;
        this.lydia = new Monitor("Lydia");
        this.aisha = new Monitor("Aisha");
    }

    public void simular() {
        for (int minuto = 0; minuto < duracionSimulacion; minuto++) {
            new Console().clearScreen();
            new Console().writeln("=".repeat(30));
            new Console().writeln("Minuto " + minuto);

            int llegadas = generadorLlegadas.llegadasEnMinuto(minuto);
            for (int i = 0; i < llegadas; i++) {
                Niño niño = new Niño(inventarNombre());
                lydia.recibeNiño(niño);
                new Console().writeln("Llega " + niño.getNombre());
            }

            gestionarNiños();
            if (aisha.puedeJugar()) {
                Juego juego = aisha.iniciarJuego();
                if (juego != null) {
                    estadisticas.registrarJuego(juego);
                    new Console().writeln("Juego completado: " + juego);
                }
            }

            verEstado();

            if (minuto < duracionSimulacion - 1) {
                new Console().readString("Pulse enter para continuar");
            }
        }
        new Console().writeln("\n" + "=".repeat(50));
        new Console().writeln("SIMULACIÓN FINALIZADA");
        estadisticas.imprimirResumen();
    }

    private void gestionarNiños() {
        if (lydia.tieneNiños() && !aisha.estaJugando()) {
            lydia.entregaNiños(aisha);
        }
    }

    private void verEstado() {
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
        new Console().writeln("Uso de colas: Lydia " + lydia.getUsoCola() + ", Aisha " + aisha.getUsoCola());
    }

    private String inventarNombre() {
        String[] nombres = {
                "Andrés", "Pablo", "Diego", "Aníbal", "Umut", "Javier", "Fernando",
                "Cayetano", "Iker", "Mario", "Adrián", "Paula", "Veronika", "Eduardo",
                "Hugo", "César", "Miguel", "Santiago", "Juan", "Daniel", "Álvaro",
                "Maura", "Neco", "Sergio", "Aurelio", "Jorge", "Raúl", "José Manuel",
                "José Luis", "Óscar", "Rubén", "Gabriel", "Iñaki", "Alejandro", "Andriuw"
        };
        return nombres[rnd.nextInt(nombres.length)];
    }
}