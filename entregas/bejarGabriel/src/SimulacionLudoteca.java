import java.util.Random;

public class SimulacionLudoteca {
    public static void main(String[] args) {
        int duracion = 60;
        long semilla = 12345L;
        double tasaMediaLlegadas = 2.5;
        GeneradorLlegadas generadorLlegadas = new GeneradorLlegadas(semilla, tasaMediaLlegadas);
        Estadisticas estadisticas = new Estadisticas();
        Random rnd = new Random(semilla);
        Ludoteca ludoteca = new Ludoteca(duracion, generadorLlegadas, estadisticas, rnd);
        ludoteca.simular();
        estadisticas.imprimirResumen();
    }
}