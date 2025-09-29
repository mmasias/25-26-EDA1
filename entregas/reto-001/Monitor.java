public class Monitor {
    private static final int CAPACIDAD_MAXIMA = 15;

    private String nombre;
    private ListaNiños niños;
    private boolean estaJugando;
    private int turnoActual;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new ListaNiños();
        this.estaJugando = false;
        this.turnoActual = 0;
    }

    public void recibeNiño(Niño niño) {
        if (niños.tamaño() >= CAPACIDAD_MAXIMA) {
            System.out.println("ERROR: ¡" + nombre + " no puede recibir más niños! Cola llena.");
            return;
        }
        niños.añadir(niño);
    }

    private void recibeNiño(Niño niño, Pizarra pizarrin) {
        niño.recibirPizarrin(pizarrin);
        recibeNiño(niño);
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (niños.tamaño() > 0) {
            System.out.println(" > " + nombre + " ENTREGA NIÑO");
            Niño unNiño = niños.sacar();
            otroMonitor.recibeNiño(unNiño, new Pizarra());
        }
    }

    public boolean tieneNiños() {
        return niños.tamaño() > 0;
    }

    public boolean puedeJugar() {
        return niños.tamaño() >= 5;
    }

    public boolean estaJugando() {
        return estaJugando;
    }

    public void jugar() {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primero = niños.obtener(0);
            if (primero != null) {
                primero.recibirMensaje("abcdefghij");
            }
        } else {
            Niño actual = niños.obtener(turnoActual);
            if (turnoActual + 1 >= niños.tamaño()) {
                estaJugando = false;
                turnoActual = 0;
            } else {
                Niño siguiente = niños.obtener(turnoActual + 1);
                if (actual != null && siguiente != null) {
                    siguiente.recibirMensaje(actual.mostrarMensaje());
                }
                turnoActual++;
            }
        }
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < niños.tamaño(); i++) {
            Niño niño = niños.obtener(i);
            if (niño != null) niño.limpiarPizarrin();
        }
    }

    public void mostrarListaNiños() {
        System.out.print("> " + nombre + " --> ");
        niños.mostrar();
        System.out.println();
    }

    public String getUsoMemoria() {
        return niños.tamaño() + "/" + CAPACIDAD_MAXIMA + " (¡fijo!)";
    }
}
