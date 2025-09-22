package vPRG2.v001;

import utils.Console;

class Monitor {
    private static final int CAPACIDAD_MAXIMA = 15;
    
    private String nombre;
    private Niño[] niños;
    private int inicio;
    private int fin;
    private int cantidad;
    private boolean estaJugando;
    private int turnoActual;

    public Monitor(String nombre) {
        this.nombre = nombre;
        this.niños = new Niño[CAPACIDAD_MAXIMA];
        this.inicio = 0;
        this.fin = 0;
        this.cantidad = 0;
        this.estaJugando = false;
        this.turnoActual = 0;
    }

    public void recibeNiño(Niño niño) {
        if (cantidad >= CAPACIDAD_MAXIMA) {
            new Console().writeln("ERROR: ¡" + nombre + " no puede recibir más niños! Cola llena.");
            return;
        }
        
        niños[fin] = niño;
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        cantidad++;
    }

    public boolean tieneNiños() {
        return cantidad > 0;
    }

    public boolean puedeJugar() {
        return cantidad >= 5;
    }

    public boolean estaJugando() {
        return estaJugando;
    }

    public void mostrarListaNiños() {
        new Console().write("> " + this.nombre + " --> ");
        int actual = inicio;
        for (int i = 0; i < cantidad; i++) {
            new Console().write(niños[actual].getNombre() + " / ");
            actual = (actual + 1) % CAPACIDAD_MAXIMA;
        }
        new Console().writeln();
    }

    private void recibeNiño(Niño niño, Pizarra pizarrin) {
        niño.recibirPizarrin(pizarrin);
        recibeNiño(niño);
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (tieneNiños()) {
            new Console().writeln(" >  " + this.nombre + " ENTREGA NIÑO");
            Niño unNiño = sacarNiño();
            otroMonitor.recibeNiño(unNiño, new Pizarra());
        }
    }

    private Niño sacarNiño() {
        if (cantidad == 0) {
            return null;
        }
        
        Niño saliente = niños[inicio];
        niños[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return saliente;
    }

    private Niño obtenerNiño(int posicion) {
        if (posicion >= cantidad) {
            return null;
        }
        int indice = (inicio + posicion) % CAPACIDAD_MAXIMA;
        return niños[indice];
    }

    public void jugar() {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primerNiño = obtenerNiño(turnoActual);
            if (primerNiño != null) {
                primerNiño.recibirMensaje("ABCDEFGHIJKLM");
            }
        } else {
            Niño niñoActual = obtenerNiño(turnoActual);
            
            if (turnoActual + 1 >= cantidad) {
                estaJugando = false;
                turnoActual = 0;
            } else {
                Niño siguienteNiño = obtenerNiño(turnoActual + 1);
                if (niñoActual != null && siguienteNiño != null) {
                    siguienteNiño.recibirMensaje(niñoActual.mostrarMensaje());
                }
                turnoActual++;
            }
        }
    }

    private void limpiarPizarrines() {
        for (int i = 0; i < cantidad; i++) {
            Niño niño = obtenerNiño(i);
            if (niño != null) {
                niño.limpiarPizarrin();
            }
        }
    }

    public String getUsoMemoria() {
        return cantidad + "/" + CAPACIDAD_MAXIMA + " (¡fijo!)";
    }
}