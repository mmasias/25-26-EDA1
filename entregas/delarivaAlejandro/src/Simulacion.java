public class Simulacion {
    public static void main(String[] args) {
        int duracionJornadaMinutos = 120;
        Restaurante restaurante = new Restaurante(duracionJornadaMinutos);
        restaurante.ejecutarSimulacion();
    }
}
