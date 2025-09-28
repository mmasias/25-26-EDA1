
class Monitor {
    private String nombre;
    private Cola colaNiños;
    private boolean estaJugando;
    private int turnoActual;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.colaNiños = new Cola();
        this.estaJugando = false;
        this.turnoActual = 0;
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
        System.out.print("> " + nombre + " --> ");
        colaNiños.listaNiños();
    }

    private void recibeNiño(Niño niño, Pizarra pizarrin) {
        niño.recibirPizarrin(pizarrin);
        colaNiños.addNiño(niño);
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (colaNiños.hayNiños()) {
            Niño unNiño = colaNiños.removeNiño();
            new Console().writeln(nombre + " entrega a " + unNiño.getNombre() + " a " + otroMonitor.nombre);
            otroMonitor.recibeNiño(unNiño, new Pizarra());
        }
    }

    public void jugar(Pizarra pizarraDelSalon) {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;

            Niño primerNiño = colaNiños.getNiño(turnoActual);
            if (primerNiño != null) {
                String mensajeInicial = "ABCDEFGHIJKLM";
                pizarraDelSalon.escribirMensaje(mensajeInicial);
                new Console().writeln("Aisha escribe [" + mensajeInicial + "] en la pizarra del salón.");
                primerNiño.recibirMensaje(pizarraDelSalon.leerMensaje());
            }
        } else {
            Niño niñoActual = colaNiños.getNiño(turnoActual);

            if (turnoActual + 1 >= colaNiños.size()) {
                estaJugando = false;
                turnoActual = 0;
                new Console().writeln("El juego ha terminado.");
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
            if (niño != null) niño.limpiarPizarrin();
        }
    }

    public String getUsoMemoria() {
        return colaNiños.size() + "/15 (capacidad fija)";
    }
}


