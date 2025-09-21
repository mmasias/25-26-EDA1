package vPRG1;

import utils.Console;

class LudotecaV2 {
    public static void main(String[] args) {
        final int TIEMPO_TOTAL = 120;
        final int MINIMO_NIÑOS = 5;
        final int CAPACIDAD_MAXIMA = 7;
        
        int lydia = 0;
        int aisha = 0;
        boolean estaJugando = false;
        String[] niñosJugando = new String[CAPACIDAD_MAXIMA];
        int turnoNiño = 0;

        for (int minuto = 0; minuto <= TIEMPO_TOTAL; minuto++) {
            new Console().clearScreen();
            new Console().writeln("=".repeat(30));
            new Console().writeln("Ludoteca PRG1 - minuto " + minuto);
            
            if (llegaNiño(minuto)) {
                if (lydia < CAPACIDAD_MAXIMA) {
                    lydia++;
                } else {
                    new Console().writeln("¡ERROR: Ludoteca llena! No pueden entrar más niños.");
                }
            }
            
            if (!estaJugando && aisha<CAPACIDAD_MAXIMA) {
                aisha = aisha + lydia;
                lydia = 0;
                mostrarCola("Lydia", lydia);
                mostrarCola("Aisha", aisha);
            } else {
                mostrarCola("Lydia", lydia);
                mostrarCola("Aisha", aisha, turnoNiño);
            }
            
            if (aisha >= MINIMO_NIÑOS && !estaJugando) {
                estaJugando = true;
                niñosJugando[0] = "Dicen que el examen estará difícil. ¡Pásalo!";
                new Console().writeln("Aisha empieza a jugar. Primer niño recibe: " + niñosJugando[0]);
                turnoNiño = 1;
            } else if (estaJugando && turnoNiño < aisha) {
                String mensajeAnterior = niñosJugando[turnoNiño - 1];
                niñosJugando[turnoNiño] = alterarMensaje(mensajeAnterior);
                new Console().writeln("Niño " + (turnoNiño + 1) + " recibe: [" + mensajeAnterior + "] y escribe: [" + niñosJugando[turnoNiño] + "]");
                turnoNiño++;
            } else if (estaJugando && turnoNiño >= aisha) {
                new Console().writeln("JUEGO TERMINADO. Mensaje final: " + niñosJugando[aisha - 1]);
                estaJugando = false;
                limpiarMensajes(niñosJugando, aisha);
            }

            new Console().readString("Pulse enter para continuar");
        }
    }

    static String alterarMensaje(String mensaje) {
        return mensaje + "X";
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

    static void limpiarMensajes(String[] mensajes, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            mensajes[i] = null;
        }
    }
}