package reto001;

public class Ludoteca {
    private Fila mainFila = new Fila(16);
    private Fila esperandoFila = new Fila(16);
    private int tiempoActual = 0;
    private int siguienteIdNino = 1;
    private int totalNinosLlegados = 0;

    private int totalJuegos = 0;
    private int totalLetrasCambiadas = 0;
    private int maxLetrasCambiadas = -1;
    private int minLetrasCambiadas = 9999;

    public static void main(String[] args) {
        Ludoteca ludoteca = new Ludoteca();
        ludoteca.iniciar();
    }

    public void iniciar() {
        int duracionSimulacion = 120;
        boolean juegoEnCurso = false;
        Juego juegoActual = null;
        int finJuego = -1;

        Llegada llegada = new Llegada();

        while (true) {
            int nLlegadas = llegada.ninosQueLlegan(tiempoActual);
            for (int i = 0; i < nLlegadas; i++) {
                Nino n = new Nino(siguienteIdNino++);
                if (juegoEnCurso) {
                    esperandoFila.encolar(n);
                } else {
                    mainFila.encolar(n);
                }
                totalNinosLlegados++;
            }

            if (!juegoEnCurso && mainFila.tamano() > 5) {
                Nino[] participantes = mainFila.extraerTodos();
                juegoActual = new Juego(participantes);
                juegoEnCurso = true;
                finJuego = tiempoActual + juegoActual.duracion();

                String mensajeFinal = juegoActual.jugar();
                int letrasCambiadas = juegoActual.letrasCambiadas();
                totalLetrasCambiadas += letrasCambiadas;
                if (letrasCambiadas > maxLetrasCambiadas) maxLetrasCambiadas = letrasCambiadas;
                if (letrasCambiadas < minLetrasCambiadas) minLetrasCambiadas = letrasCambiadas;
                totalJuegos++;

                System.out.println("Juego " + totalJuegos + ":");
                System.out.println("Niños en cola: " + participantes.length);
                System.out.println("Mensaje original: " + juegoActual.mensajeOriginal());
                System.out.println("Mensaje final en pizarra: " + mensajeFinal);
                System.out.println("Letras cambiadas: " + letrasCambiadas);
                System.out.println("----------------------");
            }

            tiempoActual++;

            if (juegoEnCurso && tiempoActual >= finJuego) {
                esperandoFila.moverATo(mainFila);
                juegoEnCurso = false;
            }

            if (tiempoActual >= duracionSimulacion && !juegoEnCurso && esperandoFila.tamano() == 0) {
                break;
            }
        }

        
        System.out.println("--- Ludoteca finalizada ---");
        System.out.println("Niños llegados: " + totalNinosLlegados);
        System.out.println("Juegos realizados: " + totalJuegos);
        if (totalJuegos > 0) {
            System.out.println("Promedio de letras cambiadas: " + (double) totalLetrasCambiadas / totalJuegos);
            System.out.println("Máximo de letras cambiadas: " + maxLetrasCambiadas);
            System.out.println("Mínimo de letras cambiadas: " + minLetrasCambiadas);
        }
        System.out.println("Niños en fila al final: " + mainFila.tamano());
    }
}