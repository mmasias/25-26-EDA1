package vPRG1;

import utils.Console;

class Ludoteca {
    public static void main(String[] args) {
        final int TIEMPO_TOTAL = 120;
        final int MINIMO_NIÑOS = 5;
        int niñosConLydia = 0;
        int niñosConAisha = 0;
        boolean estanJugando = false;
        String elMensaje = "";
        int niñoConTurno = 0;

        for (int minuto = 0; minuto <= TIEMPO_TOTAL; minuto++) {
            new Console().clearScreen();
            new Console().writeln("=".repeat(30));
            new Console().writeln("Ludoteca PRG1 - minuto " + minuto);
            
            if (llegaNiño(minuto)) {
                niñosConLydia++;
            }
            
            if (!estanJugando) {
                niñosConAisha = niñosConAisha + niñosConLydia;
                niñosConLydia = 0;
                mostrarCola("Lydia", niñosConLydia);
                mostrarCola("Aisha", niñosConAisha);
            } else {
                mostrarCola("Lydia", niñosConLydia);
                mostrarCola("Aisha", niñosConAisha, niñoConTurno);
            }
            
            if (niñosConAisha >= MINIMO_NIÑOS && !estanJugando) {
                estanJugando = true;
                elMensaje = "Dicen que el examen estará difícil. ¡Pásalo!";
                new Console().writeln("Aisha empieza a jugar pasando este mensaje: " + elMensaje);
                niñoConTurno = 1;
            } else if (estanJugando) {
                new Console().writeln("MENSAJE: " + elMensaje);
                elMensaje = alterarMensaje(elMensaje);
                niñoConTurno++;
                estanJugando = niñoConTurno <= niñosConAisha;
            }

            new Console().readString("Pulse enter para continuar");
        }
    }

    static String alterarMensaje(String elMensaje) {
        return elMensaje + "X";
    }

    static boolean llegaNiño(int minuto) {
        final double PROBABILIDAD_LLEGADA = 0.3;
        final int LIMITE_TIEMPO_LLEGADA = 40;
        return Math.random() < PROBABILIDAD_LLEGADA && minuto < LIMITE_TIEMPO_LLEGADA;
    }

    static void mostrarCola(String responsable, int numeroNiños) {
        mostrarCola(responsable, numeroNiños, 0);
    }

    static void mostrarCola(String responsable, int numeroNiños, int turnoNiño) {
        final String NIÑO = "_()_";
        final String NIÑO_ESCRIBIENDO = "_(<)_ ";

        String cola = turnoNiño > 0
                ? NIÑO.repeat(turnoNiño - 1) + NIÑO_ESCRIBIENDO + NIÑO.repeat(numeroNiños - turnoNiño)
                : NIÑO.repeat(numeroNiños);
        new Console().writeln(responsable + " > " + cola);
        new Console().writeln("-".repeat(30));
    }
}