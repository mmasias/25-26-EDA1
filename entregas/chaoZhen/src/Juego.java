import java.util.Random;

public class Juego {
    private final Nino[] jugadores;
    private final int numeroJugadores;
    private final Mensaje mensajeInicial;
    private Mensaje mensajeFinal;
    private int duracionMinutos;

    public Juego(Nino[] jugadores, int numJugadores, Mensaje mensajeInicial) {
        this.jugadores = new Nino[numJugadores];
        for (int i = 0; i < numJugadores; i++)
            this.jugadores[i] = jugadores[i];
        this.numeroJugadores = numJugadores;
        this.mensajeInicial = mensajeInicial.copiar();
    }

   public void ejecutar(Random mensajeAleatorio) {
    System.out.println("Se limpia la pizarra y los pizarrines.");

    Mensaje mensajeActual = mensajeInicial.copiar();
    System.out.println("Mensaje inicial: " + mensajeActual);

    duracionMinutos = 1; 

    for (int i = 0; i < numeroJugadores; i++) {
        Mensaje pizarrin = mensajeActual.copiar();

        double prob = mensajeAleatorio.nextDouble();
        if (prob < 0.7) {
           
        } else if (prob < 0.9) {
            pizarrin.cambiarUnaLetra(mensajeAleatorio);
        } else {
            pizarrin.cambiarDosLetras(mensajeAleatorio);
        }

        System.out.println("Niño " + jugadores[i].obtenerNombre() + " escribió: " + pizarrin);

        mensajeActual = pizarrin;
        duracionMinutos++; 
    }

    duracionMinutos++; 

    mensajeFinal = mensajeActual.copiar();
    System.out.println("Mensaje final en la pizarra: " + mensajeFinal);
    System.out.println("Juego iniciado con " + duracionMinutos + " minutos.");
}



    public Mensaje getMensajeInicial() { return mensajeInicial; }
    public Mensaje getMensajeFinal() { return mensajeFinal; }
    public int getDuracionMinutos() { return duracionMinutos; }

    public void ejecutarUnMinuto(Random rand, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutarUnMinuto'");
    }

    public int getNumeroJugadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumeroJugadores'");
    }
}
