public class Monitoras {
    private final String nombreLydia;
    private final String nombreAisha;
    private final Cola salaTemporal;
    private boolean juegoEnCurso;

    public Monitoras() {
        this.nombreLydia = "Lydia";
        this.nombreAisha = "Aisha";
        this.salaTemporal = new Cola();
        this.juegoEnCurso = false;
    }

    public Monitoras(String nombreLydia, String nombreAisha) {
        this.nombreLydia = nombreLydia;
        this.nombreAisha = nombreAisha;
        this.salaTemporal = new Cola();
        this.juegoEnCurso = false;
    }

    public void lydiaRecibeNino(Nino nuevoNino, Cola filaJuego) {
        if (juegoEnCurso) {
            salaTemporal.insertarNino(nuevoNino);
            System.out.println(nombreLydia + " recibe a " + nuevoNino.obtenerNombre() +
                    " y lo sienta a esperar (juego en curso)");
        } else {
            aishaFormaFila(nuevoNino, filaJuego);
        }
    }

    private void aishaFormaFila(Nino nino, Cola filaJuego) {
        filaJuego.insertarNino(nino);
        System.out.println(nombreAisha + " coloca a " + nino.obtenerNombre() +
                " al final de la fila y le da un pizarrín");
    }

    public boolean aishaVerificaInicio(Cola filaJuego) {
        return filaJuego.obtenerTamano() > 5;
    }

    public void aishaIniciaJuego() {
        if (!juegoEnCurso) {
            juegoEnCurso = true;
            System.out.println(nombreAisha + " dice: '¡Hay suficientes niños! Vamos a jugar teléfono descacharrado!'");
            System.out.println(nombreAisha + " limpia la pizarra del salón");
        }
    }

    public void aishaTerminaJuego(Cola filaJuego) {
        if (juegoEnCurso) {
            juegoEnCurso = false;
            System.out.println(nombreAisha + " dice: '¡Juego terminado!'");
            procesarNinosEnEspera(filaJuego);
        }
    }

    private void procesarNinosEnEspera(Cola filaJuego) {
        Nino[] ninosEsperando = salaTemporal.extraerNinos();

        if (ninosEsperando.length > 0) {
            System.out.println(nombreLydia + " envía a los niños que estaban esperando:");

            for (Nino ninosEsperando1 : ninosEsperando) {
                aishaFormaFila(ninosEsperando1, filaJuego);
            }

            salaTemporal.vaciarEstructura();
        }
    }

    public String aishaGeneraMensaje() {
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mensaje = "";
        for (int i = 0; i < 10; i++) {
            mensaje += alfabeto.charAt((int) (Math.random() * alfabeto.length()));
        }

        System.out.println(nombreAisha + " escribe en su pizarrín: '" + mensaje + "'");
        return mensaje;
    }

    public boolean hayJuegoEnCurso() {
        return juegoEnCurso;
    }

    public String obtenerEstadoMonitoras() {
        String estado = nombreLydia + " y " + nombreAisha + " - ";

        if (juegoEnCurso) {
            estado += "Juego en curso. ";
            if (salaTemporal.obtenerTamano() > 0) {
                estado += "Niños esperando con " + nombreLydia + ": " + salaTemporal.obtenerTamano();
            }
        } else {
            estado += "Sin juego activo.";
        }

        return estado;
    }

    public void aishaSupervisa(Nino ninoActual, String mensaje, boolean esUltimo) {
        if (esUltimo) {
            System.out.println(nombreAisha + " ve como " + ninoActual.obtenerNombre() +
                    " corre a escribir en la pizarra del salón");
        } else {
            System.out.println(nombreAisha + " observa como " + ninoActual.obtenerNombre() +
                    " pasa el mensaje al siguiente niño");
        }
    }

    public String obtenerNombreLydia() {
        return nombreLydia;
    }

    public String obtenerNombreAisha() {
        return nombreAisha;
    }
}