import java.io.Console;
import java.util.Random;

public class SimulacionLudoteca {
    public static void main(String[] args) {
        new Console().writeln("=".repeat(50));
        new Console().writeln("SIMULACIÓN LUDOTECA MEJORADA v2.0 (Teléfono Descompuesto)");
        new Console().writeln("Duración: 40 minutos, Colas limitadas a 15 niños");
        new Console().writeln("=".repeat(50));

        int duracion = 40;
        long semilla = 12345L;
        double tasaMediaLlegadas = 1.5; // Ajustada para simulación más realista
        GeneradorLlegadas generadorLlegadas = new GeneradorLlegadas(semilla, tasaMediaLlegadas);
        Estadisticas estadisticas = new Estadisticas();
        Random rnd = new Random(semilla);
        Ludoteca ludoteca = new Ludoteca(duracion, generadorLlegadas, estadisticas, rnd);
        ludoteca.simular();
    }
}
