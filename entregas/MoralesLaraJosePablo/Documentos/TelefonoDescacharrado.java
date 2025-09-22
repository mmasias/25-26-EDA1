import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TelefonoDescacharrado {

    private static final int TIEMPO_TOTAL_LUDOTECA = 120;
    private static final int MIN_NINIOS_PARA_JUGAR = 5;
    private static final int TIEMPO_ESCRIBIR_MENSAJE = 1;
    private static final int LONGITUD_MENSAJE = 10;

    private Queue<Nino> colaEspera;
    private List<Nino> niniosJugando;
    private List<Nino> niniosConLydia;
    private boolean juegoEnCurso;
    private int tiempoActual;
    private int numeroJuego;
    private Random random;

    private List<ResultadoJuego> resultadosJuegos;

    public TelefonoDescacharrado() {
        this.colaEspera = new LinkedList<Nino>();
        this.niniosJugando = new ArrayList<Nino>();
        this.niniosConLydia = new ArrayList<Nino>();
        this.juegoEnCurso = false;
        this.tiempoActual = 0;
        this.numeroJuego = 0;
        this.random = new Random();
        this.resultadosJuegos = new ArrayList<ResultadoJuego>();
    }

    public void simular() {
        System.out.println("=== SIMULACION LUDOTECA - TELEFONO DESCACHARRADO ===");
        System.out.println("Duracion total: " + TIEMPO_TOTAL_LUDOTECA + " minutos\n");

        while (tiempoActual < TIEMPO_TOTAL_LUDOTECA) {
            simularLlegadaNinios();

            if (!juegoEnCurso && colaEspera.size() >= MIN_NIÑOS_PARA_JUGAR) {
                iniciarJuego();
            }

            if (juegoEnCurso) {
                procesarJuego();
            }

            tiempoActual++;
        }

        mostrarEstadisticasFinales();
    }

    private void simularLlegadaNinos() {
        int niniosLlegan = 0;

        if (tiempoActual < 10) {
            niniosLlegan = random.nextInt(3);
        } else if (tiempoActual < 30) {
            if (tiempoActual % 3 == 0 && random.nextDouble() < 0.5) {
                niniosLlegan = 1;
            }
        }

        for (int i = 0; i < niñosLlegan; i++) {
            Nino nuevoNinio = new Ninio("Ninio" + (getTotalNinios() + 1));

            if (juegoEnCurso) {
                niniosConLydia.add(nuevoNinio);
                System.out.println("Minuto " + tiempoActual + ": " + nuevoNinio.getNombre() +
                        " llega y se queda con Lydia (juego en curso)");
            } else {
                colaEspera.offer(nuevoNinio);
                System.out.println("Minuto " + tiempoActual + ": " + nuevoNinio.getNombre() +
                        " llega y se une a la cola");
            }
        }
    }

    private void iniciarJuego() {
        numeroJuego++;
        juegoEnCurso = true;

        niniosJugando.clear();
        int niniosAJugar = Math.min(colaEspera.size(), 5 + random.nextInt(4));

        for (int i = 0; i < niniosAJugar && !colaEspera.isEmpty(); i++) {
            niniosJugando.add(colaEspera.poll());
        }

        System.out.println("\nAisha limpia la pizarra del salon");
        for (int i = 0; i < niniosJugando.size(); i++) {
            Nino nino = niniosJugando.get(i);
            System.out.println(ninio.getNombre() + " limpia su pizarrin");
        }

        String mensajeOriginal = generarMensajeAleatorio();

        System.out.println("\n--- JUEGO " + numeroJuego + " INICIADO (Minuto " + tiempoActual + ") ---");
        System.out.println("Ninios participantes: " + niniosJugando.size());
        System.out.println("Mensaje original de Aisha: \"" + mensajeOriginal + "\"");

        String mensajeFinal = simularPasoMensaje(mensajeOriginal);

        ResultadoJuego resultado = new ResultadoJuego(numeroJuego, mensajeOriginal,
                mensajeFinal, niniosJugando.size(),
                tiempoActual);
        resultadosJuegos.add(resultado);

        int tiempoJuego = niniosJugando.size() + 1;
        tiempoActual += tiempoJuego - 1;

        System.out.println("Mensaje final en pizarra: \"" + mensajeFinal + "\"");
        System.out.println("Precision: " + calcularPrecision(mensajeOriginal, mensajeFinal) + "%");
        System.out.println("Juego terminado en minuto " + tiempoActual);

        finalizarJuego();
    }

    private String simularPasoMensaje(String mensajeOriginal) {
        String mensajeActual = mensajeOriginal;

        for (int i = 0; i < niniosJugando.size(); i++) {
            Ninio ninio = niniosJugando.get(i);

            mensajeActual = deformarMensaje(mensajeActual);

            if (i == 0) {
                System.out.println("  " + ninio.getNombre() + " (primero) recibe: \"" + mensajeActual + "\"");
            } else if (i == niniosJugando.size() - 1) {
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

        for (Nino ninio : niniosConLydia) {
            colaEspera.offer(ninio);
            System.out.println("  " + nino.getNombre() + " pasa de Lydia a la cola");
        }
        ninosConLydia.clear();

        for (Nino ninio : niniosJugando) {
            colaEspera.offer(ninio);
        }

        System.out.println("Cola actual: " + colaEspera.size() + " ninios\n");
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
        return colaEspera.size() + niñosJugando.size() + ninosConLydia.size();
    }

    private void mostrarEstadisticasFinales() {
        System.out.println("\n=== ESTADISTICAS FINALES ===");
        System.out.println("Juegos realizados: " + numeroJuego);
        System.out.println("Total de ninios que participaron: " + getTotalNinios());

        if (!resultadosJuegos.isEmpty()) {
            double sumaPrecision = 0.0;
            for (int i = 0; i < resultadosJuegos.size(); i++) {
                ResultadoJuego resultado = resultadosJuegos.get(i);
                sumaPrecision += calcularPrecision(resultado.getMensajeOriginal(), resultado.getMensajeFinal());
            }
            double precisionPromedio = sumaPrecision / resultadosJuegos.size();

            System.out.println("Precision promedio: " + formatearDecimal(precisionPromedio) + "%");

            System.out.println("\nDetalle de juegos:");
            for (int i = 0; i < resultadosJuegos.size(); i++) {
                ResultadoJuego resultado = resultadosJuegos.get(i);
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
