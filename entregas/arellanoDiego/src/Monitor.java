import java.util.Random;

public class Monitor {
    private static final int CAPACIDAD_MAXIMA = 15;

    private String nombre;
    private Niño[] niños;
    private int inicio;
    private int fin;
    private int cantidad;
    private boolean estaJugando;
    private int turnoActual;
    private String mensajeInicial;

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
            System.out.println("ERROR: ¡" + nombre + " no puede recibir más niños!");
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
        System.out.print("> " + this.nombre + " --> ");
        int actual = inicio;
        for (int i = 0; i < cantidad; i++) {
            System.out.print(niños[actual].getNombre() + " / ");
            actual = (actual + 1) % CAPACIDAD_MAXIMA;
        }
        System.out.println();
    }

    private void recibeNiño(Niño niño, Pizarra pizarrin) {
        niño.recibirPizarrin(pizarrin);
        recibeNiño(niño);
    }

    public void entregaNiños(Monitor otroMonitor) {
        while (tieneNiños()) {
            Niño unNiño = sacarNiño();
            otroMonitor.recibeNiño(unNiño, new Pizarra());
        }
    }

    private Niño sacarNiño() {
        if (cantidad == 0) return null;
        Niño saliente = niños[inicio];
        niños[inicio] = null;
        inicio = (inicio + 1) % CAPACIDAD_MAXIMA;
        cantidad--;
        return saliente;
    }

    private Niño obtenerNiño(int posicion) {
        if (posicion >= cantidad) return null;
        int indice = (inicio + posicion) % CAPACIDAD_MAXIMA;
        return niños[indice];
    }

    public void jugar(Pizarra pizarraDelSalon) {
        if (!estaJugando) {
            estaJugando = true;
            limpiarPizarrines();
            turnoActual = 0;
            Niño primerNiño = obtenerNiño(turnoActual);

            if (primerNiño != null) {
                mensajeInicial = generarMensajeAleatorio();
                System.out.println("Aisha escribe el mensaje: " + mensajeInicial);
                primerNiño.recibirMensaje(mensajeInicial);
            }
        } else {
            Niño niñoActual = obtenerNiño(turnoActual);

            if (turnoActual + 1 >= cantidad) {
                if (niñoActual != null) {
                    pizarraDelSalon.escribirMensaje(niñoActual.mostrarMensaje());
                    System.out.println("El último niño corre y escribe: " + pizarraDelSalon.leerMensaje());
                }
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
            if (niño != null) niño.limpiarPizarrin();
        }
    }

    public String getUsoMemoria() {
        return cantidad + "/" + CAPACIDAD_MAXIMA;
    }

    private String generarMensajeAleatorio() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }
        return sb.toString();
    }
}
