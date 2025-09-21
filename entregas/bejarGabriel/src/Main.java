public class Main {
    public static void main(String[] args) {

        final long seed = 12345L;
        final boolean imprimirDetalles = true; 

        Ludoteca ludoteca = new Ludoteca(120, seed, imprimirDetalles);
        ludoteca.simular();

        System.out.println("\n====== RESUMEN FINAL ======");
        ludoteca.getEstadisticas().imprimirInforme();
    }
}