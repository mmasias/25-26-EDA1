import java.util.Random;

public class TelefonoDescacharrado {

    private static final int TIEMPO_TOTAL_LUDOTECA = 120;
    private static final int MIN_NINIOS_PARA_JUGAR = 5;
    private static final int TIEMPO_ESCRIBIR_MENSAJE = 1;
    private static final int LONGITUD_MENSAJE = 10;
    private static final int MAX_NINIOS = 100;

    private Nino[] colaEspera;
    private int inicioColaEspera;
    private int finColaEspera;
    private int tamColaEspera;

    private Nino[] niniosJugando;
    private int numNiniosJugando;

    private Nino[] niniosConLydia;
    private int numNiniosConLydia;

    private ResultadoJuego[] resultadosJuegos;
    private int numResultados;

    private boolean juegoEnCurso;
    private int tiempoActual;
    private int numeroJuego;
    private Random random;

    public TelefonoDescacharrado() {
        this.colaEspera = new Nino[MAX_NINIOS];
        this.inicioColaEspera = 0;
        this.finColaEspera = 0;
        this.tamColaEspera = 0;

        this.niniosJugando = new Nino[MAX_NINIOS];
        this.numNiniosJugando = 0;

        this.niniosConLydia = new Nino[MAX_NINIOS];
        this.numNiniosConLydia = 0;

        this.resultadosJuegos = new ResultadoJuego[MAX_NINIOS];
        this.numResultados = 0;

        this.juegoEnCurso = false;
        this.tiempoActual = 0;
        this.numeroJuego = 0;
        this.random = new Random();
    }

    private void ofrecerColaEspera(Nino nino) {
        if (tamColaEspera < MAX_NINIOS) {
            colaEspera[finColaEspera] = nino;
            finColaEspera = (finColaEspera + 1) % MAX_NINIOS;
            tamColaEspera++;
        }
    }

    private Nino sacarDeColaEspera() {
        if (tamColaEspera == 0) {
            return null;
        }
        Nino nino = colaEspera[inicioColaEspera];
        colaEspera[inicioColaEspera] = null;
        inicioColaEspera = (inicioColaEspera + 1) % MAX_NINIOS;
        tamColaEspera--;
        return nino;
    }

    private int getTamColaEspera() {
        return tamColaEspera;
    }

    private void limpiarNiniosJugando() {
        for (int i = 0; i < numNiniosJugando; i++) {
            niniosJugando[i] = null;
        }
        numNiniosJugando = 0;
    }

    private void agregarNinoJugando(Nino nino) {
        if (numNiniosJugando < MAX_NINIOS) {
            niniosJugando[numNiniosJugando] = nino;
            numNiniosJugando++;
        }
    }

    private void agregarNinoConLydia(Nino nino) {
        if (numNiniosConLydia < MAX_NINIOS) {
            niniosConLydia[numNiniosConLydia] = nino;
            numNiniosConLydia++;
        }
    }

    private void limpiarNiniosConLydia() {
        for (int i = 0; i < numNiniosConLydia; i++) {
            niniosConLydia[i] = null;
        }
        numNiniosConLydia = 0;
    }

    private void agregarResultado(ResultadoJuego resultado) {
        if (numResultados < MAX_NINIOS) {
            resultadosJuegos[numResultados] = resultado;
            numResultados++;
        }
    }

    public void simular() {
        System.out.println("=== SIMULACION LUDOTECA - TELEFONO DESCACHARRADO ===");
        System.out.println("Duracion total: " + TIEMPO_TOTAL_LUDOTECA + " minutos\n");

        while (tiempoActual < TIEMPO_TOTAL_LUDOTECA) {
            simularLlegadaNinios();

            if (!juegoEnCurso && getTamColaEspera() >= MIN_NINIOS_PARA_JUGAR) {
                iniciarJuego();
            }

            if (juegoEnCurso) {
                procesarJuego();
            }

            tiempoActual++;
        }

        mostrarEstadisticasFinales();
    }

    private void simularLlegadaNinios() {
        int niniosLlegan = 0;

        if (tiempoActual < 10) {
            niniosLlegan = random.nextInt(3);
        } else if (tiempoActual < 30) {
            if (tiempoActual % 3 == 0 && random.nextDouble() < 0.5) {
                niniosLlegan = 1;
            }
        }

        for (int i = 0; i < niniosLlegan; i++) {
            Nino nuevoNinio = new Nino("Ninio" + (getTotalNinios() + 1));

            if (juegoEnCurso) {
                agregarNinoConLydia(nuevoNinio);
                System.out.println("Minuto " + tiempoActual + ": " + nuevoNinio.getNombre() +
                        " llega y se queda con Lydia (juego en curso)");
            } else {
                ofrecerColaEspera(nuevoNinio);
                System.out.println("Minuto " + tiempoActual + ": " + nuevoNinio.getNombre() +
                        " llega y se une a la cola");
            }
        }
    }

    private void iniciarJuego() {
        numeroJuego++;
        juegoEnCurso = true;

        limpiarNiniosJugando();
        int niniosAJugar = Math.min(getTamColaEspera(), 5 + random.nextInt(4));

        for (int i = 0; i < niniosAJugar && getTamColaEspera() > 0; i++) {
            agregarNinoJugando(sacarDeColaEspera());
        }

        System.out.println("\nAisha limpia la pizarra del salon");
        for (int i = 0; i < numNiniosJugando; i++) {
            Nino nino = niniosJugando[i];
            System.out.println(nino.getNombre() + " limpia su pizarrin");
        }

        String mensajeOriginal = generarMensajeAleatorio();

        System.out.println("\n--- JUEGO " + numeroJuego + " INICIADO (Minuto " + tiempoActual + ") ---");
        System.out.println("Ninios participantes: " + numNiniosJugando);
        System.out.println("Mensaje original de Aisha: \"" + mensajeOriginal + "\"");

        String mensajeFinal = simularPasoMensaje(mensajeOriginal);

        ResultadoJuego resultado = new ResultadoJuego(numeroJuego, mensajeOriginal,
                mensajeFinal, numNiniosJugando, tiempoActual);
        agregarResultado(resultado);

        int tiempoJuego = numNiniosJugando + 1;
        tiempoActual += tiempoJuego - 1;

        System.out.println("Mensaje final en pizarra: \"" + mensajeFinal + "\"");
        System.out.println("Precision: " + calcularPrecision(mensajeOriginal, mensajeFinal) + "%");
        System.out.println("Juego terminado en minuto " + tiempoActual);

        finalizarJuego();
    }

    private String simularPasoMensaje(String mensajeOriginal) {
        String mensajeActual = mensajeOriginal;

        for (int i = 0; i < numNiniosJugando; i++) {
            Nino ninio = niniosJugando[i];

            mensajeActual = deformarMensaje(mensajeActual);

            if (i == 0) {
                System.out.println("  " + ninio.getNombre() + " (primero) recibe: \"" + mensajeActual + "\"");
            } else if (i == numNiniosJugando - 1) {
                System.out.println("  " + ninio.getNombre() + " (ultimo) escribe en pizarra: \"" + mensajeActual + "\"");
            } else {
                System.out.println("  " + ninio.getNombre() + " pasa: \"" + mensajeActual + "\"");
            }
        }

        return mensajeActual;
    }

    private String deformarMensaje(String mensaje) {
        if (random.nextDouble() < 0.3) {
            char[] chars = mensaje.toCharArray();

            int cambios = random.nextInt(2) + 1;

            for (int i = 0; i < cambios; i++) {
                int posicion = random.nextInt(chars.length);
                char nuevaLetra = (char) ('A' + random.nextInt(26));
                chars[posicion] = nuevaLetra;
            }

            return new String(chars);
        }

        return mensaje;
    }

    private void finalizarJuego() {
        juegoEnCurso = false;

        for (int i = 0; i < numNiniosConLydia; i++) {
            Nino ninio = niniosConLydia[i];
            ofrecerColaEspera(ninio);
            System.out.println("  " + ninio.getNombre() + " pasa de Lydia a la cola");
        }
        limpiarNiniosConLydia();

        for (int i = 0; i < numNiniosJugando; i++) {
            Nino ninio = niniosJugando[i];
            ofrecerColaEspera(ninio);
        }

        System.out.println("Cola actual: " + getTamColaEspera() + " ninios\n");
    }

    private void procesarJuego() {
    }

    private String generarMensajeAleatorio() {
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            mensaje.append(letra);
        }
        return mensaje.toString();
    }

    private double calcularPrecision(String original, String finalMsg) {
        if (original.length() != finalMsg.length()) {
            return 0.0;
        }

        int letrasCorrectas = 0;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == finalMsg.charAt(i)) {
                letrasCorrectas++;
            }
        }

        return (letrasCorrectas * 100.0) / original.length();
    }

    private int getTotalNinios() {
        return getTamColaEspera() + numNiniosJugando + numNiniosConLydia;
    }

    private void mostrarEstadisticasFinales() {
        System.out.println("\n=== ESTADISTICAS FINALES ===");
        System.out.println("Juegos realizados: " + numeroJuego);
        System.out.println("Total de ninios que participaron: " + getTotalNinios());

        if (numResultados > 0) {
            double sumaPrecision = 0.0;
            for (int i = 0; i < numResultados; i++) {
                ResultadoJuego resultado = resultadosJuegos[i];
                sumaPrecision += calcularPrecision(resultado.getMensajeOriginal(), resultado.getMensajeFinal());
            }
            double precisionPromedio = sumaPrecision / numResultados;

            System.out.println("Precision promedio: " + formatearDecimal(precisionPromedio) + "%");

            System.out.println("\nDetalle de juegos:");
            for (int i = 0; i < numResultados; i++) {
                ResultadoJuego resultado = resultadosJuegos[i];
                System.out.println(resultado.toString());
            }
        }
    }

    private String formatearDecimal(double valor) {
        return String.valueOf(Math.round(valor * 10.0) / 10.0);
    }

    public static void main(String[] args) {
        TelefonoDescacharrado simulacion = new TelefonoDescacharrado();
        simulacion.simular();
    }
}
