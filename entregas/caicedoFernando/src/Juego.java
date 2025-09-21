public class Juego {
    private static final int PERIODO_INICIAL = 10;
    private static final int LLEGADAS_PERMITIDAS = 2;
    private static final int TIEMPO_LIMITE = 120;
    private static final int FACTOR_MODULO = 3;
    private static final int BONUS_TEMPORAL = 2;
    private static final int CARACTERES_TEXTO = 10;
    private static final int PERIODO_SECUNDARIO = 30;
    private static final int CANTIDAD_MINIMA = 6;

    private final Cola filaEspera;
    private final Monitoras monitoras;
    private int momentoActual;
    private final int duracionMaxima;

    public Juego() {
        filaEspera = new Cola();
        monitoras = new Monitoras();
        momentoActual = 0;
        duracionMaxima = TIEMPO_LIMITE;
    }

    public void ejecutarJuego() {
        int contadorParticipantes = 1;

        while (momentoActual < duracionMaxima) {
            mostrarMomento();

            if (momentoActual < PERIODO_INICIAL) {
                procesarLlegadasRapidas(contadorParticipantes);
                contadorParticipantes += calcularLlegadas();
            } else if (momentoActual < PERIODO_SECUNDARIO) {
                if (evaluarProbabilidad() && validarMomento()) {
                    Nino participante = new Nino("Niño" + contadorParticipantes++);
                    monitoras.lydiaRecibeNino(participante, filaEspera);
                }
            }

            System.out.println("Niños en la cola: " + filaEspera.representarCola());
            System.out.println("Estado monitoras: " + monitoras.obtenerEstadoMonitoras());

            int cantidadActual = filaEspera.obtenerTamano();
            if (cantidadActual >= CANTIDAD_MINIMA && !monitoras.hayJuegoEnCurso()) {
                procesarRondaConMonitoras();
                momentoActual += cantidadActual + BONUS_TEMPORAL;
            } else {
                momentoActual++;
            }
        }
    }

    private void mostrarMomento() {
        System.out.println("\nMinuto " + momentoActual + ":");
    }

    private void procesarLlegadasRapidas(int numeroBase) {
        int llegadas = calcularLlegadas();
        for (int i = 0; i < llegadas; i++) {
            Nino participante = new Nino("Niño" + (numeroBase + i));
            monitoras.lydiaRecibeNino(participante, filaEspera);
        }
    }

    private int calcularLlegadas() {
        return (int) (Math.random() * (LLEGADAS_PERMITIDAS + 1));
    }

    private boolean evaluarProbabilidad() {
        return Math.random() < 0.5;
    }

    private boolean validarMomento() {
        return momentoActual % FACTOR_MODULO == 0;
    }

    private void procesarRondaConMonitoras() {
        monitoras.aishaIniciaJuego();
        prepararTableros();
        String textoInicial = monitoras.aishaGeneraMensaje();

        System.out.println("Mensaje final en la pizarra:");

        Nino[] participantes = filaEspera.extraerNinos();
        for (int i = 0; i < participantes.length; i++) {
            boolean esUltimo = (i == participantes.length - 1);
            textoInicial = participantes[i].modificarTexto(textoInicial);
            monitoras.aishaSupervisa(participantes[i], textoInicial, esUltimo);
            System.out.println(participantes[i].mostrarEstado());
        }

        monitoras.aishaTerminaJuego(filaEspera);
    }

    private void prepararTableros() {
        Nino[] participantes = filaEspera.extraerNinos();
        for (Nino participante : participantes) {
            participante.resetearTablero();
        }
    }
}