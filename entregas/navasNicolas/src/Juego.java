import java.util.*;

public class Juego {
    private Queue<Niño> colaEspera;
    private Lydia lydia;
    private Aisha aisha;
    private Pizarra pizarraPrincipal;
    private static final int DURACION_TOTAL_MIN = 120;
    private static final int DURACION_JUEGO_MAX_MIN = 30;
    private static final int MIN_NINOS = 5;

    public Juego() {
        lydia = new Lydia();
        aisha = new Aisha();
        colaEspera = new LinkedList<>();
        pizarraPrincipal = new Pizarra();
    }

    public void llegadaPeriodicaNiños(List<String> nombres) {
        for (String nombre : nombres) {
            System.out.println("Llega el niño: " + nombre);
            colaEspera.add(new Niño(nombre));
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }

    public void jugar() {
        int minutosTranscurridos = 0;
        Scanner sc = new Scanner(System.in);

        List<String> todosLosNombres = Arrays.asList(
            "Juan", "Ana", "Luis", "Sofia", "Pedro", "Maria", "Carlos", "Lucia", "Miguel", "Valentina"
        );
        llegadaPeriodicaNiños(todosLosNombres);

        while (minutosTranscurridos < DURACION_TOTAL_MIN) {
            if (colaEspera.size() < MIN_NINOS) {
                System.out.println("Esperando más niños para iniciar el juego...");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                minutosTranscurridos += 1;
                continue;
            }

            List<Niño> niñosEnJuego = new ArrayList<>();
            for (int i = 0; i < MIN_NINOS && !colaEspera.isEmpty(); i++) {
                niñosEnJuego.add(colaEspera.poll());
            }

            lydia.recibirNiños(niñosEnJuego);
            aisha.escribirLetrasIniciales();

            niñosEnJuego.get(0).copiarYModificar(aisha.getPizarra());

            for (int i = 1; i < niñosEnJuego.size(); i++) {
                niñosEnJuego.get(i).copiarYModificar(niñosEnJuego.get(i - 1));
            }

            Niño ultimo = niñosEnJuego.get(niñosEnJuego.size() - 1);
            pizarraPrincipal.setLetras(ultimo.getLetras());
            System.out.println(ultimo.getNombre() + " escribe en el pizarrón grande de Aisha:");
            pizarraPrincipal.mostrar();

            System.out.println("\nRecorrido de pizarras:");
            System.out.println("Aisha: " + aisha.getPizarra().getLetras());
            for (Niño n : niñosEnJuego) {
                System.out.println(n.getNombre() + ": " + n.getLetras());
            }
            System.out.println("Pizarrón grande: " + pizarraPrincipal.getLetras());

            int tiempoJuego = Math.min(DURACION_JUEGO_MAX_MIN, niñosEnJuego.size());
            minutosTranscurridos += tiempoJuego;
            System.out.println("Juego terminado. Tiempo transcurrido: " + minutosTranscurridos + " minutos.\n");

            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }

        System.out.println("¡Las 2 horas han terminado!");
    }
}