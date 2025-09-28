
class Mundo {
    private Ludoteca ludoteca;
    private int tiempoTotal;

    public Mundo(Ludoteca ludoteca, int tiempoApertura) {
        this.ludoteca = ludoteca;
        this.tiempoTotal = tiempoApertura;
    }

    public void iniciarSimulacion() {
        for (int minutoActual = 0; minutoActual < tiempoTotal; minutoActual++) {
            new Console().clearScreen();
            new Console().writeln("=".repeat(30));
            new Console().writeln("Minuto " + minutoActual);

            if (llegaNiño(minutoActual)) {
                Niño niño = generarNiño();
                ludoteca.recibirNiño(niño);
            }

            ludoteca.actualizar();
            ludoteca.verEstado();

            new Console().readString("Pulse ENTER para continuar");
        }
    }

    private boolean llegaNiño(int minuto) {
        return Math.random() > ((minuto < 10) ? 0.3 : (minuto < 30) ? 0.6 : 0.8);
    }

    private Niño generarNiño() {
        String nombre = inventarNombre();
        new Console().writeln("Llega " + nombre);
        return new Niño(nombre);
    }

    private String inventarNombre() {
        String[] nombres = {
            "Andrés", "Pablo", "Diego", "Aníbal", "Umut", "Javier", "Fernando",
            "Cayetano", "Iker", "Mario", "Adrián", "Paula", "Veronika", "Eduardo",
            "Hugo", "César", "Miguel", "Santiago", "Juan", "Daniel", "Álvaro",
            "Maura", "Neco", "Sergio", "Aurelio", "Jorge", "Raúl", "José Manuel",
            "José Luis", "Óscar", "Rubén", "Gabriel", "Iñaki", "Alejandro", "Andriuw"
        };
        return nombres[(int) (Math.random() * nombres.length)];
    }

    public static void main(String[] args) {
        new Console().writeln("=".repeat(50));
        new Console().writeln("TELÉFONO DESCACHARRADO v2.0 (OO y Fidelidad Narrativa)");
        new Console().writeln("IMPORTANTE: Colas limitadas a 15 niños máximo");
        new Console().writeln("=".repeat(50));

        Ludoteca ludoteca = new Ludoteca();
        Mundo mundo = new Mundo(ludoteca, 40);
        mundo.iniciarSimulacion();
    }
}
