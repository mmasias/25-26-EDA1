
public class Simulacion {

    public static void main(String[] args) {

        long semilla = System.currentTimeMillis();
        if (args.length > 0) {
            try {
                semilla = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Semilla inválida, usando tiempo actual.");
            }
        }

        Restaurante restaurante = new Restaurante(50, semilla);
        restaurante.ejecutar();
    }
}
