import java.util.Scanner;

public class TelefonoDescacharrado extends Juego {
    private static final int MINUTOS_APERTURA = 120;
    private static final int LONGITUD_MENSAJE = 10;
    private static final int TAMANO_MINIMO_PARA_JUGAR = 6;
    private static final int PRIMER_TRAMO_MINUTOS = 10;
    private static final int LLEGADAS_MIN_PRIMER_TRAMO = 0;
    private static final int LLEGADAS_MAX_PRIMER_TRAMO = 2;
    private static final int SEGUNDO_TRAMO_MINUTOS = 20;
    private static final int PERIODO_SEGUNDO_TRAMO = 3;
    private static final int PROBABILIDAD_LLEGADA_SEGUNDO_TRAMO_PORCENTAJE = 50;
    private static final int DURACION_LECTURA_INICIAL = 1;
    private static final int DURACION_PASO_POR_NINO = 1;
    private static final int DURACION_ESCRITURA_FINAL = 1;

    private final Lydia lydia;
    private final Aisha aisha;
    private final ColaNinos colaAisha;
    private final ColaNinos colaLydia;
    private final Scanner entradaConsola;

    public TelefonoDescacharrado() {
        this.lydia = new Lydia("Lydia");
        this.aisha = new Aisha("Aisha");
        this.colaAisha = new ColaNinos(500);
        this.colaLydia = new ColaNinos(500);
        this.entradaConsola = new Scanner(System.in);
    }

    public void jugar() {
        int minutoActual;
        minutoActual = 0;

        int juegosRealizados;
        juegosRealizados = 0;

        boolean juegoEnCurso;
        juegoEnCurso = false;

        int duracionJuegoRestante;
        duracionJuegoRestante = 0;

        String mensajeInicial;
        mensajeInicial = "";

        String mensajeEnCurso;
        mensajeEnCurso = "";

        int posicionPasoActual;
        posicionPasoActual = 0;

        char[][] historialMensajes;
        historialMensajes = new char[0][0];

        System.out.println("Inicio de la simulacion");
        while (minutoActual < MINUTOS_APERTURA) {
            boolean dentroPrimerTramo;
            dentroPrimerTramo = minutoActual < PRIMER_TRAMO_MINUTOS;

            boolean dentroSegundoTramo;
            dentroSegundoTramo = minutoActual >= PRIMER_TRAMO_MINUTOS
                    && minutoActual < PRIMER_TRAMO_MINUTOS + SEGUNDO_TRAMO_MINUTOS;

            int llegadasEsteMinuto;
            llegadasEsteMinuto = 0;

            if (!juegoEnCurso) {
                if (dentroPrimerTramo) {
                    llegadasEsteMinuto = Utilidades.numeroEnteroAleatorio(LLEGADAS_MIN_PRIMER_TRAMO,
                            LLEGADAS_MAX_PRIMER_TRAMO);
                } else {
                    llegadasEsteMinuto = 0;
                }
            } else {
                llegadasEsteMinuto = 0;
            }

            int indiceLlegada;
            indiceLlegada = 0;
            while (indiceLlegada < llegadasEsteMinuto) {
                Nino ninoNuevo;
                ninoNuevo = lydia.recibirNino(minutoActual);
                colaAisha.encolar(ninoNuevo);
                indiceLlegada = indiceLlegada + 1;
            }

            if (!juegoEnCurso) {
                boolean esMinutoDeIntentoLlegada;
                esMinutoDeIntentoLlegada = dentroSegundoTramo && minutoActual % PERIODO_SEGUNDO_TRAMO == 0;

                if (esMinutoDeIntentoLlegada) {
                    boolean ocurreLlegada;
                    ocurreLlegada = Utilidades.ocurreConPorcentaje(PROBABILIDAD_LLEGADA_SEGUNDO_TRAMO_PORCENTAJE);
                    if (ocurreLlegada) {
                        Nino ninoNuevoTramoDos;
                        ninoNuevoTramoDos = lydia.recibirNino(minutoActual);
                        colaAisha.encolar(ninoNuevoTramoDos);
                    }
                }
            }

            if (!juegoEnCurso) {
                boolean haySuficientesNinos;
                haySuficientesNinos = colaAisha.tamano() >= TAMANO_MINIMO_PARA_JUGAR;
                if (haySuficientesNinos) {
                    String mensajeCreado;
                    mensajeCreado = Mensaje.generarMensajeAleatorio(LONGITUD_MENSAJE);
                    mensajeInicial = mensajeCreado;
                    mensajeEnCurso = mensajeInicial;

                    int cantidadNinosParaRonda;
                    cantidadNinosParaRonda = colaAisha.tamano();

                    historialMensajes = new char[cantidadNinosParaRonda + 1][LONGITUD_MENSAJE];
                    int indiceColumnaInicial;
                    indiceColumnaInicial = 0;
                    while (indiceColumnaInicial < LONGITUD_MENSAJE) {
                        historialMensajes[0][indiceColumnaInicial] = mensajeInicial.charAt(indiceColumnaInicial);
                        indiceColumnaInicial = indiceColumnaInicial + 1;
                    }

                    juegoEnCurso = true;
                    posicionPasoActual = 0;
                    duracionJuegoRestante = DURACION_LECTURA_INICIAL + cantidadNinosParaRonda * DURACION_PASO_POR_NINO
                            + DURACION_ESCRITURA_FINAL;

                    System.out.println("Juego iniciado en el minuto " + minutoActual);
                    System.out.println("Mensaje original: " + mensajeInicial);
                }
            } else {
                boolean esLecturaInicial;
                esLecturaInicial = posicionPasoActual == 0;

                boolean esEscrituraFinal;
                esEscrituraFinal = posicionPasoActual == DURACION_LECTURA_INICIAL
                        + colaAisha.tamano() * DURACION_PASO_POR_NINO;

                boolean esPasoDeNino;
                esPasoDeNino = !esLecturaInicial && !esEscrituraFinal && duracionJuegoRestante > 0;

                if (esLecturaInicial) {
                    aisha.limpiarPizarra();
                    aisha.entregarMensajeAlPrimero();
                } else {
                    if (esPasoDeNino) {
                        int indiceNinoQueRecibe;
                        indiceNinoQueRecibe = posicionPasoActual - DURACION_LECTURA_INICIAL;

                        Nino ninoEnTurno;
                        ninoEnTurno = colaAisha.obtener(indiceNinoQueRecibe);

                        String mensajeRecibido;
                        mensajeRecibido = mensajeEnCurso;

                        String mensajeEscritoPorNino;
                        mensajeEscritoPorNino = ninoEnTurno.escribirMensaje(mensajeRecibido);

                        int indiceColumna;
                        indiceColumna = 0;
                        while (indiceColumna < LONGITUD_MENSAJE) {
                            historialMensajes[indiceNinoQueRecibe + 1][indiceColumna] = mensajeEscritoPorNino
                                    .charAt(indiceColumna);
                            indiceColumna = indiceColumna + 1;
                        }

                        mensajeEnCurso = mensajeEscritoPorNino;
                    } else {
                        if (esEscrituraFinal) {
                            int indiceUltimoNino;
                            indiceUltimoNino = colaAisha.tamano() - 1;

                            Nino ultimo;
                            ultimo = colaAisha.obtener(indiceUltimoNino);

                            ultimo.escribirEnPizarra(mensajeEnCurso);

                            System.out.println("Minuto " + minutoActual + " fin de juego");
                            System.out.println("Mensaje final: " + mensajeEnCurso);

                            int diferencias;
                            diferencias = Mensaje.contarDiferencias(mensajeInicial, mensajeEnCurso);
                            System.out.println("Diferencias respecto al original: " + diferencias);

                            int indiceDesencolar;
                            indiceDesencolar = 0;
                            int cantidadPorVaciar;
                            cantidadPorVaciar = colaAisha.tamano();
                            while (indiceDesencolar < cantidadPorVaciar) {
                                colaAisha.desencolar();
                                indiceDesencolar = indiceDesencolar + 1;
                            }

                            int cantidadPendientesConLydia;
                            cantidadPendientesConLydia = colaLydia.tamano();
                            int indiceTraspaso;
                            indiceTraspaso = 0;
                            while (indiceTraspaso < cantidadPendientesConLydia) {
                                Nino ninoPendiente;
                                ninoPendiente = colaLydia.desencolar();
                                colaAisha.encolar(ninoPendiente);
                                indiceTraspaso = indiceTraspaso + 1;
                            }

                            juegosRealizados = juegosRealizados + 1;
                            juegoEnCurso = false;
                            posicionPasoActual = 0;
                            duracionJuegoRestante = 0;
                            historialMensajes = new char[0][0];
                            mensajeInicial = "";
                            mensajeEnCurso = "";
                        }
                    }
                }

                duracionJuegoRestante = duracionJuegoRestante - 1;
                posicionPasoActual = posicionPasoActual + 1;

                boolean esMinutoLlegadasDuranteJuegoPrimerTramo;
                esMinutoLlegadasDuranteJuegoPrimerTramo = minutoActual < PRIMER_TRAMO_MINUTOS;

                boolean esMinutoLlegadasDuranteJuegoSegundoTramo;
                esMinutoLlegadasDuranteJuegoSegundoTramo = minutoActual >= PRIMER_TRAMO_MINUTOS
                        && minutoActual < PRIMER_TRAMO_MINUTOS + SEGUNDO_TRAMO_MINUTOS
                        && minutoActual % PERIODO_SEGUNDO_TRAMO == 0;

                int llegadasDuranteJuego;
                llegadasDuranteJuego = 0;

                if (esMinutoLlegadasDuranteJuegoPrimerTramo) {
                    llegadasDuranteJuego = Utilidades.numeroEnteroAleatorio(LLEGADAS_MIN_PRIMER_TRAMO,
                            LLEGADAS_MAX_PRIMER_TRAMO);
                } else {
                    if (esMinutoLlegadasDuranteJuegoSegundoTramo) {
                        boolean ocurreLlegadaDuranteJuego;
                        ocurreLlegadaDuranteJuego = Utilidades
                                .ocurreConPorcentaje(PROBABILIDAD_LLEGADA_SEGUNDO_TRAMO_PORCENTAJE);
                        if (ocurreLlegadaDuranteJuego) {
                            llegadasDuranteJuego = 1;
                        } else {
                            llegadasDuranteJuego = 0;
                        }
                    } else {
                        llegadasDuranteJuego = 0;
                    }
                }

                int indiceLlegadaLydia;
                indiceLlegadaLydia = 0;
                while (indiceLlegadaLydia < llegadasDuranteJuego) {
                    Nino ninoEsperando;
                    ninoEsperando = lydia.recibirNino(minutoActual);
                    colaLydia.encolar(ninoEsperando);
                    indiceLlegadaLydia = indiceLlegadaLydia + 1;
                }
            }

            minutoActual = minutoActual + 1;
        }

        System.out.println("Fin de la simulacion");
        System.out.println("Juegos realizados: " + juegosRealizados);
    }
}
