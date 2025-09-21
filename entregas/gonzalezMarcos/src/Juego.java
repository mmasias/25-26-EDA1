public class Juego {
    private static final int INTERVALO_RAPIDO = 10;
    private static final int MAX_LLEGADAS = 2;
    private static final int DURACION = 120;
    private static final int MOD_LENTO = 3;
    private static final int TIEMPO_EXTRA = 2;
    private static final int LONGITUD_MENSAJE = 10;
    private static final int INTERVALO_LENTO = 30;
    private static final int MIN_NINOS = 6;
    private Cola cola;
    private int tiempo;
    private int totalMinutos;

    public Juego() {
        cola = new Cola();
        tiempo = 0;
        totalMinutos = DURACION;
    }

    public void iniciarJuego() {
        int contadorNinos = 1;

        while (tiempo < totalMinutos) {
            System.out.println("\nMinuto " + tiempo + ":");

            if (tiempo < INTERVALO_RAPIDO) {
                int llegadas = (int) (Math.random() * (MAX_LLEGADAS + 1));
                for (int i = 0; i < llegadas; i++) {
                    Nino n = new Nino("Niño" + contadorNinos++);
                    cola.añadirNiño(n);
                    System.out.println("Llega el niño: " + n.getNombre());
                }

            } else if (tiempo < INTERVALO_LENTO) {
                if (Math.random() < 0.5 && tiempo % MOD_LENTO == 0) {
                    Nino n = new Nino("Niño" + contadorNinos++);
                    cola.añadirNiño(n);
                    System.out.println("Llega el niño: " + n.getNombre());
                }
            }

            System.out.println("Niños en la cola: " + cola);

            int tamañoCola = cola.tamañoCola();
            if (tamañoCola >= MIN_NINOS) {
                limpiarPizarras();

                String mensaje = generarMensaje();
                System.out.println("Mensaje original: " + mensaje);
                System.out.println("Mensaje final en la pizarra:");

                Nino[] ninosCola = cola.obtenerNiñosCola();
                for (int i = 0; i < ninosCola.length; i++) {
                    mensaje = ninosCola[i].escribirEnPizarra(mensaje);
                    System.out.println(ninosCola[i]);
                }

                tiempo += tamañoCola + TIEMPO_EXTRA;
            } else {
                tiempo++;
            }
        }
    }

    private void limpiarPizarras() {
        System.out.println("Limpiando pizarras...");
        Nino[] ninosCola = cola.obtenerNiñosCola();
        for (int i = 0; i < ninosCola.length; i++) {
            ninosCola[i].limpiarPizarra();
        }
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            mensaje += letras.charAt((int) (Math.random() * letras.length()));
        }
        return mensaje;
    }
}