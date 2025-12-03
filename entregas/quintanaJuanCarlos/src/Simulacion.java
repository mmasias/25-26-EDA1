public class Simulacion {

    public static void main(String[] args) {
        int minutosSimulacion = 500;
        double probabilidadLlegada = 0.4;

        Cocina cocina = new Cocina(minutosSimulacion, probabilidadLlegada);
        cocina.ejecutarSimulacion();
    }
}
