import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            int duracionMinutos = 120;
            long semilla = 12345L;
            boolean imprimirDetalles = true;

            double tasaMediaLlegadas = 3.0;
            GeneradorLlegadas generadorLlegadas = new GeneradorLlegadas(semilla, tasaMediaLlegadas);

            Estadisticas estadisticas = new Estadisticas();

            Random rnd = new Random(semilla);

            Ludoteca ludoteca = new Ludoteca(duracionMinutos, generadorLlegadas, estadisticas, rnd, imprimirDetalles);

            ludoteca.simular();

            estadisticas.imprimirResumen();

        } catch (IllegalArgumentException e) {
            System.err.println("Error en la configuración de la simulación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado durante la simulación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
