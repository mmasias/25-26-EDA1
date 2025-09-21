import java.util. *;

public class Juego {
   private Queue<Nino> fila;

    public Juego(Queue<Nino> fila) {
        this.fila = fila;
    }

    public String generarMensaje() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((char) ('A' + rand.nextInt(26)));
        }
        return sb.toString();
    }

    // Ejecuta el juego minuto a minuto
    public int jugar(int tiempo) {
        if (fila.size() < 5) return tiempo;

        List<Nino> filaJuego = new ArrayList<>(fila);
        String mensaje = generarMensaje();
        System.out.println("Mensaje original: " + mensaje);

        String mensajeActual = mensaje;
        for (Nino nino : filaJuego) {
            mensajeActual = nino.recibirMensaje(mensajeActual);
            System.out.println("Mensaje pasando: " + mensajeActual);
            tiempo++; // cada niño tarda 1 minuto
        }

        System.out.println("Mensaje final en pizarra: " + mensajeActual);
        fila.clear(); // los niños del juego se van
        return tiempo;
    }
}


