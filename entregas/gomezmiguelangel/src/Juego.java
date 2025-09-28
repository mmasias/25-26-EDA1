import java.util.Scanner;

public class Juego {

    private Ludoteca ludoteca;

    public Juego(Ludoteca ludoteca) {
        this.ludoteca = ludoteca;
    }

    public void iniciar() {
        System.out.println("---- INICIANDO JUEGO ----");

        ludoteca.getPizarraSala().limpiar();

        int cantidadNinos = ludoteca.numeroDeNinos();
        if (cantidadNinos == 0) {
            System.out.println("No hay niños. Juego cancelado.");
            return;
        }

        Nino[] fila = ludoteca.getFilaArray();
        System.out.println("Pidiendo a los niños que limpien sus pizarrines...");

        for (Nino nino : fila) {
            nino.limpiarPizarrin();
        }

        Monitora organizadora = ludoteca.getOrganizadora();
        if (organizadora == null) {
            System.out.println("No hay organizadora asignada. Juego abortado.");
            return;
        }

        String mensajeInicial = leerMensajeDesdeConsola(10);
        organizadora.escribirMensaje(fila[0].getPizarrin(), mensajeInicial);

        String mensajeActual = mensajeInicial;
        System.out.println("Mensaje inicial: " + mensajeInicial);

        for (int i = 0; i < cantidadNinos; i++) {
            Nino nino = fila[i];
            System.out.println("--> " + nino.getNombre() + " recibe: " + mensajeActual);
            nino.recibirMensaje(mensajeActual);

            mensajeActual = nino.escribirMensajeConProbabilidad();

            if (i < cantidadNinos - 1) {
                System.out.println(nino.getNombre() + " muestra su pizarrín al siguiente.");
            } else {
                System.out.println(nino.getNombre() + " es el último y corre a la pizarra del salón.");
            }
        }

        ludoteca.getPizarraSala().escribir(mensajeActual);
        System.out.println("Mensaje final escrito en la pizarra de la sala: " + ludoteca.getPizarraSala().getContenido());

        ludoteca.vaciarFila();
        System.out.println("---- FIN DEL JUEGO ----");
    }

    private String leerMensajeDesdeConsola(int longitudMaxima) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un mensaje de texto (máx. " + longitudMaxima + " caracteres): ");
        String linea = scanner.nextLine();
        if (linea == null) linea = "";

        linea = linea.trim().toUpperCase();
        return linea.length() > longitudMaxima ? linea.substring(0, longitudMaxima) : linea;
    }
}

