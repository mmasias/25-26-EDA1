public class Juego {
    private static final int TAMANO_MINIMO_FILA = 5;
    private static final int DURACION_EXTRA = 2;
    private static final int MAX_NINOS = 200;

    private Nino[] fila;
    private int cantidadFila;
    private Nino[] esperando;
    private int cantidadEsperando;
    private int tiempoJuego;
    private GeneradorMensaje generador;

    public Juego() {
        fila = new Nino[MAX_NINOS];
        esperando = new Nino[MAX_NINOS];
        cantidadFila = 0;
        cantidadEsperando = 0;
        tiempoJuego = -1;
        generador = new GeneradorMensaje();
    }

    public void recibirNino(Nino nino) {
        if (tiempoJuego == -1) {
            fila[cantidadFila] = nino;
            cantidadFila++;
        } else {
            esperando[cantidadEsperando] = nino;
            cantidadEsperando++;
        }
    }

    public void avanzarTiempo() {
        if (tiempoJuego == -1 && cantidadFila > TAMANO_MINIMO_FILA) {
            tiempoJuego = iniciarJuego();
        }
        if (tiempoJuego > 0) {
            tiempoJuego--;
        } else if (tiempoJuego == 0) {
            for (int i = 0; i < cantidadEsperando; i++) {
                fila[cantidadFila] = esperando[i];
                cantidadFila++;
            }
            cantidadEsperando = 0;
            tiempoJuego = -1;
        }
    }

    private int iniciarJuego() {
        String mensaje = generador.generarMensaje();
        String recibido = mensaje;
        int duracion = cantidadFila + DURACION_EXTRA;
        for (int i = 0; i < cantidadFila; i++) {
            recibido = fila[i].recibir(recibido);
        }
        return duracion;
    }
}
