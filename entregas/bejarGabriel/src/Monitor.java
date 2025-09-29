import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Monitor {
    private final String nombre;
    private final Cola colaNiños;
    private boolean estaJugando;
    private int turnoActual;
    private final Random rnd;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
        this.estaJugando = false;
        this.turnoActual = 0;
        this.rnd = new Random();
    }

    public void recibeNiño(Niño niño) {
        if (!colaNiños.hayNiños() || colaNiños.size() < 15) {
            niño.recibirPizarrin(new Pizarra());
            colaNiños.addNiño(niño);
        } else {
            new Console().writeln("ERROR: Cola de " + nombre + " llena, niño " + niño.getNombre() + " rechazado");
        }
    }

    public void recibeNiño(Niño niño, Pizarra pizarra) {
        niño.recibirPizarrin(pizarra);
        colaNiños.addNiño(niño);
    }

    public boolean tieneNiños() {
        return colaNiños.hayNiños();
    }

    public boolean puedeJugar() {
        return colaNiños.size() >= 5 && !estaJugando;
    }

    public boolean estaJugando() {
        return estaJugando;
    }

    public void mostrarListaNiños() {
        new Console().write("> " + this.nombre + " --> ");
        colaNiños.listaNiños();
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (colaNiños.hayNiños() && otroMonitor.colaNiños.size() < 10) { // Limitar transferencia para evitar overflow
            new Console().writeln(" > " + this.nombre + " ENTREGA NIÑO A " + otroMonitor.nombre);
            Niño unNiño = colaNiños.removeNiño();
            if (unNiño != null) {
                otroMonitor.recibeNiño(unNiño, new Pizarra());
            }
        }
    }

    public Juego iniciarJuego() {
        if (!puedeJugar()) return null;
        estaJugando = true;
        limpiarPizarrines();
        turnoActual = 0;
        List<Niño> participantes = new ArrayList<>();
        for (int i = 0; i < colaNiños.size(); i++) {
            participantes.add(colaNiños.getNiño(i));
        }
        Niño primerNiño = participantes.get(0);
        primerNiño.recibirMensaje("ABCDEFGHIJKLM", rnd);
        for (int t = 0; t < participantes.size() - 1; t++) {
            Niño actual = participantes.get(t);
            Niño siguiente = participantes.get(t + 1);
            siguiente.recibirMensaje(actual.mostrarMensaje(), rnd);
        }
        Juego juego = new Juego(participantes, rnd);
        // Vaciar cola después del juego
        while (colaNiños.hayNiños()) {
            colaNiños.removeNiño();
        }
        estaJugando = false;
        turnoActual = 0;
        return juego;
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño niño = colaNiños.getNiño(i);
            if (niño != null) {
                niño.limpiarPizarrin();
            }
        }
    }

    public String getUsoCola() {
        return colaNiños.size() + "/15";
    }
}