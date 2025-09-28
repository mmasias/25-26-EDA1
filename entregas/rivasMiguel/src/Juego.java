import java.util.Scanner;

public class Juego {
    private Ludoteca ludoteca;

    public Juego(Ludoteca ludoteca) {
        this.ludoteca = ludoteca;
    }

    public void iniciar(Nino[] participantes) {
        System.out.println("---- INICIANDO JUEGO ----");

        ludoteca.getPizarraSala().limpiar();

        int n = participantsLength(participantes);
        if (n == 0) {
            System.out.println("No hay niños. Juego cancelado.");
            return;
        }

        System.out.println("Pidiendo a los " + n + " niños que limpien sus pizarrines...");
        for (int i = 0; i < n; i++) {
            participantes[i].limpiarPizarrin();
        }

        Monitora organizadora = ludoteca.getOrganizadora();
        if (organizadora == null) {
            System.out.println("No hay organizadora asignada. Juego abortado.");
            return;
        }

        String mensajeOriginal = leerMensajeDesdeConsola(10);
        if (mensajeOriginal == null) mensajeOriginal = "";

        Nino primero = participantes[0];
        organizadora.escribirMensaje(primero.getPizarrin(), mensajeOriginal);

        String mensajeQueSePasa = mensajeOriginal;
        System.out.println("Mensaje inicial: " + mensajeOriginal);
        for (int i = 0; i < n; i++) {
            Nino actual = participantes[i];
            System.out.println("--> " + actual.getNombre() + " recibe: " + mensajeQueSePasa);
            actual.recibirMensaje(mensajeQueSePasa);
            String escrito = actual.escribirMensajeConProbabilidad();
            mensajeQueSePasa = escrito;
            if (i < n - 1) {
                System.out.println(actual.getNombre() + " muestra su pizarrín al siguiente.");
            } else {
                System.out.println(actual.getNombre() + " es el último y corre a la pizarra del salón.");
            }
        }

        ludoteca.getPizarraSala().escribir(mensajeQueSePasa);
        System.out.println("Mensaje final escrito en la pizarra de la sala: " + ludoteca.getPizarraSala().getContenido());

        System.out.println("Los niños permanecen en la fila al terminar el juego (no se vacía la fila).");

        System.out.println("---- FIN DEL JUEGO ----");
    }

    private int participantsLength(Nino[] participantes) {
        if (participantes == null) return 0;
        int c = 0;
        for (int i = 0; i < participantes.length; i++) {
            if (participantes[i] == null) break;
            c++;
        }
        return c;
    }

    private String leerMensajeDesdeConsola(int longitud) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un mensaje de texto (máx. " + longitud + " caracteres): ");
        String linea = sc.nextLine();
        if (linea == null) linea = "";
        linea = linea.trim().toUpperCase();
        if (linea.length() > longitud) {
            linea = linea.substring(0, longitud);
        }
        return linea;
    }
}
