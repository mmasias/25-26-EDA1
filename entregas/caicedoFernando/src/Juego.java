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
    private int momentoActual;
    private final int duracionMaxima;

    public Juego() {
        filaEspera = new Cola();
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
                    filaEspera.insertarNino(participante);
                    System.out.println("Llega el niño: " + participante.obtenerNombre());
                }
            }

            System.out.println("Niños en la cola: " + filaEspera.representarCola());

            int cantidadActual = filaEspera.obtenerTamano();
            if (cantidadActual >= CANTIDAD_MINIMA) {
                procesarRonda();
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
            filaEspera.insertarNino(participante);
            System.out.println("Llega el niño: " + participante.obtenerNombre());
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

    private void procesarRonda() {
        prepararTableros();
        String textoInicial = crearTexto();
        System.out.println("Mensaje original: " + textoInicial);
        System.out.println("Mensaje final en la pizarra:");

        Nino[] participantes = filaEspera.extraerNinos();
        for (Nino participante : participantes) {
            textoInicial = participante.modificarTexto(textoInicial);
            System.out.println(participante.mostrarEstado());
        }
    }

    private void prepararTableros() {
        System.out.println("Limpiando pizarras...");
        Nino[] participantes = filaEspera.extraerNinos();
        for (Nino participante : participantes) {
            participante.resetearTablero();
        }
    }

    private String crearTexto() {
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String resultado = "";
        for (int i = 0; i < CARACTERES_TEXTO; i++) {
            resultado += alfabeto.charAt((int) (Math.random() * alfabeto.length()));
        }
        return resultado;
    }
}
