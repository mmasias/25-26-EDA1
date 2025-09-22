import java.util.Scanner;

public class Juego {
    private Ludoteca ludoteca;

    public Juego(Ludoteca ludoteca) {
        this.ludoteca = ludoteca;
    }

    public void iniciar() {
        System.out.println("---- INICIANDO JUEGO ----");

        ludoteca.getPizarraSala().limpiar();

        int n = ludoteca.numeroDeNinos();
        if (n == 0) {
            System.out.println("No hay niños. Juego cancelado.");
            return;
        }
        Nino[] fila = ludoteca.getFilaArray();

        System.out.println("Pidiendo a los niños que limpien sus pizarrines...");
        for (int i = 0; i < n; i++) fila[i].limpiarPizarrin();

        Monitora organizadora = ludoteca.getOrganizadora();
        if (organizadora == null) {
            System.out.println("No hay organizadora asignada. Juego abortado.");
            return;
        }

        String mensajeOriginal = leerMensajeDesdeConsola(10);
        Nino primero = fila[0];
        organizadora.escribirMensaje(primero.getPizarrin(), mensajeOriginal);

        String mensajeQueSePasa = mensajeOriginal;
        System.out.println("Mensaje inicial: " + mensajeOriginal);
        for (int i = 0; i < n; i++) {
            Nino actual = fila[i];
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

        ludoteca.vaciarFila();
        System.out.println("---- FIN DEL JUEGO ----");
    }

    private String leerMensajeDesdeConsola(int longitud) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un mensaje de texto (máx. " + longitud + " caracteres): ");
        String linea = sc.nextLine();
        if (linea == null) linea = "";
        linea = linea.trim().toUpperCase();

        
        if (linea.length() > longitud) {
            return linea.substring(0, longitud);
        }
        return linea; 
    }
}
