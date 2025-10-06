package vPRG2.v002;

import utils.Console;

class Ludoteca {
    private Monitor lydia;
    private Monitor aisha;
    private Pizarra pizarraDelSalon;
    private boolean juegoEnCurso;

    public Ludoteca() {
        lydia = new Monitor("Lydia");
        aisha = new Monitor("Aisha");
        pizarraDelSalon = new Pizarra();
        juegoEnCurso = false;
    }

    public void recibirNiño(Niño niño) {
        if (!juegoEnCurso) {
            lydia.recibeNiño(niño);
        } else {
            new Console().writeln("Lydia recibe a " + niño.getNombre() + " (esperando para el próximo juego)");
            lydia.recibeNiño(niño);
        }
    }

    public void actualizar() {
        if (!aisha.estaJugando()) {
            if (lydia.tieneNiños()) {
                lydia.entregaNiños(aisha);
            }
            if (aisha.puedeJugar()) {
                juegoEnCurso = true;
                aisha.jugar(pizarraDelSalon);
                juegoEnCurso = false;
            }
        } else {
            aisha.continuarJuego(pizarraDelSalon);
        }
    }

    public void verEstado() {
        new Console().writeln("\nESTADO ACTUAL DE LA LUDOTECA:");
        lydia.mostrarListaNiños();
        aisha.mostrarListaNiños();
        new Console().writeln("Pizarra del salón: [" + pizarraDelSalon.leerMensaje() + "]");
        new Console().writeln("Uso de arrays: Lydia " + lydia.getUsoMemoria() + ", Aisha " + aisha.getUsoMemoria());
    }
}
