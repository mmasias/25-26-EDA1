import java.util.Random;

public class Simulador {
    private static final int HORIZONTE = 120;
    private final Random aleatorio;
    private final boolean traza;
    private final String mensajeFijo;
    private final ColaCircular colaPrincipal;
    private final ColaCircular salaDeEspera;
    private final Metrica metrica;
    private final ModeloError modeloError;

    private boolean juegoActivo = false;
    private int tiempoFinJuego = -1;

    public Simulador(long semilla, double pError, double pDos, boolean traza, String mensajeFijo) {
        this.aleatorio = new Random(semilla);
        this.traza = traza;
        this.mensajeFijo = mensajeFijo;
        this.colaPrincipal = new ColaCircular(256);
        this.salaDeEspera = new ColaCircular(256);
        this.metrica = new Metrica();
        this.modeloError = new ModeloErrorBinario(pError, pDos, aleatorio);
    }

    public void ejecutar() {
        if (traza) System.out.println("[TRACE] Inicio simulación");

        for (int minuto = 0; minuto < HORIZONTE; minuto++) {

            if (juegoActivo && minuto == tiempoFinJuego) {
                if (traza) System.out.printf("[t=%d] FIN_JUEGO%n", minuto);
                transferirSalaACola();
                juegoActivo = false;
            }

            if (!juegoActivo && colaPrincipal.tamaño() >= 6) {
                int n = colaPrincipal.tamaño();
                String original = (mensajeFijo != null) ? mensajeFijo : UtilTexto.textoAleatorio10(aleatorio);
                String finalizado = modeloError.aplicarErrores(original, n);
                int duracion = n + 2;
                tiempoFinJuego = minuto + duracion;
                juegoActivo = true;
                metrica.sumarUso(duracion);
                int h = UtilTexto.distanciaHamming(original, finalizado);
                metrica.registrarPartida(minuto, n, duracion, original, finalizado, h);

                if (traza) {
                    System.out.printf("[t=%d] INICIO_JUEGO N=%d dur=%d original=%s final=%s hamming=%d%n",
                            minuto, n, duracion, original, finalizado, h);
                }

                for (int i = 0; i < n; i++) colaPrincipal.desencolar();
            }

            int llegadas = 0;
            if (minuto >= 0 && minuto <= 9) {
                llegadas = UtilTexto.enteroUniforme(aleatorio, 0, 2);
                metrica.llegadosFaseA += llegadas;
            }
            if (minuto == 10 || minuto == 13 || minuto == 16 || minuto == 19
                    || minuto == 22 || minuto == 25 || minuto == 28) {
                if (UtilTexto.bernoulli(aleatorio, 0.5)) {
                    llegadas++;
                    metrica.llegadosFaseB++;
                }
            }

            if (llegadas > 0) {
                if (juegoActivo) {
                    for (int i = 0; i < llegadas; i++) salaDeEspera.encolar(1);
                    if (traza) System.out.printf("[t=%d] LLEGAN %d -> SALA%n", minuto, llegadas);
                } else {
                    for (int i = 0; i < llegadas; i++) colaPrincipal.encolar(1);
                    if (traza) System.out.printf("[t=%d] LLEGAN %d -> COLA%n", minuto, llegadas);
                }
            }
        }

        if (traza) System.out.println("[TRACE] Fin simulación");
        metrica.imprimirResumen(colaPrincipal.tamaño() + salaDeEspera.tamaño());
    }

    private void transferirSalaACola() {
        int movidos = 0;
        while (!salaDeEspera.vacia()) {
            colaPrincipal.encolar(salaDeEspera.desencolar());
            movidos++;
        }
        if (traza) System.out.printf("          TRANSFER sala→cola (%d movidos)%n", movidos);
    }
}

