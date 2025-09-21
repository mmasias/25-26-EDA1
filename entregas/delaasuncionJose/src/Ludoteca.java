import java.util.Random;

public class Ludoteca {

    private Monitor lydia;
    private Monitor aisha;
    private Cola fila;
    private Cola salaDeEspera;
    private String pizarraSalon;
    private int tiempo;
    private boolean juegoEnCurso;
    private int tiempoAbierto;

    public Ludoteca() {
        this.lydia = new Monitor("Lydia");
        this.aisha = new Monitor("Aisha");
        this.fila = new Cola();
        this.salaDeEspera = new Cola();
        this.pizarraSalon = "";
        this.tiempo = 0;
        this.juegoEnCurso = false;
        this.tiempoAbierto = 120
    }

    public void simular() {
        while (tiempo < tiempoAbierto) {
            llegadaNinos();

            if (!juegoEnCurso && fila.tamano() > 5) {
                iniciarJuego();
            }

            tiempo++;
        }

        System.out.println("Simulación terminada.");
    }

    private int contadorNinos = 0;

    private void llegadaNinos() {
        Random rnd = new Random();

        if (tiempo < 10) {
            int cantidad = rnd.nextInt(3);
            for (int i = 0; i < cantidad; i++) {
                Nino n = new Nino("Nino_" + contadorNinos);
                contadorNinos++;
                lydia.recibirNino(n, this);
            }
        } else if (tiempo < 30) {
            if (tiempo % 3 == 0 && rnd.nextBoolean()) {
                Nino n = new Nino("Nino_" + contadorNinos);
                contadorNinos++;
                lydia.recibirNino(n, this);
            }
        }
    }


    private void iniciarJuego() {
        juegoEnCurso = true;
        System.out.println("\n--- Iniciando un nuevo juego en el minuto " + tiempo + " ---");

        pizarraSalon = "";
        fila.recorrer(n -> n.getPizarrin().limpiar());

        String mensajeOriginal = generarMensaje(10);
        System.out.println("Mensaje original: " + mensajeOriginal);

        final String[] mensaje = {mensajeOriginal};
        fila.recorrer(n -> mensaje[0] = n.recibirMensaje(mensaje[0]));

        pizarraSalon = mensaje[0];
        System.out.println("Mensaje final en la pizarra: " + pizarraSalon);

        while (!salaDeEspera.estaVacia()) {
            aisha.formarFila(salaDeEspera.desencolar(), fila);
        }

        juegoEnCurso = false;
    }

    private String generarMensaje(int longitud) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            char letra = (char) ('A' + rnd.nextInt(26));
            sb.append(letra);
        }
        return sb.toString();
    }

    public Cola getFila() {
        return fila;
    }

    public Cola getSalaDeEspera() {
        return salaDeEspera;
    }

    public Monitor getAisha() {
        return aisha;
    }

    public boolean isJuegoEnCurso() {
        return juegoEnCurso;
    }

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        ludoteca.simular();
    }
}
