public class TelefonoEscacharrado {
    private final int MAXIMOS_ESPERANDO = 100;
    private final int DURACION_LUDOTECA = 120;
    private final int MINIMO_NIÑOS_JUEGO = 6;
    private final int LONGITUD_MENSAJE = 10;
    private final int TIEMPO_EXTRA = 2;
    private final int MAXIMO_LLEGADAS = 3;
    private final int MINUTOS_LLEGADA_RAPIDA = 10;
    private final int MINUTOS_LLEGADA_LENTA = 30;
    private final int MODULO_LLEGADA_LENTA = 3;

    private ColaNiños cola;
    private Niño[] esperando;
    private int esperandoContador;
    private int tiempo;
    private int totalMinutos;
    private int indiceNombreActual;
    private String[] nombres;
    private Mensaje texto;
    private java.util.Random random;
    private boolean juegoEnCurso;

    public TelefonoEscacharrado() {
        this.cola = new ColaNiños();
        this.esperando = new Niño[MAXIMOS_ESPERANDO];
        this.esperandoContador = 0;
        this.tiempo = 0;
        this.totalMinutos = DURACION_LUDOTECA;
        this.indiceNombreActual = 0;
        this.juegoEnCurso = false;

        this.nombres = new String[]{
            "Andrés", "Beatriz", "Camilo", "Diana", "Esteban",
            "Fernanda", "Guillermo", "Helena", "Ismael", "Julieta",
            "Kevin", "Lorena", "Mateo", "Natalia", "Óscar",
            "Paula", "Rafael", "Sofía", "Tomás", "Valeria"
        };

        this.texto = new Mensaje();
        this.random = new java.util.Random();
    }

    public void iniciarJuego() {
        while (tiempo < totalMinutos) {
            if (tiempo < MINUTOS_LLEGADA_RAPIDA) {
                int llegadas = random.nextInt(MAXIMO_LLEGADAS);
                for (int i = 0; i < llegadas && indiceNombreActual < nombres.length; i++) {
                    Niño nuevo = new Niño(nombres[indiceNombreActual++]);
                    if (juegoEnCurso) {
                        esperando[esperandoContador++] = nuevo;
                    } else {
                        cola.añadirNiño(nuevo);
                    }
                }
            } else if (tiempo < MINUTOS_LLEGADA_LENTA) {
                if (random.nextBoolean() && indiceNombreActual < nombres.length && tiempo % MODULO_LLEGADA_LENTA == 0) {
                    Niño nuevo = new Niño(nombres[indiceNombreActual++]);
                    if (juegoEnCurso) {
                        esperando[esperandoContador++] = nuevo;
                    } else {
                        cola.añadirNiño(nuevo);
                    }
                }
            }

            if (cola.tamañoCola() >= MINIMO_NIÑOS_JUEGO) {
                juegoEnCurso = true;
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
                juegoEnCurso = false;

                tiempo += cola.tamañoCola() + TIEMPO_EXTRA;
            } else {
                tiempo++;
            }
        }
    }

    private void limpiarPizarras() {
        texto.mensajeLn("Limpieza de pizarras");
        Niño[] ninos = cola.obtenerNiñosCola();
        for (int i = 0; i < cola.tamañoCola(); i++) {
            ninos[i].limpiarPizarra();
        }
    }

    private String generarMensaje() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        for (int i = 0; i < LONGITUD_MENSAJE; i++) {
            mensaje = mensaje + letras.charAt(random.nextInt(letras.length()));
        }
        return mensaje;
    }

    private String pasarMensaje(Niño[] ninos, int cantidad, String mensaje) {
        String mensajeActual = mensaje;
        for (int i = 0; i < cantidad; i++) {
            mensajeActual = ninos[i].escribirEnPizarra(mensajeActual, random);
        }
        return mensajeActual;
    }
}
