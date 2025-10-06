package entregas.arceMarina;

import utils.Console;

class Monitor {
    private String nombre;
    private Cola colaNiños;
    private boolean estaJugando;
    private int turnoActual;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
        estaJugando = false;
        turnoActual = 0;
    }

    public void recibeNiño(Niño niño) {
        colaNiños.addNiño(niño);
    }

    public boolean tieneNiños() {
        return colaNiños.hayNiños();
    }

    public boolean puedeJugar() {
        return colaNiños.size() >= 5;
    }

    public boolean estaJugando() {
        return estaJugando;
    }

    public void mostrarListaNiños() {
        System.out.print("> " + this.nombre + " --> ");
        colaNiños.listaNiños();
        System.out.println();
    }

    private void recibeNiño(Niño niño, Pizarra pizarrin) {
        niño.recibirPizarrin(pizarrin);
        colaNiños.addNiño(niño);
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (colaNiños.hayNiños()) {
            System.out.println(" >  " + this.nombre + " ENTREGA NIÑO");
            Niño unNiño = colaNiños.removeNiño();
            otroMonitor.recibeNiño(unNiño, new Pizarra());
        }
    }

    public void jugar() {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primerNiño = colaNiños.getNiño(turnoActual);
            if (primerNiño != null) {
                primerNiño.recibirMensaje("ABCDEFGHIJKLM");
            }
        } else {
            Niño niñoActual = colaNiños.getNiño(turnoActual);
            
            if (turnoActual + 1 >= colaNiños.size()) {
                estaJugando = false;
                turnoActual = 0;
            } else {
                Niño siguienteNiño = colaNiños.getNiño(turnoActual + 1);
                if (niñoActual != null && siguienteNiño != null) {
                    siguienteNiño.recibirMensaje(niñoActual.mostrarMensaje());
                }
                turnoActual++;
            }
        }
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < colaNiños.size(); i++) {
            Niño niño = colaNiños.getNiño(i);
            if (niño != null) {
                niño.limpiarPizarrin();
            }
        }
    }

    public String getUsoMemoria() {
        return colaNiños.size() + "/20 (¡fijo!)";
    }
}
