public class Simulacion {

    public static final double SEMILLA_ALEATORIA = 1234.0;
    public static final int DURACION_SIMULACION = 200;

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante(SEMILLA_ALEATORIA);
        restaurante.ejecutar(DURACION_SIMULACION);
    }
}