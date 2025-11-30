
public class Simulacion {

    public static void main(String[] args) {
        int duracionSimulacionMinutos = 60;
        double probabilidadLlegadaPedido = 0.4;

        Cocina cocina = new Cocina(duracionSimulacionMinutos, probabilidadLlegadaPedido);
        cocina.ejecutarSimulacion();
    }
}
