public class Simulacion {

    public static void main(String[] args) {
        int minutosSimulacion = 60;
        double probabilidadLlegada = 0.4;

        Cocina cocina = new Cocina(minutosSimulacion, probabilidadLlegada);
        cocina.ejecutarSimulacion();
    }
}
