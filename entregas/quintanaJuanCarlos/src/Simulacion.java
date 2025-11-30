public class Simulacion {

    public static void main(String[] args) {
        int duracionSimulacionMinutos = 60;
        double probabilidadLlegadaPedido = 0.4;

        Cocina cocinaRCCCF = new Cocina(duracionSimulacionMinutos, probabilidadLlegadaPedido);
        cocinaRCCCF.ejecutarSimulacion();
    }
}
