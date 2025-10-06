

import utils.Console;

class Mundo {
    private Ludoteca ludoteca;
    private int tiempoTotal;

    public Mundo(Ludoteca unaLudoteca, int minutosSimulacion) {
        ludoteca = unaLudoteca;
        tiempoTotal = minutosSimulacion;
    }

    public void iniciarSimulacion() {
        for (int minuto = 0; minuto < tiempoTotal; minuto++) {
            new Console().clearScreen();
            new Console().writeln("=".repeat(40));
            new Console().writeln("Minuto " + minuto);

            if (llegaNiño(minuto)) {
                Niño niño = generarNiño();
                ludoteca.recibirNiño(niño);
            }

            ludoteca.actualizar();
            ludoteca.verEstado();

            new Console().readString("Presione ENTER para continuar...");
        }
    }

    private boolean llegaNiño(int minuto) {
        if (minuto < 10) return Math.random() < 0.6; // 0-2 niños probables
        if (minuto < 30) return minuto % 3 == 0 && Math.random() < 0.5;
        return false;
    }

    private Niño generarNiño() {
        String[] nombres = {"Andrés", "Pablo", "Diego", "Sara", "Javier", "Paula", "Iker", "Lucía", "Mario", "Sofía"};
        String nombre = nombres[(int) (Math.random() * nombres.length)];
        new Console().writeln("Llega " + nombre);
        return new Niño(nombre);
    }

    public static void main(String[] args) {
        new Console().writeln("=".repeat(50));
        new Console().writeln("TELÉFONO DESCACHARRADO v2.0 (RETO 002)");
        new Console().writeln("=".repeat(50));

        Ludoteca ludoteca = new Ludoteca();
        Mundo mundo = new Mundo(ludoteca, 120); // 2 horas
        mundo.iniciarSimulacion();
    }
}
