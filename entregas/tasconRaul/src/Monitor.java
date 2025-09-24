public class Monitor {
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
    niño.recibirPizarrin(new Pizarra());
    colaNiños.addNiño(niño);
    System.out.println(nombre + " recibe a " + niño.getNombre());
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
        System.out.println();
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (colaNiños.hayNiños()) {
            Niño n = colaNiños.removeNiño();
            otroMonitor.recibeNiño(n);
        }
    }

    public void jugar(Pizarra pizarraSalon) {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primerNiño = colaNiños.getNiño(turnoActual);
            if (primerNiño != null) {
                primerNiño.recibirMensaje("ABCDEFGHIJ"); // mensaje inicial
            }
        } else {
            Niño niñoActual = colaNiños.getNiño(turnoActual);
            if (turnoActual + 1 >= colaNiños.size()) {
                if (niñoActual != null) {
                    pizarraSalon.escribirMensaje(niñoActual.mostrarMensaje());
                    System.out.println(">>> El último niño escribe en la pizarra del salón: [" 
                                       + pizarraSalon.leerMensaje() + "]");
                }
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
            Niño n = colaNiños.getNiño(i);
            if (n != null) n.limpiarPizarrin();
        }
    }

    public String getUsoMemoria() {
        return colaNiños.size() + "/15 (¡fijo!)";
    }
}
