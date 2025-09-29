import java.util.Random;

public class Mundo {
    private final Ludoteca ludoteca;
    private final int tiempoTotalMin;

    public Mundo(Ludoteca unaLudoteca, int tiempoAperturaMin) {
        this.ludoteca = unaLudoteca;
        this.tiempoTotalMin = tiempoAperturaMin;
    }

    public void iniciarSimulacion() {
        System.out.println("=".repeat(50));
        System.out.println("TELÉFONO DESCACHARRADO — Simulación por consola");
        System.out.println("Colas limitadas a 20 niños máximo");
        System.out.println("Tiempo total (min): " + tiempoTotalMin);
        System.out.println("=".repeat(50));

        for (int minutoActual = 0; minutoActual < tiempoTotalMin; ) {
            if (ludoteca.aishaPuedeJugar()) {
                int duracion = ludoteca.jugarPartida(minutoActual);
                minutoActual += duracion;
            } else {
                int llegadas = generarLlegadas(minutoActual);
                for (int i = 0; i < llegadas; i++) {
                    ludoteca.recibirNiño(new Nino(nombreAleatorio()));
                }
                ludoteca.gestionarTraspaso();
                minutoActual += 1;
            }
        }

        System.out.println("=".repeat(50));
        System.out.println("Cierre administrativo. Estado final:");
        ludoteca.verEstado();
        System.out.println("=".repeat(50));
    }

    private int generarLlegadas(int minuto) {
        if (minuto <= 9) {
            return new Random().nextInt(3);
        } else if (minuto <= 29) {
            if (minuto % 3 == 0) {
                return new Random().nextBoolean() ? 1 : 0;
            }
            return 0;
        } else {
            return 0;
        }
    }

    private String nombreAleatorio() {
        String[] nombres = {
            "Ari", "Leo", "Noa", "Iker", "Sofi", "Enzo", "Mia", "Pau", "Lia", "Luca",
            "Joel", "Nora", "Jan", "Ona", "Dani", "Sara", "Hugo", "Vera", "Eric", "Iris"
        };
        return nombres[new Random().nextInt(nombres.length)];
    }

    public static void main(String[] args) {
        Ludoteca unaLudoteca = new Ludoteca();
        Mundo elMundo = new Mundo(unaLudoteca, 120);
        elMundo.iniciarSimulacion();
    }
}