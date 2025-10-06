import java.util.Random;

public class Simulacion {
    private final int totalMinutos;
    private final Ludoteca ludoteca = new Ludoteca();
    private final Random mensajeAleatorio = new Random();

    public Simulacion(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public void ejecutar() {
        int minuto = 0;
        boolean juegoEnCurso = false;
        Juego juegoActual = null;
        int minutosRestantes = 0;

        int contadorNino = 1;

        while (minuto < totalMinutos) {
            
            if (minuto < 10) {
                int llegadas = mensajeAleatorio.nextInt(3); 
                for (int i = 0; i < llegadas; i++) {
                    Nino nino = new Nino("Niño#" + (contadorNino++));
                    if (juegoEnCurso) ludoteca.recibirEnEspera(nino);
                    else ludoteca.recibirEnCola(nino);
                }
            } else if (minuto < 30 && (minuto - 10) % 3 == 0) {
                if (mensajeAleatorio.nextDouble() < 0.5) {
                    Nino nino = new Nino("Niño#" + (contadorNino++));
                    if (juegoEnCurso) ludoteca.recibirEnEspera(nino);
                    else ludoteca.recibirEnCola(nino);
                }
            }

            
            if (!juegoEnCurso && ludoteca.haySuficientesParaJugar()) {
                juegoActual = ludoteca.iniciarJuego(mensajeAleatorio);
                juegoActual.ejecutar(mensajeAleatorio);
                juegoEnCurso = true;
                minutosRestantes = juegoActual.getDuracionMinutos();

                System.out.println("Juego iniciado con " + juegoActual.getDuracionMinutos() + " minutos.");
                System.out.println("Mensaje inicial: " + juegoActual.getMensajeInicial());
            }

            minuto++;

            if (juegoEnCurso) {
                minutosRestantes--;
                if (minutosRestantes <= 0) {
                    
                    System.out.println("Juego terminado. Mensaje final: " + juegoActual.getMensajeFinal());
                    System.out.println("Letras cambiadas: " + juegoActual.getMensajeInicial().letrasErroneas(juegoActual.getMensajeFinal()));
                    ludoteca.moverEsperaACola();
                    juegoEnCurso = false;
                }
            }
        }
    }
}
