public class SimulacionTelefono {
    public static void main(String[] args) {
        System.out.println("Iniciando simulación con cola circular (encolar/desencolar)...\n");
        Ludoteca ludoteca = new Ludoteca();
        ludoteca.ejecutarSimulacion();
        System.out.println("\nSimulación finalizada.");
    }
}
