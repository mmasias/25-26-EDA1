
public class TelefonoDescacharrado {
    private static final int MAXIMOS_ESPERANDO = 100;
    private static final int DURACION_LUDOTECA = 120;
    private static final int MINIMO_NIÑOS_JUEGO = 6;
    private static final int LONGITUD_MENSAJE = 10;
    private static final int TIEMPO_EXTRA = 2;
    private static final int MAXIMO_LLEGADAS = 3;
    private static final int MINUTOS_LLEGADA_RAPIDA = 10;
    private static final int MINUTOS_LLEGADA_LENTA = 30;
    private static final int MODULO_LLEGADA_LENTA = 3;

    private ColaNiños cola;
    private Niño[] esperando;
    private int esperandoContador;
    private int tiempo;
    private int totalMinutos;
    private int indiceNombreActual;
    private String[] nombres;
    private Mensaje texto;
    private java.util.Random random;

    public TelefonoDescacharrado() {
        this.cola = new ColaNiños();
        this.esperando = new Niño[MAXIMOS_ESPERANDO];
        this.esperandoContador = 0;
        this.tiempo = 0;
        this.totalMinutos = DURACION_LUDOTECA;
        this.indiceNombreActual = 0;
        this.nombres = new String[]{
            "Neymar", "Luis", "Messi", "Juan", "Laro", "Mario",
            "Beltran", "Pablo", "Alonso", "Ivan", "Valentin",
            "Lionel", "Sergio", "Dani", "Cristian"
        };
        this.texto = new Mensaje();
        this.random = new java.util.Random();
    }

    public void iniciarJuego() {
        while (tiempo < totalMinutos) {
            if (tiempo < MINUTOS_LLEGADA_RAPIDA) {
                int llegadas = random.nextInt(MAXIMO_LLEGADAS + 1);
                for (int i = 0; i < llegadas && indiceNombreActual < nombres.length; i++) {
                    cola.añadirNiño(new Niño(nombres[indiceNombreActual++]));
                }
            } else if (tiempo < MINUTOS_LLEGADA_LENTA) {
                if (random.nextBoolean() && indiceNombreActual < nombres.length && tiempo % MODULO_LLEGADA_LENTA == 0) {
                    cola.añadirNiño(new Niño(nombres[indiceNombreActual++]));
                }
            }
            if (cola.tamañoCola() >= MINIMO_NIÑOS_JUEGO) {
                limpiarPizarras();
                String mensaje = generarMensaje();
                texto.mensajeLn("Juego en minuto " + tiempo + ":");
                texto.mensajeLn("Mensaje original: " + mensaje);
                String mensajeFinal = pasarMensaje(cola.obtenerNiñosCola(), cola.tamañoCola(), mensaje);
                texto.mensajeLn("Mensaje final en la pizarra: " + mensajeFinal);
                for (int i = 0; i < esperandoContador; i++) {
                    cola.añadirNiño(esperando[i]);
                }
                esperandoContador = 0;
                tiempo += cola.tamañoCola() + TIEMPO_EXTRA;
            } else {
                tiempo++;
            }
        }
    }

    private void limpiarPizarras() {
        texto.mensajeLn("Limpieza de pizarras");
        Niño[] niños = cola.obtenerNiñosCola();
        for (int i = 0; i < cola.tamañoCola(); i++) {
            niños[i].limpiarPizarra();
        }
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            mensaje.append(letras.charAt(random.nextInt(letras.length())));
        }
        return mensaje.toString();
    }

    private String pasarMensaje(Niño[] niños, int cantidad, String mensaje) {
        String mensajeActual = mensaje;
        for (int i = 0; i < cantidad; i++) {
            mensajeActual = niños[i].escribirEnPizarra(mensajeActual, random);
        }
        return mensajeActual;
    }
}
