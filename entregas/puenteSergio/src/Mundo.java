public class Mundo {
    private Ludoteca ludoteca;
    private int tiempoTotal;

    public Mundo(Ludoteca unaLudoteca, int tiempoApertura) {
        ludoteca = unaLudoteca;
        tiempoTotal = tiempoApertura;
    }

    public void iniciarSimulacion() {
        Console console = new Console();

        for (int minutoActual = 0; minutoActual < tiempoTotal; minutoActual++) {
            console.clearScreen();
            console.writeln("=".repeat(30));
            console.writeln("Minuto " + minutoActual);

            if (llegaNiño(minutoActual)) {
                Nino niño = generarNiño();
                ludoteca.simularLlegadaNiño(); 
            }

            ludoteca.simularInicioJuego();
            ludoteca.mostrarMonitoresYNiños();

            console.readString("Pulse enter para continuar");
        }
    }

    private boolean llegaNiño(int minuto) {
        return Math.random() > ((minuto < 10) ? 0.3 : (minuto < 30) ? 0.6 : 0.8);
    }

    private Nino generarNiño() {
        String unNombre = inventarNombre();
        new Console().writeln("Llega " + unNombre);
        return new Nino(unNombre, 3 + (int) (Math.random() * 6)); // edad 3-8
    }

    private String inventarNombre() {
        String nombres[] = {
            "Andrés","Pablo","Diego","Aníbal","Umut","Javier","Fernando",
            "Cayetano","Iker","Mario","Adrián","Paula","Veronika","Eduardo",
            "Hugo","César","Miguel","Santiago","Juan","Daniel","Álvaro",
            "Maura","Neco","Sergio","Aurelio","Jorge","Raúl","José Manuel",
            "José Luis","Óscar","Rubén","Gabriel","Iñaki","Alejandro","Andriuw"
        };
        return nombres[(int) (Math.random() * nombres.length)];
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.writeln("=".repeat(50));
        console.writeln("LUDOTECA - SIMULACIÓN v2.0");
        console.writeln("IMPORTANTE: Colas limitadas a 50 niños máximo por monitor");
        console.writeln("=".repeat(50));

        Ludoteca unaLudoteca = new Ludoteca();
        Mundo elMundo = new Mundo(unaLudoteca, 40);
        elMundo.iniciarSimulacion();
    }
}
