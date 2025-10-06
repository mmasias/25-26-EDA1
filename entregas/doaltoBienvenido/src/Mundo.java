class Mundo {
    private static final int TIEMPO_APERTURA = 40;
    private static final double PROBABILIDAD_LLEGADA_INICIAL = 0.3;
    private static final double PROBABILIDAD_LLEGADA_MEDIA = 0.6;
    private static final double PROBABILIDAD_LLEGADA_FINAL = 0.8;
    private static final int TIEMPO_LLEGADA_INICIAL = 10;
    private static final int TIEMPO_LLEGADA_MEDIA = 30;

    private Ludoteca ludoteca;
    private int tiempoTotal;

    public Mundo(Ludoteca unaLudoteca, int tiempoApertura) {
        ludoteca = unaLudoteca;
        tiempoTotal = tiempoApertura;
    }

    public void iniciarSimulacion() {

        for (int minutoActual = 0; minutoActual < tiempoTotal; minutoActual++) {
            new Console().clearScreen();
            new Console().writeln("================================");
            new Console().writeln("Minuto " + minutoActual);

            if (llegaNiño(minutoActual)) {
                Niño niño = generarNiño();
                ludoteca.recibirNiño(niño);
            }

            ludoteca.actualizar();
            ludoteca.verEstado();

            new Console().readString("Pulse enter para continuar");
        }
    }

    private boolean llegaNiño(int minuto) {
        return Math.random() > ((minuto < TIEMPO_LLEGADA_INICIAL) ? PROBABILIDAD_LLEGADA_INICIAL : (minuto < TIEMPO_LLEGADA_MEDIA) ? PROBABILIDAD_LLEGADA_MEDIA : PROBABILIDAD_LLEGADA_FINAL);
    }

    private Niño generarNiño() {
        String unNombre = inventarNombre();
        new Console().writeln("Llega " + unNombre);
        return new Niño(unNombre);
    }

    private String inventarNombre() {
        String nombres[] = {
                "Andrés", "Pablo", "Diego", "Aníbal", "Umut", "Javier", "Fernando",
                "Cayetano", "Iker", "Mario", "Adrián", "Paula", "Veronika", "Eduardo",
                "Hugo", "César", "Miguel", "Santiago", "Juan", "Daniel", "Álvaro",
                "Maura", "Neco", "Sergio", "Aurelio", "Jorge", "Raúl", "José Manuel",
                "José Luis", "Óscar", "Rubén", "Gabriel", "Iñaki", "Alejandro", "Andriuw"
        };
        return nombres[(int) (Math.random() * nombres.length)];
    }

    public static void main(String[] args) {
        new Console().writeln("=================================");
        new Console().writeln("TELÉFONO DESCACHARRADO v2.0 (Orientado a Objetos)");
        new Console().writeln("IMPORTANTE: Colas limitadas a 20 niños máximo");
        new Console().writeln("=================================");

        Ludoteca unaLudoteca = new Ludoteca();
        Mundo elMundo = new Mundo(unaLudoteca, TIEMPO_APERTURA);
        elMundo.iniciarSimulacion();
    }
}