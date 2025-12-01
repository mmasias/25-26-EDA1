public class Simulacion {
    private static final int DURACION_SIMULACION = 200;

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        restaurante.ejecutar(DURACION_SIMULACION);
    }
}
