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
    private boolean juegoEnCurso;

    public Juego() {
        fila = new Nino[MAX_NINOS];
        esperando = new Nino[MAX_NINOS];
        cantidadFila = 0;
        cantidadEsperando = 0;
        tiempoJuego = -1;
        generador = new GeneradorMensaje();
        juegoEnCurso = false;
    }

    public void recibirNino(Nino nino) {
        if (!juegoEnCurso) {
            fila[cantidadFila] = nino;
            cantidadFila++;
        } else {
            esperando[cantidadEsperando] = nino;
            cantidadEsperando++;
        }
    }

    public void avanzarTiempo() {
        if (!juegoEnCurso && cantidadFila > TAMANO_MINIMO_FILA) {
            iniciarJuego();
        }

        if (juegoEnCurso) {
            tiempoJuego--;
            if (tiempoJuego <= 0) {
                juegoEnCurso = false;
                // pasar los niños esperando a la fila
                for (int i = 0; i < cantidadEsperando; i++) {
                    fila[cantidadFila] = esperando[i];
                    cantidadFila++;
                }
                cantidadEsperando = 0;
            }
        }
    }

    private void iniciarJuego() {
        juegoEnCurso = true;
        tiempoJuego = cantidadFila + DURACION_EXTRA;
        System.out.println("Fila completa (> 5): Aisha inicia un juego");
        System.out.println("Aisha limpia la pizarra");
        System.out.println("Aisha pide a los niños que limpien sus pizarrines");
        System.out.println("Juego iniciado con " + cantidadFila + " participantes");

        String mensaje = generador.generarMensaje();
        System.out.println("Aisha escribe la palabra: " + mensaje);

        String recibido = mensaje;
        for (int i = 0; i < cantidadFila; i++) {
            System.out.println("Aisha muestra palabra al niño " + i + ": " + recibido);
            recibido = fila[i].recibir(recibido);
            if (i < cantidadFila - 1) {
                System.out.println("Niño " + i + " muestra al siguiente");
                System.out.println("Mensaje transmitido: " + recibido);
            }
        }

        System.out.println("Último niño va a la pizarra y escribe");
        System.out.println("Pizarra: \"" + recibido + "\"");
    }

    public void imprimirEstado() {
        System.out.print("Lydia:\nAisha:");
        for (int i = 0; i < cantidadFila; i++) {
            System.out.print(" _o_");
        }
        System.out.println("\n");
    }
}
