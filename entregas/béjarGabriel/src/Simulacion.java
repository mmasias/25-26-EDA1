
public class Simulacion {

    public static void main(String[] args) {

        long semilla = (args.length > 0) ? Long.parseLong(args[0]) : System.currentTimeMillis();

        RandomGenerator rng = new RandomGenerator(semilla);

        EstructuraPedidos estructura = new ArbolPedidos();
        Cocinero cocinero = new Cocinero();

        int duracion = 50;

        Restaurant restaurant = new Restaurant(estructura, cocinero, rng, duracion);

        System.out.println("Simulación iniciada con semilla: " + semilla);
        System.out.println("-------------------------------------------\n");

        restaurant.ejecutar();
    }
}
